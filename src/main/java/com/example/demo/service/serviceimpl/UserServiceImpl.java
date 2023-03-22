package com.example.demo.service.serviceimpl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.request.ForgetForm;
import com.example.demo.request.RegisterForm;
import com.example.demo.request.UserPutForm;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

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

    @Override
    public String handleRegister(RegisterForm registerForm) {
        if (userDao.findUserByAccount(registerForm.getAccount()).isPresent())
            return "该账户已存在";
        if (userDao.findUserByEmail(registerForm.getEmail()).isPresent())
            return "该邮箱已存在";
        userDao.addUser(registerForm);
        return "OK";
    }

    @Override
    public String handleForget(ForgetForm forgetForm) {
        Optional<User> user = userDao.findUserByAccount(forgetForm.getAccount());
        if (user.isEmpty())
            return "账户不存在";
        if (!Objects.equals(user.get().getEmail(), forgetForm.getEmail()))
            return "账户对应邮箱不正确";
        user.get().setPassword(forgetForm.getPassword());
        userDao.updateUser(user.get());
        return "OK";
    }

    @Override
    public Set<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public String handleUserPut(UserPutForm userPutForm) {
        userDao.updateUser(userPutForm.getUser());
        return "OK";
    }
}
