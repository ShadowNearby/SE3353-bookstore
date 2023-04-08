package com.example.bookstore.dao;

import com.example.bookstore.entity.Tag;

import java.util.List;

public interface TagDao {
    void addTag(Tag tag);

    void removeTag(String content);

    Tag getTagByContent(String content);

    List<Tag> getAllTags();

    Tag getTagById(Long tagId);
}
