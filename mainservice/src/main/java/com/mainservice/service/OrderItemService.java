package com.mainservice.service;

import com.mainservice.entity.OrderItem;
import com.mainservice.util.request.AddOrderItemForm;
import com.mainservice.util.request.IdForm;

import java.util.Set;

public interface OrderItemService {

    Set<OrderItem> getOrderItemInCart();

    Set<OrderItem> getOrderItemInOrder();

    Long addOrderItemByBookId(AddOrderItemForm addOrderItemForm);

    void removeById(IdForm idForm);
}
