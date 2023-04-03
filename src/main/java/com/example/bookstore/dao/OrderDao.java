package com.example.bookstore.dao;

import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.User;

import java.util.Set;

public interface OrderDao {
    Set<Order> getOrdersByUserId(Long userId);

    Set<Order> getOrderByUser(User user);

    Order addOrder(Order order);
}
