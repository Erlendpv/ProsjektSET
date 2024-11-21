package org.SHA.core.port.stub;

import org.SHA.core.domain.User;
import org.SHA.core.port.repository.UserRepository;

import java.util.*;

public class UserRepositoryStub implements UserRepository {
    private final Map<String, User> users = new HashMap<>();

    @Override
    public Optional<User> findById(String userId) {
        return Optional.ofNullable(users.get(userId)); // Henter bruker basert på ID
    }

    @Override
    public List<User> findByUsername(String username) {
        List<User> result = new ArrayList<>();
        for (User user : users.values()) {
            if (user.getUsername().equalsIgnoreCase(username)) { // Søker på brukernavn
                result.add(user);
            }
        }
        return result;
    }

    @Override
    public void save(User user) {
        users.put(user.getUserId(), user); // Legger til eller oppdaterer bruker
    }

    @Override
    public void delete(String userId) {
        users.remove(userId); // Sletter bruker basert på ID
    }
}
