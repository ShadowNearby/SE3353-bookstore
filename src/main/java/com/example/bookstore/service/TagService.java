package com.example.bookstore.service;

import com.example.bookstore.entity.Tag;

import java.util.List;

public interface TagService {
    void addTag(Tag tag);

    void removeTag(String content);

    Tag getTagByContent(String content);

    List<Tag> getAllTags();
}
