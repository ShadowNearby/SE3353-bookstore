package com.mainservice.dao;

import com.mainservice.entity.Image;

public interface ImageDao {
    void updateImage(Image image);

    byte[] getBase64ByName(String name);
}
