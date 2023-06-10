package com.shf.springsecurity.service.impl;

import com.shf.springsecurity.domain.LoginUser;
import com.shf.springsecurity.domain.ResponseResult;
import com.shf.springsecurity.domain.User;
import com.shf.springsecurity.service.LoginService;
import com.shf.springsecurity.utils.JwtUtil;
import com.shf.springsecurity.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * @Author: Su HangFei
 * @Date: 2023/6/9 23:00
 * @Project: Examination_System
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (Objects.isNull(authenticate)){
            throw new RuntimeException("登陆失败");
        }

        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String id = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(id);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token",jwt);
        redisCache.setCacheObject("login:"+id,loginUser);
        return new ResponseResult(200,"登陆成功",hashMap);
    }

    @Override
    public ResponseResult logout() {
        //获取SecurityContextHolder里面的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        //删除redis中的值
        redisCache.deleteObject("login:"+userId);
        return new ResponseResult(200, "注销成功");
    }
}
