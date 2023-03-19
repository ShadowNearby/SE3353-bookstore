package com.example.demo.dao;

import com.example.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> findUserByAccount(String account);

    User getUserByAccount(String account);
}
