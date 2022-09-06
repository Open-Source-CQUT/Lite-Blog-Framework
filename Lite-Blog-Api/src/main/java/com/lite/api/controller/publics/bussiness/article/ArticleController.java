package com.lite.api.controller.publics.bussiness.article;


import com.lite.business.convert.article.ArticleConvert;
import com.lite.business.exceptioin.ArticleException;
import com.lite.business.service.article.IArticleService;
import com.lite.business.vo.article.ArticleQueryVO;
import com.lite.business.vo.article.ArticleUpdateResVO;
import com.lite.business.vo.article.ArticleVO;
import com.lite.common.dto.ResultResponse;
import com.lite.common.i18n.SystemMessages;
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
    public ResultResponse<Boolean> publishArticle(@RequestBody ArticleVO articleVO) throws ArticleException {
        return articleService.publishArticle(articleConvert.voToDto(articleVO)) ?
                ResultResponseUtils.success(true, SystemMessages.get("success.article.publish")) :
                ResultResponseUtils.error(false, SystemMessages.get("error.article.publish"));

    }

    /**
     * 创建一份草稿，即未发布的文章
     *
     * @param articleVO 草稿文章
     */
    @PostMapping("/createDraft")
    public ResultResponse<ArticleUpdateResVO> createDraft(@RequestBody ArticleVO articleVO) throws ArticleException {

        return ResultResponseUtils.success(
                (articleService.createDraft(articleConvert.voToDto(articleVO))), SystemMessages.get("success.article.create"));
    }

    /**
     * 更新一篇已发布的文章
     */
    @PostMapping("/updateArticle")
    public ResultResponse<ArticleUpdateResVO> updateArticle(@RequestBody ArticleVO articleVO) throws ArticleException {
        return ResultResponseUtils.success(
                articleService.updateArticle(articleConvert.voToDto(articleVO)), SystemMessages.get("success.article.update"));
    }

    /**
     * 更新 一篇草稿文章
     *
     * @param articleVO 草稿文章
     * @return 是否保存成功
     */
    @PostMapping("/saveDraft")
    public ResultResponse<ArticleUpdateResVO> saveDraft(@RequestBody ArticleVO articleVO) throws ArticleException {
        return ResultResponseUtils.success(
                (articleService.updateDraft(articleConvert.voToDto(articleVO))), SystemMessages.get("success.article.save"));
    }


    /**
     * 分页查询文章列表,
     *
     * @param statusId 可选参数 必须为正整数 : 文章的状态ID
     * @param page     必填参数 必须为正整数 : 页数
     * @param size     可选参数 必须为正整数 默认值10, 最大值100 : 一页的容量
     * @return 分页查询后的文章列表, 列表中的文章信息只包括简单信息, 不会带有文章内容
     */
    @GetMapping("/getArticleList")
    public ResultResponse<List<ArticleVO>> getArticleList(
            @Positive Long statusId,
            @NotNull @Positive Integer page,
            @Positive @Max(100) Integer size) {

        return ResultResponseUtils.success(
                articleService.getArticleList(statusId, page, size), SystemMessages.get("success.article.getList"));
    }

    /**
     * 获取一篇文章的详细信息
     *
     * @param id 文章id
     * @return 文章信息
     */
    @GetMapping("/getArticleDetails")
    public ResultResponse<ArticleQueryVO> getArticleDetails(@NotNull @Positive @RequestParam Long id) {
        return ResultResponseUtils.success(
                articleService.getArticleQueryInfo(id), SystemMessages.get("success.article.details"));
    }


    /**
     * 获取一篇文章的修改信息，在编辑文章的时候用
     *
     * @param id 文章ID
     * @return 文章的编辑信息
     */
    @GetMapping("/getArticleUpdateInfo")
    public ResultResponse<ArticleVO> getArticleUpdateInfo(@NotNull @Positive @RequestParam Long id) {
        return ResultResponseUtils.success(
                articleService.getArticleUpdateInfo(id), SystemMessages.get("success.article.details"));
    }

}
