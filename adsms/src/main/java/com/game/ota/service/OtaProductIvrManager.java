package com.game.ota.service;

import com.game.modules.service.GenericManager;
import com.game.ota.entity.OtaProductIvr;

public interface OtaProductIvrManager extends GenericManager<OtaProductIvr, Long> {

    public OtaProductIvr getProduct(String customerid, String cityid, int providerCode, int deviceDays, String currentDate, String currentTime, int restValue);
}
