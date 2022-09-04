package com.lite.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;


// * 开启跨域注解
@CrossOrigin
// * Mapper注解
@MapperScan("com.lite.**.dao")
// * 声明式事务开启注解
@EnableTransactionManagement
// * 扫描包的路径，多模块注入必需
@SpringBootApplication(scanBasePackages = {"com.lite"})
// * 开启缓存
@EnableCaching
public class LiteBlogWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(LiteBlogWebApplication.class,args);
    }
}
