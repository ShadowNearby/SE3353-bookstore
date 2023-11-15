package com.mainservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.*;

@Getter
@Setter
@Node
public class BookType {
    @Relationship(type = "relateBooks")
    @JsonIgnore
    public Set<BookType> relateBookTypes;
    @Id
    @GeneratedValue
    private Long Id;
    private String typeName;
    private List<Long> bookIds;

    private BookType() {
    }

    public BookType(String typeName) {
        this.typeName = typeName;
    }

    public void addRelateBookType(BookType bookType) {
        if (relateBookTypes == null)
            relateBookTypes = new HashSet<>();
        relateBookTypes.add(bookType);
    }

    public void addBookID(Long id) {
        if (bookIds == null)
            bookIds = new ArrayList<>();
        for (Long bookID : bookIds) {
            if (Objects.equals(bookID, id))
                return;
        }
        bookIds.add(id);
    }
}
