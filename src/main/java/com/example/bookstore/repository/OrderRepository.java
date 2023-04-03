package com.example.bookstore.repository;

import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Set<Order> getOrdersByUserId(Long id);

    Order getOrderByUserId(Long id);

    Set<Order> getOrderByUser(User user);
}
