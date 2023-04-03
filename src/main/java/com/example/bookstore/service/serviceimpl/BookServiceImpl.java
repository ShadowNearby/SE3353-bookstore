package com.example.bookstore.service.serviceimpl;

import com.example.bookstore.dao.BookDao;
import com.example.bookstore.dao.TagDao;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Tag;
import com.example.bookstore.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final TagDao tagDao;

    public BookServiceImpl(BookDao bookDao, TagDao tagDao) {
        this.bookDao = bookDao;
        this.tagDao = tagDao;
    }

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
    public List<Book> getBooksByTagNames(List<String> tagNames) {
        List<Tag> tags = new ArrayList<Tag>();
        for (String tagName : tagNames) {
            tags.add(tagDao.getTagByContent(tagName));
        }
        return bookDao.getBooksByTags(tags);
    }

    @Override
    public void addBook(String name, String image, String desc, String author, String isbn, Double price, Integer inventory, String tagName) {

        Tag tag = tagDao.getTagByContent(tagName);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        bookDao.addBook(new Book(name, image, desc, author, isbn, price, inventory, tags));
    }

    @Override
    public Integer addInventory(String name, Integer count) {
        return bookDao.addInventory(name, count);
    }

    @Override
    public Integer subInventory(String name, Integer count) {
        return bookDao.subInventory(name, count);
    }
}
