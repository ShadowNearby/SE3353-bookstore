package com.example.demo.dao;

import com.example.demo.entity.Tag;

public interface TagDao {
    void addTag(Tag tag);

    void removeTag(String content);

    Tag getTagByContent(String content);
}
