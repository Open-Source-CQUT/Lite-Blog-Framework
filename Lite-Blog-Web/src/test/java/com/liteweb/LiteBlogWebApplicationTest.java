package com.liteweb;

import com.alibaba.fastjson2.JSON;
import com.liteweb.convert.auth.UserConverter;
import com.liteweb.dao.auth.AuthMapper;
import com.liteweb.dto.auth.UserNormalDto;
import com.liteweb.entity.auth.User;
import com.liteweb.utils.serializer.RedisCache;
import com.liteweb.utils.tool.DateUtils;
import com.liteweb.vo.Auth.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class LiteBlogWebApplicationTest {

    @Autowired
    UserConverter userConverter;

    @Autowired
    AuthMapper authMapper;

    @Autowired
    RedisCache redisCache;

    @Test
    void test() {
        log.info(JSON.parseObject("{\"gender\":\"男\",\"mail\":\"263@qq.com\",\"nickName\":\"wyh\"}", UserVo.class).toString());
    }

    @Test
    void test2() {
        log.info(DateUtils.isBefore("2022-08-15 21:39:59", "2022-08-15 22:39:59").toString());
    }

    @Test
    void test3() {
        //手动建一个dto
        UserNormalDto userNormalDto = new UserNormalDto();

        userNormalDto.setMail("2633565580@qq.com");
        userNormalDto.setPassword("123456");
        userNormalDto.setGender(1);
        userNormalDto.setRoleId(0);
        userNormalDto.setAvatar("hh");
        userNormalDto.setDescription("hello");

        User user = userConverter.dtoToEntity(userNormalDto);

        log.info(userConverter.entityToTokenVo(user).toString());

    }

    @Test
    void test4() {
        log.info(redisCache.keys("*263@qq.com*").toString());
    }
}
