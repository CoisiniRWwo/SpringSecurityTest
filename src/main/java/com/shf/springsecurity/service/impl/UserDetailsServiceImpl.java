package com.shf.springsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shf.springsecurity.domain.LoginUser;
import com.shf.springsecurity.domain.User;
import com.shf.springsecurity.mapper.MenuMapper;
import com.shf.springsecurity.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //查询用户信息
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<User>();
        userLambdaQueryWrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(userLambdaQueryWrapper);
        //如果没有查询到用户就抛出异常
        if (StringUtils.isEmpty(user)) {
            throw new RuntimeException("用户名或密码错误");
        }

        //查询对应的权限信息
//        ArrayList<String> list = new ArrayList<>(Arrays.asList("test", "admin"));
        List<String> list = menuMapper.selectPermsByUserId(user.getId());

        //把数据封装成UserDetails对象
        return new LoginUser(user, list);
    }
}
