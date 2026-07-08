package org.example.fundspark.service;

import org.example.fundspark.model.*;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);

    List<User> getAllUsers();

    Optional<User> getUserById(String id);

    User updateUser(String id, User user);

    void deleteUser(String id);

    Optional<User> findByUsername(String username);
}
