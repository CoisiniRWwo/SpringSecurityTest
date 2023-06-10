package com.shf.springsecurity.service;

import com.shf.springsecurity.domain.ResponseResult;
import com.shf.springsecurity.domain.User;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
