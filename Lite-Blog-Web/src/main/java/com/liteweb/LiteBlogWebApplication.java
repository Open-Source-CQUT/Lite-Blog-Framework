package com.liteweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
@MapperScan("com.liteweb.dao")
public class LiteBlogWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(LiteBlogWebApplication.class);
    }
}
