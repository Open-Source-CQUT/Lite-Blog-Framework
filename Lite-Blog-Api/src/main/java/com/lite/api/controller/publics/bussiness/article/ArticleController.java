package com.lite.api.controller.publics.bussiness.article;


import com.lite.business.convert.article.ArticleConvert;
import com.lite.business.exceptioin.ArticleException;
import com.lite.business.service.article.IArticleService;
import com.lite.business.vo.article.ArticleSimpleVO;
import com.lite.business.vo.article.ArticleVO;
import com.lite.common.dto.ResultResponse;
import com.lite.common.utils.ResultResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * <p>
 * 博客文章信息表 前端控制器
 * </p>
 *
 * @author stranger
 * @since 2022-08-31
 */
@RestController
@RequestMapping("/article")
@Validated
public class ArticleController {


    @Autowired
    IArticleService articleService;

    @Autowired
    ArticleConvert articleConvert;

    /**
     * 正式发布一篇文章
     *
     * @param articleVO 要发布的文章
     * @return 是否发布成功
     */
    @PostMapping("/publishArticle")
    public ResultResponse<Boolean> publishArticle(@RequestBody ArticleVO articleVO) {

        return articleService.publishArticle(articleConvert.voToDto(articleVO)) ?
                ResultResponseUtils.success(true, "文章发布成功") :
                ResultResponseUtils.error(false, "文章发布失败");

    }

    /**
     * 创建一份草稿，即未发布的文章
     *
     * @param articleVO 草稿文章
     * @return 是否创建成功
     */
    @PostMapping("/createDraft")
    public ResultResponse<ArticleSimpleVO> createDraft(@RequestBody ArticleVO articleVO) throws ArticleException {

        return ResultResponseUtils.success((articleService.createDraft(articleConvert.voToDto(articleVO))), "文章创建成功");
    }

    /**
     * 保存/更新 一篇草稿文章
     *
     * @param articleVO 草稿文章
     * @return 是否保存成功
     */
    @PostMapping("/saveDraft")
    public ResultResponse<ArticleSimpleVO> saveDraft(@RequestBody ArticleVO articleVO) throws ArticleException {

        return ResultResponseUtils.success((articleService.saveDraft(articleConvert.voToDto(articleVO))), "文章保存成功");
    }


    /**
     * 分页查询文章列表,
     *
     * @param statusId 可选参数 必须为正整数 : 文章的状态ID
     * @param page     必填参数 必须为正整数 : 页数
     * @param size     可选参数 必须为正整数 默认值10, 最大值100 : 一页的容量
     * @return 分页查询后的文章列表,列表中的文章信息只包括简单信息,不会带有文章内容
     */
    @GetMapping("/getArticleList")
    public ResultResponse<List<ArticleVO>> getArticleList(
            @Positive Long statusId,
            @NotNull @Positive Integer page,
            @Positive @Max(100) Integer size) {

        return ResultResponseUtils.success(articleService.getArticleList(statusId, page, size), "博客文章列表获取成功");
    }

    /**
     * 获取一篇文章的详细信息
     * @param id 文章id
     * @return 文章信息
     */
    @GetMapping("/getArticleDetails")
    public ResultResponse<ArticleVO> getArticleDetails(@NotNull @Positive @RequestParam Long id) {
        return ResultResponseUtils.success(articleService.getArticleDetail(id), "文章信息获取成功");
    }

}
