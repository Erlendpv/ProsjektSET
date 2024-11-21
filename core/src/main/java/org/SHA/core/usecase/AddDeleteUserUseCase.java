package org.SHA.core.usecase;

import org.SHA.core.domain.User;
import org.SHA.core.dto.UserDTO;
import org.SHA.core.port.repository.UserRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class AddDeleteUserUseCase {
    private final UserRepository userRepository;

    public AddDeleteUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Oppretter en ny bruker med en generert ID og validerer input
    public UserDTO addUser(String username, String email) {
        validateUserData(username, email);
        String userId = generateUniqueUserId();

        User newUser = new User(userId, username, email);
        userRepository.save(newUser);

        return new UserDTO(userId, username, email, new ArrayList<>());
    }

    // Sletter en bruker basert på bruker-ID
    public boolean deleteUser(String userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            System.out.println("Ingen bruker funnet med ID: " + userId);
            return false;
        }

        userRepository.delete(userId);
        System.out.println("Bruker med ID " + userId + " ble slettet.");
        return true;
    }

    // Genererer en unik ID med 5 tall
    private String generateUniqueUserId() {
        Random random = new Random();
        String userId;

        do {
            userId = String.format("%05d", random.nextInt(100000));
        } while (userRepository.findById(userId).isPresent());

        return userId;
    }

    // Validerer brukernavn og e-post
    private void validateUserData(String username, String email) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Brukernavn kan ikke være tomt.");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Ugyldig e-postadresse.");
        }
    }
}
