package com.mainservice.dao;

import com.mainservice.entity.Image;

public interface ImageDao {
    void updateImage(Image image);

    void deleteImageByName(String name);

    String getBase64ByName(String name);
}
