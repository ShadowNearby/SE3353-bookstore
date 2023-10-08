package com.mainservice.dao.daoimpl;

import com.alibaba.fastjson2.JSON;
import com.mainservice.dao.OrderItemDao;
import com.mainservice.entity.Book;
import com.mainservice.entity.Order;
import com.mainservice.entity.OrderItem;
import com.mainservice.entity.User;
import com.mainservice.repository.OrderItemRepository;
import com.mainservice.util.CacheKeyConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {

    private final OrderItemRepository orderItemRepository;
    private final StringRedisTemplate redisTemplate;
    private final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

    public OrderItemDaoImpl(OrderItemRepository orderItemRepository, StringRedisTemplate redisTemplate) {
        this.orderItemRepository = orderItemRepository;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<OrderItem> getAllOrderItem() {
        var key = CacheKeyConstant.ALL_ORDER_ITEM;
        var cache_result = redisTemplate.opsForValue().get(key);
        if (cache_result != null) {
            log.info("cache hit {}", key);
            return JSON.parseArray(cache_result, OrderItem.class);
        }
        log.warn("cache miss {}", key);
        var result = orderItemRepository.findAll();
        redisTemplate.opsForValue().set(key, JSON.toJSONString(result));
        log.info("cache set {}", key);
        return result;
    }

    @Override
    public Set<OrderItem> getOrderItemInCart(User user, Book book) {
        var key = String.format("order-item-cart-user-id-%d-book-id-%d", user.getId(), book.getId());
//        var cache_result = redisTemplate.opsForValue().get(key);
//        if (cache_result != null) {
//            log.info("cache hit {}", ke】
//            、y);
//            return new HashSet<>(JSON.parseArray(cache_result, OrderItem.class));
//        }
        log.warn("cache miss {}", key);
        var result = orderItemRepository.getAllByUserAndBook(user, book);
        redisTemplate.opsForValue().set(key, JSON.toJSONString(result));
        log.info("cache set {}", key);
        return result;
    }

    @Override
    public Set<OrderItem> getOrderItemByUser(User user) {
        var key = String.format("order-item-cart-user-id-%d", user.getId());
//        var cache_result = redisTemplate.opsForValue().get(key);
//        if (cache_result != null) {
//            log.info("cache hit {}", key);
//            return new HashSet<>(JSON.parseArray(cache_result, OrderItem.class));
//        }
        log.warn("cache miss {}", key);
        var result = orderItemRepository.getAllByUser(user);
        redisTemplate.opsForValue().set(key, JSON.toJSONString(result));
        log.info("cache set {}", key);
        return result;
    }


    @Override
    public Set<OrderItem> getOrderItemByOrders(Set<Order> orders) {
        return orderItemRepository.getAllByOrderIn(orders);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Set<OrderItem> getOrderItemByIds(Set<Long> ids) {
        return orderItemRepository.getAllByIdIn(ids);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public OrderItem updateOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }


    @Override
    public void removeById(Long id) {
        orderItemRepository.deleteOrderItemById(id);
    }
}
