package com.example.demo.dao.daoimpl;

import com.example.demo.dao.OrderDao;
import com.example.demo.repository.OrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    private final OrderRepository orderRepository;

    public OrderDaoImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
