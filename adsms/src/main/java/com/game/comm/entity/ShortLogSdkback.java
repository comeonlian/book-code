package com.game.comm.entity;

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
 * 渠道反馈日志
 */
@Entity
@Table(name = "short_log_sdkback")
public class ShortLogSdkback implements java.io.Serializable {

    private static final long serialVersionUID = -3237116586744705668L;

    private Long id;
    private Date accesstime; // 访问时间
    private String ip;
    private String userAgent;
    private String state; // 计费结果值（DELIVERED代表计费成功，其他值均代表计费失败）
    private String smsId; // 一个唯一的流水号
    private String moContent; // 上行内容
    private String phone; // 手机号码
    private String serviceCode; // 长号码

    public ShortLogSdkback() {
        super();
    }

    public ShortLogSdkback(Date accesstime, String ip, String userAgent, String state, String smsId, String moContent, String phone, String serviceCode) {
        super();
        this.accesstime = accesstime;
        this.ip = ip;
        this.userAgent = userAgent;
        this.state = state;
        this.smsId = smsId;
        this.moContent = moContent;
        this.phone = phone;
        this.serviceCode = serviceCode;
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSmsId() {
        return smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }

    public String getMoContent() {
        return moContent;
    }

    public void setMoContent(String moContent) {
        this.moContent = moContent;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
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