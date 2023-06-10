package com.shf.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shf.springsecurity.domain.Menu;

import java.util.List;

/**
 * @Author: Su HangFei
 * @Date: 2023/6/10 17:45
 * @Project: Examination_System
 */
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(Long id);
}
