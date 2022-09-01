package com.lite.business.vo.category;

    import lombok.Data;

/**
* <p>
    * CategoryVO 视图层对象
    * </p>
*
* @author stranger
* @since 2022-09-01
*/
@Data
public class CategoryVO {

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
}
