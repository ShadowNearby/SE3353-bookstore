package com.example.demo.dao.daoimpl;

import com.example.demo.dao.TagDao;
import com.example.demo.entity.Tag;
import com.example.demo.repository.TagRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagDaoImpl implements TagDao {
    private final TagRepository tagRepository;

    public TagDaoImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public void addTag(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public void removeTag(String content) {
        tagRepository.delete(tagRepository.findByContent(content));
    }

    @Override
    public Tag getTagByContent(String content) {
        return tagRepository.findByContent(content);
    }
}
