package com.lite.business.dto.label;

import lombok.Data;

/**
 * <p>
 * LabelDTO 传输层对象
 * </p>
 *
 * @author stranger
 * @since 2022-08-31
 */
@Data
public class LabelDTO {

    /**
     * 标签名
     */
    private String name;

    /**
     * 别名
     */
    private String alias;
}
