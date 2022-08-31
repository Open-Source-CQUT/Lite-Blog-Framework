package com.lite.business.entity.comment;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lite.common.entity.BaseEntity;
import lombok.*;

/**
 * <p>
 * 评论信息表
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
@TableName("info_comment")
public class Comment extends BaseEntity {

    /**
     * 评论内容
     */
    private String content;


}
