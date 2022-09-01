package com.lite.business.vo.share;

    import lombok.Data;

/**
* <p>
    * ShareVO 视图层对象
    * </p>
*
* @author stranger
* @since 2022-09-01
*/
@Data
public class ShareVO {

    /**
     * 对象ID
     */
    private Long id;


    /**
     * 分享的内容
     */
    private String content;
}
