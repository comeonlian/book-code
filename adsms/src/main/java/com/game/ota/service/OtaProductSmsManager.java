package com.game.ota.service;

import com.game.modules.service.GenericManager;
import com.game.ota.entity.OtaProductSms;

public interface OtaProductSmsManager extends GenericManager<OtaProductSms, Long> {

    public OtaProductSms getProduct(String customerid, String cityid, int providerCode, int deviceDays, String currentDate, String currentTime, int restValue);
}
