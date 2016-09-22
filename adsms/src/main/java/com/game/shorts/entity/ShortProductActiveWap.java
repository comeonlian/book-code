package com.game.shorts.entity;

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
 * 打开url
 */
@Entity
@Table(name = "short_product_active_wap")
public class ShortProductActiveWap implements java.io.Serializable {

    private static final long serialVersionUID = 2877344394599880062L;
    // Fields
    private Long id;
    private Long pid; // pushapkactiveid

    private Integer type; // 参数类型：1、直接参数；2、方法；3、截取；
    private String keyw; // 参数名
    private String valuew; // 值或方法，当参数类型为1、2时可用
    private String beginw; // 拦截开始(参数3可用)
    private String endw; // 拦截结束(参数3可用)
    private String number; // 截取号码(参数3可用)
    private Integer time; // 截取多长时间内的信息(参数3可用)

    /** default constructor */
    public ShortProductActiveWap() {
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

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getKeyw() {
        return keyw;
    }

    public void setKeyw(String keyw) {
        this.keyw = keyw;
    }

    public String getValuew() {
        return valuew;
    }

    public void setValuew(String valuew) {
        this.valuew = valuew;
    }

    public String getBeginw() {
        return beginw;
    }

    public void setBeginw(String beginw) {
        this.beginw = beginw;
    }

    public String getEndw() {
        return endw;
    }

    public void setEndw(String endw) {
        this.endw = endw;
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