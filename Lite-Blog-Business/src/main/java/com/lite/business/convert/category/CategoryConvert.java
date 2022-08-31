package com.lite.business.convert.category;


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
* @since 2022-08-31
*/

@Mapper(componentModel = "spring")
public interface CategoryConvert {

    @Mappings({})
    CategoryDTO voToDto(CategoryVO vo);

    @Mappings({})
    CategoryVO dtoToVo(CategoryDTO dto);

    @Mappings({})
    Category dtoToEntity(CategoryDTO dto);

    @Mappings({})
    CategoryDTO entityToDto(Category entity);
}
