package com.example.bookstore.entity;

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
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItemList;

    public Order() {
    }

    public Order(User user, Set<OrderItem> orderItemSet) {
        this.user = user;
        this.orderItemList = orderItemSet;
        this.orderTime = new Date();
    }
}
