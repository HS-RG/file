package com.hsrg.service.impl;

import com.hsrg.mapper.FileInsert;
import com.hsrg.pojo.File;
import com.hsrg.service.FileService;
import com.hsrg.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wiki.xsx.core.snowflake.config.Snowflake;

import java.time.LocalDateTime;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileInsert fileInsert;
    @Autowired
    Snowflake snowflake;
    @Autowired
    AliOSSUtils aliOSSUtils;
    @Override
    public File uploadFile(File file) throws Exception{
        file.setCreateTime(LocalDateTime.now());
        file.setUpdateTime(LocalDateTime.now());
        file.setFilename(file.getFile().getOriginalFilename());
        file.setFileId(snowflake.nextId());
        file.setFileUrl(aliOSSUtils.upload(file.getFile()));
        fileInsert.insert(file);
        file.setFile(null);
        log.info("上传文件：{}",file.getFilename());
        log.info("URL：{}",file.getFileUrl());
        return file;
    }
}
