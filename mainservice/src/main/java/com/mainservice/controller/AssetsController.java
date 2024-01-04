package com.mainservice.controller;

import com.alibaba.fastjson2.JSONObject;
import com.mainservice.service.AssetsService;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaTypeFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;

@RestController
@Transactional
public class AssetsController {
    private final AssetsService assetsService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public AssetsController(AssetsService assetsService) {
        this.assetsService = assetsService;
    }

    @RequestMapping(value = "/assets/image", method = RequestMethod.POST)
    public JSONObject imagePost(@RequestParam("file") MultipartFile file) throws IOException {
        return assetsService.imageAdd(file);
    }

    @RequestMapping(value = "/assets/image/{fileName}", method = RequestMethod.GET)
    public void imageGet(@PathVariable("fileName") String fileName, @NotNull HttpServletResponse response) throws IOException {
        log.info("get image {}", fileName);
        byte[] base64 = assetsService.imageGet(fileName);
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(base64);
        response.setHeader("content-type", MediaTypeFactory.getMediaType(fileName).toString());
    }
}
