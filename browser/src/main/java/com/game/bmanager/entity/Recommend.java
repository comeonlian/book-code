package com.game.bmanager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.game.entity.AuditableEntity;

/**
 * 流行推荐
 */
@Entity
@Table(name = "browser_recommend")
public class Recommend extends AuditableEntity {

    private static final long serialVersionUID = -8070239655272511289L;

    private String title; // 名称
    private String cdesc; // 描述
    private String realDownUrl; // 真实下载地址
    private String dumpDownUrl; // 跳转下载地址
    private String iconUrl; // icon地址

    // 客户过滤条件
    private String customers; // 客户编号
    private Integer status = 0; // 是否运营 —— 1、开启；0、关闭; -1 为已删除状态(做逻辑删除)
    private String begintime = "00:00:00"; // 指定开始时间
    private String endtime = "23:59:59"; // 指定结束时间
    private String citys; // 开通省市

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRealDownUrl() {
        return realDownUrl;
    }

    public void setRealDownUrl(String realDownUrl) {
        this.realDownUrl = realDownUrl;
    }

    public String getDumpDownUrl() {
        return dumpDownUrl;
    }

    public void setDumpDownUrl(String dumpDownUrl) {
        this.dumpDownUrl = dumpDownUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getCustomers() {
        return customers;
    }

    public void setCustomers(String customers) {
        this.customers = customers;
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