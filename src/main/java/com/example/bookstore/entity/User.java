package com.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", length = 128, nullable = false, unique = true)
    private String username;
    @Column(name = "email", length = 128, nullable = false, unique = true)
    private String email;
    @Column(name = "avatar", length = 1024)
    private String avatar;
    @Column(name = "role", nullable = false)
    private String role;
    @Column(name = "registertime", length = 128, nullable = false)
    private Date registerTime;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orderList;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserAuth userAuth;
    @Column(name = "banned", nullable = false)
    private Boolean banned = false;

    public User(String username, String email, String role, Date registerTime, String avatar) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.avatar = avatar;
        this.registerTime = registerTime;
    }

    public User() {

    }

}