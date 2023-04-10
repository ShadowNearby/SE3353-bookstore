package com.example.bookstore.dao.daoimpl;

import com.example.bookstore.constant.Constant;
import com.example.bookstore.dao.UserDao;
import com.example.bookstore.entity.User;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.util.request.RegisterForm;
import org.springframework.stereotype.Repository;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {
    private final UserRepository userRepository;

    public UserDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User getUserByUsername(String account) {
        return userRepository.getUserByUsername(account);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public void addUser(RegisterForm registerForm) {
        Date data = new Date();
        String avatar = Constant.DEFAULT_AVATAR;
        String password = DigestUtils.md5DigestAsHex(registerForm.getPassword().getBytes());
        String userRole = Constant.USER;
        User user = new User(registerForm.getUsername(), password, registerForm.getEmail(), userRole, data, avatar);
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public Set<User> getAllUsers() {
        return new HashSet<>(userRepository.findAll());
    }
}
