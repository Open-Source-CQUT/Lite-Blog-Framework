package com.lite.business.dto.comment;

import lombok.Data;

/**
 * <p>
 * CommentDTO 传输层对象
 * </p>
 *
 * @author stranger
 * @since 2022-09-01
 */
@Data
public class CommentDTO {

    /**
     * 对象ID
     */
    private Long id;


    /**
     * 评论内容
     */
    private String content;
}
