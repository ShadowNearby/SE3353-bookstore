package com.mainservice.dao.daoimpl;

import com.mainservice.dao.OrderDao;
import com.mainservice.entity.Order;
import com.mainservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    private final OrderRepository orderRepository;
    private final StringRedisTemplate redisTemplate;
    private final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

    public OrderDaoImpl(OrderRepository orderRepository, StringRedisTemplate redisTemplate) {
        this.orderRepository = orderRepository;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
//        var key = String.format("order-user-id-%d", userId);
//        var cache_result = redisTemplate.opsForValue().get(key);
//        if (cache_result != null) {
//            log.info("cache hit {}", key);
//            return JSON.parseArray(cache_result, Order.class);
//        }
//        log.warn("cache miss {}", key);
        var result = orderRepository.getOrdersByUserId(userId);
//        redisTemplate.opsForValue().set(key, JSON.toJSONString(result));
//        log.info("cache set {}", key);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Order updateOrder(Order order) {
//        var user_order = String.format("order-user-id-%d", order.getUser().getId());
//        var all_order = CacheKeyConstant.ALL_ORDER;
        var result = orderRepository.save(order);
//        redisTemplate.delete(Stream.of(user_order, all_order).toList());
//        log.info("cache remove {}, {}", user_order, all_order);
        return result;
    }

    @Override
    public List<Order> getAllOrders() {
//        var key = CacheKeyConstant.ALL_ORDER;
//        var cache_result = redisTemplate.opsForValue().get(key);
//        if (cache_result != null) {
//            log.info("cache hit {}", key);
//            return JSON.parseArray(cache_result, Order.class);
//        }
//        log.warn("cache miss {}", key);
        var result = orderRepository.findAll();
//        redisTemplate.opsForValue().set(key, JSON.toJSONString(result));
//        log.info("cache set {}", key);
        return result;
    }
}
