package com.lite.business.dto.article;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Stranger
 * @version 1.0
 * @description: TODO
 * @date 2022/9/1 22:46
 */
@Data
public class ArticleSimpleDto {

    /**
     * 对象ID
     */
    private Long id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章状态
     */
    private Long statusId;

    /**
     * 用户id
     */
    private Long uId;

    /**
     * 用户昵称
     */
    private String nickName;

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
