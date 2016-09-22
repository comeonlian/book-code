package com.game.copartner.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 每天首次访问表：组合唯一键 currentdate + deviceid
 * 
 * @author srain12
 */
@Entity
@Table(name = "co_first_visit_daily")
public class FirstVisitDaily implements java.io.Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = -3237116586744705668L;
    // Fields

    private Long id;
    private String currentdate; // 访问日期
    private Date accesstime; // 每日首次访问时间
    private String customid; // 客户id
    private String osVersionCode; // 手机系统版本号
    private String osVersionName; // 手机系统版本名
    private String inline; // 平台是否内置 1：内置 0：否
    private String cpu; // CPU型号
    private String initPackageName; // 启动应用包名
    private String appVersionCode; // app版本号
    private String src; // 手机分辨率
    private String brand; // 手机商标
    private String modle; // 手机型号
    private String sign; // 是否有系统签名
    private String langCountry; // 系统语言国家编码
    private String initVersionName; // 启动应用版本名
    private String netType; // 联网方式
    private String wifi; // 是否wifi
    private Long countryid;
    private String countryname;
    private Integer isdevice;
    private String deviceid; // mac + "_" + imei + "_" + androidid
    private String imei;
    private String mac;
    private String androidid;
    private String imsi;
    private String ip;

    public FirstVisitDaily() {
        super();
    }

    public FirstVisitDaily(String currentdate, Date accesstime, String customid, String osVersionCode, String osVersionName, String inline, String cpu, String initPackageName,
            String appVersionCode, String src, String brand, String modle, String sign, String langCountry, String initVersionName, String netType, String wifi, Long countryid,
            String countryname, String deviceid, String imei, String mac, String androidid, String imsi, Integer isdevice, String ip) {
        super();
        this.currentdate = currentdate;
        this.accesstime = accesstime;
        this.customid = customid;
        this.osVersionCode = osVersionCode;
        this.osVersionName = osVersionName;
        this.inline = inline;
        this.cpu = cpu;
        this.initPackageName = initPackageName;
        this.appVersionCode = appVersionCode;
        this.src = src;
        this.brand = brand;
        this.modle = modle;
        this.sign = sign;
        this.langCountry = langCountry;
        this.initVersionName = initVersionName;
        this.netType = netType;
        this.wifi = wifi;
        this.countryid = countryid;
        this.countryname = countryname;
        this.deviceid = deviceid;
        this.imei = imei;
        this.mac = mac;
        this.androidid = androidid;
        this.imsi = imsi;
        this.isdevice = isdevice;
        this.ip = ip;
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

    public String getCustomid() {
        return this.customid;
    }

    public void setCustomid(String customid) {
        this.customid = customid;
    }

    public String getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(String currentdate) {
        this.currentdate = currentdate;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public Long getCountryid() {
        return countryid;
    }

    public void setCountryid(Long countryid) {
        this.countryid = countryid;
    }

    public Date getAccesstime() {
        return accesstime;
    }

    public void setAccesstime(Date accesstime) {
        this.accesstime = accesstime;
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

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getInitPackageName() {
        return initPackageName;
    }

    public void setInitPackageName(String initPackageName) {
        this.initPackageName = initPackageName;
    }

    public String getAppVersionCode() {
        return appVersionCode;
    }

    public void setAppVersionCode(String appVersionCode) {
        this.appVersionCode = appVersionCode;
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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getLangCountry() {
        return langCountry;
    }

    public void setLangCountry(String langCountry) {
        this.langCountry = langCountry;
    }

    public String getInitVersionName() {
        return initVersionName;
    }

    public void setInitVersionName(String initVersionName) {
        this.initVersionName = initVersionName;
    }

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getAndroidid() {
        return androidid;
    }

    public void setAndroidid(String androidid) {
        this.androidid = androidid;
    }

    public Integer getIsdevice() {
        return isdevice;
    }

    public void setIsdevice(Integer isdevice) {
        this.isdevice = isdevice;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}