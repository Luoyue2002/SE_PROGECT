package com.se.EC.Service.CommentPicture;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.se.EC.Entity.CommentPicture;

import java.util.List;

public interface CommentPictureServiceInterface extends IMppService<CommentPicture> {
    void addPicture(Integer commentId, String pictureUrl);
    List<String> getPictureById(Integer commentId);
}
