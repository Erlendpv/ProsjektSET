package org.SHA.core.port.repository;
import org.SHA.core.domain.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(String userId); //Finne bruker gjennom userId, Optional hvis bruker ikke eksisterer.
    List<User> findByUsername(String username); //Nytt mulig søk som kan brukes, for å finne bruker.
    void save(User user); //Lagrer en bruker i databasen
    void delete(String userId); //Sletter en bruker i databasen
}
