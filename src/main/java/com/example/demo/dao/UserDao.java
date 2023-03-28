package com.example.demo.dao;

import com.example.demo.entity.User;
import com.example.demo.util.request.RegisterForm;

import java.util.Optional;
import java.util.Set;

public interface UserDao {
    Optional<User> findUserByAccount(String account);

    User getUserByAccount(String account);

    User getUserById(Long id);

    void addUser(RegisterForm registerForm);

    void updateUser(User user);

    Optional<User> findUserByEmail(String email);

    Set<User> getAllUsers();
}
