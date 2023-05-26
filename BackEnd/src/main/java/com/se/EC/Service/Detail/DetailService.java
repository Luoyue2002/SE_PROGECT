package com.se.EC.Service.Detail;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.Detail;
import com.se.EC.Mapper.DetailMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailService extends MppServiceImpl<DetailMapper, Detail> implements DetailServiceInterface {
    @Resource
    DetailMapper detailMapper;

    @Override
    public List<Detail> getDetailsByParentId(Integer parentId) {
        QueryWrapper<Detail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("commodityId", parentId);
        return detailMapper.selectList(queryWrapper);
    }
}
