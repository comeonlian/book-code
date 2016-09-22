package com.game.docking.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.game.comm.entity.City;
import com.game.docking.entity.VisitAdsms;
import com.game.util.ip.sina.JsonIPInfo;

/**
 * 通用服务接口
 */
public interface DockingGeneralManager {

    public String getDownUrl(long id, int jumpType);
    
    /**
     * 插入访问日志
     */
    public void insertLogVisit(VisitAdsms visit);


    /**
     * 合作客户数据处理
     */
    public void dealPartnerDatas(VisitAdsms visit, String currentdate);

    /**
     * 写手机时间转换错误
     */
    public void insertLogErrorMobileTime(VisitAdsms visit);

    /**
     * 写空访问日志
     */
    public void insertLogErrorNull(VisitAdsms visit);
    
    /**
     * 根据sina接口获取省市
     */
    public City getByJsonIPInfo(JsonIPInfo info);
}
