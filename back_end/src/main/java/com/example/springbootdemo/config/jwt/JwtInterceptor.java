package com.example.springbootdemo.config.jwt;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.springbootdemo.common.Constants;
import com.example.springbootdemo.entity.User;
import com.example.springbootdemo.exception.ServiceException;
import com.example.springbootdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        if(StrUtil.isBlank(token)) {
            throw new ServiceException(Constants.CODE_401,"无token，请重新登录");
        }
        String userId;
        try{
            userId = JWT.decode(token).getAudience().get(0);
        }catch(JWTDecodeException e){
            throw new ServiceException(Constants.CODE_401, "token验证失败");
        }
        User user = userMapper.selectById(userId);
        if(user == null){
            throw new ServiceException(Constants.CODE_401, "没有用户,请重新登录");
        }
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try{
            jwtVerifier.verify(token);
        }catch(JWTVerificationException e){
            throw new ServiceException(Constants.CODE_401, "token验证失败");
        }
        return true;
    }
}
