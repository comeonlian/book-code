package com.game.browser.service;

import java.util.List;
import java.util.Map;

import com.game.modules.orm.Page;

/**
 * 数据分析通用服务接口
 *
 */
public interface UserAnalysisManager {
    
    /**
     * 新增活跃分析
     */
    public List<Map<String, Object>> newActiveAnalysisDetail(String virtu,String begindate,String enddate,String country,String customid);
    
    public Long newActiveAnalysisCount(String virtu,String date,String country,String customid);
    //总共新增
    public Long newActiveAnalysisTotal(String virtu,String begindate,String enddate,String country,String customid);
    //国内新增
    public Long newActiveAnalysisInTotal(String virtu,String begindate,String enddate,String country,String customid);
    //国外新增
    public Long newActiveAnalysisOutTotal(String virtu,String begindate,String enddate,String country,String customid);
    
    public List<Map<String, Object>> analysisGrahNew(String virtu,String begindate,String enddate,String country,String customid);
    
    public List<Map<String, Object>> analysisGrahActive(String virtu,String begindate,String enddate,String country,String customid);
    
    /**
     * 新增活跃分析(国内)
     */
    public Page<Map<String,Object>> newActiveAnalysisAreaDetail(Page<Map<String,Object>> page,String virtu,String begindate,String enddate,String area,String customid);
    
    public List<Map<String,Object>> areaInNewAjax(String virtu,String begindate,String enddate,String area,String customid);

    public List<Map<String,Object>> areaInActiveAjax(String virtu,String begindate,String enddate,String area,String customid);
    /**
     * 新增活跃分析(国外)
     */
    public Page<Map<String,Object>> newActiveAnalysisAreaOut(Page<Map<String,Object>> page,String virtu,String begindate,String enddate,String area,String customid);
    
    public List<Map<String,Object>> areaOutNewAjax(String virtu,String begindate,String enddate,String area,String customid);

    public List<Map<String,Object>> areaOutActiveAjax(String virtu,String begindate,String enddate,String area,String customid);
    
    /**
     * 查询 地区
     */
    public List<String> queryAllArea();
    
    
}
