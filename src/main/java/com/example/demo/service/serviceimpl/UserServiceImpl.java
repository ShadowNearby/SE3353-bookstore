package com.example.demo.service.serviceimpl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;


    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Boolean LoginCheck(String account, String password) {
        Optional<User> user = userDao.findUserByAccount(account);
        if (user.isEmpty())
            return false;
        return Objects.equals(user.get().getPassword(), password);
    }

    @Override
    public User getUserByAccount(String account) {
        return userDao.getUserByAccount(account);
    }
}
