package com.game.shorts.service;

import java.util.List;

import com.game.modules.service.GenericManager;
import com.game.shorts.entity.ShortDeviceImsi;

public interface ShortDeviceImsiManager extends GenericManager<ShortDeviceImsi, Long> {

    public boolean delAll(List<Long> ids);

    /**
     * 根据imsi获取设备
     */
    public ShortDeviceImsi getDeviceByImsi(String imsi);
}
