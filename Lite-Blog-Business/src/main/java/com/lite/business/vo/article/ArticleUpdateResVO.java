package com.lite.business.vo.article;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Stranger
 * @version 1.0
 * @description: 在进行创建一个空白草稿或者编辑保存草稿时会返回此对象
 *               只包含文章最基础的信息，不包含内容标题等信息,
 *               只是为了确认文章创建或更新成功
 * @date 2022/9/1 22:47
 */
@Data
@AllArgsConstructor
public class ArticleUpdateResVO {

    /**
     * 对象ID
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;


    /**
     * 更新版本
     */
    private Integer version;

    /**
     * 发布时间
     */
    private LocalDateTime updatedTime;
}
