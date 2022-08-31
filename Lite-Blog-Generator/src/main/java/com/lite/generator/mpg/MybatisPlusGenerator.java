package com.lite.generator.mpg;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.lite.common.entity.BaseEntity;
import com.lite.generator.core.Generator;
import com.lite.generator.engine.EnhanceFreemarkerTemplatesEngines;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Stranger
 * @version 1.0
 * @description: TODO
 * @date 2022/8/30 15:46
 */
@Slf4j
public class MybatisPlusGenerator implements Generator {

    public static final String PROJECT_PATH = "./%s/src/main/java/";

    public static final String RESOURCES_PATH = "./%s/src/main/resources/mapper/";

    public static final String CONFIG_PATH = "./Lite-Blog-Generator/src/main/resources/mpg.properties";

    @Override
    public void generate() throws IOException {
        this.generateByConfig();
    }

    @Data
    static class MybatisInfo {
        String author;
        String moduleName;
        String url;
        String username;
        String password;
        String packageName;
        String likeTable;
        String[] tablePrefix;
        boolean dtoEnable;
        boolean voEnable;
        boolean mapEnable;
        List<String> includeTableList;
    }

    /**
     * 根据配置文件自动包装配置对象然后生成代码
     */
    public void generateByConfig() throws IOException {
        ResourceBundle resourceBundle = new PropertyResourceBundle(Files.newInputStream(Paths.get(CONFIG_PATH)));
        MybatisInfo mybatisInfo = new MybatisInfo();
        mybatisInfo.setAuthor(resourceBundle.getString("author"));
        mybatisInfo.setUrl(resourceBundle.getString("url"));
        mybatisInfo.setUsername(resourceBundle.getString("username"));
        mybatisInfo.setPassword(resourceBundle.getString("password"));
        mybatisInfo.setModuleName(resourceBundle.getString("moduleName"));
        mybatisInfo.setPackageName(resourceBundle.getString("packageName"));
        mybatisInfo.setDtoEnable(Boolean.parseBoolean(resourceBundle.getString("dtoEnable")));
        mybatisInfo.setVoEnable(Boolean.parseBoolean(resourceBundle.getString("voEnable")));
        mybatisInfo.setMapEnable(Boolean.parseBoolean(resourceBundle.getString("mapEnable")));
        mybatisInfo.setLikeTable(resourceBundle.getString("likeTable"));
        mybatisInfo.setTablePrefix(resourceBundle.getString("tablePrefix").split(","));
        mybatisInfo.setIncludeTableList(Arrays.stream(resourceBundle.getString("includeTableList").split(",")).collect(Collectors.toList()));
        generateMvcCode(mybatisInfo);
    }

    /**
     * 手动构造信息对象然后生成代码 不推荐使用
     * @param mybatisInfo 信息对象
     */
    private void generateMvcCode(MybatisInfo mybatisInfo) {

        //作者名称
        String author = mybatisInfo.getAuthor();
        //项目路径
        String projectPath = String.format(PROJECT_PATH, mybatisInfo.getModuleName());
        //项目包名
        String packageName = mybatisInfo.getPackageName();
        //MapperXml路径
        String mapperPath = String.format(RESOURCES_PATH, mybatisInfo.getModuleName());
        //数据库URL
        String url = mybatisInfo.getUrl();
        //数据库用户名
        String username = mybatisInfo.getUsername();
        //数据库密码
        String password = mybatisInfo.getPassword();
        //待生成的表
        List<String> includeTableList = mybatisInfo.getIncludeTableList();

        String likeTable = mybatisInfo.getLikeTable();

        String[] tablePrefix = mybatisInfo.getTablePrefix();

        boolean dtoEnable = mybatisInfo.isDtoEnable();

        boolean voEnable = mybatisInfo.isVoEnable();

        boolean mapEnable = mybatisInfo.isMapEnable();


        log.info("作者:" + author);
        log.info("父包名为: " + packageName);
        log.info("MVC文件的生成路径为: " + projectPath + packageName);
        log.info("MapperXml文件的生成路径为: " + mapperPath);
        log.info("待生成的数据库表实体: " + mybatisInfo.getIncludeTableList().toString());
        log.info("过滤的相似表名: "+likeTable);

        //lamada构造 以下代码其实只有5行，为了可读性所以缩进
        FastAutoGenerator.create(url, username, password)
                //全局配置
                .globalConfig(builder -> builder
                        .outputDir(projectPath)
                        .author(author)
                        .disableOpenDir()
                        .build())
                //包配置
                .packageConfig(builder -> builder.parent(packageName)
                        .xml("mapper")
                        .entity("entity")
                        .pathInfo(Collections.singletonMap(OutputFile.mapperXml, mapperPath))
                        .mapper("dao")
                )
                .injectionConfig(consumer -> {
                    HashMap<String, String> customMap = new HashMap<>();
                    if (dtoEnable)
                        customMap.put("DTO.java", "/templates/entityDTO.java.ftl");
                    if (voEnable)
                        customMap.put("VO.java", "/templates/entityVO.java.ftl");
                    if (mapEnable)
                        customMap.put("Convert.java", "/templates/entityConvert.java.ftl");
                    consumer.customFile(customMap);
                })
                //策略配置
                .strategyConfig(builder -> {
                    StrategyConfig.Builder resBuilder = builder.enableCapitalMode();
                    if (!includeTableList.isEmpty())
                        resBuilder.addInclude(includeTableList);
                    if (!Strings.isBlank(likeTable))
                        resBuilder.likeTable(new LikeTable(likeTable));
                    resBuilder.addTablePrefix(tablePrefix)
                            .entityBuilder()
                            .superClass(BaseEntity.class)
                            .disableSerialVersionUID()
                            .enableLombok()
                            .naming(NamingStrategy.underline_to_camel)
                            .columnNaming(NamingStrategy.underline_to_camel)
                            .addSuperEntityColumns("id", "deleted", "version", "updated_time", "created_time")
                            .controllerBuilder()
                            .enableRestStyle();
                })
                .templateConfig(builder ->
                        builder.entity("/templates/entity.java")
                                .mapper("/templates/mapper.java")
                                .service("/templates/service.java")
                                .serviceImpl("/templates/serviceImpl.java")
                                .mapperXml("/templates/mapper.xml")
                                .controller("/templates/controller.java")
                )
                //设置模板引擎
                .templateEngine(new EnhanceFreemarkerTemplatesEngines())
                .execute();
    }

}
