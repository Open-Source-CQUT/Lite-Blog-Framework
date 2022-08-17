package com.liteweb;

import com.alibaba.fastjson2.JSON;
import com.liteweb.modules.auth.convert.UserConverter;
import com.liteweb.modules.auth.dao.AuthMapper;
import com.liteweb.modules.auth.dto.user.UserNormalDto;
import com.liteweb.modules.auth.entity.User;
import com.liteweb.modules.auth.vo.user.UserVo;
import com.liteweb.utils.serializer.RedisCache;
import com.liteweb.utils.tool.DateUtils;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.region.Region;
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

    @Test
    void test5() {
        // 1 初始化用户身份信息（secretId, secretKey）。
// SECRETID和SECRETKEY请登录访问管理控制台 https://console.cloud.tencent.com/cam/capi 进行查看和管理
        String secretId = "AKID2vXtzoLbbJkunPjmX2GDtJRIr9AuMXR4";
        String secretKey = "MRGojNkrhFWWFgqjKehSw95k9G6ewxg8";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
// 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
// clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region("ap-chongqing");
        ClientConfig clientConfig = new ClientConfig(region);
// 这里建议设置使用 https 协议
// 从 5.6.54 版本开始，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
// 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);

        cosClient.listBuckets().forEach(bucket -> {
            log.info(bucket.toString());
        });

        cosClient.shutdown();


    }
}
