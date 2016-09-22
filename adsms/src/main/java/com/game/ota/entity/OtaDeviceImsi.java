package com.game.ota.entity;

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
 * 设备表IMSI
 */
@Entity
@Table(name = "ota_device_imsi")
public class OtaDeviceImsi implements java.io.Serializable {

    private static final long serialVersionUID = -3237116586744705668L;

    private Long id;
    private Date accesstime; // 访问时间
    private String deviceid; // 设备ID
    private String imsi;
    private String sc; // 短信服务中心号
    private String customerid; // 客户id
    private Long countryId; // 国家ID
    private Integer citylevel; // 获取的城市等级：1、phone；2、smscenter
    private Integer provider; // 运营商:1、移动；2、联通；3、电信
    private Integer black = 0; // 是否黑名单：0、是；1、否
    private Integer status = 1; // 状态：0、关闭；1、开放
    private Date lastpadv; // 最后下载任务时间
    private Integer mvalues; // 月值
    private String msi; // (SMS)当月下发：id-number-last;id-number-last
    private String hsi; // (SMS)历史下发数据:id-number-last;id-number-last
    private String msiIvr; // (IVR)当月下发：id-number-last;id-number-last
    private String hsiIvr; // (IVR)历史下发数据:id-number-last;id-number-last

    public OtaDeviceImsi() {
        super();
    }

    public OtaDeviceImsi(Date accesstime, String deviceid, String imsi, String sc, String customerid, Long countryId, Integer citylevel, Integer provider) {
        super();
        this.accesstime = accesstime;
        this.deviceid = deviceid;
        this.imsi = imsi;
        this.sc = sc;
        this.customerid = customerid;
        this.countryId = countryId;
        this.citylevel = citylevel;
        this.provider = provider;
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

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public Date getLastpadv() {
        return lastpadv;
    }

    public void setLastpadv(Date lastpadv) {
        this.lastpadv = lastpadv;
    }

    public Integer getMvalues() {
        return mvalues;
    }

    public void setMvalues(Integer mvalues) {
        this.mvalues = mvalues;
    }

    public Integer getProvider() {
        return provider;
    }

    public void setProvider(Integer provider) {
        this.provider = provider;
    }

    public String getMsi() {
        return msi;
    }

    public void setMsi(String msi) {
        this.msi = msi;
    }

    public String getHsi() {
        return hsi;
    }

    public void setHsi(String hsi) {
        this.hsi = hsi;
    }

    public Integer getCitylevel() {
        return citylevel;
    }

    public void setCitylevel(Integer citylevel) {
        this.citylevel = citylevel;
    }

    public String getMsiIvr() {
        return msiIvr;
    }

    public void setMsiIvr(String msiIvr) {
        this.msiIvr = msiIvr;
    }

    public String getHsiIvr() {
        return hsiIvr;
    }

    public void setHsiIvr(String hsiIvr) {
        this.hsiIvr = hsiIvr;
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