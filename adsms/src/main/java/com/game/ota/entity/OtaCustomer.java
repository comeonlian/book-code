package com.game.ota.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.game.entity.AuditableEntity;

/**
 * 客户管理
 */
@Entity
@Table(name = "ota_customer")
public class OtaCustomer extends AuditableEntity {

    private static final long serialVersionUID = -8070239655272511289L;

    private String customername; // 名称
    private String customerid; // 客户id
    private String cdesc; // 描述

    // 下发的数据
    private Integer linkinterval = 1; // 下次联网间隔(小时)
    private Integer smsdays = 3; // sms标识下发间隔

    // 客户过滤条件
    private Integer ptype = 0; // 产品类型 —— 0：不指定;1、IVR;2、SMS;3、WAP
    private Integer status = 0; // 是否运营 —— 1、开启；0、关闭; -1 为已删除状态(做逻辑删除)
    private Integer apkdays = 2; // 间隔
    private Integer apkdown = 10; // 月值
    private Integer provider = 0; // 运营商：0、不指定；1、移动优先；2、联通优先
    private String begintime = "00:00:00"; // 指定开始时间
    private String endtime = "23:59:59"; // 指定结束时间
    private String citys; // 开通省市

    // 开放客户条件
    private Integer openrate = 100; // 开放比例 0-100
    private Integer deviceday = 7; // 黑名单天数
    private Integer devicecount = 20; // 永久黑名单个数
    private Integer unitprice = 10; // 推广单价(角)
    private Integer passdevice = 0; // 是否通过了永久黑名单数

    public String getCustomername() {
        return this.customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCdesc() {
        return this.cdesc;
    }

    public void setCdesc(String cdesc) {
        this.cdesc = cdesc;
    }

    public String getBegintime() {
        return this.begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getEndtime() {
        return this.endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    @Column(name = "citys")
    public String getCitys() {
        return citys;
    }

    public void setCitys(String citys) {
        this.citys = citys;
    }

    public Integer getApkdays() {
        return apkdays;
    }

    public void setApkdays(Integer apkdays) {
        this.apkdays = apkdays;
    }

    public Integer getApkdown() {
        return apkdown;
    }

    public void setApkdown(Integer apkdown) {
        this.apkdown = apkdown;
    }

    public Integer getOpenrate() {
        return openrate;
    }

    public void setOpenrate(Integer openrate) {
        this.openrate = openrate;
    }

    public Integer getDeviceday() {
        return deviceday;
    }

    public void setDeviceday(Integer deviceday) {
        this.deviceday = deviceday;
    }

    public Integer getDevicecount() {
        return devicecount;
    }

    public void setDevicecount(Integer devicecount) {
        this.devicecount = devicecount;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public Integer getLinkinterval() {
        return linkinterval;
    }

    public void setLinkinterval(Integer linkinterval) {
        this.linkinterval = linkinterval;
    }

    public Integer getProvider() {
        return provider;
    }

    public void setProvider(Integer provider) {
        this.provider = provider;
    }

    public Integer getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Integer unitprice) {
        this.unitprice = unitprice;
    }

    public Integer getPassdevice() {
        return passdevice;
    }

    public void setPassdevice(Integer passdevice) {
        this.passdevice = passdevice;
    }

    public Integer getSmsdays() {
        return smsdays;
    }

    public void setSmsdays(Integer smsdays) {
        this.smsdays = smsdays;
    }

    public Integer getPtype() {
        return ptype;
    }

    public void setPtype(Integer ptype) {
        this.ptype = ptype;
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