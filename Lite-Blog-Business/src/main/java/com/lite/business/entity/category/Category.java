package com.lite.business.entity.category;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lite.common.entity.BaseEntity;
import lombok.*;

/**
 * <p>
 * 文章分类信息表
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
@TableName("info_category")
public class Category extends BaseEntity {

    /**
     * 分类名称
     */
    private String name;

    /**
     * 别名
     */
    private String alias;


}
