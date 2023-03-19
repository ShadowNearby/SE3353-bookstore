package com.example.demo.service.serviceimpl;

import com.example.demo.dao.TagDao;
import com.example.demo.entity.Tag;
import com.example.demo.service.TagService;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {
    private final TagDao tagDao;

    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public void addTag(Tag tag) {
        tagDao.addTag(tag);
    }

    @Override
    public void removeTag(String content) {
        tagDao.removeTag(content);
    }

    @Override
    public Tag getTagByContent(String content) {
        return tagDao.getTagByContent(content);
    }
}
