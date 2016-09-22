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

@Entity
@Table(name = "ota_log_push_sms")
public class OtaLogPushSms implements java.io.Serializable {

    private static final long serialVersionUID = -3237116586744705668L;

    private Long id;
    private Date accesstime; // 访问时间
    private String deviceid; // 设备ID
    private String ip;
    private Long countryId; // 国家ID
    private String customerid; // 客户id
    private String imsi;
    private Long padvid;
    private Byte downtime;

    public OtaLogPushSms() {
        super();
    }

    public OtaLogPushSms(Date accesstime, String deviceid, String ip, Long countryId, String customerid, String imsi, Long padvid, Byte downtime) {
        super();
        this.accesstime = accesstime;
        this.deviceid = deviceid;
        this.ip = ip;
        this.countryId = countryId;
        this.customerid = customerid;
        this.imsi = imsi;
        this.padvid = padvid;
        this.downtime = downtime;
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

    public Long getPadvid() {
        return padvid;
    }

    public void setPadvid(Long padvid) {
        this.padvid = padvid;
    }

    public Byte getDowntime() {
        return downtime;
    }

    public void setDowntime(Byte downtime) {
        this.downtime = downtime;
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