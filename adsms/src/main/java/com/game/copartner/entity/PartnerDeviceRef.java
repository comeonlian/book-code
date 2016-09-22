package com.game.copartner.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 统计设备参考表：组合唯一键 mac + imei + androidid
 * 
 * @author srain12
 */
@Entity
@Table(name = "co_device_ref")
public class PartnerDeviceRef implements java.io.Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = -3237116586744705668L;
    // Fields

    private Long id;
    private Date accesstime; // 访问时间
    private Long countryid;
    private String countryname;
    private String customid; // 客户id
    private String osVersionCode; // 手机系统版本号
    private String osVersionName; // 手机系统版本名
    private String imei;
    private String mac;
    private String androidid;
    private String langCountry; // 系统语言国家编码
    private String inline; // 平台是否内置 1：内置 0：否
    private String cpu; // CPU型号
    private String initPackageName; // 启动应用包名
    private String appVersionCode; // app版本号
    private String src; // 手机分辨率
    private String brand; // 手机商标
    private String modle; // 手机型号
    private String sign; // 是否有系统签名
    private String deviceid; // mac + "_" + imei + "_" + androidid
    private Integer nextretention = 0; // 次日留存
    private Integer thirdretention = 0; // 3日留存
    private Integer sevenretention = 0; // 七日留存

    public PartnerDeviceRef() {
        super();
    }

    public PartnerDeviceRef(Date accesstime, Long countryid, String countryname, String customid, String osVersionCode, String osVersionName, String imei, String mac,
            String androidid, String langCountry, String inline, String cpu, String initPackageName, String appVersionCode, String src, String brand, String modle, String sign,
            String deviceid, Integer nextretention, Integer thirdretention, Integer sevenretention) {
        super();
        this.accesstime = accesstime;
        this.countryid = countryid;
        this.countryname = countryname;
        this.customid = customid;
        this.osVersionCode = osVersionCode;
        this.osVersionName = osVersionName;
        this.imei = imei;
        this.mac = mac;
        this.androidid = androidid;
        this.langCountry = langCountry;
        this.inline = inline;
        this.cpu = cpu;
        this.initPackageName = initPackageName;
        this.appVersionCode = appVersionCode;
        this.src = src;
        this.brand = brand;
        this.modle = modle;
        this.sign = sign;
        this.deviceid = deviceid;
        this.nextretention = nextretention;
        this.thirdretention = thirdretention;
        this.sevenretention = sevenretention;
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
        return this.accesstime;
    }

    public void setAccesstime(Date accesstime) {
        this.accesstime = accesstime;
    }

    public String getCustomid() {
        return this.customid;
    }

    public void setCustomid(String customid) {
        this.customid = customid;
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

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public Integer getNextretention() {
        return nextretention;
    }

    public void setNextretention(Integer nextretention) {
        this.nextretention = nextretention;
    }

    public Integer getThirdretention() {
        return thirdretention;
    }

    public void setThirdretention(Integer thirdretention) {
        this.thirdretention = thirdretention;
    }

    public Integer getSevenretention() {
        return sevenretention;
    }

    public void setSevenretention(Integer sevenretention) {
        this.sevenretention = sevenretention;
    }

    public Long getCountryid() {
        return countryid;
    }

    public void setCountryid(Long countryid) {
        this.countryid = countryid;
    }

    public String getAndroidid() {
        return androidid;
    }

    public void setAndroidid(String androidid) {
        this.androidid = androidid;
    }

    public String getLangCountry() {
        return langCountry;
    }

    public void setLangCountry(String langCountry) {
        this.langCountry = langCountry;
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

}