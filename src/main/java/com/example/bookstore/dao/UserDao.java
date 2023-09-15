package com.example.bookstore.dao;

import com.example.bookstore.entity.User;
import com.example.bookstore.util.request.RegisterForm;

import java.util.Optional;
import java.util.Set;

public interface UserDao {
    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

    User getUserByUsername(String username);

    User getUserById(Long id);

    void addUser(RegisterForm registerForm);

    void updateUser(User user);


    Set<User> getAllUsers();
}
