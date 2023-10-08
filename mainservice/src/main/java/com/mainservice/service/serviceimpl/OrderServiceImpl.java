package com.mainservice.service.serviceimpl;

import com.mainservice.dao.BookDao;
import com.mainservice.dao.OrderDao;
import com.mainservice.dao.OrderItemDao;
import com.mainservice.dao.UserDao;
import com.mainservice.entity.Book;
import com.mainservice.entity.Order;
import com.mainservice.entity.OrderItem;
import com.mainservice.entity.User;
import com.mainservice.service.OrderService;
import com.mainservice.util.SessionUtil;
import com.mainservice.util.request.AddOrderForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
    @Transactional(propagation = Propagation.REQUIRED)
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
