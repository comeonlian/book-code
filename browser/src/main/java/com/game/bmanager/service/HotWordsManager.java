package com.game.bmanager.service;

import java.util.List;

import com.game.bmanager.entity.HotWords;
import com.game.modules.service.GenericManager;

public interface HotWordsManager extends GenericManager<HotWords, Long> {

    public boolean delAll(List<Long> ids);

    /**
     * 根据customid查找列表
     */
    public List<HotWords> getByCustomid(String customerid);
}
