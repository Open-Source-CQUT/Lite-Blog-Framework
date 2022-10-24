package ${customPackage.VO};

<#if entityLombokModel>
    import lombok.Data;
</#if>
<#list table.importPackages as pkg>
import ${pkg};
</#list>
/**
* <p>
    * ${vo} 视图层对象
    * </p>
*
* @author ${author}
* @since ${date}
*/
<#if entityLombokModel>
@Data
</#if>
public class ${vo} {

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
}
