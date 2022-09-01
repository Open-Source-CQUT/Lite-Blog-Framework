package com.lite.business.convert.category;


import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.lite.business.entity.category.Category;
import com.lite.business.dto.category.CategoryDTO;
import com.lite.business.vo.category.CategoryVO;

/**
* <p>
    * Category 对象不同数据层转换器
    * </p>
*
* @author stranger
* @since 2022-09-01
*/

@Mapper(componentModel = "spring")
public interface CategoryConvert {

    /**
     * VO 转换成 DTO
     */
    @Mappings({})
    CategoryDTO voToDto(CategoryVO vo);

    /**
     * VO列表 转换成 DTO列表
     */
    @Mappings({})
    List<CategoryDTO> voListToDtoList(List<CategoryVO> voList);

    /**
     * VO 转换成 Entity
     */
    @Mappings({})
    Category voToEntity(CategoryVO vo);

    /**
     * VO列表 转换成 DTO列表
     */
    @Mappings({})
    List<Category> voListToEntityList(List<CategoryVO> voList);

    /**
     * DTO 转换成 VO
     */
    @Mappings({})
    CategoryVO dtoToVo(CategoryDTO dto);

    /**
     * DTO列表 转换成 VO列表
     */
    @Mappings({})
    List<CategoryVO> dtoListToVoList(List<CategoryDTO> dtoList);

    /**
     * DTO 转换成 Entity
     */
    @Mappings({})
    Category dtoToEntity(CategoryDTO dto);

    /**
     * DTO列表 转换成 Entity列表
     */
    @Mappings({})
    List<Category> dtoListToEntityList(List<CategoryDTO> dtoList);

    /**
     * Entity 转换成 DTO
     */
    @Mappings({})
    CategoryDTO entityToDto(Category entity);

    /**
     * Entity列表 转换成 DTO列表
     */
    @Mappings({})
    List<CategoryDTO> entityListToDtoList(List<Category> entityList);

    /**
     * Entity 转换成 VO
     */
    @Mappings({})
    CategoryVO entityToVO(Category entity);

    /**
     * Entity列表 转换成 DTO列表
     */
    @Mappings({})
    List<CategoryVO> entityListToVoList(List<Category> entityList);


}
