package com.example.demo.service.serviceimpl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.util.request.ForgetForm;
import com.example.demo.util.request.RegisterForm;
import com.example.demo.util.request.UserPutForm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Boolean LoginCheck(String account, String password) {
        Optional<User> user = userDao.findUserByAccount(account);
        if (user.isEmpty())
            return false;
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password, user.get().getPassword());
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
        user.get().setPassword(passwordEncoder.encode(forgetForm.getPassword()));
        userDao.updateUser(user.get());
        return "OK";
    }

    @Override
    public Set<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public String handleUserPut(UserPutForm userPutForm) {
//        userDao.getUserById();
        return "OK";
    }
}
