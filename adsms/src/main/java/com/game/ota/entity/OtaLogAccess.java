package com.game.ota.entity;

import java.util.Date;

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
 * 访问日志
 */
@Entity
@Table(name = "ota_log_access")
public class OtaLogAccess implements java.io.Serializable {

    private static final long serialVersionUID = -3237116586744705668L;

    private Long id;
    private Date accesstime; // 访问时间
    private String deviceid; // 设备ID
    private Long countryId; // 国家ID
    private String countryName;
    private String customerid; // 客户id
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
    private Integer runlevel;
    private String responseTxt;

    public OtaLogAccess() {
        super();
    }

    public OtaLogAccess(Date accesstime, String deviceid, Long countryId, String customerid, String ip, String imsi, String sc, String imsi1, String sc1, String imsi2, String sc2,
            String imsi3, String sc3, String imsi4, String sc4, String ver, String plat, String pot, String endian, String lasterror, String platver, Integer runlevel,
            String responseTxt) {
        super();
        this.accesstime = accesstime;
        this.deviceid = deviceid;
        this.countryId = countryId;
        this.customerid = customerid;
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
        this.runlevel = runlevel;
        this.responseTxt = responseTxt;
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

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
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

    public Integer getRunlevel() {
        return runlevel;
    }

    public void setRunlevel(Integer runlevel) {
        this.runlevel = runlevel;
    }

    public String getResponseTxt() {
        return responseTxt;
    }

    public void setResponseTxt(String responseTxt) {
        this.responseTxt = responseTxt;
    }

    @Transient
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
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