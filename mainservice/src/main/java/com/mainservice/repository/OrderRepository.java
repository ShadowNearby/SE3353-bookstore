package com.mainservice.repository;

import com.mainservice.entity.Order;
import com.mainservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> getOrdersByUserId(Long id);

    List<Order> getOrderByUser(User user);
}
