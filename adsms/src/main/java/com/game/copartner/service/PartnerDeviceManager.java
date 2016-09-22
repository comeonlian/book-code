package com.game.copartner.service;

import java.util.List;

import com.game.copartner.entity.PartnerDevice;
import com.game.modules.service.GenericManager;

public interface PartnerDeviceManager extends GenericManager<PartnerDevice, Long> {

    public boolean delAll(List<Long> ids);

    public PartnerDevice getByDeviceid(String deviceid);

    public boolean isMacExist(String mac);

    public boolean isImeiExist(String imei);

    public boolean isAndroididExist(String androidid);
}
