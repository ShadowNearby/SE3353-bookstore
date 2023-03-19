package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
    Boolean LoginCheck(String account, String password);

    User getUserByAccount(String account);
}
