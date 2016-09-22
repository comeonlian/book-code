package com.game.comm.service;

import com.game.comm.entity.SmscenterCmcc;
import com.game.modules.service.GenericManager;

public interface SmscenterCmccManager extends GenericManager<SmscenterCmcc, String> {

    public SmscenterCmcc getEntityByCode(String areacode);
}
