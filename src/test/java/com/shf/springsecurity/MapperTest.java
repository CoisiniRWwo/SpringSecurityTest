package com.shf.springsecurity;

import com.shf.springsecurity.domain.User;
import com.shf.springsecurity.mapper.MenuMapper;
import com.shf.springsecurity.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @Author: Su HangFei
 * @Date: 2023/6/8 14:20
 * @Project: Examination_System
 */
@SpringBootTest
public class MapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUserMapper(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void TestBCryptPasswordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode1 = bCryptPasswordEncoder.encode("000000");
        String encode2 = bCryptPasswordEncoder.encode(encode1);
        System.out.println(encode1);
//        System.out.println(encode2);
        // $2a$10$oey.AiFNnWVbQuab4yEbKuCOfmOK4rUaQs0Ouqx2UJEQY4I62SDBO
        boolean matches = bCryptPasswordEncoder.matches("000000", encode1);
        System.out.println(matches);
    }

    @Test
    public void redisTest01() {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("192.168.28.128",6379);
        // 如果 Redis 服务设置了密码，需要用下面这行代码输入密码
        // jedis.auth("123456");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
    }


    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void testselectPermsByUserId(){
        List<String> strings = menuMapper.selectPermsByUserId(3L);
        System.out.println(strings);
    }
}
