package com.se.EC.Service.Comment;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.se.EC.Entity.Comment;

import java.util.List;

public interface CommentServiceInterface extends IMppService<Comment> {
    /**
     * 添加评论
     * @param comment 见Entity.Comment类
     */
    void addComment(Comment comment);

    /**
     * 删除评论
     * @param id 评价的id，使用getComment获得此id
     */
    void deleteComment(Integer id);

    /**
     * 根据商品获取评论
     * @param itemId 商品子分类的id
     * @return Comment组成的链表
     */
    List<Comment> getCommentByItem(Integer itemId);

    /**
     * 根据用户获取评论
     * @param userId 用户id
     * @return Comment组成的链表
     */
    List<Comment> getCommentByUser(Integer userId);
}
