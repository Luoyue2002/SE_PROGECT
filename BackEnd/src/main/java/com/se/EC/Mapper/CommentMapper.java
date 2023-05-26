package com.se.EC.Mapper;

import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.se.EC.Entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends MppBaseMapper<Comment> {

}