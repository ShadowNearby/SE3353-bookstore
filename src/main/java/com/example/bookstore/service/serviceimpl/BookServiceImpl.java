package com.example.bookstore.service.serviceimpl;

import com.example.bookstore.dao.BookDao;
import com.example.bookstore.dao.GoodsDao;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Goods;
import com.example.bookstore.entity.Order;
import com.example.bookstore.service.BookService;
import com.example.bookstore.util.request.BookStatisticsForm;
import com.example.bookstore.util.request.StatisticForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private GoodsDao goodsDao;

//    public BookServiceImpl(BookDao bookDao, GoodsDao goodsDao) {
//        this.bookDao = bookDao;
//        this.goodsDao = goodsDao;
//    }

    @Override
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }

    @Override
    public Book getBookById(Long id) {
        return bookDao.getBookById(id);
    }

    public Book getBookByName(String name) {
        return bookDao.getBookByName(name);
    }


    @Override
    public void addBook(String name, String image, String desc, String author, String isbn, Double price, Integer inventory) {
        bookDao.addBook(new Book(name, image, desc, author, isbn, price, inventory));
    }

    @Override
    public void putBook(Long id, String name, String image, String desc, String author, String isbn, Double price, Integer inventory) {
        bookDao.addBook(new Book(id, name, image, desc, author, isbn, price, inventory));
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
        List<Goods> goodsList = goodsDao.getAllGoods();
        HashMap<String, Integer> nameCountMap = new HashMap<>();
        for (Goods goods : goodsList) {
            Order order = goods.getOrder();
            if (order == null || order.getOrderTime().getTime() < statisticForm.getBeginDate().getTime() || order.getOrderTime().getTime() > statisticForm.getEndDate().getTime())
                continue;
            String key = goods.getBook().getName();
            boolean exist = nameCountMap.containsKey(key);
            if (exist) {
                Integer value = nameCountMap.get(key);
                value += goods.getCount();
                nameCountMap.put(key, value);
            } else {
                nameCountMap.put(key, goods.getCount());
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
