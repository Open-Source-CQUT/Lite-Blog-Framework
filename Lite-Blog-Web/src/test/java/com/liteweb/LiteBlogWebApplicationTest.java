package com.liteweb;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liteweb.config.cos.CosConfig;
import com.liteweb.config.mail.MailConfig;
import com.liteweb.config.web.CorsConfig;
import com.liteweb.config.web.WebUrlConfig;
import com.liteweb.modules.auth.convert.UserConverter;
import com.liteweb.modules.auth.dao.AuthMapper;
import com.liteweb.modules.auth.entity.User;
import com.liteweb.modules.cos.dao.CosMapper;
import com.liteweb.modules.cos.service.CosService;
import com.liteweb.modules.mail.service.MailService;
import com.liteweb.utils.serializer.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootTest
public class LiteBlogWebApplicationTest {

    @Autowired
    UserConverter userConverter;

    @Autowired
    AuthMapper authMapper;

    @Autowired
    RedisCache redisCache;

    @Autowired
    CosConfig cosConfig;

    @Autowired
    MailConfig mailConfig;

    @Autowired
    MailService mailService;

    @Autowired
    WebUrlConfig webUrlConfig;

    @Autowired
    CosMapper cosMapper;

    @Autowired
    CosService cosService;

    @Autowired
    CorsConfig corsConfig;

    /**
     * 这是一个非常简单的查询执行用户的测试方法
     * 建议用于测试后端应用是否可以正常运行
     */
    @Test
    void applicationTest(){
        LambdaQueryWrapper<User> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getMail,"2633565580@qq.com");
        log.info(authMapper.selectList(queryWrapper).toString());
    }

    @Test
    void logicDeleteTest(){
        LambdaQueryWrapper<User> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getMail,"123");
        authMapper.delete(queryWrapper);
    }

    @Test
    void selectAllTest(){
        List<User> users = authMapper.selectList(null);
        log.info(users.toString());
    }

    @Test
    void CorsConfigLogTest(){
        log.info(Arrays.toString(corsConfig.getAllowedMethods()));
    }
}
