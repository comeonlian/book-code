package com.game.docking.ota.service.impl;

import java.nio.ByteBuffer;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.game.comm.entity.City;
import com.game.comm.entity.LogCityUndefine;
import com.game.comm.entity.SmscenterCmcc;
import com.game.comm.entity.SmscenterUnion;
import com.game.comm.service.CityManager;
import com.game.comm.service.LogCityUndefineManager;
import com.game.comm.service.OscacheManager;
import com.game.comm.service.SmscenterCmccManager;
import com.game.comm.service.SmscenterUnionManager;
import com.game.comm.service.impl.OscacheManagerImpl;
import com.game.docking.ota.entity.SendOtaHeader;
import com.game.docking.ota.entity.SendOtaIvr;
import com.game.docking.ota.entity.SendOtaSms;
import com.game.docking.ota.entity.VisitOta;
import com.game.docking.ota.service.OtaGeneralManager;
import com.game.docking.util.InterfaceUtil;
import com.game.docking.util.OtaUtil;
import com.game.docking.util.sina.SinaIpUtils;
import com.game.docking.util.sina.JsonIPInfo;
import com.game.modules.utils.DateUtil;
import com.game.ota.entity.OtaCustomer;
import com.game.ota.entity.OtaDevice;
import com.game.ota.entity.OtaDeviceImsi;
import com.game.ota.entity.OtaLogAccess;
import com.game.ota.entity.OtaLogPushIvr;
import com.game.ota.entity.OtaLogPushSms;
import com.game.ota.entity.OtaProductIvr;
import com.game.ota.entity.OtaProductSms;
import com.game.ota.service.OtaDeviceImsiManager;
import com.game.ota.service.OtaDeviceManager;
import com.game.ota.service.OtaLogAccessManager;
import com.game.ota.service.OtaLogPushIvrManager;
import com.game.ota.service.OtaLogPushSmsManager;
import com.game.ota.service.OtaProductIvrManager;
import com.game.ota.service.OtaProductSmsManager;
import com.game.otapartner.entity.OtaFirstVisitDaily;
import com.game.otapartner.entity.OtaPartnerDevice;
import com.game.otapartner.service.OtaFirstVisitDailyManager;
import com.game.otapartner.service.OtaPartnerDeviceManager;
import com.game.services.common.DicManager;
import com.game.util.DateUtils;

@Service("otaGeneralManager")
public class OtaGeneralManagerImpl implements OtaGeneralManager {
    public static final Logger LOGGER = LoggerFactory.getLogger(OtaGeneralManagerImpl.class);
    private static final String COUNTRY_CHINA = "中国";
    private static final int UNDEFILE_LEVEL_CITY = 1;
    private static final int UNDEFILE_LEVEL_PROVINCE = 2;
    private static final int UNDEFILE_LEVEL_COUNTRY = 3;

    private OscacheManager oscacheManager = new OscacheManagerImpl();
    @Autowired
    private CityManager cityManager;
    @Autowired
    private DicManager dicManager;
    @Autowired
    private LogCityUndefineManager logCityUndefineManager;
    @Autowired
    private SmscenterCmccManager smscenterCmccManager;
    @Autowired
    private SmscenterUnionManager smscenterUnionManager;
    @Autowired
    private OtaDeviceImsiManager otaDeviceImsiManager;
    @Autowired
    private OtaDeviceManager otaDeviceManager;
    @Autowired
    private OtaPartnerDeviceManager otaPartnerDeviceManager;
    @Autowired
    private OtaFirstVisitDailyManager otaFirstVisitDailyManager;
    @Autowired
    private OtaProductIvrManager otaProductIvrManager;
    @Autowired
    private OtaProductSmsManager otaProductSmsManager;
    @Autowired
    private OtaLogAccessManager otaLogAccessManager;
    @Autowired
    private OtaLogPushIvrManager otaLogPushIvrManager;
    @Autowired
    private OtaLogPushSmsManager otaLogPushSmsManager;

    private Long dealCity(VisitOta visit) {
        Long cityid = NumberUtils.LONG_ZERO;
        String ip = visit.getIp();
        JsonIPInfo info = SinaIpUtils.getAddress(ip);
        if (info != null) {
            City city = this.getByJsonIPInfo(info);
            if (city != null) {
                cityid = city.getId();
            } else {
                cityid = 19999L;
            }
        } else {
            Map<String, Object> cityMap = getCity(ip);
            if (!CollectionUtils.isEmpty(cityMap)) {
                cityid = (Long) cityMap.get("cityCode");
                if (cityid == null || cityid == NumberUtils.LONG_ZERO) {
                    cityid = 19999L;
                }
            }
        }
        return cityid;
    }

    /**
     * 获取地市(如果找不到，默认为0L)
     */
    private Map<String, Object> getCity(String ip) {
        Map<String, Object> map = oscacheManager.findCityByCDN(ip);
        Long cityCode = (Long) map.get("cityCode");
        if (cityCode == null) {
            map.put("cityCode", NumberUtils.LONG_ZERO);
        }
        return map;
    }

    @Override
    public void dealPartnerDatas(VisitOta visit, String currentdate) {
        boolean addDevice = false;
        String deviceid = visit.getDeviceid();
        // ******** 开放设备处理
        OtaPartnerDevice partnerDevice = otaPartnerDeviceManager.getByDeviceid(deviceid);
        if (partnerDevice == null) {
            // 写设备入库
            partnerDevice = createPartnerDevice(visit);
            addDevice = true;
        }

        if (!otaFirstVisitDailyManager.isDeviceExist(currentdate, deviceid)) {
            // 创建每日首次访问信息
            createFirstVisitDaily(visit, currentdate);
            // 合作设备留存处理
            dealPartnerDeviceRetention(visit, addDevice, partnerDevice);
        }

    }

    /**
     * 开放设备留存率计算
     */
    private void dealPartnerDeviceRetention(VisitOta visit, boolean addDevice, OtaPartnerDevice partnerDevice) {
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
                otaPartnerDeviceManager.save(partnerDevice);
            }
        }
    }

    /**
     * 创建每日首次访问信息
     */
    private void createFirstVisitDaily(VisitOta visit, String currentdate) {
        OtaFirstVisitDaily firstVisitDaily = new OtaFirstVisitDaily(currentdate, visit.getAccesstime(), visit.getDeviceid(), visit.getCountryId(), visit.getCustomerid(),
                visit.getIp(), visit.getImsi(), visit.getSc(), visit.getImsi1(), visit.getSc1(), visit.getImsi2(), visit.getSc2(), visit.getImsi3(), visit.getSc3(),
                visit.getImsi4(), visit.getSc4(), visit.getVer(), visit.getPlat(), visit.getPot(), visit.getEndian(), visit.getLasterror(), visit.getPlatver());
        otaFirstVisitDailyManager.save(firstVisitDaily);
        return;
    }

    /**
     * 创建合作设备
     */
    private OtaPartnerDevice createPartnerDevice(VisitOta visit) {
        OtaPartnerDevice partnerDevice = new OtaPartnerDevice(visit.getDeviceid(), visit.getAccesstime(), visit.getCountryId(), visit.getCustomerid(), visit.getVer(),
                visit.getPlat(), visit.getImsi(), visit.getSc());
        otaPartnerDeviceManager.save(partnerDevice);
        return partnerDevice;
    }

    private Long getCityBySc(int provider, String sc) {
        if (StringUtils.isNotBlank(sc) && sc.length() >= 11) {
            if (provider == NumberUtils.INTEGER_ONE) {
                // 移动：+861380xxxx500 取xxxx
                String areacode = sc.substring(sc.length() - 7, sc.length() - 3);
                SmscenterCmcc smscenterCmcc = smscenterCmccManager.getEntityByCode(areacode);
                if (smscenterCmcc != null) {
                    return smscenterCmcc.getCityid();
                }
            } else if (provider == 2) {
                String areacode = sc.substring(sc.length() - 11);
                SmscenterUnion smscenterUnion = smscenterUnionManager.getEntityByCode(areacode);
                if (smscenterUnion != null) {
                    return smscenterUnion.getCityid();
                }
            }
        }
        return null;
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
    public OtaDevice getDevice(VisitOta visit) {
        Map<String, String> imsiScMap = OtaUtil.getImsiSc(visit);
        if (CollectionUtils.isEmpty(imsiScMap)) {
            return null;
        }
        String mapImsi = imsiScMap.get("imsi");
        if (StringUtils.isBlank(mapImsi) || mapImsi.length() < OtaUtil.LIMIT_LENGTH_IMSI) {
            return null;
        }
        if (mapImsi.length() > 15 && !mapImsi.startsWith("460")) {
            mapImsi = mapImsi.substring(1);
        }
        visit.setDealImsi(mapImsi);
        int provider = InterfaceUtil.getProviderCode(mapImsi);
        visit.setProviderCode(provider);
        String mapSc = imsiScMap.get("sc");
        Long cityid = NumberUtils.LONG_ZERO;
        OtaDeviceImsi deviceImsi = otaDeviceImsiManager.getDeviceByImsi(mapImsi);
        if (deviceImsi == null) {
            int citylevel = OtaUtil.CITYLEVEL_IP;
            cityid = getCityBySc(provider, mapSc);
            if (cityid != null && cityid > 0) {
                citylevel = OtaUtil.CITYLEVEL_SC;
            } else {
                cityid = dealCity(visit);
            }
            deviceImsi = new OtaDeviceImsi(visit.getAccesstime(), visit.getDeviceid(), mapImsi, mapSc, visit.getCustomerid(), cityid, citylevel, provider);
            otaDeviceImsiManager.save(deviceImsi);
        }
        if (deviceImsi != null && deviceImsi.getCountryId() != null && deviceImsi.getCountryId() > 0) {
            cityid = deviceImsi.getCountryId();
        }
        if (cityid == null || NumberUtils.LONG_ZERO.equals(cityid)) {
            cityid = dealCity(visit);
        }
        OtaDevice device = otaDeviceManager.getDeviceByDeviceid(visit.getDeviceid());
        if (device == null) {
            device = new OtaDevice(visit.getAccesstime(), visit.getDeviceid(), visit.getCustomerid(), cityid, mapImsi, mapSc, visit.getImsi1(), visit.getSc1(), visit.getImsi2(),
                    visit.getSc2(), visit.getImsi3(), visit.getSc3(), visit.getImsi4(), visit.getSc4(), visit.getVer(), visit.getPlat());
            otaDeviceManager.save(device);
        }
        if (device != null) {
            device.setDeviceImsi(deviceImsi);
        }
        visit.setCountryId(cityid);
        return device;
    }

    @Override
    public ByteBuffer dealProduct(SendOtaHeader otaHeader, VisitOta visit, OtaDevice device, OtaCustomer customer) {
        ByteBuffer bbNull = OtaUtil.generateOtaDatasNull(otaHeader);
        visit.setResponseContext(otaHeader.toString());
        String currentDate = DateUtils.parseDate(visit.getAccesstime(), "yyyy-MM-dd");
        String currentTime = DateUtils.parseDate(visit.getAccesstime(), "HH:mm:ss");
        int runlevel = OtaUtil.passCustomer(customer, currentTime, visit.getDealImsi(), visit.getCountryId(), visit.getProviderCode());
        visit.setRunlevel(runlevel);
        if (device == null || runlevel < OtaUtil.RUNLEVEL_PASS_CUSTOMER) {
            return bbNull;
        }
        int cApkdays = customer.getApkdays() == null ? 0 : customer.getApkdays(); // 发送频率
        int cApkdown = customer.getApkdown() == null ? 0 : customer.getApkdown(); // 月值
        OtaDeviceImsi deviceImsi = device.getDeviceImsi();
        if (deviceImsi == null) {
            return bbNull;
        }
        int restValue = InterfaceUtil.differ(cApkdown, deviceImsi.getMvalues());
        boolean isLastpadvInMonth = InterfaceUtil.isLastpadvInMonth(deviceImsi.getLastpadv(), visit.getAccesstime());
        int daybetw = 30;
        if (deviceImsi.getLastpadv() != null) {
            daybetw = DateUtils.daysBetween(deviceImsi.getLastpadv(), visit.getAccesstime(), "yyyy-MM-dd");
        }
        runlevel = OtaUtil.passDevice(device, daybetw, cApkdays, isLastpadvInMonth, restValue);
        visit.setRunlevel(runlevel);
        if (runlevel < OtaUtil.RUNLEVEL_PASS_DEVICE) {
            return bbNull;
        }
        int providerCode = visit.getProviderCode();
        String cityidStr = "|" + visit.getCountryId() + "|";
        int productType = customer.getPtype() == null ? 0 : customer.getPtype();
        visit.setRunlevel(productType * 100 + OtaUtil.RUNLEVEL_INTERCEPT_PRODUCT_NODATA);
        if (productType == OtaUtil.PRODUCT_TYPE_ALL || productType == OtaUtil.PRODUCT_TYPE_IVR) {
            OtaProductIvr productIvr = otaProductIvrManager.getProduct(customer.getCustomerid(), cityidStr, providerCode, daybetw, currentDate, currentTime, restValue);
            if (productIvr != null) {
                otaHeader.setSendtype(OtaUtil.SEND_HTTP_IVR7);
                visit.setRunlevel(OtaUtil.RUNLEVEL_PASS_SUCCESS_IVR);
                return joinIvrBuffer(visit, otaHeader, productIvr, deviceImsi, isLastpadvInMonth);
            }
        }
        if (productType == OtaUtil.PRODUCT_TYPE_ALL || productType == OtaUtil.PRODUCT_TYPE_SMS) {
            OtaProductSms productSms = otaProductSmsManager.getProduct(customer.getCustomerid(), cityidStr, providerCode, daybetw, currentDate, currentTime, restValue);
            if (productSms != null) {
                otaHeader.setSendtype(OtaUtil.SEND_HTTP_SMS);
                visit.setRunlevel(OtaUtil.RUNLEVEL_PASS_SUCCESS_SMS);
                return joinSmsBuffer(visit, otaHeader, productSms, deviceImsi, isLastpadvInMonth);
            }
        }
        return bbNull;
    }

    /**
     * 拼接SMS流
     */
    private ByteBuffer joinSmsBuffer(VisitOta visit, SendOtaHeader otaHeader, OtaProductSms productSms, OtaDeviceImsi deviceImsi, boolean isLastpadvInMonth) {
        String moNumber = productSms.getMoNumber();
        String moContent = productSms.getMoContent();
        byte degree = productSms.getDegree();
        byte betInterval = productSms.getBetInterval();
        String deleteKeyWord = StringUtils.isBlank(productSms.getDeleteKeyWord()) ? OtaUtil.STR_VERTICAL : productSms.getDeleteKeyWord();
        String deleteKeyNumber = StringUtils.isBlank(productSms.getDeleteKeyNumber()) ? OtaUtil.STR_VERTICAL : productSms.getDeleteKeyNumber();
        byte replyType = productSms.getReplyType();
        String replyContent = productSms.getContent();
        SendOtaSms sendOtaSms = new SendOtaSms(moNumber, moContent, degree, betInterval, deleteKeyWord, deleteKeyNumber, replyType, replyContent);
        visit.setResponseContext(otaHeader.toString() + " " + sendOtaSms.toString());
        // 写推送日志，变更imsiDevice
        Long productId = productSms.getId();
        String vLastPadv = DateUtil.format(visit.getAccesstime(), "yyyy-MM-dd HH:mm:ss");
        int productPrice = productSms.getPrice() == null ? 0 : productSms.getPrice() * degree;
        this.insertLogPushSms(visit, productId, degree);
        if (isLastpadvInMonth) {
            int deviceImsiValues = deviceImsi.getMvalues() == null ? 0 : deviceImsi.getMvalues();
            deviceImsi.setMvalues(deviceImsiValues + productPrice);
            deviceImsi.setMsi(InterfaceUtil.generateHsi(deviceImsi.getMsi(), productId.toString(), vLastPadv));
        } else {
            deviceImsi.setMsiIvr("");
            deviceImsi.setMvalues(productPrice);
            deviceImsi.setMsi(productId + "_" + NumberUtils.INTEGER_ONE + "_" + vLastPadv);
        }
        deviceImsi.setHsi(InterfaceUtil.generateHsi(deviceImsi.getHsi(), productId.toString(), vLastPadv));
        deviceImsi.setLastpadv(visit.getAccesstime());
        otaDeviceImsiManager.save(deviceImsi);
        int downnum = productSms.getDownnum() == null ? degree : productSms.getDownnum() + degree;
        productSms.setDownnum(downnum);
        otaProductSmsManager.save(productSms);
        return OtaUtil.generateOtaDatasSms(otaHeader, moNumber, moContent, degree, betInterval, deleteKeyWord, deleteKeyNumber, replyType, replyContent);
    }

    private void insertLogPushSms(VisitOta visit, Long productid, Byte downtime) {
        OtaLogPushSms logPushSms = new OtaLogPushSms(visit.getAccesstime(), visit.getDeviceid(), visit.getIp(), visit.getCountryId(), visit.getCustomerid(), visit.getDealImsi(),
                productid, downtime);
        otaLogPushSmsManager.save(logPushSms);
    }

    /**
     * 拼接IVR流
     */
    private ByteBuffer joinIvrBuffer(VisitOta visit, SendOtaHeader otaHeader, OtaProductIvr productIvr, OtaDeviceImsi deviceImsi, boolean isLastpadvInMonth) {
        String moNumber = productIvr.getMoNumber();
        int callTime = InterfaceUtil.getCallTime(productIvr.getCallSpan());
        byte degree = productIvr.getDegree() == null ? 3 : productIvr.getDegree();
        String deleteKeyWord = StringUtils.isBlank(productIvr.getDeleteKeyWord()) ? OtaUtil.STR_VERTICAL : productIvr.getDeleteKeyWord().trim();
        String deleteKeyNumber = StringUtils.isBlank(productIvr.getDeleteKeyNumber()) ? OtaUtil.STR_VERTICAL : productIvr.getDeleteKeyNumber().trim();
        String keyContent = InterfaceUtil.getKeyContent(productIvr.getContentSpan());
        SendOtaIvr sendOtaIvr = new SendOtaIvr(moNumber, callTime, degree, deleteKeyWord, deleteKeyNumber, keyContent);
        visit.setResponseContext(otaHeader.toString() + " " + sendOtaIvr.toString());
        // 写推送日志，变更imsiDevice
        Long productId = productIvr.getId();
        String vLastPadv = DateUtil.format(visit.getAccesstime(), "yyyy-MM-dd HH:mm:ss");
        int productPrice = InterfaceUtil.getProductPriceIvr(productIvr.getPrice(), productIvr.getLimitValue(), callTime);
        this.insertLogPushIvr(visit, productId, callTime, productPrice, keyContent);
        if (productPrice > 0) {
            if (isLastpadvInMonth) {
                int deviceImsiValues = deviceImsi.getMvalues() == null ? 0 : deviceImsi.getMvalues();
                deviceImsi.setMvalues(deviceImsiValues + productPrice);
                deviceImsi.setMsiIvr(InterfaceUtil.generateHsi(deviceImsi.getMsiIvr(), productId.toString(), vLastPadv));
            } else {
                deviceImsi.setMsi("");
                deviceImsi.setMvalues(productPrice);
                deviceImsi.setMsiIvr(productId + "_" + NumberUtils.INTEGER_ONE + "_" + vLastPadv);
            }
            deviceImsi.setHsiIvr(InterfaceUtil.generateHsi(deviceImsi.getHsiIvr(), productId.toString(), vLastPadv));
        }
        deviceImsi.setLastpadv(visit.getAccesstime());
        otaDeviceImsiManager.save(deviceImsi);
        int downnum = productIvr.getDownnum() == null ? 1 : productIvr.getDownnum() + 1;
        productIvr.setDownnum(downnum);
        otaProductIvrManager.save(productIvr);
        return OtaUtil.generateOtaDatasIvr(otaHeader, moNumber, callTime, degree, deleteKeyWord, deleteKeyNumber, keyContent);
    }

    private void insertLogPushIvr(VisitOta visit, Long productid, int callTime, int productPrice, String keyContent) {
        OtaLogPushIvr logPushIvr = new OtaLogPushIvr(visit.getAccesstime(), visit.getDeviceid(), visit.getIp(), visit.getCountryId(), visit.getCustomerid(), visit.getDealImsi(),
                productid, callTime, productPrice, keyContent);
        otaLogPushIvrManager.save(logPushIvr);
    }

    @Override
    public void insertLogAccess(VisitOta visit) {
        OtaLogAccess logAccess = new OtaLogAccess(visit.getAccesstime(), visit.getDeviceid(), visit.getCountryId(), visit.getCustomerid(), visit.getIp(), visit.getImsi(),
                visit.getSc(), visit.getImsi1(), visit.getSc1(), visit.getImsi2(), visit.getSc2(), visit.getImsi3(), visit.getSc3(), visit.getImsi4(), visit.getSc4(),
                visit.getVer(), visit.getPlat(), visit.getPot(), visit.getEndian(), visit.getLasterror(), visit.getPlatver(), visit.getRunlevel(), visit.getResponseContext());
        otaLogAccessManager.save(logAccess);
    }
}
