package com.se.EC.Controller.Comment;

import com.se.EC.Entity.Comment;
import com.se.EC.Utils.ApiResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CommentControllerInterface {
    /**
     * 添加评论
     * @param comment 见Entity.Comment类
     * @return 成功/失败
     */
    @PostMapping("/addComment")
    ApiResult<Boolean> addComment(@RequestBody Comment comment);

    /**
     * 删除评论
     * @param id 评价的id，使用getComment获得此id
     * @return 成功/失败
     */
    @RequestMapping("/deleteComment")
    ApiResult<Boolean> deleteComment(@RequestParam(value = "id") Integer id);

    /**
     * 根据商品获取评论
     * @param itemId 商品子分类的id
     * @return Comment组成的链表
     */
    @RequestMapping("/getCommentByItem")
    ApiResult<List<Comment>> getCommentByItem(@RequestParam(value = "itemId") Integer itemId);

    /**
     * 根据商品获取评论
     * @param commodityId 商品的id
     * @return Comment组成的链表
     */
    @RequestMapping("/getCommentByCommodity")
    ApiResult<List<Comment>> getCommentByCommodity(Integer commodityId);

    /**
     * 根据用户获取评论
     * @param userId 用户id
     * @return Comment组成的链表
     */
    @RequestMapping("/getCommentByUser")
    ApiResult<List<Comment>> getCommentByUser(@RequestParam(value = "userId") Integer userId);
}
