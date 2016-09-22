package com.game.ota.service;

import java.util.List;

import com.game.modules.service.GenericManager;
import com.game.ota.entity.OtaDevice;

public interface OtaDeviceManager extends GenericManager<OtaDevice, Long> {

    public boolean delAll(List<Long> ids);

    /**
     * 根据deviceid获取设备
     */
    public OtaDevice getDeviceByDeviceid(String deviceid);
}
