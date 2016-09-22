package com.game.shorts.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.game.entity.AuditableEntity;

/**
 * 第三方信息
 */
@Entity
@Table(name = "short_padv")
public class ShortPadv extends AuditableEntity {

    // Fields
    /**
	 * 
	 */
    private static final long serialVersionUID = -119901210038398075L;

    private String pnum; // 号码
    private String content; // 内容
    private Integer value; // 有效值
    private String citys; // 区域
    private Integer status; // 状态：0、失效；1、生效
    private Integer provider; // 运营商 0:通用 1：移动，2：联通 imsi前5位 移动 ：46000，46002，46007 联通：46001
    private Integer ordernum; // 优先级
    private Integer delay; // 延迟多长时间执行

    private String padvname; // 别名
    private String customid; // 客户ID

    // Constructors

    /** default constructor */
    public ShortPadv() {
    }

    public String getCitys() {
        return this.citys;
    }

    public void setCitys(String citys) {
        this.citys = citys;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPnum() {
        return pnum;
    }

    public void setPnum(String pnum) {
        this.pnum = pnum;
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public Integer getProvider() {
        return provider;
    }

    public void setProvider(Integer provider) {
        this.provider = provider;
    }

    public String getPadvname() {
        return padvname;
    }

    public void setPadvname(String padvname) {
        this.padvname = padvname;
    }

    public String getCustomid() {
        return customid;
    }

    public void setCustomid(String customid) {
        this.customid = customid;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
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