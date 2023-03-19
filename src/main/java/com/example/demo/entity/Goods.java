package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "count", length = 16, nullable = false)
    private Integer count;
    @OneToOne
    @JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "fk_book_id"))
    private Book book;
    @ManyToOne
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "fk_order_id"))
    private Order order;
}
