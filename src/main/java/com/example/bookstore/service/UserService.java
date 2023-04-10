package com.example.bookstore.service;

import com.example.bookstore.entity.User;
import com.example.bookstore.util.request.*;

import java.util.List;
import java.util.Set;

public interface UserService {
    User handleLogin(String username, String password);

    User getUserByUsername(String account);

    String handleRegister(RegisterForm registerForm);

    String handleForget(ForgetForm forgetForm);

    Set<User> getAllUsers();

    String handleUserPut(UserPutForm userPutForm);

    List<UserStatisticsForm> statistics(StatisticForm statisticForm);

    User getUser();
}
