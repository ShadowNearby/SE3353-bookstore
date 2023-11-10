package com.mainservice.service.serviceimpl;

import com.alibaba.fastjson2.JSONObject;
import com.mainservice.constant.Constant;
import com.mainservice.dao.ImageDao;
import com.mainservice.entity.Image;
import com.mainservice.service.AssetsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@Service
public class AssetsServiceImpl implements AssetsService {
    private final ImageDao imageDao;

    public AssetsServiceImpl(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    @Override
    public JSONObject imageAdd(MultipartFile file) throws IOException {
        JSONObject object = new JSONObject();
        if (file == null)
            return object;
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            originalFilename = "default.png";
        }
        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + fileSuffix;
        String base64 = Base64.getEncoder().encodeToString(file.getBytes());
        imageDao.updateImage(new Image(fileName, base64));
        String responsePath = "http://localhost:" + Constant.PORT + "/assets/image/" + fileName;
        object.put("path", responsePath);
        return object;
    }

    @Override
    public String imageGet(String fileName) {
        return imageDao.getBase64ByName(fileName);
    }

}
