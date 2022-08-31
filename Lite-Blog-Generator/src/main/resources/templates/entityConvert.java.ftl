package ${package.Parent}.convert.${entityLowerName};


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ${customPackage.Entity}.${entity};
import ${customPackage.DTO}.${dto};
import ${customPackage.VO}.${vo};

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

    @Mappings({})
    ${dto} voToDto(${vo} vo);

    @Mappings({})
    ${vo} dtoToVo(${dto} dto);

    @Mappings({})
    ${entity} dtoToEntity(${dto} dto);

    @Mappings({})
    ${dto} entityToDto(${entity} entity);
}
