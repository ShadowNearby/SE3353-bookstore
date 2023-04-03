package com.example.bookstore.dao;

import com.example.bookstore.entity.User;
import com.example.bookstore.util.request.RegisterForm;

import java.util.Optional;
import java.util.Set;

public interface UserDao {
    Optional<User> findUserByUsername(String account);

    User getUserByUsername(String account);

    User getUserById(Long id);

    void addUser(RegisterForm registerForm);

    void updateUser(User user);

    Optional<User> findUserByEmail(String email);

    Set<User> getAllUsers();
}
