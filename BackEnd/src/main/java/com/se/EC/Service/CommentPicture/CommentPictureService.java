package com.se.EC.Service.CommentPicture;

import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.CommentPicture;
import com.se.EC.Mapper.CommentPictureMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CommentPictureService extends MppServiceImpl<CommentPictureMapper, CommentPicture> implements CommentPictureServiceInterface {
    @Resource
    CommentPictureMapper commentPictureMapper;
}
