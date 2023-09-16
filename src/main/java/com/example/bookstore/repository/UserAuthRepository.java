package com.example.bookstore.repository;

import com.example.bookstore.entity.User;
import com.example.bookstore.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    UserAuth getUserAuthByUser(User user);
}
