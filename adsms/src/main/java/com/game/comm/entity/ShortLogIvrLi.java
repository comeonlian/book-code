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
 * IVR(LI)反馈日志
 */
@Entity
@Table(name = "short_log_ivr_li")
public class ShortLogIvrLi implements java.io.Serializable {

    private static final long serialVersionUID = -3237116586744705668L;

    private Long id;
    private Date accesstime; // 访问时间
    private String ip;
    private String userAgent;
    private String ivrId; // 一个唯一的流水号
    private String mobile; // 主叫号码
    private String calledNumber; // 被叫号码
    private String timeBegin; // 记费起始时间(YYYY-mm-dd HH:ii:s)
    private String timeEnd; // 记费结束时间(YYYY-mm-dd HH:ii:s)
    private String time; // 记费时长(单位：分钟)

    public ShortLogIvrLi() {
        super();
    }

    public ShortLogIvrLi(Date accesstime, String ip, String userAgent, String ivrId, String mobile, String calledNumber, String timeBegin, String timeEnd, String time) {
        super();
        this.accesstime = accesstime;
        this.ip = ip;
        this.userAgent = userAgent;
        this.ivrId = ivrId;
        this.mobile = mobile;
        this.calledNumber = calledNumber;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
        this.time = time;
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

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getIvrId() {
        return ivrId;
    }

    public void setIvrId(String ivrId) {
        this.ivrId = ivrId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCalledNumber() {
        return calledNumber;
    }

    public void setCalledNumber(String calledNumber) {
        this.calledNumber = calledNumber;
    }

    public String getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(String timeBegin) {
        this.timeBegin = timeBegin;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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