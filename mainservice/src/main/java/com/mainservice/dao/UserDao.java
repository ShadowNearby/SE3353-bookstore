package com.mainservice.dao;

import com.mainservice.entity.User;
import com.mainservice.entity.UserAuth;

import java.util.Optional;
import java.util.Set;

public interface UserDao {
    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

    User getUserByUsername(String username);

    User getUserByEmail(String username);

    User getUserById(Long id);

    UserAuth getUserAuthByUsername(String username);

    void updateUser(User user, UserAuth userAuth);

    Set<User> getAllUsers();
}
