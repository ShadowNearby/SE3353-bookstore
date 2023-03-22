package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ordertime", nullable = false)
    private Date orderTime;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @JsonIgnoreProperties(value = {"order"})
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<Goods> goodsList;
//    @OneToOne
//    @JoinColumn(name = "receiver_id", foreignKey = @ForeignKey(name = "order_fk_receiver_id"))
//    private Receiver receiver;

    public Order() {
    }

    public Order(User user, Set<Goods> goodsList) {
        this.user = user;
        this.goodsList = goodsList;
        this.orderTime = new Date();
    }
}
