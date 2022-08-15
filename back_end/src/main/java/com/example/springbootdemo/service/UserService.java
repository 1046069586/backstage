package com.example.springbootdemo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.springbootdemo.controller.dto.UserDTO;
import com.example.springbootdemo.entity.User;

import java.util.List;

public interface UserService {

    IPage<User> page(IPage<User> page, QueryWrapper<User> queryWrapper);

    int saveUser(User user);

    int removeById(Integer id);

    int removeByIds(List<Integer> ids);

    UserDTO login(UserDTO userDTO);

    List<User> findAll();

    User register(UserDTO userDTO);

    User selectOne(String username);
}
