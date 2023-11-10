package com.mainservice.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;
import java.util.Set;

@Node
public class BookTag {
    @Relationship(type = "relateBooks")
    public Set<BookTag> relateBookTags;
    @Id
    @GeneratedValue
    private Long Id;
    private String TagName;
    private List<Long> bookIds;
}
