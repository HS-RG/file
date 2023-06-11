package com.hsrg.mapper;

import com.hsrg.pojo.File;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileSelect {
    List<File> selectByTagAndSearchString(File file, String searchString);

    @Select("select * from file where file_id=#{fileId}")
    File selectByFileId(Long fileId);

    @Select("select * from file where user_id=#{userId}")
    List<File> selectByUserId(Long userId);
}
