package com.example.bookstore.dao.daoimpl;

import com.alibaba.fastjson2.JSON;
import com.example.bookstore.dao.OrderItemDao;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.OrderItem;
import com.example.bookstore.entity.User;
import com.example.bookstore.repository.OrderItemRepository;
import com.example.bookstore.util.CacheKeyConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

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
        return orderItemRepository.findAllByUserAndBook(user, book);
    }

    @Override
    public Set<OrderItem> getOrderItemByUser(User user) {
        return orderItemRepository.getAllByUser(user);
    }


    @Override
    public Set<OrderItem> getOrderItemByOrders(Set<Order> orders) {
        return orderItemRepository.getAllByOrderIn(orders);
    }

    @Override
    public Set<OrderItem> getOrderItemByIds(Set<Long> ids) {
        return orderItemRepository.getAllByIdIn(ids);
    }

    @Override
    public OrderItem updateOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }


    @Override
    public void removeById(Long id) {
        orderItemRepository.deleteOrderItemById(id);
    }
}
