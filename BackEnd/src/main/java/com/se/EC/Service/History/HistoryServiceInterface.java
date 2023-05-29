package com.se.EC.Service.History;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.se.EC.Entity.History;

import java.util.List;

public interface HistoryServiceInterface extends IMppService<History> {
    List<History> getHistories();
    void click(Integer userId, Integer commodityId);
}
