package com.lite.business.entity.label;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lite.common.entity.BaseEntity;
import lombok.*;

/**
 * <p>
 * 标签信息表
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
@TableName("info_label")
public class Label extends BaseEntity {

    /**
     * 标签名
     */
    private String name;

    /**
     * 别名
     */
    private String alias;


}
