package com.mainservice.service.serviceimpl;

import com.alibaba.fastjson2.JSONObject;
import com.mainservice.constant.Constant;
import com.mainservice.service.AssetsService;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@Service
public class AssetsServiceImpl implements AssetsService {
    @Override
    public JSONObject imageAdd(MultipartFile file) throws IOException {
        JSONObject object = new JSONObject();
        if (file == null)
            return object;
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null)
            originalFilename = "default.png";
        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String basePath = ResourceUtils.getURL("classpath:").getPath() + "static/assets/image/";
        String fileName = uuid + fileSuffix;
        File dest = new File(basePath, fileName);
        if (!dest.getParentFile().exists()) {
            String res = String.valueOf(dest.getParentFile().mkdirs());
        }
        file.transferTo(dest);
        String responsePath = "http://localhost:" + Constant.PORT + "/assets/image/" + fileName;
        object.put("path", responsePath);
        return object;
    }

    @Override
    public File imageGet(String fileName) throws FileNotFoundException {
        String baseUrl = ResourceUtils.getURL("classpath:").getPath() + "static/assets/image/";
        String path = baseUrl + fileName;
        return new File(path);
    }

}
