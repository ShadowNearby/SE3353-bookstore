package com.mainservice.repository;

import com.mainservice.entity.User;
import com.mainservice.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    UserAuth getUserAuthByUser(User user);
}
