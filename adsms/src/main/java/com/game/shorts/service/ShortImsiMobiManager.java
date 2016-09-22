package com.game.shorts.service;

import com.game.modules.service.GenericManager;
import com.game.shorts.entity.ShortImsiMobi;

public interface ShortImsiMobiManager extends GenericManager<ShortImsiMobi, String> {

    public ShortImsiMobi getEntityByImsi(String imsi);
}
