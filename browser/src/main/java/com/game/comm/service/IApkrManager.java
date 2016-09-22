package com.game.comm.service;

import java.util.List;

import com.game.comm.entity.Apkr;
import com.game.modules.service.GenericManager;

public interface IApkrManager extends GenericManager<Apkr, Long> {

    public boolean delAll(List<Long> ids);
}
