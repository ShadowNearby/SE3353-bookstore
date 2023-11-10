package com.mainservice.dao.daoimpl;

import com.mainservice.dao.ImageDao;
import com.mainservice.entity.Image;
import com.mainservice.repository.ImageRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ImageDaoImpl implements ImageDao {
    private final ImageRepository imageRepository;

    public ImageDaoImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void updateImage(Image image) {
        imageRepository.save(image);
    }

    @Override
    public void deleteImageByName(String name) {
        imageRepository.deleteImageByName(name);
    }

    @Override
    public String getBase64ByName(String name) {
        var opt_image = imageRepository.findImageByName(name);
        return opt_image.map(Image::getBase64).orElse("");
    }
}
