package com.game.otapartner.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 每天首次访问表：组合唯一键 currentdate + deviceid
 */
@Entity
@Table(name = "ota_co_first_visit_daily")
public class OtaFirstVisitDaily implements java.io.Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = -3237116586744705668L;
    // Fields

    private Long id;
    private String currentdate; // 访问日期
    private Date accesstime; // 访问时间
    private String deviceid; // 设备ID
    private Long countryId; // 国家ID
    private String customid; // 客户id
    private String ip;
    private String imsi;
    private String sc;
    private String imsi1;
    private String sc1;
    private String imsi2;
    private String sc2;
    private String imsi3;
    private String sc3;
    private String imsi4;
    private String sc4;
    private String ver; // 计费版本
    private String plat; // 平台
    private String pot; // 机器的累积待机时间（半小时为单位）
    private String endian;
    private String lasterror;
    private String platver;

    public OtaFirstVisitDaily() {
        super();
    }

    public OtaFirstVisitDaily(String currentdate, Date accesstime, String deviceid, Long countryId, String customid, String ip, String imsi, String sc, String imsi1, String sc1,
            String imsi2, String sc2, String imsi3, String sc3, String imsi4, String sc4, String ver, String plat, String pot, String endian, String lasterror, String platver) {
        super();
        this.currentdate = currentdate;
        this.accesstime = accesstime;
        this.deviceid = deviceid;
        this.countryId = countryId;
        this.customid = customid;
        this.ip = ip;
        this.imsi = imsi;
        this.sc = sc;
        this.imsi1 = imsi1;
        this.sc1 = sc1;
        this.imsi2 = imsi2;
        this.sc2 = sc2;
        this.imsi3 = imsi3;
        this.sc3 = sc3;
        this.imsi4 = imsi4;
        this.sc4 = sc4;
        this.ver = ver;
        this.plat = plat;
        this.pot = pot;
        this.endian = endian;
        this.lasterror = lasterror;
        this.platver = platver;
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

    public Date getAccesstime() {
        return accesstime;
    }

    public void setAccesstime(Date accesstime) {
        this.accesstime = accesstime;
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

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public String getImsi1() {
        return imsi1;
    }

    public void setImsi1(String imsi1) {
        this.imsi1 = imsi1;
    }

    public String getSc1() {
        return sc1;
    }

    public void setSc1(String sc1) {
        this.sc1 = sc1;
    }

    public String getImsi2() {
        return imsi2;
    }

    public void setImsi2(String imsi2) {
        this.imsi2 = imsi2;
    }

    public String getSc2() {
        return sc2;
    }

    public void setSc2(String sc2) {
        this.sc2 = sc2;
    }

    public String getImsi3() {
        return imsi3;
    }

    public void setImsi3(String imsi3) {
        this.imsi3 = imsi3;
    }

    public String getSc3() {
        return sc3;
    }

    public void setSc3(String sc3) {
        this.sc3 = sc3;
    }

    public String getImsi4() {
        return imsi4;
    }

    public void setImsi4(String imsi4) {
        this.imsi4 = imsi4;
    }

    public String getSc4() {
        return sc4;
    }

    public void setSc4(String sc4) {
        this.sc4 = sc4;
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

    public String getPot() {
        return pot;
    }

    public void setPot(String pot) {
        this.pot = pot;
    }

    public String getEndian() {
        return endian;
    }

    public void setEndian(String endian) {
        this.endian = endian;
    }

    public String getLasterror() {
        return lasterror;
    }

    public void setLasterror(String lasterror) {
        this.lasterror = lasterror;
    }

    public String getPlatver() {
        return platver;
    }

    public void setPlatver(String platver) {
        this.platver = platver;
    }

}