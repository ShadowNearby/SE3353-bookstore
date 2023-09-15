package com.example.bookstore.service;

import com.example.bookstore.entity.OrderItem;
import com.example.bookstore.util.request.AddOrderItemForm;
import com.example.bookstore.util.request.IdForm;

import java.util.Set;

public interface OrderItemService {

    Set<OrderItem> getOrderItemInCart();

    Set<OrderItem> getOrderItemInOrder();

    Long addOrderItemByBookId(AddOrderItemForm addOrderItemForm);

    void removeById(IdForm idForm);
}
