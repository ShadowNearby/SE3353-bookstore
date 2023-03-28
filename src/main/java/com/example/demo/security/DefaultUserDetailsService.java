package com.example.demo.security;

import com.example.demo.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DefaultUserDetailsService implements UserDetailsService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public DefaultUserDetailsService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userDao.getUserByAccount(username);
        String role = user.getRole() ? "ADMIN" : "USER";
        return User.builder()
                .username(username)
                .password(user.getPassword())
                .disabled(user.getBanned())
                .accountExpired(false).credentialsExpired(false).accountLocked(false)
                .authorities("ADMIN").build();
    }
}
