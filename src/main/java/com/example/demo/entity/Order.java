package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "total", nullable = false)
    private Double totalPrice;
    @Column(name = "ordertime", nullable = false)
    private Date orderTime;
    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_id"))
    private User user;
    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE)
    private List<Goods> goodsList;
    @OneToOne
    @JoinColumn(name = "receiver_id", foreignKey = @ForeignKey(name = "fk_receiver_id"))
    private Receiver receiver;

    public Order() {
    }
}
