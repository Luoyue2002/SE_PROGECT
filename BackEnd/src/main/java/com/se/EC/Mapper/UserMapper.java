package com.se.EC.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.se.EC.Entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
