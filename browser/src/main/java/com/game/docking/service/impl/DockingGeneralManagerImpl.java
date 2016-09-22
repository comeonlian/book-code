package com.game.docking.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.bmanager.entity.HotWords;
import com.game.bmanager.entity.Recommend;
import com.game.bmanager.service.HotWordsManager;
import com.game.bmanager.service.RecommendManager;
import com.game.bwlog.entity.LogAccess;
import com.game.bwlog.service.LogAccessManager;
import com.game.comm.entity.City;
import com.game.comm.entity.LogCityUndefine;
import com.game.comm.service.CityManager;
import com.game.comm.service.LogCityUndefineManager;
import com.game.copartner.entity.FirstVisitDaily;
import com.game.copartner.entity.PartnerDevice;
import com.game.copartner.entity.PartnerDeviceRef;
import com.game.copartner.service.FirstVisitDailyManager;
import com.game.copartner.service.PartnerDeviceManager;
import com.game.copartner.service.PartnerDeviceRefManager;
import com.game.docking.entity.VisitAdsms;
import com.game.docking.service.DockingGeneralManager;
import com.game.services.common.DicManager;
import com.game.util.DateUtils;
import com.game.util.ip.sina.JsonIPInfo;

@Service("dockingGeneralManager")
public class DockingGeneralManagerImpl implements DockingGeneralManager {
    public static final Logger LOGGER = LoggerFactory.getLogger(DockingGeneralManagerImpl.class);
    private static final String COUNTRY_CHINA = "中国";
    private static final int UNDEFILE_LEVEL_CITY = 1;
    private static final int UNDEFILE_LEVEL_PROVINCE = 2;
    private static final int UNDEFILE_LEVEL_COUNTRY = 3;

    @Autowired
    private CityManager cityManager;
    @Autowired
    private DicManager dicManager;
    @Autowired
    private PartnerDeviceManager partnerDeviceManager;
    @Autowired
    private FirstVisitDailyManager firstVisitDailyManager;
    @Autowired
    private PartnerDeviceRefManager partnerDeviceRefManager;
    @Autowired
    private LogCityUndefineManager logCityUndefineManager;
    @Autowired
    private RecommendManager recommendManager;
    @Autowired
    private HotWordsManager hotWordsManager;
    @Autowired
    private LogAccessManager logAccessManager;

    @Override
    public void dealPartnerDatas(VisitAdsms visit, String currentdate) {
        int isDevice = 1;
        boolean addDevice = false;
        String deviceid = visit.getDeviceid();
        // ******** 开放设备处理
        PartnerDevice partnerDevice = partnerDeviceManager.getByDeviceid(deviceid);
        if (partnerDevice == null) {
            if (partnerDeviceManager.isMacExist(visit.getMac()) || partnerDeviceManager.isImeiExist(visit.getImei()) || partnerDeviceManager.isAndroididExist(visit.getAndroidid())) {
                isDevice = 0;
            } else {
                // 写设备入库
                partnerDevice = createPartnerDevice(visit);
                addDevice = true;
            }
        }

        // ******* 开放设备对照表处理
        PartnerDeviceRef deviceRef = getPartnerDeviceRef(visit, deviceid);

        if (!firstVisitDailyManager.isDeviceExist(currentdate, deviceid)) {
            // 创建每日首次访问信息
            createFirstVisitDaily(visit, currentdate, isDevice);
            // 合作设备留存处理
            dealPartnerDeviceRetention(visit, addDevice, partnerDevice);
            // 合作设备参照表留存处理
            dealPartnerDeviceRefRetention(visit, deviceRef);
        }

    }

    /**
     * 开放设备对照留存率计算
     */
    private void dealPartnerDeviceRefRetention(VisitAdsms visit, PartnerDeviceRef partnerDeviceRef) {
        if (partnerDeviceRef != null) {
            boolean saveDevice = true;
            int diffDay = 0;
            diffDay = DateUtils.daysBetween(partnerDeviceRef.getAccesstime(), visit.getAccesstime(), "yyyy-MM-dd");
            if (diffDay == 1) { // 次日留存
                partnerDeviceRef.setNextretention(1);
            } else if (diffDay == 2) { // 三日留存
                partnerDeviceRef.setThirdretention(1);
            } else if (diffDay == 6) { // 七日留存
                partnerDeviceRef.setSevenretention(1);
            } else {
                saveDevice = false;
            }
            if (saveDevice) {
                partnerDeviceRefManager.save(partnerDeviceRef);
            }
        }
    }

    /**
     * 开放设备留存率计算
     */
    private void dealPartnerDeviceRetention(VisitAdsms visit, boolean addDevice, PartnerDevice partnerDevice) {
        if (partnerDevice != null && !addDevice) {
            boolean saveDevice = true;
            int diffDay = 0;
            diffDay = DateUtils.daysBetween(partnerDevice.getAccesstime(), visit.getAccesstime(), "yyyy-MM-dd");
            if (diffDay == 1) { // 次日留存
                partnerDevice.setNextretention(1);
            } else if (diffDay == 2) { // 三日留存
                partnerDevice.setThirdretention(1);
            } else if (diffDay == 6) { // 七日留存
                partnerDevice.setSevenretention(1);
            } else {
                saveDevice = false;
            }
            if (saveDevice) {
                partnerDeviceManager.save(partnerDevice);
            }
        }
    }

    /**
     * 获取合作数据对照信息
     */
    private PartnerDeviceRef getPartnerDeviceRef(VisitAdsms visit, String deviceid) {
        PartnerDeviceRef deviceRef = partnerDeviceRefManager.getByDeviceid(deviceid);
        if (deviceRef == null) {
            deviceRef = new PartnerDeviceRef(visit.getAccesstime(), visit.getCountryId(), visit.getCountryName(), visit.getCustomerid(), visit.getOsVersionCode(),
                    visit.getOsVersionName(), visit.getImei(), visit.getMac(), visit.getAndroidid(), visit.getLangCountry(), visit.getInline(), visit.getCpu(),
                    visit.getInitPackageName(), visit.getAppVersionCode(), visit.getSrc(), visit.getBrand(), visit.getModle(), visit.getSign(), visit.getDeviceid(), 0, 0, 0);
            partnerDeviceRefManager.save(deviceRef);
        }
        return deviceRef;
    }

    /**
     * 创建每日首次访问信息
     */
    private void createFirstVisitDaily(VisitAdsms visit, String currentdate, int isDevice) {
        FirstVisitDaily firstVisitDaily = new FirstVisitDaily(currentdate, visit.getAccesstime(), visit.getCustomerid(), visit.getOsVersionCode(), visit.getOsVersionName(),
                visit.getInline(), visit.getCpu(), visit.getInitPackageName(), visit.getAppVersionCode(), visit.getSrc(), visit.getBrand(), visit.getModle(), visit.getSign(),
                visit.getLangCountry(), visit.getInitVersionName(), visit.getNetType(), visit.getWifi(), visit.getCountryId(), visit.getCountryName(), visit.getDeviceid(),
                visit.getImei(), visit.getMac(), visit.getAndroidid(), visit.getImsi(), isDevice, visit.getIp());
        firstVisitDailyManager.save(firstVisitDaily);
    }

    /**
     * 创建合作设备
     */
    private PartnerDevice createPartnerDevice(VisitAdsms visit) {
        PartnerDevice partnerDevice;
        partnerDevice = new PartnerDevice(visit.getAccesstime(), visit.getCountryId(), visit.getCountryName(), visit.getCustomerid(), visit.getOsVersionCode(),
                visit.getOsVersionName(), visit.getImei(), visit.getMac(), visit.getAndroidid(), visit.getLangCountry(), visit.getInline(), visit.getCpu(),
                visit.getInitPackageName(), visit.getAppVersionCode(), visit.getSrc(), visit.getBrand(), visit.getModle(), visit.getSign(), visit.getDeviceid(), 0, 0, 0);
        partnerDeviceManager.save(partnerDevice);
        return partnerDevice;
    }

    @Override
    public City getByJsonIPInfo(JsonIPInfo info) {
        City result = null;
        String country = info.getCountry();
        String province = info.getProvince();
        String city = info.getCity();
        if (COUNTRY_CHINA.equals(country)) {
            if (StringUtils.isNotBlank(city)) {
                result = cityManager.getByName(city);
                if (result != null) {
                    return result;
                }
                // 记录未在省市表日志
                insertLogCityUndefine(city, province, country, UNDEFILE_LEVEL_CITY);
            }
            if (StringUtils.isNotBlank(province)) {
                result = cityManager.getByName(province);
                if (result == null) {
                    // 记录未在省市表日志
                    insertLogCityUndefine(city, province, country, UNDEFILE_LEVEL_PROVINCE);
                }
            }
        } else {
            if (StringUtils.isNotBlank(country)) {
                result = cityManager.getByName(country);
                if (result == null) {
                    // 记录未在省市表日志
                    insertLogCityUndefine(city, province, country, UNDEFILE_LEVEL_COUNTRY);
                }
            }
        }
        return result;
    }

    /**
     * 写省市未定义日志
     */
    private void insertLogCityUndefine(String city, String province, String country, Integer level) {
        LogCityUndefine log = new LogCityUndefine(city, province, country, level);
        logCityUndefineManager.save(log);
    }

    @Override
    public void insertLogVisit(VisitAdsms visit) {
        LogAccess log = new LogAccess(visit.getAccesstime(), visit.getDeviceid(), visit.getIp(), visit.getRunlevel(), visit.getCountryId(), visit.getCountryName(),
                visit.getMac(), visit.getImei(), visit.getImsi(), visit.getAndroidid(), visit.getCustomerid(), visit.getOsVersionCode(), visit.getOsVersionName(),
                visit.getInline(), visit.getLangCountry(), visit.getNetType(), visit.getCpu(), visit.getRomLess(), visit.getSdcardLess(), visit.getRomTotal(),
                visit.getSdcardTotal(), visit.getAppVersionCode(), visit.getAppVersionName(), visit.getInitVersionName(), visit.getInitPackageName(), visit.getWifi(),
                visit.getSrc(), visit.getBrand(), visit.getModle(), visit.getMobileCurrentTime(), visit.getSign(), visit.getSessionid());
        logAccessManager.save(log);
    }

    @Override
    public void insertLogErrorMobileTime(VisitAdsms visit) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getDownUrl(long id, int jumpType) {
        if (id >= 0) {
            switch (jumpType) {
            case 1:
                Recommend recommend = recommendManager.get(id);
                if (recommend != null) {
                    return recommend.getRealDownUrl();
                }
                break;
            case 2:
                HotWords hotWord = hotWordsManager.get(id);
                if (hotWord != null) {
                    return hotWord.getRealDownUrl();
                }
                break;
            case 3:
                break;
            default:
                break;
            }
        }
        return null;
    }

    @Override
    public void insertLogErrorNull(VisitAdsms visit) {
        // TODO Auto-generated method stub

    }
}
