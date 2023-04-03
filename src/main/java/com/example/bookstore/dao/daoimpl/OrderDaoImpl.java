package com.example.bookstore.dao.daoimpl;

import com.example.bookstore.dao.OrderDao;
import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.User;
import com.example.bookstore.repository.OrderRepository;
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
