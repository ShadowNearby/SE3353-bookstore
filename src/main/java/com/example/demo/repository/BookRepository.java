package com.example.demo.repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByName(String name);

    List<Book> findAllByTagsIn(List<Tag> tags);

    Book findBookByISBN(String isbn);

    Book findBooksByAuthor(String author);
}
