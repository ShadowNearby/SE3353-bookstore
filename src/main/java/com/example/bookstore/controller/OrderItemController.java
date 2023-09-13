package com.example.bookstore.controller;

import com.example.bookstore.entity.OrderItem;
import com.example.bookstore.service.OrderItemService;
import com.example.bookstore.util.request.AddOrderItemForm;
import com.example.bookstore.util.request.IdForm;
import org.jetbrains.annotations.NotNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@Transactional
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @RequestMapping(value = "/api/orderItem/cart", method = RequestMethod.GET)
    public Set<OrderItem> getOrderItemInCart() {
        return orderItemService.getOrderItemInCart();
    }

    @RequestMapping(value = "/api/orderItem/order", method = RequestMethod.GET)
    public Set<OrderItem> getOrderItemInOrder() {
        return orderItemService.getOrderItemInOrder();
    }

    @RequestMapping(value = "/api/orderItem/cart/add", method = RequestMethod.POST)
    public Long addOrderItemToCart(@RequestBody @NotNull AddOrderItemForm addOrderItemForm) {
        return orderItemService.addOrderItemByBookId(addOrderItemForm);
    }

    @RequestMapping(value = "/api/orderItem/cart/remove", method = RequestMethod.POST)
    public void addOrderItemToCart(@RequestBody @NotNull IdForm idForm) {
        orderItemService.removeById(idForm);
    }

    @RequestMapping(value = "/api/orderItem/order/add", method = RequestMethod.POST)
    public void addOrderItemToOrder(@RequestBody @NotNull AddOrderItemForm addOrderItemForm) {
        orderItemService.addOrderItemByBookId(addOrderItemForm);
    }

}
