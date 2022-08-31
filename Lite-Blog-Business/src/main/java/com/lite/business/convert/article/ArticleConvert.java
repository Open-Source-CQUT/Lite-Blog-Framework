package com.lite.business.convert.article;


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
* @since 2022-08-31
*/

@Mapper(componentModel = "spring")
public interface ArticleConvert {

    @Mappings({})
    ArticleDTO voToDto(ArticleVO vo);

    @Mappings({})
    ArticleVO dtoToVo(ArticleDTO dto);

    @Mappings({})
    Article dtoToEntity(ArticleDTO dto);

    @Mappings({})
    ArticleDTO entityToDto(Article entity);
}
