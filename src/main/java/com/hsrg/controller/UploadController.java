package com.hsrg.controller;

import com.hsrg.pojo.Result;
import com.hsrg.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    AliOSSUtils aliOSSUtils;
    @PostMapping("/file/uploadFile")
    public Result upload(MultipartFile file) throws Exception {

        log.info("文件上传：,{}",file.getOriginalFilename());
        String url = aliOSSUtils.upload(file);
        log.info("文件上传完成，其url：{}",url);

        return Result.success(url);
    }
}
