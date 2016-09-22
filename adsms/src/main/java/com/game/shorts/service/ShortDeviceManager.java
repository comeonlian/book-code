package com.game.shorts.service;

import java.util.List;

import com.game.modules.service.GenericManager;
import com.game.shorts.entity.ShortDevice;

public interface ShortDeviceManager extends GenericManager<ShortDevice, Long> {

    public boolean delAll(List<Long> ids);

    /**
     * 根据deviceid获取设备
     */
    public ShortDevice getDeviceByDeviceid(String deviceid);

    /**
     * 根据androidid获取设备
     */
    public ShortDevice getDeviceByAndroidid(String androidid);
}
