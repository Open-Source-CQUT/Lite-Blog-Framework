package com.lite.business.service.comment.Impl;

import com.lite.business.entity.comment.Comment;
import com.lite.business.dao.comment.CommentMapper;
import com.lite.business.service.comment.ICommentService;
import com.lite.business.convert.comment.CommentConvert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 评论信息表 服务实现类
 * </p>
 *
 * @author stranger
 * @since 2022-09-01
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {


        @Autowired
        CommentMapper commentMapper;

        @Autowired
        CommentConvert commentConvert;
}
