package com.example.springbootdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.springbootdemo.common.Constants;
import com.example.springbootdemo.controller.dto.UserDTO;
import com.example.springbootdemo.entity.User;
import com.example.springbootdemo.exception.ServiceException;
import com.example.springbootdemo.mapper.UserMapper;
import com.example.springbootdemo.service.UserService;
import com.example.springbootdemo.config.jwt.TokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int saveUser(User user) {
        if(userMapper.selectById(user.getId()) == null){
            return userMapper.insert(user);
        }else{
            return userMapper.updateById(user);
        }
    }

    @Override
    public int removeById(Integer id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int removeByIds(List<Integer> ids) {
        return userMapper.deleteBatchIds(ids);
    }

    @Override
    public UserDTO login(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        User one;
        try {
            one = userMapper.selectOne(queryWrapper);
        }catch(Exception e){
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        if (one != null) {
            BeanUtils.copyProperties(one, userDTO);
            //设置token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            userDTO.setToken(token);
            return userDTO;
        }else{
            throw new ServiceException(Constants.CODE_500, "用户名或密码错误");
        }
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectList(null);
    }

    @Override
    public User register(UserDTO userDTO) {
        User one = new User();
        //BeanUtils.copyProperties(userDTO, one);
        one.setUsername(userDTO.getUsername());
        one.setPassword(userDTO.getUsername());
        one.setNickname("12345");
        one.setEmail("12345");
        one.setAddress("12345");
        one.setPhone("12345");
        userMapper.insert(one);
        return one;
    }

    @Override
    public User selectOne(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public IPage<User> page(IPage<User> page, QueryWrapper<User> queryWrapper) {
        IPage<User> currentPage = userMapper.selectPage(page, queryWrapper);
        return currentPage;
    }
}
