package com.example.bookstore.dao.daoimpl;

import com.alibaba.fastjson2.JSON;
import com.example.bookstore.dao.UserDao;
import com.example.bookstore.entity.User;
import com.example.bookstore.entity.UserAuth;
import com.example.bookstore.repository.UserAuthRepository;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.util.CacheKeyConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@Repository
public class UserDaoImpl implements UserDao {
    private final UserRepository userRepository;
    private final UserAuthRepository userAuthRepository;
    private final StringRedisTemplate redisTemplate;
    private final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

    public UserDaoImpl(UserRepository userRepository, UserAuthRepository userAuthRepository, StringRedisTemplate redisTemplate) {
        this.userRepository = userRepository;
        this.userAuthRepository = userAuthRepository;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        var key = String.format("optional-user-username-%s", username);
        var cache_result = redisTemplate.opsForValue().get(key);
        if (cache_result != null) {
            log.info("cache hit {}", key);
            var result = JSON.parseObject(cache_result, Optional.class);
            if (result.isEmpty()) {
                return Optional.empty();
            }
            return Optional.ofNullable(getUserByUsername(username));
        }
        log.warn("cache miss {}", key);
        var result = userRepository.findUserByUsername(username);
        redisTemplate.opsForValue().set(key, JSON.toJSONString(result));
        log.info("cache set {}", key);
        return result;
    }


    @Override
    public Optional<User> findUserByEmail(String email) {
        var key = String.format("optional-user-email-%s", email);
        var cache_result = redisTemplate.opsForValue().get(key);
        if (cache_result != null) {
            log.info("cache hit {}", key);
            var result = JSON.parseObject(cache_result, Optional.class);
            if (result.isEmpty()) {
                return Optional.empty();
            }
            return Optional.ofNullable(getUserByEmail(email));
        }
        log.warn("cache miss {}", key);
        var result = userRepository.findUserByEmail(email);
        redisTemplate.opsForValue().set(key, JSON.toJSONString(result));
        log.info("cache set {}", key);
        return result;
    }

    @Override
    public User getUserByUsername(String username) {
        var key = String.format("user-username-%s", username);
        var cache_result = redisTemplate.opsForValue().get(key);
        if (cache_result != null) {
            log.info("cache hit {}", key);
            return JSON.parseObject(cache_result, User.class);
        }
        log.warn("cache miss {}", key);
        var result = userRepository.getUserByUsername(username);
        redisTemplate.opsForValue().set(key, JSON.toJSONString(result));
        log.info("cache set {}", key);
        return result;
    }

    @Override
    public User getUserByEmail(String email) {
        var key = String.format("user-email-%s", email);
        var cache_result = redisTemplate.opsForValue().get(key);
        if (cache_result != null) {
            log.info("cache hit {}", key);
            return JSON.parseObject(cache_result, User.class);
        }
        log.warn("cache miss {}", key);
        var result = userRepository.getUserByEmail(email);
        redisTemplate.opsForValue().set(key, JSON.toJSONString(result));
        log.info("cache set {}", key);
        return result;
    }

    @Override
    public User getUserById(Long id) {
        var key = String.format("user-id-%d", id);
        var cache_result = redisTemplate.opsForValue().get(key);
        if (cache_result != null) {
            log.info("cache hit {}", key);
            return JSON.parseObject(cache_result, User.class);
        }
        log.warn("cache miss {}", key);
        var result = userRepository.getUserById(id);
        redisTemplate.opsForValue().set(key, JSON.toJSONString(result));
        log.info("cache set {}", key);
        return result;
    }


    @Override
    public void updateUser(User user, UserAuth userAuth) {
        var username_key = String.format("user-username-%s", user.getUsername());
        var email_key = String.format("user-email-%s", user.getEmail());
        var id_key = String.format("user-id-%d", user.getId());
        var all_user_key = CacheKeyConstant.ALL_USER;
        userRepository.save(user);
        if (userAuth != null) {
            userAuthRepository.save(userAuth);
        }
        redisTemplate.delete(Stream.of(username_key, email_key, id_key, all_user_key).toList());
        log.info("cache remove {}, {}, {}, {}", username_key, email_key, id_key, all_user_key);
    }


    @Override
    public Set<User> getAllUsers() {
        var key = CacheKeyConstant.ALL_USER;
        var cache_result = redisTemplate.opsForValue().get(key);
        if (cache_result != null) {
            log.info("cache hit {}", key);
            var result = JSON.parseArray(cache_result, User.class);
            return new HashSet<>(result);
        }
        log.warn("cache miss {}", key);
        var result = userRepository.findAll();
        redisTemplate.opsForValue().set(key, JSON.toJSONString(result));
        log.info("cache set {}", key);
        return new HashSet<>(result);
    }
}
