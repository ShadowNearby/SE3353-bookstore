package com.mainservice.service.serviceimpl;

import com.mainservice.dao.BookDao;
import com.mainservice.dao.BookTypeDao;
import com.mainservice.dao.OrderItemDao;
import com.mainservice.entity.Book;
import com.mainservice.entity.OrderItem;
import com.mainservice.service.BookService;
import com.mainservice.util.request.BookStatisticsForm;
import com.mainservice.util.request.StatisticForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final BookTypeDao bookTypeDao;
    private final OrderItemDao orderItemDao;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public BookServiceImpl(BookDao bookDao, BookTypeDao bookTypeDao, OrderItemDao orderItemDao) {
        this.bookDao = bookDao;
        this.bookTypeDao = bookTypeDao;
        this.orderItemDao = orderItemDao;
    }

    @Override
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }

    @Override
    public Book getBookById(Long id) {
        return bookDao.getBookById(id);
    }

    @Override
    public Book getBookByName(String name) {
        return bookDao.getBookByName(name);
    }

    @Override
    public List<Book> getBookByTypeName(String name) {
        bookTypeDao.init();
        return bookTypeDao.findBooksByTypeRelation(name);
    }

    @Override
    public void addBook(String name, String image, String desc, String author, String isbn, Double price, Integer inventory) {
        bookDao.updateBook(new Book(name, image, desc, author, isbn, price, inventory));
    }

    @Override
    public void putBook(Long id, String name, String image, String desc, String author, String isbn, Double price, Integer inventory) {
        bookDao.updateBook(new Book(id, name, image, desc, author, isbn, price, inventory));
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookDao.getBookById(id);
        bookDao.deleteBook(book);
    }

    @Override
    public Integer addInventory(String name, Integer count) {
        return bookDao.addInventory(name, count);
    }

    @Override
    public Integer subInventory(String name, Integer count) {
        return bookDao.subInventory(name, count);
    }

    @Override
    public List<BookStatisticsForm> statistics(StatisticForm statisticForm) {
        List<OrderItem> orderItemList = orderItemDao.getAllOrderItem();
        HashMap<String, Integer> nameCountMap = new HashMap<>();
        for (OrderItem orderItem : orderItemList) {
            Date orderTime = orderItem.getOrderTime();
            if (orderTime == null || orderTime.getTime() < statisticForm.getBeginDate().getTime() || orderTime.getTime() > statisticForm.getEndDate().getTime()) {
                continue;
            }
            String key = orderItem.getBook().getName();
            boolean exist = nameCountMap.containsKey(key);
            if (exist) {
                Integer value = nameCountMap.get(key);
                value += orderItem.getCount();
                nameCountMap.put(key, value);
            } else {
                nameCountMap.put(key, orderItem.getCount());
            }
        }
        List<BookStatisticsForm> bookStatisticsForms = new ArrayList<>();
        for (String key : nameCountMap.keySet()) {
            bookStatisticsForms.add(new BookStatisticsForm(key, nameCountMap.get(key)));
        }
        bookStatisticsForms.sort((o1, o2) -> o2.getCount() - o1.getCount());
        return bookStatisticsForms;
    }
}
