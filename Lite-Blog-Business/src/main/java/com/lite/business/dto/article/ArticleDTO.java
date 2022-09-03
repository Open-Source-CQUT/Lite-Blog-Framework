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
     * 用户id
     */
    private Long uId;

    /**
     * 用户昵称
     */
    private String nickName;

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
     * 文章状态
     */
    private Long statusId;

    /**
     * 更新版本
     */
    private Integer version;
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;

}
