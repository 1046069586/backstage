package com.example.springbootdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootdemo.common.Result;
import com.example.springbootdemo.controller.dto.UserDTO;
import com.example.springbootdemo.entity.User;
import com.example.springbootdemo.service.UserService;
import com.example.springbootdemo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //新增和修改
    @PostMapping
    public Result save(@RequestBody User user){
        return Result.success(userService.saveUser(user));
    }

//    @GetMapping
//    public List<User> findAll() {
//        return userService.findAll();
//    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {

        return Result.success(userService.removeById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(userService.removeByIds(ids));
    }


    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                          @RequestParam Integer pageSize,
                                          @RequestParam(defaultValue = "") String username){
        IPage<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(!"".equals(username)){
            queryWrapper.like("username", username);
        }
        IPage<User> currentPage = userService.page(page, queryWrapper);
        return Result.success(currentPage);
    }

    @PostMapping("/login")
    public Result login (@RequestBody UserDTO userDTO){
        UserDTO dto = userService.login(userDTO);
        return Result.success(dto);
    }

    @PostMapping("/register")
    public Result register (@RequestBody UserDTO userDTO){
        return Result.success(userService.register(userDTO));
    }

    @GetMapping("/username/{username}")
    public Result selectOne(@PathVariable String username){
        return Result.success(userService.selectOne(username));
    }
}
