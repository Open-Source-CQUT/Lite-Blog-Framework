package com.lite.business.convert.article;


import java.util.List;

import com.lite.business.dto.article.ArticleSimpleDto;
import com.lite.business.entity.article.ArticleUser;
import com.lite.business.vo.article.ArticleSimpleVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.lite.business.entity.article.Article;
import com.lite.business.dto.article.ArticleDTO;
import com.lite.business.vo.article.ArticleVO;

/**
* <p>
    * Article 对象不同数据层转换器
    * </p>
*
* @author stranger
* @since 2022-09-01
*/

@Mapper(componentModel = "spring")
public interface ArticleConvert {

    /**
     * VO 转换成 DTO
     */
    @Mappings({})
    ArticleDTO voToDto(ArticleVO vo);

    /**
     * VO列表 转换成 DTO列表
     */
    @Mappings({})
    List<ArticleDTO> voListToDtoList(List<ArticleVO> voList);

    /**
     * VO 转换成 Entity
     */
    @Mappings({})
    Article voToEntity(ArticleVO vo);

    /**
     * VO列表 转换成 DTO列表
     */
    @Mappings({})
    List<Article> voListToEntityList(List<ArticleVO> voList);

    /**
     * DTO 转换成 VO
     */
    @Mappings({
            @Mapping(source = "updatedTime",target = "publishTime")
    })
    ArticleVO dtoToVo(ArticleDTO dto);

    @Mappings({
            @Mapping(source = "updatedTime",target = "publishTime")
    })
    ArticleSimpleVO simpleDtoToSimpleVo(ArticleSimpleDto simpleDto);

    /**
     * DTO列表 转换成 VO列表
     */
    @Mappings({
            @Mapping(source = "updatedTime",target = "publishTime")
    })
    List<ArticleVO> dtoListToVoList(List<ArticleDTO> dtoList);

    /**
     * DTO 转换成 Entity
     */
    @Mappings({})
    Article dtoToEntity(ArticleDTO dto);

    /**
     * DTO列表 转换成 Entity列表
     */
    @Mappings({})
    List<Article> dtoListToEntityList(List<ArticleDTO> dtoList);

    /**
     * Entity 转换成 DTO
     */
    @Mappings({})
    ArticleDTO entityToDto(Article entity);

    @Mappings({})
    ArticleSimpleDto entityToSimpleDto(Article entity);


    /**
     * Entity列表 转换成 DTO列表
     */
    @Mappings({})
    List<ArticleDTO> entityListToDtoList(List<Article> entityList);

    /**
     * Entity 转换成 VO
     */
    @Mappings({
            @Mapping(source = "updatedTime",target = "publishTime")
    })
    ArticleVO entityToVO(Article entity);


    @Mappings({
            @Mapping(source = "updatedTime",target = "publishTime")
    })
    ArticleSimpleVO entityToSimpleVO(Article entity);

    /**
     * Entity列表 转换成 DTO列表
     */
    @Mappings({
            @Mapping(source = "updatedTime",target = "publishTime")
    })
    List<ArticleVO> entityListToVoList(List<Article> entityList);

}
