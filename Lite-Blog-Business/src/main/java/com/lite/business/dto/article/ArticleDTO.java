package com.lite.business.dto.article;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * <p>
 * ArticleDTO 传输层对象
 * </p>
 *
 * @author stranger
 * @since 2022-09-01
 */
@Data
public class ArticleDTO {

    /**
     * 对象ID
     */
    private Long id;


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

    /**
     * 是否发布
     */
    private Boolean published;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;
}
