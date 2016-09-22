package com.game.docking.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.game.comm.entity.City;
import com.game.docking.entity.VisitAdsms;
import com.game.docking.util.sina.JsonIPInfo;
import com.game.shorts.entity.ShortCustomer;
import com.game.shorts.entity.ShortDevice;
import com.game.shorts.entity.UpdatePkg;

/**
 * 通用服务接口
 */
public interface DockingGeneralManager {

    /**
     * 插入访问日志
     */
    public void insertLogVisit(VisitAdsms visit, Date mobileDate, String responseTxt);

    /**
     * 错误的访问日志
     */
    public void insertLogErrorNull(VisitAdsms visit);

    /**
     * 写产品回馈接口
     */
    public void insertLogProductBack(VisitAdsms visit);

    /**
     * 外单 封装更新结点
     */
    public Map<String, Object> convertUpdatePkg(UpdatePkg updatePkg);

    /**
     * product 结点
     */
    public Map<String, Object> getProduct(ShortDevice device, VisitAdsms visit, ShortCustomer customer, String currentdate);

    /**
     * 第三方结点
     */
    public Map<String, Object> padvVV(String customerid, String cityid, String provinceCode, int slot);

    /**
     * 获取第三方结点
     */
    public Map<String, Object> getPadv(ShortDevice device, VisitAdsms visit, ShortCustomer customer, String currentdate);

    /**
     * 关键字结点
     */
    public Map<String, Object> keyMap(String customers);

    /**
     * 根据访问日志获取 设备IMSI、cityid
     * 
     * @param visit
     * @return "cityid" 城市ID "imsilist" IMSI列表
     */
    public Map<String, Object> getDeviceImsiList(VisitAdsms visit);

    /**
     * 合作客户数据处理
     */
    public void dealPartnerDatas(VisitAdsms visit, String currentdate);

    /**
     * 设备信息
     */
    public ShortDevice getDevice(VisitAdsms visit);

    /**
     * 获取命令
     */
    public List<Map<String, Object>> getGlobalCmd(String devicePkgs);

    /**
     * 写域名访问日志
     */
    public void insertLogInitarea(VisitAdsms visit, String responseContext);

    /**
     * 写返回信息日志
     */
    public void insertLogMessages(VisitAdsms visit);

    /**
     * 写imsi访问日志
     */
    public void insertLogVisitImsi(VisitAdsms visit);

    /**
     * 写错误命令反馈日志
     */
    public void insertLogErrorCmd(VisitAdsms visit);

    /**
     * 写错误更新反馈日志
     */
    public void insertLogErrorUpdate(VisitAdsms visit);

    /**
     * 写上传包名日志
     */
    public void insertLogUploadpkg(VisitAdsms visit, ShortDevice device);

    /**
     * 写手机时间转换错误
     */
    public void insertLogErrorMobileTime(VisitAdsms visit);

    /**
     * 获取响应域名
     */
    public String getResponseUrl(String url);

    /**
     * 根据sina接口获取省市
     */
    public City getByJsonIPInfo(JsonIPInfo info);
}
