package com.example.bookstore.dao;

import com.example.bookstore.entity.Tag;

public interface TagDao {
    void addTag(Tag tag);

    void removeTag(String content);

    Tag getTagByContent(String content);
}
