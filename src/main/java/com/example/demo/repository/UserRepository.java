package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByAccount(String account);

    Optional<User> findUserByEmail(String email);

    User getUserById(Long id);

    User getUserByAccount(String account);

}
