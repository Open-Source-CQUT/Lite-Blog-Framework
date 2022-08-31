package com.lite.business.convert.share;


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
* @since 2022-08-31
*/

@Mapper(componentModel = "spring")
public interface ShareConvert {

    @Mappings({})
    ShareDTO voToDto(ShareVO vo);

    @Mappings({})
    ShareVO dtoToVo(ShareDTO dto);

    @Mappings({})
    Share dtoToEntity(ShareDTO dto);

    @Mappings({})
    ShareDTO entityToDto(Share entity);
}
