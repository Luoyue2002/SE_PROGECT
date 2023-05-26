package com.se.EC.Service.Detail;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.se.EC.Entity.Detail;

import java.util.List;

public interface DetailServiceInterface extends IMppService<Detail> {
    List<Detail> getDetailsByParentId(Integer parentId);
}
