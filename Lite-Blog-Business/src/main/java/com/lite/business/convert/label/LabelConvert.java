package com.lite.business.convert.label;


import java.util.List;
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
* @since 2022-09-01
*/

@Mapper(componentModel = "spring")
public interface LabelConvert {

    /**
     * VO 转换成 DTO
     */
    @Mappings({})
    LabelDTO voToDto(LabelVO vo);

    /**
     * VO列表 转换成 DTO列表
     */
    @Mappings({})
    List<LabelDTO> voListToDtoList(List<LabelVO> voList);

    /**
     * VO 转换成 Entity
     */
    @Mappings({})
    Label voToEntity(LabelVO vo);

    /**
     * VO列表 转换成 DTO列表
     */
    @Mappings({})
    List<Label> voListToEntityList(List<LabelVO> voList);

    /**
     * DTO 转换成 VO
     */
    @Mappings({})
    LabelVO dtoToVo(LabelDTO dto);

    /**
     * DTO列表 转换成 VO列表
     */
    @Mappings({})
    List<LabelVO> dtoListToVoList(List<LabelDTO> dtoList);

    /**
     * DTO 转换成 Entity
     */
    @Mappings({})
    Label dtoToEntity(LabelDTO dto);

    /**
     * DTO列表 转换成 Entity列表
     */
    @Mappings({})
    List<Label> dtoListToEntityList(List<LabelDTO> dtoList);

    /**
     * Entity 转换成 DTO
     */
    @Mappings({})
    LabelDTO entityToDto(Label entity);

    /**
     * Entity列表 转换成 DTO列表
     */
    @Mappings({})
    List<LabelDTO> entityListToDtoList(List<Label> entityList);

    /**
     * Entity 转换成 VO
     */
    @Mappings({})
    LabelVO entityToVO(Label entity);

    /**
     * Entity列表 转换成 DTO列表
     */
    @Mappings({})
    List<LabelVO> entityListToVoList(List<Label> entityList);


}
