package com.se.EC.Mapper;

import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.se.EC.Entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends MppBaseMapper<User> {

}
