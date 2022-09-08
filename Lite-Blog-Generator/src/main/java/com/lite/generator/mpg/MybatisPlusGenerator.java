package com.lite.generator.mpg;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.Entity;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.lite.generator.engine.EnhanceFreemarkerTemplatesEngines;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author Stranger
 * @version 1.0
 * @description: TODO
 * @date 2022/8/30 15:46
 */
@Slf4j
public class MybatisPlusGenerator extends MybatisPlusAbstractGenerator {

    public static final String PROJECT_PATH = "./%s/src/main/java/";

    public static final String RESOURCES_PATH = "./%s/src/main/resources/mapper/";


    /**
     * 生成模板代码
     * @throws IOException IO异常
     */
    @Override
    public void generate() throws IOException {
        this.generate(this.getConfig());
    }

    /**
     * 生成模板代码
     * @param path 指定的配置文件路径
     * @throws IOException IO异常
     */
    public void generate(String path) throws IOException {
        this.generate(this.getConfig(path));
    }

    private void generate(MyBatisInfo mybatisInfo) {

        AutoGenerator generator = new AutoGenerator(dataSourceConfig(mybatisInfo));

        //模板配置
        generator.global(globalConfig(mybatisInfo));
        generator.packageInfo(packageConfig(mybatisInfo));
        generator.strategy(strategyConfig(mybatisInfo));
        generator.injection(injectionConfigConsumer(mybatisInfo));
        generator.template(templateConfig(mybatisInfo));

        //执行
        generator.execute(new EnhanceFreemarkerTemplatesEngines(mybatisInfo));
    }

    private DataSourceConfig dataSourceConfig(MyBatisInfo mybatisInfo) {
        return new DataSourceConfig
                .Builder(mybatisInfo.getUrl(),
                mybatisInfo.getUsername(),
                mybatisInfo.getPassword())
                .build();
    }

    private GlobalConfig globalConfig(MyBatisInfo mybatisInfo) {
        //项目路径
        String projectPath = String.format(PROJECT_PATH, mybatisInfo.getModuleName());

        return new GlobalConfig.Builder()
                .author(mybatisInfo.getAuthor())
                .outputDir(projectPath)
                .disableOpenDir()
                .build();
    }

    private PackageConfig packageConfig(MyBatisInfo mybatisInfo) {

        return new PackageConfig.Builder()
                .parent(mybatisInfo.getPackageName())
                .entity(mybatisInfo.getEntity())
                .controller(mybatisInfo.getController())
                .service(mybatisInfo.getService())
                .serviceImpl(mybatisInfo.getServiceImpl())
                .mapper(mybatisInfo.getMapper())
                .xml(mybatisInfo.getXml())
                .other(mybatisInfo.getOther())
                .pathInfo(Collections.singletonMap(OutputFile.mapperXml, String.format(RESOURCES_PATH, mybatisInfo.getModuleName())))
                .build();
    }

    private StrategyConfig strategyConfig(MyBatisInfo mybatisInfo) {
        StrategyConfig.Builder builder = new StrategyConfig.Builder();

        //controller策略配置
        builder.controllerBuilder()
                .enableRestStyle()
                .formatFileName(mybatisInfo.getControllerFormatName());

        //包含的表名
        if (!mybatisInfo.getIncludeTableList().isEmpty()){
            builder.addInclude(mybatisInfo.getIncludeTableList());
        }

        //过滤表名
        if (!StringUtils.isEmpty(mybatisInfo.getNotLikeTable())){
            builder.notLikeTable(new LikeTable(mybatisInfo.getNotLikeTable()));
        }

        //entity策略配置
        Entity.Builder entityBuilder = builder.entityBuilder()
                .formatFileName(mybatisInfo.getEntityFormatName())
                .disableSerialVersionUID()
                .naming(NamingStrategy.underline_to_camel)
                .columnNaming(NamingStrategy.underline_to_camel);

        //开启lombok
        if (mybatisInfo.isLombokEnable()) {
            entityBuilder.enableLombok();
        }

        //实体父类设置
        if (!StringUtils.isEmpty(mybatisInfo.getSuperClassName())) {
            entityBuilder.superClass(mybatisInfo.getSuperClassName());
            entityBuilder.addSuperEntityColumns(mybatisInfo.getSuperEntityColumns());
        }

        //service策略配置
        builder.serviceBuilder()
                .formatServiceFileName(mybatisInfo.getServiceFormatName())
                .formatServiceImplFileName(mybatisInfo.getServiceImplFormatName());

        //mapper策略配置
        builder.mapperBuilder()
                .formatMapperFileName(mybatisInfo.getMapperFormatName())
                .formatXmlFileName(mybatisInfo.getXmlFormatName());


        return builder.build();
    }

    private TemplateConfig templateConfig(MyBatisInfo mybatisInfo) {

        TemplateConfig.Builder templateBuilder = new TemplateConfig.Builder();

        templateBuilder.entity("/templates/entity.java")
                .mapper("/templates/mapper.java")
                .service("/templates/service.java")
                .serviceImpl("/templates/serviceImpl.java")
                .mapperXml("/templates/mapper.xml")
                .controller("/templates/controller.java");


        if (!mybatisInfo.isControllerEnable()) {
            templateBuilder.disable(TemplateType.CONTROLLER);
        }

        if (!mybatisInfo.isServiceEnable()) {
            templateBuilder.disable(TemplateType.SERVICE);
        }

        if (!mybatisInfo.isServiceImplEnable()) {
            templateBuilder.disable(TemplateType.SERVICEIMPL);
        }

        if (!mybatisInfo.isEntityEnable()) {
            templateBuilder.disable(TemplateType.ENTITY);
        }

        if (!mybatisInfo.isMapperEnable()) {
            templateBuilder.disable(TemplateType.MAPPER);
        }

        return templateBuilder.build();
    }

    private InjectionConfig injectionConfigConsumer(MyBatisInfo mybatisInfo) {


        HashMap<String, String> customMap = new HashMap<>(16);

        if (mybatisInfo.isDtoEnable()) {
            customMap.put("DTO.java", "/templates/entityDTO.java.ftl");
        }
        if (mybatisInfo.isVoEnable()) {
            customMap.put("VO.java", "/templates/entityVO.java.ftl");
        }
        if (mybatisInfo.isMapEnable()) {
            customMap.put("Convert.java", "/templates/entityConvert.java.ftl");
        }

        return new InjectionConfig
                .Builder()
                .customFile(customMap)
                .build();
    }

}
