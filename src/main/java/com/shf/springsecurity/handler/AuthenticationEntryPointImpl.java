package com.shf.springsecurity.handler;

import com.alibaba.fastjson.JSON;
import com.shf.springsecurity.domain.ResponseResult;
import com.shf.springsecurity.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Su HangFei
 * @Date: 2023/6/10 20:40
 * @Project: Examination_System
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseResult responseResult = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "认证失败请重新登录");
        String jsonString = JSON.toJSONString(responseResult);
        //处理异常
        WebUtils.renderString(response,jsonString);
    }
}
