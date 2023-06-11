package com.hsrg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hsrg.clients.UserClient;
import com.hsrg.mapper.FileDelete;
import com.hsrg.mapper.FileSelect;
import com.hsrg.pojo.File;
import com.hsrg.pojo.PageBean;
import com.hsrg.pojo.User;
import com.hsrg.service.FileService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileSelect fileSelect;
    @Autowired
    private UserClient userClient;
    @Autowired
    private FileDelete fileDelete;
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

    @Override
    public File QueryFileByFileId(Long fileId) {
        return fileSelect.selectByFileId(fileId);
    }

    @Override
    public void DeleteOneFile(Long fileId) {
        fileDelete.DeleteOneFileByFileId(fileId);
    }

    @Override
    public PageBean GetMyFiles(Long userId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<File> fileList = fileSelect.selectByUserId(userId);
        Page<File> page = (Page<File>) fileList;
        PageBean pageBean = new PageBean(page.getTotal(),page.getResult());
        return pageBean;
    }
}
