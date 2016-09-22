package com.game.ota.service;

import java.util.List;

import com.game.modules.service.GenericManager;
import com.game.ota.entity.OtaCustomer;

public interface OtaCustomerManager extends GenericManager<OtaCustomer, Long> {

    public boolean delAll(List<Long> ids);

    /**
     * 根据customid查找客户
     */
    public OtaCustomer getByCustomerid(String customerid);

    /**
     * 校验客户id唯一性
     */
    public boolean isCustomidUnique(String customerid, String oldName);

    /**
     * 根据最少启用状态查找列表
     */
    public List<OtaCustomer> getAllByPassdevice(int passdevice);
}
