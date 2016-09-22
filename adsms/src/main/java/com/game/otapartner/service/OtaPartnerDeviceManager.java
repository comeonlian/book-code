package com.game.otapartner.service;

import com.game.modules.service.GenericManager;
import com.game.otapartner.entity.OtaPartnerDevice;

public interface OtaPartnerDeviceManager extends GenericManager<OtaPartnerDevice, Long> {

    public OtaPartnerDevice getByDeviceid(String deviceid);
}
