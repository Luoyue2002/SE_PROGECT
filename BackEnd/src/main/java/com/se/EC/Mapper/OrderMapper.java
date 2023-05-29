package com.se.EC.Mapper;

import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.se.EC.Entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends MppBaseMapper<Order> {

}
