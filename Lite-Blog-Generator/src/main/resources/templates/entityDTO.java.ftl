package ${customPackage.DTO};

import java.time.LocalDateTime;
<#if entityLombokModel>
import lombok.Data;
</#if>
<#list table.importPackages as pkg>
import ${pkg};
</#list>
/**
 * <p>
 * ${dto} 传输层对象
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if entityLombokModel>
@Data
</#if>
public class ${dto} {

    /**
     * 对象ID
     */
    private Long id;

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.comment!?length gt 0>
        <#if swagger>
            @ApiModelProperty("${field.comment}")
        <#else>
            /**
            * ${field.comment}
            */
        </#if>
    </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->

    /**
     * 更新版本
     */
    private Integer version;
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;
}
