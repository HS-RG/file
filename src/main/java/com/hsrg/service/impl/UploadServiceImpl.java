package com.hsrg.service.impl;

import com.hsrg.mapper.FileInsert;
import com.hsrg.pojo.File;
import com.hsrg.service.UploadService;
import com.hsrg.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wiki.xsx.core.snowflake.config.Snowflake;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    FileInsert fileInsert;
    @Autowired
    Snowflake snowflake;
    @Autowired
    AliOSSUtils aliOSSUtils;
    @Override
    public File uploadFile(File file){
        file.setCreateTime(LocalDateTime.now());
        file.setUpdateTime(LocalDateTime.now());
        file.setFilename(file.getFile().getOriginalFilename());
        file.setFileId(snowflake.nextId());
        try {
            file.setFileUrl(aliOSSUtils.upload(file.getFile(),"file"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fileInsert.insert(file);
        file.setFile(null);
        log.info("上传文件：{}",file.getFilename());
        log.info("URL：{}",file.getFileUrl());
        return file;
    }

    @Override
    public String uploadImage(MultipartFile image) {
        String url;
        try {
            url = aliOSSUtils.upload(image, "image");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("上传文件：{}", image.getOriginalFilename());
        log.info("URL：{}", url);
        return url;
    }
}
