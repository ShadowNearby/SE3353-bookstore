package com.example.bookstore.dao;

import com.example.bookstore.entity.User;
import com.example.bookstore.entity.UserAuth;

import java.util.Optional;
import java.util.Set;

public interface UserDao {
    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

    User getUserByUsername(String username);

    User getUserByEmail(String username);

    User getUserById(Long id);

    void updateUser(User user, UserAuth userAuth);

    Set<User> getAllUsers();
}
