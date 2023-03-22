package com.example.demo.dao;

import com.example.demo.entity.Order;
import com.example.demo.entity.User;

import java.util.Set;

public interface OrderDao {
    Set<Order> getOrdersByUserId(Long userId);

    Set<Order> getOrderByUser(User user);

    Order addOrder(Order order);
}
