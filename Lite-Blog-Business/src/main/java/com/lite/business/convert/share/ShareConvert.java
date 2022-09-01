package com.lite.business.convert.share;


import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.lite.business.entity.share.Share;
import com.lite.business.dto.share.ShareDTO;
import com.lite.business.vo.share.ShareVO;

/**
* <p>
    * Share 对象不同数据层转换器
    * </p>
*
* @author stranger
* @since 2022-09-01
*/

@Mapper(componentModel = "spring")
public interface ShareConvert {

    /**
     * VO 转换成 DTO
     */
    @Mappings({})
    ShareDTO voToDto(ShareVO vo);

    /**
     * VO列表 转换成 DTO列表
     */
    @Mappings({})
    List<ShareDTO> voListToDtoList(List<ShareVO> voList);

    /**
     * VO 转换成 Entity
     */
    @Mappings({})
    Share voToEntity(ShareVO vo);

    /**
     * VO列表 转换成 DTO列表
     */
    @Mappings({})
    List<Share> voListToEntityList(List<ShareVO> voList);

    /**
     * DTO 转换成 VO
     */
    @Mappings({})
    ShareVO dtoToVo(ShareDTO dto);

    /**
     * DTO列表 转换成 VO列表
     */
    @Mappings({})
    List<ShareVO> dtoListToVoList(List<ShareDTO> dtoList);

    /**
     * DTO 转换成 Entity
     */
    @Mappings({})
    Share dtoToEntity(ShareDTO dto);

    /**
     * DTO列表 转换成 Entity列表
     */
    @Mappings({})
    List<Share> dtoListToEntityList(List<ShareDTO> dtoList);

    /**
     * Entity 转换成 DTO
     */
    @Mappings({})
    ShareDTO entityToDto(Share entity);

    /**
     * Entity列表 转换成 DTO列表
     */
    @Mappings({})
    List<ShareDTO> entityListToDtoList(List<Share> entityList);

    /**
     * Entity 转换成 VO
     */
    @Mappings({})
    ShareVO entityToVO(Share entity);

    /**
     * Entity列表 转换成 DTO列表
     */
    @Mappings({})
    List<ShareVO> entityListToVoList(List<Share> entityList);


}
