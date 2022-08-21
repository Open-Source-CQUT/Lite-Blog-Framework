package com.liteweb;

import com.alibaba.fastjson2.JSON;
import com.liteweb.config.CosConfig;
import com.liteweb.config.MailConfig;
import com.liteweb.config.WebUrlConfig;
import com.liteweb.modules.auth.convert.UserConverter;
import com.liteweb.modules.auth.dao.AuthMapper;
import com.liteweb.modules.auth.dto.user.UserNormalDto;
import com.liteweb.modules.auth.entity.User;
import com.liteweb.modules.auth.vo.UserVo;
import com.liteweb.modules.mail.exception.MailException;
import com.liteweb.modules.mail.service.MailService;
import com.liteweb.utils.serializer.RedisCache;
import com.liteweb.utils.tool.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import java.io.IOException;

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
    void test4() throws MessagingException, MailException {
        log.info(webUrlConfig.toString());
    }

    @Test
    void test5() throws IOException {

        User user = new User();
        user.setMail("123456@qq.com");
        user.setAvatar("1123");

        authMapper.updateUserInfo(user);
    }
}
