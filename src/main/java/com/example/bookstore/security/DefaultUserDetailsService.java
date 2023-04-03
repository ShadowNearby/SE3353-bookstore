package com.example.bookstore.security;

import com.example.bookstore.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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
        if (StringUtils.isBlank(username)) {
            throw new UsernameNotFoundException("用户名不能为空");
        }
        var userOptional = userDao.findUserByUsername(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("用户不存在");
        }
        var user = userOptional.get();

        return User.builder()
                .username(username)
                .password(user.getPassword())
                .disabled(user.getBanned())
                .accountExpired(false).credentialsExpired(false).accountLocked(false)
                .authorities(user.getRole()).build();
    }
}
