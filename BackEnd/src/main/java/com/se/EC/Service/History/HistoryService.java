package com.se.EC.Service.History;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.se.EC.Entity.History;
import com.se.EC.Mapper.HistoryMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoryService extends MppServiceImpl<HistoryMapper, History> implements HistoryServiceInterface {
    @Resource
    HistoryMapper historyMapper;

    @Override
    public List<History> getHistories() {
        return historyMapper.selectList(null);
    }

    @Override
    public void click(Integer userId, Integer commodityId) {
        QueryWrapper<History> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", userId);
        queryWrapper.eq("commodityId", commodityId);
        History history = historyMapper.selectOne(queryWrapper);
        if (history == null) {
            History userHistory = new History(userId, commodityId, 1, LocalDateTime.now());
            historyMapper.insert(userHistory);
        } else {
            History userHistory = new History(userId, commodityId, history.getNumber() + 1, LocalDateTime.now());
            historyMapper.update(userHistory, queryWrapper);
        }
    }
}
