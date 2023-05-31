package com.se.EC.Controller.Comment;

import com.se.EC.Entity.Comment;
import com.se.EC.Entity.Item;
import com.se.EC.Pojo.CommentObject;
import com.se.EC.Service.Comment.CommentServiceInterface;
import com.se.EC.Service.CommentPicture.CommentPictureServiceInterface;
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
    @Resource
    private CommentPictureServiceInterface commentPictureServiceInterface;

    @Override
    @PostMapping("/addComment")
    public ApiResult<Boolean> addComment(@RequestBody CommentObject commentObject) {
        try {
            Integer userId = commentObject.getUserId();
            Integer itemId = commentObject.getItemId();
            String content = commentObject.getContent();
            Integer review = commentObject.getReview();
            checkUser(userId);
            checkItem(itemId);
            checkContent(content);
            checkReview(review);
            commentServiceInterface.addComment(commentObject);
            Integer commentId = commentServiceInterface.getCommentIdByUserItem(userId, itemId);
            for (var picture : commentObject.getPictureUrl()) {
                commentPictureServiceInterface.addPicture(commentId, picture);
            }
            return ApiResult.success();
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/deleteComment")
    public ApiResult<Boolean> deleteComment(@RequestParam(value = "id") Integer id) {
        try {
            commentServiceInterface.deleteComment(id);
            return ApiResult.success();
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/getCommentByItem")
    public ApiResult<List<CommentObject>> getCommentByItem(@RequestParam(value = "itemId") Integer itemId) {
        try {
            checkItem(itemId);
            List<Comment> commentList = commentServiceInterface.getCommentByItem(itemId);
            return ApiResult.success(sortComment(packComment(commentList)));
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/getCommentByCommodity")
    public ApiResult<List<CommentObject>> getCommentByCommodity(Integer commodityId) {
        try {
            List<Item> itemList = itemServiceInterface.getItemsByParentId(commodityId);
            List<CommentObject> commentList = new ArrayList<>();
            for (var item : itemList) {
                Integer itemId = item.getId();
                checkItem(itemId);
                commentList.addAll(packComment(commentServiceInterface.getCommentByItem(itemId)));
            }
            return ApiResult.success(sortComment(commentList));
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/getCommentByUser")
    public ApiResult<List<CommentObject>> getCommentByUser(@RequestParam(value = "userId") Integer userId) {
        try {
            checkUser(userId);
            List<Comment> commentList = commentServiceInterface.getCommentByUser(userId);
            return ApiResult.success(packComment(commentList));
        } catch (Exception e) {
            return ApiResult.error(e.getMessage());
        }
    }

    private List<CommentObject> packComment(List<Comment> commentList) {
        List<CommentObject> commentObjects = new ArrayList<>();
        for (var comment : commentList) {
            List<String> picturesUrl = commentPictureServiceInterface.getPictureById(comment.getItemId());
            CommentObject commentObject = new CommentObject(comment.getUserId(), comment.getItemId(),
                    comment.getContent(), comment.getTime(), comment.getReview(), picturesUrl);
            commentObjects.add(commentObject);
        }
        return commentObjects;
    }

    /**
     * 将消息按时间顺序排序
     * @param commentList 消息队列
     * @return 排序号的消息队列，最近的在最前面
     */
    private List<CommentObject> sortComment(List<CommentObject> commentList) {
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
