package com.hsrg.controller;

import com.hsrg.pojo.File;
import com.hsrg.pojo.Result;
import com.hsrg.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/file/QueryFileList")
    public Result QueryFileList(@RequestBody File file,@RequestParam String searchString,@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        List<File> files = fileService.QueryFileList(file,searchString,pageNum,pageSize);
        return Result.success(files);
    }

}
