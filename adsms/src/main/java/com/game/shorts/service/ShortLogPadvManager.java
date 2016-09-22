package com.game.shorts.service;

import com.game.modules.service.GenericManager;
import com.game.shorts.entity.ShortLogPadv;
import com.game.shorts.entity.ShortLogPadvIvr;

public interface ShortLogPadvManager extends GenericManager<ShortLogPadv, Long> {

    public ShortLogPadvIvr saveLogPadvIvr(ShortLogPadvIvr log);
}
