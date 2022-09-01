package com.lite.business.vo.comment;

    import lombok.Data;

/**
* <p>
    * CommentVO 视图层对象
    * </p>
*
* @author stranger
* @since 2022-09-01
*/
    @Data
public class CommentVO {

    /**
     * 对象ID
     */
    private Long id;


    /**
     * 评论内容
     */
    private String content;
}
