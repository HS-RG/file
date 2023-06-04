package com.hsrg.service;

import com.hsrg.pojo.File;

import java.util.List;

public interface FileService {
    List<File> QueryFileList(File file,String searchString,Integer pageNum, Integer pageSize);
}
