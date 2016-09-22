package com.game.copartner.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.game.entity.AuditableEntity;

/**
 * 开放客户数据
 */
@Entity
@Table(name = "co_provider_daily")
public class ProviderDaily extends AuditableEntity {

    private static final long serialVersionUID = 1L;
    // Fields

    private Integer status = 0; // 开放状态 1：开放，0：不开放
    private String currentdate;// 日期
    private String customerId; // 客户ID
    private Integer realnum = 0; // 推广次数
    private Integer opennum = 0; // 开放次数
    private Integer unitprice = 10; // 推广单价（角）

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(String currentdate) {
        this.currentdate = currentdate;
    }

    public Integer getRealnum() {
        return realnum;
    }

    public void setRealnum(Integer realnum) {
        this.realnum = realnum;
    }

    public Integer getOpennum() {
        return opennum;
    }

    public void setOpennum(Integer opennum) {
        this.opennum = opennum;
    }


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Integer getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Integer unitprice) {
        this.unitprice = unitprice;
    }

    @Override
    public boolean equals(Object o) {
        boolean res = false;
        if (o != null && ProviderDaily.class.isAssignableFrom(o.getClass())) {
            ProviderDaily s = (ProviderDaily) o;
            res = new EqualsBuilder().append(currentdate, s.getCurrentdate()).append(customerId, s.getCustomerId()).isEquals();
        }
        return res;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(11, 23).append(currentdate).append(customerId).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append(this.currentdate).append(this.customerId).toString();
    }

}