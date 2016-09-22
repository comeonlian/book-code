package com.game.ota.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.game.entity.AuditableEntity;

/**
 * 产品:sms
 */
@Entity
@Table(name = "ota_product_sms")
public class OtaProductSms extends AuditableEntity {

    // Fields
    /**
	 * 
	 */
    private static final long serialVersionUID = -119901210038398075L;

    private String serviceCode; // 通道代码
    private String serviceName; // 通道名称
    private Integer price; // '单价(元)',

    private String moNumber; // 通道号码
    private String moContent; // 指令
    private String deleteKeyNumber; // '删除关键号码',
    private String deleteKeyWord; // '删除关键字',
    private Byte degree = 1; // 次数
    private Byte betInterval = 5; // 发送间隔
    private Byte replyType; // 回复类型：0、随机；1、答题；2、截取
    private String content; // 内容

    private Integer priority = 10; // 优先级
    private Integer provider; // '运营商：1、移动；2、联通；3、电信',
    private Integer status = 1; // 状态：0、失效；1、生效
    private String begintime = "00:00:00"; // 开始时刻
    private String endtime = "23:59:59"; // 结束时刻
    private Date begindate; // 开始时间
    private Date enddate; // 结束时间
    private Integer feeDays = 7; // 计费间隔时间(天)
    private Integer confignum = 1000; // 配置次数
    private Integer downnum = 0; // 已下载次数
    private String citys; // 省市
    private String customid; // 客户ID

    // Constructors

    /** default constructor */
    public OtaProductSms() {
    }

    public String getCitys() {
        return this.citys;
    }

    public void setCitys(String citys) {
        this.citys = citys;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getProvider() {
        return provider;
    }

    public void setProvider(Integer provider) {
        this.provider = provider;
    }

    public String getCustomid() {
        return customid;
    }

    public void setCustomid(String customid) {
        this.customid = customid;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getMoNumber() {
        return moNumber;
    }

    public void setMoNumber(String moNumber) {
        this.moNumber = moNumber;
    }

    public String getMoContent() {
        return moContent;
    }

    public void setMoContent(String moContent) {
        this.moContent = moContent;
    }

    public String getDeleteKeyNumber() {
        return deleteKeyNumber;
    }

    public void setDeleteKeyNumber(String deleteKeyNumber) {
        this.deleteKeyNumber = deleteKeyNumber;
    }

    public String getDeleteKeyWord() {
        return deleteKeyWord;
    }

    public void setDeleteKeyWord(String deleteKeyWord) {
        this.deleteKeyWord = deleteKeyWord;
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

    public Integer getFeeDays() {
        return feeDays;
    }

    public void setFeeDays(Integer feeDays) {
        this.feeDays = feeDays;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getConfignum() {
        return confignum;
    }

    public void setConfignum(Integer confignum) {
        this.confignum = confignum;
    }

    public Integer getDownnum() {
        return downnum;
    }

    public void setDownnum(Integer downnum) {
        this.downnum = downnum;
    }

    @Temporal(TemporalType.DATE)
    public Date getBegindate() {
        return begindate;
    }

    public void setBegindate(Date begindate) {
        this.begindate = begindate;
    }

    @Temporal(TemporalType.DATE)
    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Byte getDegree() {
        return degree;
    }

    public void setDegree(Byte degree) {
        this.degree = degree;
    }

    public Byte getReplyType() {
        return replyType;
    }

    public void setReplyType(Byte replyType) {
        this.replyType = replyType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getBetInterval() {
        return betInterval;
    }

    public void setBetInterval(Byte betInterval) {
        this.betInterval = betInterval;
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