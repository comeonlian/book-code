package com.game.shorts.service;

import java.util.List;

import com.game.modules.service.GenericManager;
import com.game.shorts.entity.ShortShell;

public interface ShortShellManager extends GenericManager<ShortShell, Long> {

    public List<ShortShell> getListByStatus(int status);
}
