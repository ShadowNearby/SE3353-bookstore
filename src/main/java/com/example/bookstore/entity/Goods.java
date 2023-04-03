package com.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "count", length = 16, nullable = false)
    private Integer count;
    @OneToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    private Book book;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    @JsonIgnoreProperties(value = {"user", "goodsList"})
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    public Goods(Integer count, Book book, User user, Order order) {
        this.count = count;
        this.book = book;
        this.user = user;
        this.order = order;
    }

    public Goods(Integer count, Book book, User user) {
        this.count = count;
        this.book = book;
        this.user = user;
    }

    public Goods() {

    }
}
