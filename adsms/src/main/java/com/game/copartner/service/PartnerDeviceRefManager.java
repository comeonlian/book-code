package com.game.copartner.service;

import java.util.List;

import com.game.copartner.entity.PartnerDeviceRef;
import com.game.modules.service.GenericManager;

public interface PartnerDeviceRefManager extends GenericManager<PartnerDeviceRef, Long> {

    public boolean delAll(List<Long> ids);

    public PartnerDeviceRef getByDeviceid(String deviceid);

    public boolean isMacExist(String mac);

    public boolean isImeiExist(String imei);

    public boolean isAndroididExist(String androidid);
}
