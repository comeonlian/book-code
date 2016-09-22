package com.game.shorts.service;

import java.util.List;

import com.game.modules.service.GenericManager;
import com.game.shorts.entity.UpdatePkg;

public interface UpdatePkgManager extends GenericManager<UpdatePkg, Long> {

    public boolean delAll(List<Long> ids);

    public UpdatePkg getEntityByCustomid(String customerid);
}
