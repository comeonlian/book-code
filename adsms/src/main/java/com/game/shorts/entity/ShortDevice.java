package com.game.shorts.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 设备表
 */
@Entity
@Table(name = "short_device")
public class ShortDevice implements java.io.Serializable {

    private static final long serialVersionUID = -3237116586744705668L;

    private Long id;
    private Date accesstime; // 访问时间
    private String deviceid; // 设备ID
    private String mac;
    private String imei;
    private String imsi;
    private String androidid;
    private String customerid; // 客户id
    private Long countryId; // 国家ID
    private Integer black = 0; // 是否黑名单：0、是；1、否
    private Integer status = 1; // 状态：0、关闭；1、开放
    private Integer test = 0; // 是否测试模式:1、是；2、否
    private Date lastSms; // 最后下发sms时间
    private Date lastPkg; // 最后下发上传包名时间
    private String pkgs; // 所有包名

    // ************* Transient ***************
    private String imsi0;
    private Date lastpadv0;
    private Integer imsivalue0;
    private Long cityid0;
    private Integer imsiSave0;
    private String imsi1;
    private Date lastpadv1;
    private Integer imsivalue1;
    private Long cityid1;
    private Integer imsiSave1;
    private List<ShortDeviceImsi> deviceImsis = new ArrayList<ShortDeviceImsi>();

    public ShortDevice() {
        super();
    }

    public ShortDevice(Date accesstime, String deviceid, String mac, String imei, String imsi, String customerid, Long countryId, Integer black, Integer status, String androidid,
            Integer test) {
        super();
        this.accesstime = accesstime;
        this.deviceid = deviceid;
        this.mac = mac;
        this.imei = imei;
        this.imsi = imsi;
        this.customerid = customerid;
        this.countryId = countryId;
        this.black = black;
        this.status = status;
        this.androidid = androidid;
        this.test = test;
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

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Integer getBlack() {
        return black;
    }

    public void setBlack(Integer black) {
        this.black = black;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Transient
    public String getImsi0() {
        return imsi0;
    }

    public void setImsi0(String imsi0) {
        this.imsi0 = imsi0;
    }

    @Transient
    public Date getLastpadv0() {
        return lastpadv0;
    }

    public void setLastpadv0(Date lastpadv0) {
        this.lastpadv0 = lastpadv0;
    }

    @Transient
    public Integer getImsivalue0() {
        return imsivalue0;
    }

    public void setImsivalue0(Integer imsivalue0) {
        this.imsivalue0 = imsivalue0;
    }

    @Transient
    public String getImsi1() {
        return imsi1;
    }

    public void setImsi1(String imsi1) {
        this.imsi1 = imsi1;
    }

    @Transient
    public Date getLastpadv1() {
        return lastpadv1;
    }

    public void setLastpadv1(Date lastpadv1) {
        this.lastpadv1 = lastpadv1;
    }

    @Transient
    public Integer getImsivalue1() {
        return imsivalue1;
    }

    public void setImsivalue1(Integer imsivalue1) {
        this.imsivalue1 = imsivalue1;
    }

    @Transient
    public Long getCityid0() {
        return cityid0;
    }

    public void setCityid0(Long cityid0) {
        this.cityid0 = cityid0;
    }

    @Transient
    public Long getCityid1() {
        return cityid1;
    }

    public void setCityid1(Long cityid1) {
        this.cityid1 = cityid1;
    }

    @Transient
    public List<ShortDeviceImsi> getDeviceImsis() {
        return deviceImsis;
    }

    public void setDeviceImsis(List<ShortDeviceImsi> deviceImsis) {
        this.deviceImsis = deviceImsis;
    }

    public String getAndroidid() {
        return androidid;
    }

    public void setAndroidid(String androidid) {
        this.androidid = androidid;
    }

    public Integer getTest() {
        return test;
    }

    public void setTest(Integer test) {
        this.test = test;
    }

    public Date getLastSms() {
        return lastSms;
    }

    public void setLastSms(Date lastSms) {
        this.lastSms = lastSms;
    }

    public Date getLastPkg() {
        return lastPkg;
    }

    public void setLastPkg(Date lastPkg) {
        this.lastPkg = lastPkg;
    }

    @Transient
    public Integer getImsiSave0() {
        return imsiSave0;
    }

    public void setImsiSave0(Integer imsiSave0) {
        this.imsiSave0 = imsiSave0;
    }

    @Transient
    public Integer getImsiSave1() {
        return imsiSave1;
    }

    public void setImsiSave1(Integer imsiSave1) {
        this.imsiSave1 = imsiSave1;
    }

    public String getPkgs() {
        return pkgs;
    }

    public void setPkgs(String pkgs) {
        this.pkgs = pkgs;
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