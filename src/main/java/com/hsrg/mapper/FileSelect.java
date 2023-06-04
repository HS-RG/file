package com.hsrg.mapper;

import com.hsrg.pojo.File;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileSelect {
    List<File> selectByTagAndSearchString(File file, String searchString);
}
