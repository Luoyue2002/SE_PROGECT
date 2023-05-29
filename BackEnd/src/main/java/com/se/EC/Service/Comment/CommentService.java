package com.se.EC.Service.Comment;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.Comment;
import com.se.EC.Mapper.CommentMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService extends MppServiceImpl<CommentMapper, Comment> implements CommentServiceInterface {
    @Resource
    CommentMapper commentMapper;

    @Override
    public void addComment(Comment comment) {
        // 查看评论是否已经存在
        Integer userId = comment.getUserId();
        Integer itemId = comment.getItemId();
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", userId);
        queryWrapper.eq("itemId", itemId);
        Long count = commentMapper.selectCount(queryWrapper);
        if (count != 0) {
            throw new RuntimeException("You have already add a comment to the commodity");
        }

        commentMapper.insert(comment);
    }

    @Override
    public void deleteComment(Integer id) {
        // 查看评论是否已经存在
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Long count = commentMapper.selectCount(queryWrapper);
        if (count == 0) {
            throw new RuntimeException("Comment not exist");
        }

        commentMapper.delete(queryWrapper);
    }

    @Override
    public List<Comment> getCommentByItem(Integer itemId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("itemId", itemId);
        return commentMapper.selectList(queryWrapper);
    }

    @Override
    public List<Comment> getCommentByUser(Integer userId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", userId);
        return commentMapper.selectList(queryWrapper);
    }
}
