package com.lite.business.controller.comment;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.lite.business.service.comment.ICommentService;

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
}
