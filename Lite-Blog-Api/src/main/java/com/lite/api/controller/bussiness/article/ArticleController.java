package com.lite.api.controller.bussiness.article;


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

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

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
    public ResultResponse<Boolean> publishArticle(@NotNull @RequestBody ArticleVO articleVO) {

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
    public ResultResponse<ArticleSimpleVO> createDraft(@NotNull @RequestBody ArticleVO articleVO) throws ArticleException {

        return ResultResponseUtils.success((articleService.createDraft(articleConvert.voToDto(articleVO))),"文章创建成功");
    }

    /**
     * 保存/更新 一篇草稿文章
     *
     * @param articleVO 草稿文章
     * @return 是否保存成功
     */
    @PostMapping("/saveDraft")
    public ResultResponse<ArticleSimpleVO> saveDraft(@NotNull @RequestBody ArticleVO articleVO) throws ArticleException {

        return ResultResponseUtils.success((articleService.saveDraft(articleConvert.voToDto(articleVO))),"文章保存成功");
    }


    /**
     * 根据页数获取文章列表,每一页10个
     *
     * @return 对应的博客文章列表
     */
    @GetMapping("/getArticleList")
    public ResultResponse<List<ArticleVO>> getArticleList(Integer page,Integer size) {

        return ResultResponseUtils.success(
                Objects.isNull(size) ? articleService.getArticleList(page) : articleService.getArticleList(page,size), "博客文章列表获取成功");
    }

}
