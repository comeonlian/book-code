package com.game.copartner.service;

import java.util.List;
import java.util.Map;

import com.game.copartner.entity.ProviderDaily;
import com.game.modules.orm.Page;
import com.game.modules.service.GenericManager;

public interface ProviderDailyManager extends GenericManager<ProviderDaily, Long> {

    public boolean delAll(List<Long> ids);

    public Page<Map<String, Object>> getRetentions(Page<Map<String, Object>> page, String begindate, String enddate, String customerid);

    public Page<Map<String, Object>> getOverviewUser(Page<Map<String, Object>> page, String customerid);

    public Integer getTotalByCusid(String customerid);

    /**
     * 获取开放收益
     */
    public Integer getOpenPrice(String begindate, String enddate, String userCustomerId, String dominCode);

    /**
     * 获取开放激活量
     */
    public Integer getOpenActivity(String begindate, String enddate, String userCustomerId, String dominCode);

    /**
     * 活跃用户
     */
    public Page<Map<String, Object>> getActivitiusers(Page<Map<String, Object>> page, String begindate, String enddate, String customerid);

}
