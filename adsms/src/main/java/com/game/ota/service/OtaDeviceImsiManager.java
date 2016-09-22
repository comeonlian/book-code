package com.game.ota.service;

import java.util.List;

import com.game.modules.service.GenericManager;
import com.game.ota.entity.OtaDeviceImsi;

public interface OtaDeviceImsiManager extends GenericManager<OtaDeviceImsi, Long> {

    public boolean delAll(List<Long> ids);

    /**
     * 根据imsi获取设备
     */
    public OtaDeviceImsi getDeviceByImsi(String imsi);
}
