package com.mainservice.util.request;

import lombok.Getter;
import lombok.Setter;

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
}
