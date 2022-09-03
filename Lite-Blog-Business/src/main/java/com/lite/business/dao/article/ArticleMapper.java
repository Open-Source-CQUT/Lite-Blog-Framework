package com.lite.business.dao.article;

import com.lite.business.entity.article.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lite.business.entity.status.Status;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 博客文章信息表 Mapper 接口
 * </p>
 *
 * @author stranger
 * @since 2022-08-31
 */
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 查询文章列表
     *
     * @param offset   偏移量 开启分页可用
     * @param count    查询数目 开启分页可用
     * @param page     是否开启分页功能
     * @param original 是否查询原始数据，原始数据包括逻辑删除的数据
     * @return 查询得到的文章列表
     */
    List<Article> selectList(@Param("statusId") Long statusId,
                             @Param("offset") int offset,
                             @Param("count") int count,
                             @Param("page") boolean page,
                             @Param("original") boolean original);

    /**
     * 查询原始文章
     *
     * @param offset 偏移量 开启分页可用
     * @param count  查询数目 开启分页可用
     * @param page   是否开启分页功能
     * @return 查询得到的原始文章
     */
    default List<Article> selectOriginalList(Long statusId, int offset, int count, boolean page) {
        return selectList(statusId, offset, count, page, true);
    }

    default List<Article> selectPage(Long statusId, int offset, int count) {
        return selectList(statusId, offset, count, true, false);
    }

    default List<Article> selectOriginalPage(Long statusId, int offset, int count) {
        return selectOriginalList(statusId, offset, count, true);
    }


    /**
     * 获取一个文章的所有信息
     * @param articleId 文章ID
     * @param statusId 状态ID
     * @param original 是否为原始数据
     * @return 一个文章的全部数据
     */
    Article getArticleById(@Param("articleId") Long articleId,
                           @Param("statusId") Long statusId,
                           @Param("original") boolean original);

    default Article getById(Long articleId, Long statusId) {
        return getArticleById(articleId, statusId, false);
    }

    default Article getById(Long articleId) {
        return getArticleById(articleId, null, false);
    }

    default Article getByIdOriginal(Long articleId, Long statusId) {
        return getArticleById(articleId, statusId, true);
    }

    default Article getByIdOriginal(Long articleId) {
        return getArticleById(articleId, null, true);
    }

}
