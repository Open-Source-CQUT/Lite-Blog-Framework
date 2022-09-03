package com.lite.business.vo.article;

import com.lite.common.groups.NormalGroups;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * <p>
 * ArticleVO 视图层对象
 * </p>
 *
 * @author stranger
 * @since 2022-09-01
 */
@Data
public class ArticleVO {

    /**
     * 对象ID
     */
    private Long id;


    /**
     * 文章标题
     */
    @NotBlank(groups = {NormalGroups.Crud.Insert.class,NormalGroups.Crud.Update.class})
    private String title;

    /**
     * 文章封面
     */
    private String cover;

    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 文章内容
     */
    @NotBlank(groups = NormalGroups.Crud.Update.class)
    private String content;

    /**
     * 文章状态
     */
    private Long statusId;

    /**
     * 用户id
     */
    private Long uId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 更新版本
     */
    private Integer version;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;
}
