package com.game.shorts.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 访问日志
 */
@Entity
@Table(name = "short_log_error_null")
public class ShortLogErrorNull implements java.io.Serializable {

    private static final long serialVersionUID = -3237116586744705668L;

    private Long id;
    private Date accesstime; // 访问时间
    private String deviceid; // 设备ID
    private String ip;
    private Long countryId; // 国家ID
    private String countryName; // 国家
    private String url; // 请求的url

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
    private String test; // 是否测试模式
    private String appVersionCode; // app版本号
    private String appVersionName; // app版本名
    private String initVersionName; // 启动应用版本名
    private String initPackageName; // 启动应用包名
    private String wifi; // 是否wifi
    private String apn; // apn方式
    private String src; // 手机分辨率
    private String brand; // 手机商标
    private String modle; // 手机型号
    private String mobileCurrentTime; // 手机当前时间
    private String sign; // 是否有系统签名
    private String simParams; // sim卡信息

    public ShortLogErrorNull() {
        super();
    }

    public ShortLogErrorNull(Date accesstime, String deviceid, String ip, Long countryId, String countryName, String url, String mac, String imei, String imsi,
            String androidid, String customerid, String osVersionCode, String osVersionName, String inline, String langCountry, String netType, String cpu, String romLess,
            String sdcardLess, String romTotal, String sdcardTotal, String test, String appVersionCode, String appVersionName, String initVersionName, String initPackageName,
            String wifi, String apn, String src, String brand, String modle, String mobileCurrentTime, String sign, String simParams) {
        super();
        this.accesstime = accesstime;
        this.deviceid = deviceid;
        this.ip = ip;
        this.countryId = countryId;
        this.countryName = countryName;
        this.url = url;
        this.mac = mac;
        this.imei = imei;
        this.imsi = imsi;
        this.androidid = androidid;
        this.customerid = customerid;
        this.osVersionCode = osVersionCode;
        this.osVersionName = osVersionName;
        this.inline = inline;
        this.langCountry = langCountry;
        this.netType = netType;
        this.cpu = cpu;
        this.romLess = romLess;
        this.sdcardLess = sdcardLess;
        this.romTotal = romTotal;
        this.sdcardTotal = sdcardTotal;
        this.test = test;
        this.appVersionCode = appVersionCode;
        this.appVersionName = appVersionName;
        this.initVersionName = initVersionName;
        this.initPackageName = initPackageName;
        this.wifi = wifi;
        this.apn = apn;
        this.src = src;
        this.brand = brand;
        this.modle = modle;
        this.mobileCurrentTime = mobileCurrentTime;
        this.sign = sign;
        this.simParams = simParams;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getAndroidid() {
        return androidid;
    }

    public void setAndroidid(String androidid) {
        this.androidid = androidid;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
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

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
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

    public String getApn() {
        return apn;
    }

    public void setApn(String apn) {
        this.apn = apn;
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

    public String getSimParams() {
        return simParams;
    }

    public void setSimParams(String simParams) {
        this.simParams = simParams;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}