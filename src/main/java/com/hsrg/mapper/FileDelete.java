package com.hsrg.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileDelete {

    @Delete("delete from file where file_id=#{fileId}")
    void DeleteOneFileByFileId(Long fileId);
}
