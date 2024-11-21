package org.SHA.core.usecase;

import org.SHA.core.domain.User;
import org.SHA.core.dto.UserDTO;
import org.SHA.core.port.repository.UserRepository;

import java.util.ArrayList;
import java.util.UUID;

public class AddDeleteUserUseCase {
    private final UserRepository userRepository;

    // Konstruktør som tar inn UserRepository
    public AddDeleteUserUseCase(UserRepository userRepository) {
        if (userRepository == null) {
            throw new IllegalArgumentException("UserRepository kan ikke være null.");
        }
        this.userRepository = userRepository;
    }

    public UserDTO addUser(String username, String email) {
        String userId = generateUniqueUserId();
        User newUser = new User(userId, username, email);
        userRepository.save(newUser);

        return new UserDTO(userId, username, email, new ArrayList<>());
    }

    private String generateUniqueUserId() {
        String userId;
        do {
            userId = UUID.randomUUID().toString();
        } while (userRepository.findById(userId).isPresent());
        return userId;
    }
}
