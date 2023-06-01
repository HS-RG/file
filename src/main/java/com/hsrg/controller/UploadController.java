package com.hsrg.controller;

import com.hsrg.pojo.File;
import com.hsrg.pojo.Result;
import com.hsrg.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    FileService fileService;
    @PostMapping("/file/uploadFile")
    public Result uploadFile(File file) throws Exception {
        return Result.success(fileService.uploadFile(file));
    }

}
