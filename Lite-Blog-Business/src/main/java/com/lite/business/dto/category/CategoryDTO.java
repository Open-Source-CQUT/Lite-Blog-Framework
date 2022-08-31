package com.lite.business.dto.category;

import lombok.Data;

/**
 * <p>
 * CategoryDTO 传输层对象
 * </p>
 *
 * @author stranger
 * @since 2022-08-31
 */
@Data
public class CategoryDTO {

    /**
     * 分类名称
     */
    private String name;

    /**
     * 别名
     */
    private String alias;
}
