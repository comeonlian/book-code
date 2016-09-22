package com.game.otapartner.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 统计设备表
 */
@Entity
@Table(name = "ota_co_device")
public class OtaPartnerDevice implements java.io.Serializable {

    private static final long serialVersionUID = -3237116586744705668L;

    private Long id;
    private String deviceid;
    private Date accesstime; // 访问时间
    private Long countryid;
    private String customid; // 客户id
    private String ver; // 计费版本
    private String plat; // 平台
    private String imsi;
    private String sc;
    private Integer nextretention = 0; // 次日留存
    private Integer thirdretention = 0; // 3日留存
    private Integer sevenretention = 0; // 七日留存

    public OtaPartnerDevice() {
        super();
    }

    public OtaPartnerDevice(String deviceid, Date accesstime, Long countryid, String customid, String ver, String plat, String imsi, String sc) {
        super();
        this.deviceid = deviceid;
        this.accesstime = accesstime;
        this.countryid = countryid;
        this.customid = customid;
        this.ver = ver;
        this.plat = plat;
        this.imsi = imsi;
        this.sc = sc;
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

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

}