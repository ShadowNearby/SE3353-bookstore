package com.example.bookstore.util.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddGoodsForm {
    private Long bookId;
    private Integer count;
}
