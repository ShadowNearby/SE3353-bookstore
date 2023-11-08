package com.mainservice.repository;

import com.mainservice.entity.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ImageRepository extends MongoRepository<Image, String> {
    Optional<Image> findImageByName(String name);

    void deleteImageByName(String name);
}
