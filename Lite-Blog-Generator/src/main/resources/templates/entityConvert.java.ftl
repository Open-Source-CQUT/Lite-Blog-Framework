package ${customPackage.Convert};


import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ${customPackage.Entity}.${entity};
<#if config.dtoEnable>
import ${customPackage.DTO}.${dto};
</#if>
<#if config.voEnable>
import ${customPackage.VO}.${vo};
</#if>

/**
* <p>
    * ${entity} 对象不同数据层转换器
    * </p>
*
* @author ${author}
* @since ${date}
*/

@Mapper(componentModel = "spring")
public interface ${convert} {

    /**
     * VO 转换成 DTO
     */
    @Mappings({})
    ${dto} voToDto(${vo} vo);

    /**
     * VO列表 转换成 DTO列表
     */
    @Mappings({})
    List<${dto}> voListToDtoList(List<${vo}> voList);

    /**
     * VO 转换成 Entity
     */
    @Mappings({})
    ${entity} voToEntity(${vo} vo);

    /**
     * VO列表 转换成 DTO列表
     */
    @Mappings({})
    List<${entity}> voListToEntityList(List<${vo}> voList);

    /**
     * DTO 转换成 VO
     */
    @Mappings({})
    ${vo} dtoToVo(${dto} dto);

    /**
     * DTO列表 转换成 VO列表
     */
    @Mappings({})
    List<${vo}> dtoListToVoList(List<${dto}> dtoList);

    /**
     * DTO 转换成 Entity
     */
    @Mappings({})
    ${entity} dtoToEntity(${dto} dto);

    /**
     * DTO列表 转换成 Entity列表
     */
    @Mappings({})
    List<${entity}> dtoListToEntityList(List<${dto}> dtoList);

    /**
     * Entity 转换成 DTO
     */
    @Mappings({})
    ${dto} entityToDto(${entity} entity);

    /**
     * Entity列表 转换成 DTO列表
     */
    @Mappings({})
    List<${dto}> entityListToDtoList(List<${entity}> entityList);

    /**
     * Entity 转换成 VO
     */
    @Mappings({})
    ${vo} entityToVO(${entity} entity);

    /**
     * Entity列表 转换成 DTO列表
     */
    @Mappings({})
    List<${vo}> entityListToVoList(List<${entity}> entityList);


}
