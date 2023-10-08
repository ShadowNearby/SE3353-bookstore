package com.mainservice.util.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookStatisticsForm {
    private String bookName;
    private Integer count;

    public BookStatisticsForm(String bookName, Integer count) {
        this.bookName = bookName;
        this.count = count;
    }
}
