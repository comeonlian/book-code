package com.game.docking.ota.entity;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class VisitOta {

    private Date accesstime; // 访问时间
    private String ip;
    private String dealImsi;
    private Long countryId; // 国家ID
    private String countryName; // 国家名称
    private String deviceid; // 设备ID
    private String customerid; // 客户id
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
    private int providerCode = 0; // 运营商编码
    private String responseContext;

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

    public Integer getRunlevel() {
        return runlevel;
    }

    public void setRunlevel(Integer runlevel) {
        this.runlevel = runlevel;
    }

    public int getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(int providerCode) {
        this.providerCode = providerCode;
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

    public String getDealImsi() {
        return dealImsi;
    }

    public void setDealImsi(String dealImsi) {
        this.dealImsi = dealImsi;
    }

    public String getResponseContext() {
        return responseContext;
    }

    public void setResponseContext(String responseContext) {
        this.responseContext = responseContext;
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
