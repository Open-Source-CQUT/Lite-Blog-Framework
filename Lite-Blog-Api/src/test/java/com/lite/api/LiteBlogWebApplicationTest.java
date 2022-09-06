package com.lite.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lite.auth.dao.AuthMapper;
import com.lite.auth.entity.User;
import com.lite.common.serializer.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class LiteBlogWebApplicationTest {

    @Autowired
    AuthMapper authMapper;

    @Autowired
    RedisCache redisCache;


    /**
     * 这是一个非常简单的查询执行用户的测试方法
     * 建议用于测试后端应用是否可以正常运行
     */
    @Test
    void applicationTest() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getMail, "2633565580@qq.com");
        redisCache.setCacheObject("redis",authMapper.selectList(queryWrapper).toString());
        log.info(redisCache.getCacheObject("redis").toString());
    }

}
