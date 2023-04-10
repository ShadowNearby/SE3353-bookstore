package com.example.bookstore.util.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AddOrderForm {
    private Set<Long> goodsIds;
}
