package com.example.bookstore.service;

import com.alibaba.fastjson2.JSONObject;
import com.example.bookstore.entity.User;
import com.example.bookstore.util.request.*;

import java.util.List;
import java.util.Set;

public interface UserService {
    User handleLogin(String username, String password);

    User getUserByUsername(String username);

    String handleRegister(RegisterForm registerForm);

    String handleForget(ForgetForm forgetForm);

    Set<User> getAllUsers();

    String handleUserPut(UserPutForm userPutForm);

    List<UserStatisticsForm> statisticsAll(StatisticForm statisticForm);

    User getUser();

    JSONObject statisticsPersonal(StatisticForm statisticForm);
}
