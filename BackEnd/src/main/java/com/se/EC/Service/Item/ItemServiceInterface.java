package com.se.EC.Service.Item;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.se.EC.Entity.Item;

import java.util.List;

public interface ItemServiceInterface extends IMppService<Item> {
    List<Item> getItemsByParentId(Integer parentId);
}
