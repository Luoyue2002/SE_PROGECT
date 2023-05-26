package com.se.EC.Controller.Comment;

import com.se.EC.Entity.Comment;
import com.se.EC.Service.Comment.CommentServiceInterface;
import com.se.EC.Utils.ApiResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController implements CommentControllerInterface {
    @Resource
    private CommentServiceInterface commentServiceInterface;

    @PostMapping("/addComment")
    public ApiResult<Boolean> addComment(@RequestBody Comment comment) {
        try {
            commentServiceInterface.addComment(comment);
            return ApiResult.success();
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @RequestMapping("/deleteComment")
    public ApiResult<Boolean> deleteComment(@RequestParam(value = "id") Integer id) {
        try {
            commentServiceInterface.deleteComment(id);
            return ApiResult.success();
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @RequestMapping("/getCommentByItem")
    public ApiResult<List<Comment>> getCommentByItem(@RequestParam(value = "itemId") Integer itemId) {
        try {
            List<Comment> commentList = commentServiceInterface.getCommentByItem(itemId);
            return ApiResult.success(commentList);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @RequestMapping("/getCommentByUser")
    public ApiResult<List<Comment>> getCommentByUser(@RequestParam(value = "userId") Integer userId) {
        try {
            List<Comment> commentList = commentServiceInterface.getCommentByUser(userId);
            return ApiResult.success(commentList);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }
}
