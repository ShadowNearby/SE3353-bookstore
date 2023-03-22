package com.example.demo.controller;

import com.example.demo.entity.Tag;
import com.example.demo.service.TagService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @RequestMapping(value = "/api/tag", method = RequestMethod.GET)
    public Tag getTagByContent(@RequestParam("content") String content) {
        return tagService.getTagByContent(content);
    }

    @RequestMapping(value = "/api/tag", method = RequestMethod.POST)
    public void addTag(@RequestParam("content") String content) {
        tagService.addTag(new Tag(content));
    }

    @RequestMapping(value = "api/tag", method = RequestMethod.DELETE)
    public void removeTag(@RequestParam("content") String content) {
        tagService.removeTag(content);
    }

}
