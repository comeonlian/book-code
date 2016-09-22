package com.game.copartner.service;

import java.util.List;

import com.game.copartner.entity.FirstVisitDaily;
import com.game.modules.service.GenericManager;

public interface FirstVisitDailyManager extends GenericManager<FirstVisitDaily, Long> {

    public boolean delAll(List<Long> ids);

    public boolean isDeviceExist(String currentdate, String deviceid);
}
