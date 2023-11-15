package com.mainservice.dao;

import com.mainservice.entity.Book;

import java.util.List;

public interface BookTypeDao {
    List<Book> findBooksByTypeRelation(String typeName);

    void init();
}
