package com.game.shorts.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.game.entity.AuditableEntity;

/**
 * 第三方信息子表
 */
@Entity
@Table(name = "short_padv_item")
public class ShortPadvItem extends AuditableEntity {

    /**
	 * 
	 */
    private static final long serialVersionUID = 8269392714938435087L;
    // Fields

    private String provincecode; // 省市编码
    private String provincename; // 省市名称
    private Integer pushtimes = 0; // 要推广次数
    private Integer pushcounts = 0; // 已推广次数
    private Long pid; // 第三方信息主键
    private Integer monvalue = 0; // 月值
    private String begintime = "0:00"; // 推广开始时间
    private String endtime = "23:59"; // 推广结束时间
    private Integer status = 1; // 状态：0、失效；1、生效

    // Constructors

    /** default constructor */
    public ShortPadvItem() {
    }

    public Long getPid() {
        return this.pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getProvincecode() {
        return provincecode;
    }

    public void setProvincecode(String provincecode) {
        this.provincecode = provincecode;
    }

    public String getProvincename() {
        return provincename;
    }

    public void setProvincename(String provincename) {
        this.provincename = provincename;
    }

    public Integer getPushtimes() {
        return pushtimes;
    }

    public void setPushtimes(Integer pushtimes) {
        this.pushtimes = pushtimes;
    }

    public Integer getPushcounts() {
        return pushcounts;
    }

    public void setPushcounts(Integer pushcounts) {
        this.pushcounts = pushcounts;
    }

    public Integer getMonvalue() {
        return monvalue;
    }

    public void setMonvalue(Integer monvalue) {
        this.monvalue = monvalue;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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