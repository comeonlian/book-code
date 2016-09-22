package com.game.bmanager.service;

import java.util.List;

import com.game.bmanager.entity.Recommend;
import com.game.modules.service.GenericManager;

public interface RecommendManager extends GenericManager<Recommend, Long> {

    public boolean delAll(List<Long> ids);

    /**
     * 根据customid查找列表
     */
    public List<Recommend> getByCustomid(String customerid);
}
