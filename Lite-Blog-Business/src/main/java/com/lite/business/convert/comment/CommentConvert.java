package com.lite.business.convert.comment;


import java.util.List;
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
* @since 2022-09-01
*/

@Mapper(componentModel = "spring")
public interface CommentConvert {

    /**
     * VO 转换成 DTO
     */
    @Mappings({})
    CommentDTO voToDto(CommentVO vo);

    /**
     * VO列表 转换成 DTO列表
     */
    @Mappings({})
    List<CommentDTO> voListToDtoList(List<CommentVO> voList);

    /**
     * VO 转换成 Entity
     */
    @Mappings({})
    Comment voToEntity(CommentVO vo);

    /**
     * VO列表 转换成 DTO列表
     */
    @Mappings({})
    List<Comment> voListToEntityList(List<CommentVO> voList);

    /**
     * DTO 转换成 VO
     */
    @Mappings({})
    CommentVO dtoToVo(CommentDTO dto);

    /**
     * DTO列表 转换成 VO列表
     */
    @Mappings({})
    List<CommentVO> dtoListToVoList(List<CommentDTO> dtoList);

    /**
     * DTO 转换成 Entity
     */
    @Mappings({})
    Comment dtoToEntity(CommentDTO dto);

    /**
     * DTO列表 转换成 Entity列表
     */
    @Mappings({})
    List<Comment> dtoListToEntityList(List<CommentDTO> dtoList);

    /**
     * Entity 转换成 DTO
     */
    @Mappings({})
    CommentDTO entityToDto(Comment entity);

    /**
     * Entity列表 转换成 DTO列表
     */
    @Mappings({})
    List<CommentDTO> entityListToDtoList(List<Comment> entityList);

    /**
     * Entity 转换成 VO
     */
    @Mappings({})
    CommentVO entityToVO(Comment entity);

    /**
     * Entity列表 转换成 DTO列表
     */
    @Mappings({})
    List<CommentVO> entityListToVoList(List<Comment> entityList);


}
