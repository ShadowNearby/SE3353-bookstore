package com.example.bookstore.service;

import com.example.bookstore.entity.User;
import com.example.bookstore.util.request.ForgetForm;
import com.example.bookstore.util.request.RegisterForm;
import com.example.bookstore.util.request.UserPutForm;

import java.util.Set;

public interface UserService {
    Boolean LoginCheck(String account, String password);

    User getUserByAccount(String account);

    String handleRegister(RegisterForm registerForm);

    String handleForget(ForgetForm forgetForm);

    Set<User> getAllUsers();

    String handleUserPut(UserPutForm userPutForm);
}
