package com.hsrg.service;

import com.hsrg.pojo.File;
import com.hsrg.pojo.PageBean;

import java.util.List;

public interface FileService {
    PageBean QueryFileList(File file, String searchString, Integer pageNum, Integer pageSize);

    File QueryFileByFileId(Long fileId);

    void DeleteOneFile(Long fileId);

    PageBean GetMyFiles(Long userId, Integer pageSize, Integer pageNum);
}
