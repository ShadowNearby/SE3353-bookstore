package com.mainservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "image")
public class Image {
    @MongoId
    private String _id;

    private String name;
    private byte[] base64;

    public Image(String name, byte[] base64) {
        this.name = name;
        this.base64 = base64;
    }
}
