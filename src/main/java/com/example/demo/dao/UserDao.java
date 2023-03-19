package com.example.demo.dao;

import com.example.demo.entity.User;
import com.example.demo.request.ForgetForm;
import com.example.demo.request.RegisterForm;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> findUserByAccount(String account);

    User getUserByAccount(String account);

    void addUser(RegisterForm registerForm);

    void updateUser(User user);

    Optional<User> findUserByEmail(String email);
}
