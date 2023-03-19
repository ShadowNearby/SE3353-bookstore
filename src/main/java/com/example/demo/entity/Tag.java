package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false, unique = true)
    private String content;

    @ManyToMany(mappedBy = "tags")
    private List<Book> books;

    public Tag(String content) {
        this.content = content;
    }

    public Tag() {

    }
}
