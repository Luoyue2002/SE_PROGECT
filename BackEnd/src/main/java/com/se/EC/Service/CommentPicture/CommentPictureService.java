package com.se.EC.Service.CommentPicture;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.CommentPicture;
import com.se.EC.Mapper.CommentPictureMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentPictureService extends MppServiceImpl<CommentPictureMapper, CommentPicture> implements CommentPictureServiceInterface {
    @Resource
    CommentPictureMapper commentPictureMapper;

    @Override
    public void addPicture(Integer commentId, String pictureUrl) {
        CommentPicture commentPicture = new CommentPicture(commentId, pictureUrl);
        commentPictureMapper.insert(commentPicture);
    }

    @Override
    public List<String> getPictureById(Integer commentId) {
        QueryWrapper<CommentPicture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("commentId", commentId);
        List<CommentPicture> commentPictures = commentPictureMapper.selectList(queryWrapper);
        List<String> pictures = new ArrayList<>();
        for (var item : commentPictures) {
            pictures.add(item.getImage());
        }
        return pictures;
    }
}
