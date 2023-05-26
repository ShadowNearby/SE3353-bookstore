package com.example.bookstore.service;

import net.sf.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface AssetsService {
    JSONObject imageAdd(MultipartFile file) throws IOException;

    File imageGet(String fileName) throws FileNotFoundException;

}
