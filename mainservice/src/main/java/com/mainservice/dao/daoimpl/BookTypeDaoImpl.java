package com.mainservice.dao.daoimpl;

import com.mainservice.dao.BookTypeDao;
import com.mainservice.entity.Book;
import com.mainservice.entity.BookType;
import com.mainservice.repository.BookRepository;
import com.mainservice.repository.BookTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class BookTypeDaoImpl implements BookTypeDao {
    private final BookTypeRepository bookTypeRepository;
    private final BookRepository bookRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public BookTypeDaoImpl(BookTypeRepository bookTypeRepository, BookRepository bookRepository) {
        this.bookTypeRepository = bookTypeRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findBooksByTypeRelation(String typeName) {
        var optionalBookType = bookTypeRepository.findBookTypesByTypeName(typeName);
        if (optionalBookType.isEmpty()) {
            return new ArrayList<>();
        }
        var type = optionalBookType.get();
        HashMap<Long, Long> result = new HashMap<>();
        List<Book> resultBook = new ArrayList<>();
        for (int j = 0; j < type.getBookIds().size(); j++) {
            long id = type.getBookIds().get(j);
            result.put(id, 1L);
        }
        String keyName = type.getTypeName();
        List<BookType> list1 = bookTypeRepository.findNodeRelatedBookTypesDistance1(keyName);
        List<BookType> list2 = bookTypeRepository.findNodeRelatedBookTypesDistance2(keyName);

        for (BookType bookType : list1) {
            for (int j = 0; j < bookType.getBookIds().size(); j++) {
                Long id = bookType.getBookIds().get(j);
                result.put(id, 1L);
            }
        }

        for (BookType bookType : list2) {
            for (int j = 0; j < bookType.getBookIds().size(); j++) {
                Long id = bookType.getBookIds().get(j);
                result.put(id, 1L);
            }
        }
        for (var id : result.keySet()) {
            var optBook = bookRepository.findById(id);
            optBook.ifPresent(resultBook::add);
        }
        return resultBook;
    }

    @Override
    public void init() {
        log.info("init start");
        bookTypeRepository.deleteAll();
        if (!bookTypeRepository.findAll().isEmpty()) {
            return;
        }
        log.info("init start");
        BookType bookType1 = new BookType("小说");
        BookType bookType2 = new BookType("古典小说");
        BookType bookType3 = new BookType("科幻小说");
        BookType bookType4 = new BookType("超现实科幻小说");

        Book book1 = new Book(4L, "book1", "", "", "", "1", 10.0, 1000);
        Book book2 = new Book(5L, "book2", "", "", "", "2", 10.0, 1000);
        Book book3 = new Book(6L, "book3", "", "", "", "3", 10.0, 1000);
        Book book4 = new Book(7L, "book4", "", "", "", "4", 10.0, 1000);
        Book book5 = new Book(8L, "book5", "", "", "", "5", 10.0, 1000);

        bookType1.addRelateBookType(bookType2);
        bookType1.addRelateBookType(bookType3);
        bookType3.addRelateBookType(bookType4);

        bookType1.addBookID(book1.getId());
        bookType1.addBookID(book2.getId());
        bookType1.addBookID(book3.getId());
        bookType1.addBookID(book4.getId());
        bookType1.addBookID(book5.getId());

        bookType2.addBookID(book1.getId());
        bookType2.addBookID(book2.getId());

        bookType3.addBookID(book3.getId());
        bookType3.addBookID(book4.getId());
        bookType3.addBookID(book5.getId());

        bookType4.addBookID(book5.getId());

        bookTypeRepository.save(bookType1);
        bookTypeRepository.save(bookType2);
        bookTypeRepository.save(bookType3);
        bookTypeRepository.save(bookType4);
    }
}
