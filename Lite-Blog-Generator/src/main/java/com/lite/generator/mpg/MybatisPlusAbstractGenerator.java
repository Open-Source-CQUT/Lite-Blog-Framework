package com.lite.generator.mpg;

import com.alibaba.fastjson2.JSON;
import com.lite.generator.core.Generator;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;

/**
 * @author Stranger
 * @version 1.0
 * @description: mvc代码生成器抽象类，如果有自定义的需求可以继承此类,也可以重写getConfig方法
 * @date 2022/9/7 17:27
 */
@Slf4j
public abstract class MybatisPlusAbstractGenerator implements Generator {

    private static final String DEFAULT_CONFIG_PATH = "./Lite-Blog-Generator/src/main/resources/mpg_config.json";


    /**
     * 读取配置文件
     */
    public MyBatisInfo getConfig(String path) throws IOException {

        File configFile = new File(path);

        if (!configFile.exists()){
            log.error("{}路径下配置文件不存在!",path);
            throw new RuntimeException();
        }

        if (configFile.length() > Integer.MAX_VALUE){
            log.error("配置文件长度过大");
            throw new RuntimeException();
        }

        //读取json文件并通过fastjson转换成java类
        char[] content = new char[(int) configFile.length()];

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Files.newInputStream(configFile.toPath())));

        bufferedReader.read(content);

        bufferedReader.close();

        String json = new String(content);

        MyBatisInfo mybatisInfo = JSON.parseObject(json, MyBatisInfo.class);

        if (mybatisInfo == null){
            log.error("配置文件生成失败");
            throw new RuntimeException();
        }

        return mybatisInfo;
    }

    public MyBatisInfo getConfig() throws IOException {
        return this.getConfig(DEFAULT_CONFIG_PATH);
    }
}
