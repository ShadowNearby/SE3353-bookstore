package com.example.bookstore.util.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddBookForm {
    private String name;
    private String image;
    private String description;
    private String author;
    private String isbn;
    private Double price;
    private Integer inventory;
    private List<Long> tagIds;
}
