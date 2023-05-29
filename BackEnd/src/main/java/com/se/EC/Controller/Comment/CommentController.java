package com.se.EC.Controller.Comment;

import com.se.EC.Entity.Comment;
import com.se.EC.Entity.Item;
import com.se.EC.Service.Comment.CommentServiceInterface;
import com.se.EC.Service.Item.ItemServiceInterface;
import com.se.EC.Service.User.UserServiceInterface;
import com.se.EC.Utils.ApiResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController implements CommentControllerInterface {
    @Resource
    private CommentServiceInterface commentServiceInterface;
    @Resource
    private UserServiceInterface userServiceInterface;
    @Resource
    private ItemServiceInterface itemServiceInterface;

    @PostMapping("/addComment")
    public ApiResult<Boolean> addComment(@RequestBody Comment comment) {
        try {
            Integer userId = comment.getUserId();
            Integer itemId = comment.getItemId();
            String content = comment.getContent();
            Integer review = comment.getReview();
            checkUser(userId);
            checkItem(itemId);
            checkContent(content);
            checkReview(review);

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
            checkItem(itemId);
            List<Comment> commentList = commentServiceInterface.getCommentByItem(itemId);
            return ApiResult.success(sortComment(commentList));
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @RequestMapping("/getCommentByCommodity")
    public ApiResult<List<Comment>> getCommentByCommodity(Integer commodityId) {
        try {
            List<Item> itemList = itemServiceInterface.getItemsByParentId(commodityId);
            List<Comment> commentList = new ArrayList<>();
            for (var item : itemList) {
                Integer itemId = item.getId();
                checkItem(itemId);
                commentList.addAll(commentServiceInterface.getCommentByItem(itemId));
            }
            return ApiResult.success(sortComment(commentList));
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @RequestMapping("/getCommentByUser")
    public ApiResult<List<Comment>> getCommentByUser(@RequestParam(value = "userId") Integer userId) {
        try {
            checkUser(userId);
            List<Comment> commentList = commentServiceInterface.getCommentByUser(userId);
            return ApiResult.success(commentList);
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    /**
     * 将消息按时间顺序排序
     * @param commentList 消息队列
     * @return 排序号的消息队列，最近的在最前面
     */
    private List<Comment> sortComment(List<Comment> commentList) {
        commentList.sort((o1, o2) -> {
            if (o1.getTime().isAfter(o2.getTime())) {
                return -1;
            } else if (o1.getTime().equals(o2.getTime())) {
                return 0;
            } else {
                return 1;
            }
        });
        return commentList;
    }

    /**
     * 检查用户是否存在
     * @param userId 用户 id
     */
    private void checkUser(Integer userId) {
        if (!userServiceInterface.ifUserExists(userId)) {
            throw new RuntimeException("User " + userId + " does not exist");
        }
    }

    /**
     * 检查商品细分类是否存在
     * @param itemId 商品细分类id
     */
    private void checkItem(Integer itemId) {
        if (!itemServiceInterface.ifItemExists(itemId)) {
            throw new RuntimeException("Item " + itemId + " does not exist");
        }
    }

    /**
     * 检查消息长度是否合法
     * @param content 消息内容
     */
    private void checkContent(String content) {
        if (content.length() > 128) {
            throw new RuntimeException("Content is too long");
        }
    }

    /**
     * 检查评分是否合法
     * @param review 评级
     */
    private void checkReview(Integer review) {
        if (review < 1 || review > 5) {
            throw new RuntimeException("Review should between 1 and 5");
        }
    }
}
