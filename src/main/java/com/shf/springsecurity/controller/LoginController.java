package com.shf.springsecurity.controller;

import com.shf.springsecurity.domain.ResponseResult;
import com.shf.springsecurity.domain.User;
import com.shf.springsecurity.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Su HangFei
 * @Date: 2023/6/9 22:57
 * @Project: Examination_System
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        //登录
       return loginService.login(user);
    }

    @GetMapping("/user/logout")
    public ResponseResult logput(){
        return loginService.logout();
    }
}
