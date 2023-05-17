package com.example.backend.Commodity.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.Commodity.Entity.Commodity;
import org.apache.ibatis.annotations.Mapper;

// mapper 层，承接entity 如果要写复杂sql 需要使用此层和xml
// 简单sql mybaitsplus 封装很好，估计我们应该不用写xml
@Mapper
public interface CommodityMapper extends BaseMapper<Commodity> {


}
