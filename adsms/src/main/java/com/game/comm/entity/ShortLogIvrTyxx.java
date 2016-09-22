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
 * IVR(tyxx)反馈日志
 */
@Entity
@Table(name = "short_log_ivr_tyxx")
public class ShortLogIvrTyxx implements java.io.Serializable {

    private static final long serialVersionUID = -3237116586744705668L;

    private Long id;
    private Date accesstime; // 访问时间
    private String ip;
    private String userAgent;
    private String spnumber; // 通道长号码
    private String mobile; // 用户手机号
    private String linkid; // 唯一标识符
    private String starttime;
    private String stoptime;
    private String feetime; // 通话时长(分)

    public ShortLogIvrTyxx() {
        super();
    }

    public ShortLogIvrTyxx(Date accesstime, String ip, String userAgent, String spnumber, String mobile, String linkid, String starttime, String stoptime, String feetime) {
        super();
        this.accesstime = accesstime;
        this.ip = ip;
        this.userAgent = userAgent;
        this.spnumber = spnumber;
        this.mobile = mobile;
        this.linkid = linkid;
        this.starttime = starttime;
        this.stoptime = stoptime;
        this.feetime = feetime;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSpnumber() {
        return spnumber;
    }

    public void setSpnumber(String spnumber) {
        this.spnumber = spnumber;
    }

    public String getLinkid() {
        return linkid;
    }

    public void setLinkid(String linkid) {
        this.linkid = linkid;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getStoptime() {
        return stoptime;
    }

    public void setStoptime(String stoptime) {
        this.stoptime = stoptime;
    }

    public String getFeetime() {
        return feetime;
    }

    public void setFeetime(String feetime) {
        this.feetime = feetime;
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