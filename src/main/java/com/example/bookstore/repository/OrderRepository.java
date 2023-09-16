package com.example.bookstore.repository;

import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> getOrdersByUserId(Long id);

    List<Order> getOrderByUser(User user);
}
