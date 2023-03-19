package com.example.demo.dao.daoimpl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
}
