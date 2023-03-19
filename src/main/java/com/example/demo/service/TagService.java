package com.example.demo.service;

import com.example.demo.entity.Tag;

public interface TagService {
    void addTag(Tag tag);

    void removeTag(String content);

    Tag getTagByContent(String content);
}
