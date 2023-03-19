package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "user")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account", length = 128, nullable = false, unique = true)
    private String account;
    @Column(name = "password", length = 128, nullable = false)
    private String password;
    @Column(name = "email", length = 128, nullable = false, unique = true)
    private String email;
    @Column(name = "introduction", length = 1024)
    private String introduction;
    @Column(name = "role", length = 16, nullable = false)
    private Role role;
    @Column(name = "registertime", length = 128, nullable = false)
    private Date registerTime;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Order> orderList;

    enum Role {
        admin,
        user
    }
    
}
