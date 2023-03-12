package com.example.backend.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.Mapper.EntityMapper;
import com.example.backend.Service.IEntityService;
import com.example.backend.entity.Entity;
import com.example.backend.utils.ApiResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class EntityServiceImpl extends ServiceImpl<EntityMapper, Entity> implements IEntityService {
    //
    @Resource //依赖注入,必须要注入对应的mapper
    private EntityMapper entityMapper;


    @Override
    public ApiResult Demo( int id) {
        // 一个简单的mybaits plus 查询，不需要写xml
                QueryWrapper<Entity> EntityQueryWrapper = new QueryWrapper<>();
                EntityQueryWrapper.eq("entityid",id);
                List<Entity> entityArrayList = entityMapper.selectList(EntityQueryWrapper);
                return ApiResult.success(entityArrayList); // 返回前端的是json
    }
}
