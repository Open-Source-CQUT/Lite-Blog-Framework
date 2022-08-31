package com.lite.business.vo.label;

    import lombok.Data;

/**
* <p>
    * LabelVO 视图层对象
    * </p>
*
* @author stranger
* @since 2022-08-31
*/
    @Data
public class LabelVO {

            /**
            * 标签名
            */
    private String name;

            /**
            * 别名
            */
    private String alias;
}
