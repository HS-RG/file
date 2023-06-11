package com.hsrg.controller;

import com.hsrg.pojo.File;
import com.hsrg.pojo.PageBean;
import com.hsrg.pojo.Result;
import com.hsrg.service.FileService;
import com.hsrg.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Year;


@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/file/QueryFileList")
    public Result QueryFileList(@RequestParam Integer yearTag,@RequestParam String courseTag,@RequestParam String typeTag,
                                @RequestParam String searchString,@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        Year yearTag1=null;
        if(yearTag!=-1){
            yearTag1 = Year.of(yearTag);
        }
        PageBean pageBean = fileService.QueryFileList(new File(yearTag1,courseTag,typeTag),searchString,pageNum,pageSize);
        return Result.success(pageBean);
    }

    @PostMapping("/file/QueryFileByFileId")
    public Result QueryFileByFileId(@RequestBody File file){
        return Result.success(fileService.QueryFileByFileId(file.getFileId()));
    }

    @PostMapping("/file/DeleteOneFile")
    public Result DeleteOneFile(@RequestBody File file){
        fileService.DeleteOneFile(file.getFileId());
        return Result.success();
    }

    @PostMapping("/file/GetMyFiles")
    public Result GetMyFiles(@RequestHeader("Authorization")String jwt,@RequestParam Integer pageSize,@RequestParam Integer pageNum){
        Claims claims = JwtUtils.parseJWT(jwt);
        Long userId = (Long) claims.get("userId");
        return Result.success(fileService.GetMyFiles(userId,pageSize,pageNum));
    }


}
