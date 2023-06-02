package com.hsrg.service;

import com.hsrg.pojo.File;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    File uploadFile(File file);

    String uploadImage(MultipartFile image);
}
