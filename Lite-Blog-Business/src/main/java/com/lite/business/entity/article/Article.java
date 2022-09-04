package com.lite.business.entity.article;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lite.business.entity.status.Status;
import com.lite.common.entity.BaseEntity;
import lombok.*;

/**
 * <p>
 * 博客文章信息表
 * </p>
 *
 * @author stranger
 * @since 2022-08-31
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@TableName("info_article")
public class Article extends BaseEntity {

    /**
     * 用户id 由于在
     */
    @TableField(exist = false)
    private Long userId;

    /**
     * 用户昵称
     */
    @TableField(exist = false)
    private String nickName;

    /**
     * 文章状态
     */
    private Long statusId;

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


    public Article(Article article) {
        this.title = article.getTitle();
        this.cover = article.getCover();
        this.summary = article.getSummary();
        this.content = article.content;
        this.statusId = Status.UN_PUBLISHED.val();
    }

    public Article(String title, String cover, String summary, String content) {
        this.title = title;
        this.cover = cover;
        this.summary = summary;
        this.content = content;
        this.statusId = Status.UN_PUBLISHED.val();
    }


    public void clone(Article article){
        this.title = article.getTitle();
        this.summary = article.getSummary();
        this.cover = article.getCover();
        this.content = article.getContent();
    }
}
