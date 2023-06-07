package com.hsrg.service;

import com.hsrg.pojo.File;
import com.hsrg.pojo.PageBean;

public interface FileService {
    PageBean QueryFileList(File file, String searchString, Integer pageNum, Integer pageSize);
}
