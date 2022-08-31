package com.lite.business.vo.category;

    import lombok.Data;

/**
* <p>
    * CategoryVO 视图层对象
    * </p>
*
* @author stranger
* @since 2022-08-31
*/
    @Data
public class CategoryVO {

            /**
            * 分类名称
            */
    private String name;

            /**
            * 别名
            */
    private String alias;
}
