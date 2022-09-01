package com.lite.business.dto.comment;

import java.time.LocalDateTime;
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

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;
}
