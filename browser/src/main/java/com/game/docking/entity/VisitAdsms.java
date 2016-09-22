package com.game.docking.entity;

import java.util.Date;

/**
 * --------------------上传参数-------------------------<br/>
 * clientId --客户ID<br/>
 * androidId --android ID<br/>
 * imei --默认SIM卡1<br/>
 * imsi --默认SIM卡1<br/>
 * osVc --android 系统版本号<br/>
 * osVn --android 系统版本名<br/>
 * internal --平台是否内置 1：内置 0：否<br/>
 * language --系统语言<br/>
 * netType --联网方式<br/>
 * brand --手机商标<br/>
 * model --手机型号<br/>
 * pn --手机cpu型号<br/>
 * rl --ROM剩余空间<br/>
 * sdl --SD卡剩余空间<br/>
 * rt --rom总大小<br/>
 * sdt --sd卡总大小<br/>
 * src --分辨率<br/>
 * tst --是否测试<br/>
 * vn --app版本名<br/>
 * vc --app版本号<br/>
 * ivn --启动 App版本名<br/>
 * ipkg --启动 APP包名<br/>
 * wifi --是否wifi联网 1：是 0：否<br/>
 * x --芯片型号<br/>
 * mac --wifi mac地址<br/>
 * ct --手机当前时间<br/>
 * sign --是否有系统签名<br/>
 */
public class VisitAdsms {

    private Date accesstime; // 访问时间
    private String deviceid; // 设备ID
    private String ip;
    private Integer runlevel = 0; // 监控步骤
    private Long countryId; // 国家ID
    private String countryName; // 国家
    private boolean gzip; // 是否压缩

    private String mac;
    private String imei;
    private String imsi;
    private String androidid;
    private String customerid; // 客户id
    private String osVersionCode; // 手机系统版本号
    private String osVersionName; // 手机系统版本名
    private String inline; // 平台是否内置 1：内置 0：否
    private String langCountry; // 系统语言国家编码
    private String netType; // 联网方式
    private String cpu; // CPU型号
    private String romLess; // ROM剩余空间
    private String sdcardLess; // sdcar剩余空间
    private String romTotal; // ROM 总大小
    private String sdcardTotal; // sdcard 总大小
    private String appVersionCode; // app版本号
    private String appVersionName; // app版本名
    private String initVersionName; // 启动应用版本名
    private String initPackageName; // 启动应用包名
    private String wifi; // 是否wifi
    private String src; // 手机分辨率
    private String brand; // 手机商标
    private String modle; // 手机型号
    private String mobileCurrentTime; // 手机当前时间
    private String sign; // 是否有系统签名
    private String sessionid;

    public Date getAccesstime() {
        return accesstime;
    }

    public void setAccesstime(Date accesstime) {
        this.accesstime = accesstime;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getRunlevel() {
        return runlevel;
    }

    public void setRunlevel(Integer runlevel) {
        this.runlevel = runlevel;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getAndroidid() {
        return androidid;
    }

    public void setAndroidid(String androidid) {
        this.androidid = androidid;
    }

    public String getOsVersionCode() {
        return osVersionCode;
    }

    public void setOsVersionCode(String osVersionCode) {
        this.osVersionCode = osVersionCode;
    }

    public String getOsVersionName() {
        return osVersionName;
    }

    public void setOsVersionName(String osVersionName) {
        this.osVersionName = osVersionName;
    }

    public String getInline() {
        return inline;
    }

    public void setInline(String inline) {
        this.inline = inline;
    }

    public String getLangCountry() {
        return langCountry;
    }

    public void setLangCountry(String langCountry) {
        this.langCountry = langCountry;
    }

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRomLess() {
        return romLess;
    }

    public void setRomLess(String romLess) {
        this.romLess = romLess;
    }

    public String getSdcardLess() {
        return sdcardLess;
    }

    public void setSdcardLess(String sdcardLess) {
        this.sdcardLess = sdcardLess;
    }

    public String getRomTotal() {
        return romTotal;
    }

    public void setRomTotal(String romTotal) {
        this.romTotal = romTotal;
    }

    public String getSdcardTotal() {
        return sdcardTotal;
    }

    public void setSdcardTotal(String sdcardTotal) {
        this.sdcardTotal = sdcardTotal;
    }

    public String getAppVersionCode() {
        return appVersionCode;
    }

    public void setAppVersionCode(String appVersionCode) {
        this.appVersionCode = appVersionCode;
    }

    public String getAppVersionName() {
        return appVersionName;
    }

    public void setAppVersionName(String appVersionName) {
        this.appVersionName = appVersionName;
    }

    public String getInitVersionName() {
        return initVersionName;
    }

    public void setInitVersionName(String initVersionName) {
        this.initVersionName = initVersionName;
    }

    public String getInitPackageName() {
        return initPackageName;
    }

    public void setInitPackageName(String initPackageName) {
        this.initPackageName = initPackageName;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModle() {
        return modle;
    }

    public void setModle(String modle) {
        this.modle = modle;
    }

    public String getMobileCurrentTime() {
        return mobileCurrentTime;
    }

    public void setMobileCurrentTime(String mobileCurrentTime) {
        this.mobileCurrentTime = mobileCurrentTime;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public boolean isGzip() {
        return gzip;
    }

    public void setGzip(boolean gzip) {
        this.gzip = gzip;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

}
