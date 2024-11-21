package org.SHA.core.usecase;

import org.SHA.core.domain.User;
import org.SHA.core.dto.UserDTO;
import org.SHA.core.port.repository.UserRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

// Use case for å legge til og slette brukere
public class AddDeleteUserUseCase {
    private final UserRepository userRepository;

    // Konstruktør som mottar en implementasjon av UserRepository
    public AddDeleteUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Legger til en ny bruker med validering for e-postadresse
    public UserDTO addUser(String username, String email) {
        String userId = generateUniqueUserId();

        // Forsøker å opprette bruker og håndterer valideringsfeil
        while (true) {
            try {
                // Opprett og lagre brukeren
                User newUser = new User(userId, username, email);
                userRepository.save(newUser);

                // Returnerer en UserDTO med en tom liste for enheter
                return new UserDTO(userId, username, email, new ArrayList<>());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.print("Prøv igjen: Skriv inn en gyldig e-postadresse: ");
                email = new java.util.Scanner(System.in).nextLine();
            }
        }
    }

    // Sletter en eksisterende bruker
    public boolean deleteUser(String userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            System.out.println("Ingen bruker funnet med ID: " + userId);
            return false;
        }

        // Slett brukeren fra repository
        userRepository.delete(userId);
        System.out.println("Bruker med ID " + userId + " ble slettet.");
        return true;
    }

    // Genererer en unik bruker-ID
    private String generateUniqueUserId() {
        String userId;
        do {
            userId = UUID.randomUUID().toString();
        } while (userRepository.findById(userId).isPresent());
        return userId;
    }
}
