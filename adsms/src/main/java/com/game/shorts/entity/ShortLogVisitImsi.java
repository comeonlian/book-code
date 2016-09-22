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

@Entity
@Table(name = "short_log_visit_imsi")
public class ShortLogVisitImsi implements java.io.Serializable {

    private static final long serialVersionUID = -3237116586744705668L;

    private Long id;
    private Date accesstime; // 访问时间
    private String deviceid; // 设备ID
    private String ip;
    private Long countryId; // 国家ID
    private String customerid; // 客户id
    private Boolean data; // 移动数据是否打开:0、否； 1、有效；
    private String imsi;
    private String imsiprefix; // imsi前面部分
    private String apn;
    private Integer slot; // 卡槽
    private String sc; // 短信服务中心号
    private String iccid; // SIM卡的ICCID号
    private String imei; // imei
    private Integer providerCode; // 运营商代号

    public ShortLogVisitImsi() {
        super();
    }

    public ShortLogVisitImsi(Date accesstime, String deviceid, String ip, Long countryId, String customerid, Boolean data, String imsi, String numeric, String apn, Integer slot,
            String sc, String iccid, String imei, Integer providerCode) {
        super();
        this.accesstime = accesstime;
        this.deviceid = deviceid;
        this.ip = ip;
        this.countryId = countryId;
        this.customerid = customerid;
        this.data = data;
        this.imsi = imsi;
        this.setImsiprefix(numeric);
        this.apn = apn;
        this.slot = slot;
        this.sc = sc;
        this.iccid = iccid;
        this.imei = imei;
        this.providerCode = providerCode;
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

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public Boolean getData() {
        return data;
    }

    public void setData(Boolean data) {
        this.data = data;
    }

    public String getApn() {
        return apn;
    }

    public void setApn(String apn) {
        this.apn = apn;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsiprefix() {
        return imsiprefix;
    }

    public void setImsiprefix(String imsiprefix) {
        this.imsiprefix = imsiprefix;
    }

    public Integer getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(Integer providerCode) {
        this.providerCode = providerCode;
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