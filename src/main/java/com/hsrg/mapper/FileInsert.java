package com.hsrg.mapper;

import com.hsrg.pojo.File;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface FileInsert {
    @Insert("insert into file(file_id, user_id, filename, file_url, title, context, year_tag, course_tag, type_tag, update_time, create_time) " +
            "VALUES (#{fileId},#{userId},#{filename},#{fileUrl},#{title},#{context},#{yearTag},#{courseTag},#{typeTag},#{updateTime},#{createTime})")
    void insert(File file);
}
