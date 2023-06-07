package com.hsrg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hsrg.clients.UserClient;
import com.hsrg.mapper.FileSelect;
import com.hsrg.pojo.File;
import com.hsrg.pojo.PageBean;
import com.hsrg.pojo.User;
import com.hsrg.service.FileService;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileSelect fileSelect;
    @Autowired
    private UserClient userClient;
    @Override
    public PageBean QueryFileList(File file, String searchString, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<File> files=fileSelect.selectByTagAndSearchString(file,searchString);
        files.forEach(file1 -> {
            User user=new User();
            user.setUserId(file1.getUserId());
            JSONObject jsonObject = JSONObject.fromObject(userClient.selectByUserId(user).getData());
            file1.setUsername(jsonObject.get("username").toString());
        });
        Page<File> page= (Page<File>) files;
        PageBean pageBean = new PageBean(page.getTotal(),page.getResult());
        return pageBean;
    }
}
