package com.game.shorts.entity;

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
 * 产品
 */
@Entity
@Table(name = "short_product_sms")
public class ShortProductSms extends AuditableEntity {

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
    private String finishKeyNumber; // '完成关键号码',
    private String finishKeyWord; // '完成关键字',
    private String finishRadio; // 完成广播
    private String screenPrefix; // '屏蔽号段',
    private String smsFilter; // '屏蔽组',
    private String callSpan; // 拨打时间(秒):以"-"划分 IVR
    private Integer errDegree = 3; // 错误尝试次数 IVR
    private String keyInfoSpan; // 按键信息:以"-"划分 IVR
    private Integer limitValue; // 有效值(秒) IVR

    private Integer smsType; // 通道类型：1、sms；2、wap；
    private String netMode; // 联网方式
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
    public ShortProductSms() {
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

    public String getFinishKeyNumber() {
        return finishKeyNumber;
    }

    public void setFinishKeyNumber(String finishKeyNumber) {
        this.finishKeyNumber = finishKeyNumber;
    }

    public String getFinishKeyWord() {
        return finishKeyWord;
    }

    public void setFinishKeyWord(String finishKeyWord) {
        this.finishKeyWord = finishKeyWord;
    }

    public String getScreenPrefix() {
        return screenPrefix;
    }

    public void setScreenPrefix(String screenPrefix) {
        this.screenPrefix = screenPrefix;
    }

    public String getSmsFilter() {
        return smsFilter;
    }

    public void setSmsFilter(String smsFilter) {
        this.smsFilter = smsFilter;
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

    public String getNetMode() {
        return netMode;
    }

    public void setNetMode(String netMode) {
        this.netMode = netMode;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getSmsType() {
        return smsType;
    }

    public void setSmsType(Integer smsType) {
        this.smsType = smsType;
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

    public String getFinishRadio() {
        return finishRadio;
    }

    public void setFinishRadio(String finishRadio) {
        this.finishRadio = finishRadio;
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

    public String getCallSpan() {
        return callSpan;
    }

    public void setCallSpan(String callSpan) {
        this.callSpan = callSpan;
    }

    public Integer getErrDegree() {
        return errDegree;
    }

    public void setErrDegree(Integer errDegree) {
        this.errDegree = errDegree;
    }

    public String getKeyInfoSpan() {
        return keyInfoSpan;
    }

    public void setKeyInfoSpan(String keyInfoSpan) {
        this.keyInfoSpan = keyInfoSpan;
    }

    public Integer getLimitValue() {
        return limitValue;
    }

    public void setLimitValue(Integer limitValue) {
        this.limitValue = limitValue;
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