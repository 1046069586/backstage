package com.example.springbootdemo.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootdemo.entity.Files;
import com.example.springbootdemo.mapper.FileMapper;
import com.example.springbootdemo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Autowired
    private FileMapper fileMapper;

    @Override
    public int update(Files files) {
        return fileMapper.updateById(files);
    }

    @Override
    public int delete(Integer id) {
        Files files = fileMapper.selectById(id);
        files.setDelete(true);
        return fileMapper.updateById(files);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        List<Files> files = fileMapper.selectList(queryWrapper);
        for(Files file :files) {
            file.setDelete(true);
            fileMapper.updateById(file);
        }
    }

    @Override
    public IPage<Files> findPage(Integer pageNum, Integer pageSize, String name) {
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete", false);
        queryWrapper.orderByDesc("id");
        if(!"".equals(name)){
            queryWrapper.like("name", name);
        }
        IPage<Files> files = fileMapper.selectPage(new Page<>(pageNum,pageSize),queryWrapper);
        return files;
    }

    @Override
    public int upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();

        String uuid = IdUtil.fastSimpleUUID();
        String fileUUID = uuid + StrUtil.DOT + type;
        File uploadFile = new File(fileUploadPath + fileUUID);
        File parentFile = uploadFile.getParentFile();
        if(!parentFile.exists()){
            parentFile.mkdirs();
        }
        String url;
        file.transferTo(uploadFile);
        String md5 = SecureUtil.md5(uploadFile);
        Files dbFiles = getFileByMd5(md5);
        if (dbFiles != null){
            url = dbFiles.getUrl();
            uploadFile.delete();
        }else{
            url = "http://localhost:9090/file/" + fileUUID;
        }

        Files saveFile = new Files();
        saveFile.setName(originalFilename);
        saveFile.setType(type);
        saveFile.setSize(size/1024);
        saveFile.setUrl(url);
        saveFile.setMd5(md5);
        return fileMapper.insert(saveFile);
    }

    private Files getFileByMd5(String md5) {
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5", md5);
        List<Files> filesList = fileMapper.selectList(queryWrapper);
        return filesList.size() == 0 ? null : filesList.get(0);
    }
}
