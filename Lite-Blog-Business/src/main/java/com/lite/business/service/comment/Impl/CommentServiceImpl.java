package com.lite.business.service.comment.Impl;

import com.lite.business.entity.comment.Comment;
import com.lite.business.dao.comment.CommentMapper;
import com.lite.business.service.comment.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论信息表 服务实现类
 * </p>
 *
 * @author stranger
 * @since 2022-08-31
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
