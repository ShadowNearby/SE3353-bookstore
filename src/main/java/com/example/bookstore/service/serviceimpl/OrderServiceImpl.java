package com.example.bookstore.service.serviceimpl;

import com.example.bookstore.dao.BookDao;
import com.example.bookstore.dao.OrderDao;
import com.example.bookstore.dao.OrderItemDao;
import com.example.bookstore.dao.UserDao;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.OrderItem;
import com.example.bookstore.entity.User;
import com.example.bookstore.service.OrderService;
import com.example.bookstore.util.SessionUtil;
import com.example.bookstore.util.request.AddOrderForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final UserDao userDao;
    private final OrderItemDao orderItemDao;
    private final BookDao bookDao;

    public OrderServiceImpl(OrderDao orderDao, UserDao userDao, OrderItemDao orderItemDao, BookDao bookDao) {
        this.orderDao = orderDao;
        this.userDao = userDao;
        this.orderItemDao = orderItemDao;
        this.bookDao = bookDao;
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderDao.getOrdersByUserId(userId);
    }

    @Override
    @Transactional
    public void updateOrder(AddOrderForm addOrderForm) {
        Long userId = addOrderForm.getUserId();
        User user = userDao.getUserById(userId);
        Set<OrderItem> orderItemSet = orderItemDao.getOrderItemByIds(addOrderForm.getOrderItemIds());
        Order order = orderDao.updateOrder(new Order(user, orderItemSet));
        List<OrderItem> orderItemList = new ArrayList<>(orderItemSet);
        for (OrderItem orderItem : orderItemList) {
            orderItem.setOrder(order);
            orderItem.setOrderTime(order.getOrderTime());
            Book book = orderItem.getBook();
            book.setInventory(book.getInventory() - orderItem.getCount());
            bookDao.updateBook(book);
            orderItemDao.updateOrderItem(orderItem);
        }
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    @Override
    public List<Order> getOrders() {
        Long userId = SessionUtil.getUserId();
        return orderDao.getOrdersByUserId(userId);
    }
}
