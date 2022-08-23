package com.liteweb;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liteweb.config.cos.CosConfig;
import com.liteweb.config.mail.MailConfig;
import com.liteweb.config.web.WebUrlConfig;
import com.liteweb.modules.auth.convert.UserConverter;
import com.liteweb.modules.auth.dao.AuthMapper;
import com.liteweb.modules.auth.dto.user.UserNormalDto;
import com.liteweb.modules.auth.entity.User;
import com.liteweb.modules.auth.vo.UserVo;
import com.liteweb.modules.cos.dao.CosMapper;
import com.liteweb.modules.cos.service.CosService;
import com.liteweb.modules.mail.exception.MailException;
import com.liteweb.modules.mail.service.MailService;
import com.liteweb.utils.serializer.RedisCache;
import com.liteweb.utils.tool.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
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
}
