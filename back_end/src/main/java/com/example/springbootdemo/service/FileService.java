package com.example.springbootdemo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.springbootdemo.entity.Files;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    int update(Files files);

    int delete(Integer id);

    void deleteBatch( List<Integer> ids);

    IPage<Files> findPage(Integer pageNum, Integer pageSize, String name);

    int upload(MultipartFile file) throws IOException;
}
