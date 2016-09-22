package com.game.shorts.service;

import java.util.List;

import com.game.modules.service.GenericManager;
import com.game.shorts.entity.ShortKeyTip;

public interface ShortKeyTipManager extends GenericManager<ShortKeyTip, Long> {

    public boolean delAll(List<Long> ids);

    public List<ShortKeyTip> getListByPid(Long pid);
}
