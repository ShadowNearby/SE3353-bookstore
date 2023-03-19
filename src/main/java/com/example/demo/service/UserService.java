package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.request.ForgetForm;
import com.example.demo.request.RegisterForm;

public interface UserService {
    Boolean LoginCheck(String account, String password);

    User getUserByAccount(String account);

    String handleRegister(RegisterForm registerForm);

    String handleForget(ForgetForm forgetForm);
}
