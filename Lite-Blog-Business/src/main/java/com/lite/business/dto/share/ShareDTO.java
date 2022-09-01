package com.lite.business.dto.share;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * <p>
 * ShareDTO 传输层对象
 * </p>
 *
 * @author stranger
 * @since 2022-09-01
 */
@Data
public class ShareDTO {

    /**
     * 对象ID
     */
    private Long id;


    /**
     * 分享的内容
     */
    private String content;

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
