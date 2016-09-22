package com.game.otapartner.service;

import com.game.modules.service.GenericManager;
import com.game.otapartner.entity.OtaFirstVisitDaily;

public interface OtaFirstVisitDailyManager extends GenericManager<OtaFirstVisitDaily, Long> {

    public boolean isDeviceExist(String currentdate, String deviceid);
}
