package com.lite.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lite.common.serializer.RedisCache;
import com.lite.auth.config.CorsConfig;
import com.lite.auth.config.WebUrlConfig;
import com.lite.auth.convert.UserConverter;
import com.lite.auth.dao.AuthMapper;
import com.lite.auth.entity.User;
import com.lite.cos.config.CosConfig;
import com.lite.cos.dao.CosMapper;
import com.lite.cos.service.CosService;
import com.lite.mail.config.MailConfig;
import com.lite.mail.service.MailService;
import com.lite.system.config.SystemConfig;
import com.lite.system.entity.SystemApi;
import com.lite.system.entity.SystemController;
import com.lite.system.utils.SystemStringUtils;
import com.lite.system.utils.list.iml.SystemEntityComparator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPattern;


import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    @Autowired
    WebApplicationContext webApplicationContext;

    /**
     * 这是一个非常简单的查询执行用户的测试方法
     * 建议用于测试后端应用是否可以正常运行
     */
    @Test
    void applicationTest() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getMail, "2633565580@qq.com");
        log.info(authMapper.selectList(queryWrapper).toString());
    }

    @Test
    void logicDeleteTest() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getMail, "123");
        authMapper.delete(queryWrapper);
    }

    @Test
    void selectAllTest() {
        List<User> users = authMapper.selectList(null);
        log.info(users.toString());
    }

    @Test
    void CorsConfigLogTest() {
        log.info(Arrays.toString(corsConfig.getAllowedMethods()));
    }

    @Test
    void commonLang3Test() {
        log.info(String.valueOf(StringUtils.isBlank(null)));
        log.info(String.valueOf(StringUtils.isEmpty(null)));
    }

    @Test
    void autoConfigTest() {
        log.info(cosConfig.toString());
        log.info(corsConfig.toString());
        log.info(webUrlConfig.toString());
    }

    @Autowired
    SystemConfig systemConfig;


    @Test
    void applicationContext() {
        Map<String, SystemApi> map = redisCache.getCacheMap(systemConfig.getRedisMapKey());
    }


    @Autowired
    SystemEntityComparator comparator;


    @Test
    public void strTest() {
        log.info(SystemStringUtils.removeBracketContent("com.lite.auth.authcontroller#uploader(file)"));
    }
}
