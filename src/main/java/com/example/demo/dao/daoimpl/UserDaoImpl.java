package com.example.demo.dao.daoimpl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.RegisterForm;
import org.springframework.stereotype.Repository;

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
    public Optional<User> findUserByAccount(String account) {
        return userRepository.findUserByAccount(account);
    }

    @Override
    public User getUserByAccount(String account) {
        return userRepository.getUserByAccount(account);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public void addUser(RegisterForm registerForm) {
        Date data = new Date();
        User user = new User(registerForm.getAccount(), registerForm.getPassword(), registerForm.getEmail(), registerForm.getRole(), data);
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
