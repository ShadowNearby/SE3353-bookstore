package com.example.bookstore.util.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
public class AddOrderForm {
    private Set<Long> goodsIds;
    private Long userId;
}
