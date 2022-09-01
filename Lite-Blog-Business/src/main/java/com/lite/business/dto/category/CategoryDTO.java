package com.lite.business.dto.category;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * <p>
 * CategoryDTO 传输层对象
 * </p>
 *
 * @author stranger
 * @since 2022-09-01
 */
@Data
public class CategoryDTO {

    /**
     * 对象ID
     */
    private Long id;


    /**
     * 分类名称
     */
    private String name;

    /**
     * 别名
     */
    private String alias;

    /**
     * 更新版本
     */
    private Integer version;
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;
}
