package com.lite.business.dto.share;

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
}
