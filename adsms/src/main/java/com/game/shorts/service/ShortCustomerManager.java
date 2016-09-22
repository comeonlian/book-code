package com.game.shorts.service;

import java.util.List;

import com.game.modules.service.GenericManager;
import com.game.shorts.entity.ShortCustomer;

public interface ShortCustomerManager extends GenericManager<ShortCustomer, Long> {

    public boolean delAll(List<Long> ids);

    /**
     * 根据customid查找客户
     */
    public ShortCustomer getByCustomerid(String customerid);

    /**
     * 校验客户id唯一性
     * 
     * @param customid
     * @param oldName
     * @return
     */
    public boolean isCustomidUnique(String customerid, String oldName);
    

    /**
     * 根据最少启用状态查找列表
     */
    public List<ShortCustomer> getAllByPassdevice(int passdevice);
}
