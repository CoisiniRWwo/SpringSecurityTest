package com.shf.springsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Su HangFei
 * @Date: 2023/6/8 13:36
 * @Project: Examination_System
 */

@RestController
public class HelloController {

    @RequestMapping("hello")
//    @PreAuthorize("hasAuthority('system:dept:list')")
    @PreAuthorize("@ex.hasAuthority('system:dept:list')")
    public String hello(){
        return "hello";
    }
}
