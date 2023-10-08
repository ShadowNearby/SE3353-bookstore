package com.mainservice.repository;

import com.mainservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByName(String name);

    List<Book> findBooksByDeletedNot(Boolean deleted);
}
