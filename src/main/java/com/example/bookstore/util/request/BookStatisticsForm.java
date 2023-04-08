package com.example.bookstore.util.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookStatisticsForm {
    private String BookName;
    private Integer Count;

    public BookStatisticsForm(String bookName, Integer count) {
        BookName = bookName;
        Count = count;
    }
}
