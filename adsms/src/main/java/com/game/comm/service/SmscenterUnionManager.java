package com.game.comm.service;

import com.game.comm.entity.SmscenterUnion;
import com.game.modules.service.GenericManager;

public interface SmscenterUnionManager extends GenericManager<SmscenterUnion, String> {

    public SmscenterUnion getEntityByCode(String areacode);
}
