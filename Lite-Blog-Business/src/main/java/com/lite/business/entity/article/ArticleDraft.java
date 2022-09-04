package com.lite.business.entity.article;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lite.common.entity.BaseEntity;
import lombok.*;

/**
 * @author Stranger
 * @version 1.0
 * @description: 用于描述文章草稿关系的关系表
 * @date 2022/9/4 19:09
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@TableName("relation_article_draft")
public class ArticleDraft extends BaseEntity {

    private Long articleId;

    private Long draftId;

}
