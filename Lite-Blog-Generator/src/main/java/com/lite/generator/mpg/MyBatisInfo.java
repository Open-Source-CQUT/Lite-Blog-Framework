package com.lite.generator.mpg;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * @author Stranger
 * @version 1.0
 * @description: 代码生成器配置文件的映射对象
 * @date 2022/9/7 19:29
 */
@Builder
@Getter
@ToString
public class MyBatisInfo {

    public String author;
    public String moduleName;
    public String url;
    public String username;
    public String password;

    public String packageName;
    public String controller;
    public String controllerFormatName;
    public String service;
    public String serviceFormatName;
    public String serviceImpl;
    public String serviceImplFormatName;
    public String entity;
    public String entityFormatName;
    public String mapper;
    public String mapperFormatName;
    public String xml;
    public String xmlFormatName;
    public String other;

    public boolean lombokEnable;
    public boolean controllerEnable;
    public boolean serviceEnable;
    public boolean serviceImplEnable;
    public boolean entityEnable;
    public boolean mapperEnable;
    public boolean xmlEnable;
    public boolean dtoEnable;
    public boolean voEnable;
    public boolean mapEnable;

    public String superClassName;
    public String[] superEntityColumns;
    public List<String> includeTableList;
    public String notLikeTable;
    public String[] tablePrefix;
}
