package com.example.demo.service.serviceimpl;

import com.example.demo.dao.OrderDao;
import com.example.demo.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
