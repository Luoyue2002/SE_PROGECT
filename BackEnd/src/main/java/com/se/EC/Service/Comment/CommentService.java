package com.se.EC.Service.Comment;

import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.Comment;
import com.se.EC.Mapper.CommentMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CommentService extends MppServiceImpl<CommentMapper, Comment> implements CommentServiceInterface {
    @Resource
    CommentMapper commentMapper;
}
