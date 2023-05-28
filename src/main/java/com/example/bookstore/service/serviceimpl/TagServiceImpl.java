package com.example.bookstore.service.serviceimpl;

import com.example.bookstore.dao.TagDao;
import com.example.bookstore.entity.Tag;
import com.example.bookstore.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;

//    public TagServiceImpl(TagDao tagDao) {
//        this.tagDao = tagDao;
//    }

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

    @Override
    public List<Tag> getAllTags() {
        return tagDao.getAllTags();
    }
}
