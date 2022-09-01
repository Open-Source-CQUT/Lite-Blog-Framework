package com.lite.business.vo.article;

    import lombok.Data;

    import java.time.LocalDateTime;

/**
* <p>
    * ArticleVO 视图层对象
    * </p>
*
* @author stranger
* @since 2022-09-01
*/
@Data
public class ArticleVO {

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
     * 更新版本
     */
    private Integer version;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;
}
