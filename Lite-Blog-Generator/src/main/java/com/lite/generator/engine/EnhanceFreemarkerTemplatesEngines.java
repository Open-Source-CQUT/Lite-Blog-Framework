package com.lite.generator.engine;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Stranger
 * @version 1.0
 * @description: 为了实现一些自定义的效果这里继承了FreemarkerTemplateEngine重写了AbstractTemplateEngine的方法
 * @date 2022/8/30 20:50
 */
@Slf4j
public final class EnhanceFreemarkerTemplatesEngines extends FreemarkerTemplateEngine {


    /**
     * 为了能够调用重写的getObjectMap方法，故重写batchOutput方法
     *
     * @return
     */
    @Override
    public @NotNull AbstractTemplateEngine batchOutput() {
        try {
            ConfigBuilder config = this.getConfigBuilder();
            List<TableInfo> tableInfoList = config.getTableInfoList();
            tableInfoList.forEach(tableInfo -> {
                Map<String, Object> objectMap = this.getObjectMap(config, tableInfo);
                Optional.ofNullable(config.getInjectionConfig()).ifPresent(t -> {
                    t.beforeOutputFile(tableInfo, objectMap);
                    // 输出自定义文件
                    outputCustomFile(t.getCustomFile(), tableInfo, objectMap);
                });
                // Mp.java
                outputEntity(tableInfo, objectMap);
                // mapper and xml
                outputMapper(tableInfo, objectMap);
                // service
                outputService(tableInfo, objectMap);
                // MpController.java
                outputController(tableInfo, objectMap);
            });
        } catch (Exception e) {
            throw new RuntimeException("无法创建文件，请检查配置信息！", e);
        }
        return this;
    }

    /**
     * 因为我们为了自定义的效果需要额外加入一点数据，给模板引擎用
     *
     * @param config    配置信息
     * @param tableInfo 表信息对象
     * @return
     */
    @Override
    public @NotNull Map<String, Object> getObjectMap(@NotNull ConfigBuilder config, @NotNull TableInfo tableInfo) {
        StrategyConfig strategyConfig = config.getStrategyConfig();
        Map<String, Object> controllerData = strategyConfig.controller().renderData(tableInfo);
        Map<String, Object> objectMap = new HashMap<>(controllerData);
        Map<String, Object> mapperData = strategyConfig.mapper().renderData(tableInfo);
        objectMap.putAll(mapperData);
        Map<String, Object> serviceData = strategyConfig.service().renderData(tableInfo);
        objectMap.putAll(serviceData);
        Map<String, Object> entityData = strategyConfig.entity().renderData(tableInfo);
        objectMap.putAll(entityData);
        objectMap.put("config", config);
        objectMap.put("package", config.getPackageConfig().getPackageInfo());
        GlobalConfig globalConfig = config.getGlobalConfig();
        objectMap.put("author", globalConfig.getAuthor());
        objectMap.put("kotlin", globalConfig.isKotlin());
        objectMap.put("swagger", globalConfig.isSwagger());
        objectMap.put("date", globalConfig.getCommentDate());
        // 启用 schema 处理逻辑
        String schemaName = "";
        if (strategyConfig.isEnableSchema()) {
            // 存在 schemaName 设置拼接 . 组合表名
            schemaName = config.getDataSourceConfig().getSchemaName();
            if (StringUtils.isNotBlank(schemaName)) {
                schemaName += ".";
                tableInfo.setConvert(true);
            }
        }
        objectMap.put("schemaName", schemaName);
        objectMap.put("table", tableInfo);
        objectMap.put("entity", tableInfo.getEntityName());
        objectMap.put("dto",tableInfo.getEntityName() + "DTO");
        objectMap.put("vo",tableInfo.getEntityName() + "VO");
        objectMap.put("convert",tableInfo.getEntityName() + "Convert");
        objectMap.put("entityLowerName", tableInfo.getEntityName().toLowerCase());
        objectMap.put("customPackage", getCustomPackageInfo(
                config.getPackageConfig().getPackageInfo(),
                objectMap.get("entityLowerName").toString()
        ));
        objectMap.put("parentPath",
                config.getPathInfo().get(OutputFile.entity)
                        .substring(0, config.getPathInfo().get(OutputFile.other).lastIndexOf(File.separator)));
        return objectMap;
    }


    private Map<String, String> getCustomPackageInfo(Map<String, String> packageInfo, String lowerName) {

        Map<String, String> map =
                new HashMap<>(packageInfo).entrySet()
                .stream()
                .peek(entry -> entry.setValue(entry.getValue() + "." + lowerName))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));

        //一些特别改造的数据
        map.put("ServiceImpl",map.get("Service") + ".Impl");
        map.put("DTO",packageInfo.get("Parent") + ".dto." + lowerName);
        map.put("VO",packageInfo.get("Parent") + ".vo." + lowerName);
        map.put("Convert",packageInfo.get("Parent") + ".convert." + lowerName);
        map.put("ModuleName",packageInfo.get("ModuleName"));


        return map;
    }

    private String getParentPath(Map<String, Object> objectMap) {
        return objectMap.get("parentPath").toString() + File.separator;
    }

    @Override
    protected void outputCustomFile(@NotNull Map<String, String> customFile, @NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
        String entityName = tableInfo.getEntityName();
        customFile.forEach((key, value) -> {
            //获取model名 例如entity，dto，vo
            String lowerModelName = key.substring(0, key.indexOf(".")).toLowerCase();
            //拼接路径
            String fileName = String.format((getParentPath(objectMap) + File.separator + lowerModelName + File.separator + entityName.toLowerCase() + File.separator + entityName + "%s"), key);
            //生成文件 value就是ftl模板文件
            outputFile(new File(fileName), objectMap, value);
        });
    }

    @Override
    protected void outputMapper(@NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
        // MpMapper.java
        String entityName = tableInfo.getEntityName();
        String mapperPath = getPathInfo(OutputFile.mapper);
        if (StringUtils.isNotBlank(tableInfo.getMapperName()) && StringUtils.isNotBlank(mapperPath)) {
            //生成完整路径
            getTemplateFilePath(TemplateConfig::getMapper).ifPresent(mapper -> {
                //拼接路径
                String mapperFile = String.format((mapperPath + File.separator + entityName.toLowerCase() + File.separator + tableInfo.getMapperName() + suffixJavaOrKt()), entityName);
                //生成文件
                outputFile(new File(mapperFile), objectMap, mapper);
            });
        }
        // MpMapper.xml
        String xmlPath = getPathInfo(OutputFile.mapperXml);
        if (StringUtils.isNotBlank(tableInfo.getXmlName()) && StringUtils.isNotBlank(xmlPath)) {
            //生成完整路径
            getTemplateFilePath(TemplateConfig::getXml).ifPresent(xml -> {
                //拼接路径
                String xmlFile = String.format((xmlPath + File.separator + entityName.toLowerCase() + File.separator + tableInfo.getXmlName() + ConstVal.XML_SUFFIX), entityName);
                //生成文件
                outputFile(new File(xmlFile), objectMap, xml);
            });
        }
    }

    @Override
    protected void outputEntity(@NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
        String entityName = tableInfo.getEntityName();
        String entityPath = getPathInfo(OutputFile.entity);
        if (StringUtils.isNotBlank(entityName) && StringUtils.isNotBlank(entityPath)) {
            //生成完整路径
            getTemplateFilePath(template -> template.getEntity(getConfigBuilder().getGlobalConfig().isKotlin())).ifPresent((entity) -> {
                //拼接路径
                String entityFile = String.format((entityPath + File.separator + entityName.toLowerCase() + File.separator + "%s" + suffixJavaOrKt()), entityName);
                //生成文件
                outputFile(new File(entityFile), objectMap, entity);
            });
        }
    }

    @Override
    protected void outputService(@NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
        // IMpService.java
        String entityName = tableInfo.getEntityName();
        String servicePath = getPathInfo(OutputFile.service);
        if (StringUtils.isNotBlank(tableInfo.getServiceName()) && StringUtils.isNotBlank(servicePath)) {
            //生成完整路径
            getTemplateFilePath(TemplateConfig::getService).ifPresent(service -> {
                //拼接路径
                String serviceFile = String.format((servicePath + File.separator + entityName.toLowerCase() + File.separator + tableInfo.getServiceName() + suffixJavaOrKt()), entityName);
                //生成文件
                outputFile(new File(serviceFile), objectMap, service);
            });
        }
        // MpServiceImpl.java
        String serviceImplPath = getPathInfo(OutputFile.service) + File.separator + entityName.toLowerCase() + File.separator + "Impl";
        if (StringUtils.isNotBlank(tableInfo.getServiceImplName()) && StringUtils.isNotBlank(serviceImplPath)) {
            //生成完整路径
            getTemplateFilePath(TemplateConfig::getServiceImpl).ifPresent(serviceImpl -> {
                //拼接路径
                String implFile = String.format((serviceImplPath  + File.separator + tableInfo.getServiceImplName() + suffixJavaOrKt()), entityName);
                //生成文件
                outputFile(new File(implFile), objectMap, serviceImpl);
            });
        }
    }

    @Override
    protected void outputController(@NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
        //生成controller前缀路径
        String controllerPath = getPathInfo(OutputFile.controller);
        if (StringUtils.isNotBlank(tableInfo.getControllerName()) && StringUtils.isNotBlank(controllerPath)) {
            //生成完整路径
            getTemplateFilePath(TemplateConfig::getController).ifPresent(controller -> {
                //获取实体名称
                String entityName = tableInfo.getEntityName();
                //拼接路径
                String controllerFile = String.format((controllerPath + File.separator + entityName.toLowerCase() + File.separator + tableInfo.getControllerName() + suffixJavaOrKt()), entityName);
                //生成文件
                outputFile(new File(controllerFile), objectMap, controller);
            });
        }
    }
}
