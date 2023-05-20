package com.se.EC.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.se.EC.Entity.Favorites;
import org.apache.ibatis.annotations.Mapper;

// mapper 层，承接entity 如果要写复杂sql 需要使用此层和xml
// 简单sql mybaitsplus 封装很好，估计我们应该不用写xml
@Mapper
public interface FavoritesMapper extends BaseMapper<Favorites> {

}
