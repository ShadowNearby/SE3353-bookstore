package com.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false, unique = true)
    private String content;

    @JsonIgnoreProperties(value = {"tags"})
    @ManyToMany(mappedBy = "tags", cascade = CascadeType.ALL)
    private List<Book> books;

    public Tag(String content) {
        this.content = content;
    }

    public Tag() {

    }
}
