package com.lite.business.dto.article;

import lombok.Data;

/**
 * <p>
 * ArticleDTO 传输层对象
 * </p>
 *
 * @author stranger
 * @since 2022-08-31
 */
@Data
public class ArticleDTO {

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章封面
     */
    private String cover;

    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 文章内容
     */
    private String content;
}
