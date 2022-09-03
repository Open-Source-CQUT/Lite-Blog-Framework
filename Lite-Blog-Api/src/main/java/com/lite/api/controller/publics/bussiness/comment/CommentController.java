package com.lite.api.controller.publics.bussiness.comment;


import com.lite.business.convert.comment.CommentConvert;
import com.lite.business.service.comment.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 评论信息表 前端控制器
 * </p>
 *
 * @author stranger
 * @since 2022-09-01
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
        @Autowired
        ICommentService commentService;

        @Autowired
        CommentConvert commentConvert;
}
