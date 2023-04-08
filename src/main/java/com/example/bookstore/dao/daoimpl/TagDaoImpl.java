package com.example.bookstore.dao.daoimpl;

import com.example.bookstore.dao.TagDao;
import com.example.bookstore.entity.Tag;
import com.example.bookstore.repository.TagRepository;
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

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag getTagById(Long tagId) {
        return tagRepository.findById(tagId).get();
    }
}
