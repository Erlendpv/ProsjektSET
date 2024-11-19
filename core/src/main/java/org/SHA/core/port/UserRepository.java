package org.SHA.core.port;

import org.SHA.core.domain.User;

public interface UserRepository {
    User findById(String userId);
    void save(User user);
    void delete(String userId); //jsdkjnebkwlj
}
