package com.lite.business.convert.comment;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.lite.business.entity.comment.Comment;
import com.lite.business.dto.comment.CommentDTO;
import com.lite.business.vo.comment.CommentVO;

/**
* <p>
    * Comment 对象不同数据层转换器
    * </p>
*
* @author stranger
* @since 2022-08-31
*/

@Mapper(componentModel = "spring")
public interface CommentConvert {

    @Mappings({})
    CommentDTO voToDto(CommentVO vo);

    @Mappings({})
    CommentVO dtoToVo(CommentDTO dto);

    @Mappings({})
    Comment dtoToEntity(CommentDTO dto);

    @Mappings({})
    CommentDTO entityToDto(Comment entity);
}
