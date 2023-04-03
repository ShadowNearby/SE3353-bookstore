package com.example.bookstore.repository;

import com.example.bookstore.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByContent(String content);
}
