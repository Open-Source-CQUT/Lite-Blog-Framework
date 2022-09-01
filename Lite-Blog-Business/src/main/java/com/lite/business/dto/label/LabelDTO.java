package com.lite.business.dto.label;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * <p>
 * LabelDTO 传输层对象
 * </p>
 *
 * @author stranger
 * @since 2022-09-01
 */
@Data
public class LabelDTO {

    /**
     * 对象ID
     */
    private Long id;


    /**
     * 标签名
     */
    private String name;

    /**
     * 别名
     */
    private String alias;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;
}
