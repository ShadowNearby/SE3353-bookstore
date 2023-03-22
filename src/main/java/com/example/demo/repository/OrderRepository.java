package com.example.demo.repository;

import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Set<Order> getOrdersByUserId(Long id);

    Order getOrderByUserId(Long id);

    Set<Order> getOrderByUser(User user);
}
