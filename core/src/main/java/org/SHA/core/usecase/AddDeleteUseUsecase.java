package org.SHA.core.usecase;

import org.SHA.core.domain.User;
import org.SHA.core.port.UserRepository;

import java.util.Optional;
import java.util.UUID;
public class AddDeleteUseUsecase {
    private UserRepository userRepository;

    public AddDeleteUseUsecase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void AddDeleteUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String addUser(String username, String email) {
        // Generer en unik ID for brukeren
        String userId = generateUniqueUserId();

        // Opprett en ny bruker og lagre den i UserRepository
        User newUser = new User(userId, username, email);
        userRepository.save(newUser);

        System.out.println("Bruker lagt til med ID: " + userId);
        return userId; // Returner den genererte ID-en
    }

    public boolean deleteUser(String userId) {
        // Sjekk om brukeren finnes i UserRepository
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isEmpty()) {
            System.out.println("Ingen bruker funnet med ID: " + userId);
            return false; // Returner false hvis brukeren ikke finnes
        }

        // Slett brukeren fra UserRepository
        userRepository.delete(userId);
        System.out.println("Bruker slettet: " + userId);
        return true; // Suksess
    }

    private String generateUniqueUserId() {
        String userId;
        do {
            userId = UUID.randomUUID().toString(); // Generer en tilfeldig UUID
        } while (userRepository.findById(userId).isPresent()); // Sjekk for duplikater
        return userId;
    }
}
