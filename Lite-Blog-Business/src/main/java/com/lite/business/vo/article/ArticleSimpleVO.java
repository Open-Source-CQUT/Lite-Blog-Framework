package com.lite.business.vo.article;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Stranger
 * @version 1.0
 * @description: TODO
 * @date 2022/9/1 22:47
 */
@Data
public class ArticleSimpleVO {

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
     * 更新版本
     */
    private Integer version;
    /**
     * 文章状态
     */
    private Long statusId;


    /**
     * 发布时间
     */
    private LocalDateTime publishTime;
}
