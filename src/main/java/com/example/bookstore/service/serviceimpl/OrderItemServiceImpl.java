package com.example.bookstore.service.serviceimpl;

import com.example.bookstore.dao.BookDao;
import com.example.bookstore.dao.OrderDao;
import com.example.bookstore.dao.OrderItemDao;
import com.example.bookstore.dao.UserDao;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.OrderItem;
import com.example.bookstore.entity.User;
import com.example.bookstore.service.OrderItemService;
import com.example.bookstore.util.SessionUtil;
import com.example.bookstore.util.request.AddOrderItemForm;
import com.example.bookstore.util.request.IdForm;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemDao orderItemDao;
    private final UserDao userDao;
    private final OrderDao orderDao;
    private final BookDao bookDao;

    public OrderItemServiceImpl(OrderItemDao orderItemDao, UserDao userDao, OrderDao orderDao, BookDao bookDao) {
        this.orderItemDao = orderItemDao;
        this.userDao = userDao;
        this.orderDao = orderDao;
        this.bookDao = bookDao;
    }


    @Override
    public Set<OrderItem> getOrderItemInCart() {
        Long userId = SessionUtil.getUserId();
        User user = userDao.getUserById(userId);
        Set<OrderItem> allOrderItem = orderItemDao.getOrderItemByUser(user);
        allOrderItem.removeIf(orderItem -> orderItem.getOrder() != null || orderItem.getBook().getDeleted());
        return allOrderItem;
    }

    @Override
    public Set<OrderItem> getOrderItemInOrder() {
        Long userId = SessionUtil.getUserId();
        var orders = new HashSet<>(orderDao.getOrdersByUserId(userId));
        return orderItemDao.getOrderItemByOrders(orders);
    }

    @Override
    public Long addOrderItemByBookId(AddOrderItemForm addOrderItemForm) {
        Long userId = SessionUtil.getUserId();
        User user = userDao.getUserById(userId);
        Book book = bookDao.getBookById(addOrderItemForm.getBookId());
        Set<OrderItem> orderItems = orderItemDao.getOrderItemInCart(user, book);
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getOrder() == null) {
                orderItem.setCount(orderItem.getCount() + addOrderItemForm.getCount());
                orderItemDao.updateOrderItem(orderItem);
                return orderItem.getId();
            }
        }
        return orderItemDao.updateOrderItem(new OrderItem(addOrderItemForm.getCount(), book, user)).getId();
    }

    @Override
    public void removeById(IdForm idForm) {
        orderItemDao.removeById(idForm.getId());
    }
}

