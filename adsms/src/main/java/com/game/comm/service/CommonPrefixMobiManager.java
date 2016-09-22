package com.game.comm.service;

import com.game.comm.entity.CommonPrefixMobi;
import com.game.modules.service.GenericManager;

public interface CommonPrefixMobiManager extends GenericManager<CommonPrefixMobi, String> {

    public CommonPrefixMobi getEntityByCode(String prefixMobi);
}
