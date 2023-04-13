package com.example.bookstore.util.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class FileForm {
    private MultipartFile file;
}
