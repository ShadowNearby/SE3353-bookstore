package com.mainservice.service;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AssetsService {
    JSONObject imageAdd(MultipartFile file) throws IOException;

    byte[] imageGet(String fileName);

}
