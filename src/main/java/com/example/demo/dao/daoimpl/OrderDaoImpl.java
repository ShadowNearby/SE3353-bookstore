package com.example.demo.dao.daoimpl;

import com.example.demo.dao.OrderDao;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class OrderDaoImpl implements OrderDao {
    private final OrderRepository orderRepository;

    public OrderDaoImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Set<Order> getOrdersByUserId(Long userId) {
        return orderRepository.getOrdersByUserId(userId);
    }

    @Override
    public Set<Order> getOrderByUser(User user) {
        return orderRepository.getOrderByUser(user);
    }

    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }
}
