package com.game.browser.service;

import java.util.List;
import java.util.Map;

/**
 * 数据分析通用服务接口
 *
 */
public interface AnalysisGeneralManager {

    /**
     * 测试类
     */
    public String testDual();
    
    /**
     * 用户留存
     */
    public List<Map<String, Object>> userRetention(String virtu,String begindate,String enddate,String customerid);
    
    /**
     * 流失用户
     */
    public List<Map<String, Object>> lossUser(String virtu,String begindate,String enddate,String customerid);
    /**
     * 用户生命周期
     */
    public List<Map<String, Object>> userCycleBar(String virtu,String customerid);
    public List<Map<String, Object>> userCycleTable(String virtu,String customerid);
    
    /**
     * 用户回访
     */
    public List<Map<String,Object>> userBack(String virtu,String begindate,String enddate,String customerid);
    
}
