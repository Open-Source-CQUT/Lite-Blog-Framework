package com.lite.business.vo.article;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Stranger
 * @version 1.0
 * @description: QueryVo与Vo的区别在于，此QueryVo在呈现给前端时不会携带文章的version
 *               没有version的话就无法修改文章
 * @date 2022/9/4 16:45
 */
@Data
public class ArticleQueryVO {

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
     * 文章状态
     */
    private Long statusId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;
}
