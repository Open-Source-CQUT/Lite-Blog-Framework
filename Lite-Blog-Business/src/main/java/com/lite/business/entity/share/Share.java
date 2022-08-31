package com.lite.business.entity.share;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lite.common.entity.BaseEntity;
import lombok.*;

/**
 * <p>
 * 动态信息表
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
@TableName("info_share")
public class Share extends BaseEntity {

    /**
     * 分享的内容
     */
    private String content;


}
