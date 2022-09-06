package com.lite.business.entity.article;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lite.common.entity.BaseEntity;
import com.lite.common.entity.Entity;
import lombok.*;

/**
 * <p>
 * 文章-用户关系表
 * </p>
 *
 * @author stranger
 * @since 2022-09-03
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@TableName("relation_article_user")
public class ArticleUser extends Entity {

    /**
     * 文章ID
     */
    private Long articleId;

    /**
     * 用户ID
     */
    private Long userId;


}
