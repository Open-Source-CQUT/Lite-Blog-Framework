package com.lite.business.convert.label;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.lite.business.entity.label.Label;
import com.lite.business.dto.label.LabelDTO;
import com.lite.business.vo.label.LabelVO;

/**
* <p>
    * Label 对象不同数据层转换器
    * </p>
*
* @author stranger
* @since 2022-08-31
*/

@Mapper(componentModel = "spring")
public interface LabelConvert {

    @Mappings({})
    LabelDTO voToDto(LabelVO vo);

    @Mappings({})
    LabelVO dtoToVo(LabelDTO dto);

    @Mappings({})
    Label dtoToEntity(LabelDTO dto);

    @Mappings({})
    LabelDTO entityToDto(Label entity);
}
