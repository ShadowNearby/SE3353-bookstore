package com.example.bookstore.controller;

import com.example.bookstore.constant.Constant;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

@RestController
@Transactional
public class AssetsController {
    @RequestMapping(value = "/assets/image", method = RequestMethod.POST)
    public JSONObject imagePostTest(@RequestParam("file") MultipartFile file) throws IOException {
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

    @RequestMapping(value = "/assets/image/{fileName}", method = RequestMethod.GET)
    public void imageGetTest(@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
//        MultipartFile file = fileForm.getFile();
        String baseUrl = ResourceUtils.getURL("classpath:").getPath() + "static/assets/image/";
        String path = baseUrl + fileName;
        File file = new File(path);
        BufferedImage image = ImageIO.read(new FileInputStream(file));
        OutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "png", outputStream);
//        response.setContentType("image/png");
        response.setHeader("content-type", "image/png");
    }
}
