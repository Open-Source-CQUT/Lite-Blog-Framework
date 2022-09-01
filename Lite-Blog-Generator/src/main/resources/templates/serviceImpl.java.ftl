package ${customPackage.ServiceImpl};

import ${customPackage.Entity}.${entity};
import ${customPackage.Mapper}.${table.mapperName};
import ${customPackage.Service}.${table.serviceName};
import ${customPackage.Convert}.${convert};
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

        @Autowired
        ${convert} ${entityLowerName}Convert;
}
</#if>
