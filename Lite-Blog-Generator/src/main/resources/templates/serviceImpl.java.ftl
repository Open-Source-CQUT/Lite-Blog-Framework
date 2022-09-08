package ${customPackage.ServiceImpl};

import ${customPackage.Entity}.${entity};
import ${customPackage.Mapper}.${table.mapperName};
import ${customPackage.Service}.${table.serviceName};
<#if config.mapEnable == true>
import ${customPackage.Convert}.${convert};
</#if>

import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {


        @Autowired
        ${table.mapperName} ${entityLowerName}Mapper;

    <#if config.mapEnable == true>
        @Autowired
        ${convert} ${entityLowerName}Convert;
    </#if>

}
</#if>
