package com.game.shorts.service;

import com.game.modules.service.GenericManager;
import com.game.shorts.entity.ShortProductSms;

public interface ShortProductSmsManager extends GenericManager<ShortProductSms, Long> {

    public ShortProductSms getProduct(String customerid, String cityid, int providerCode, int deviceDays, String currentDate, String currentTime, int restValue);
}
