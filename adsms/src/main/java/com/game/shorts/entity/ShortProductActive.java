package com.game.shorts.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.game.entity.AuditableEntity;

/**
 * 激活信息
 */
@Entity
@Table(name = "short_product_active")
public class ShortProductActive extends AuditableEntity {

    /**
	 * 
	 */
    private static final long serialVersionUID = 8269392714938435087L;
    // Fields

    private Long pid; // push资源主键
    private Integer ordernum; // 排序号
    private Integer activeType = 1; // 模拟类型：1、回复sms；2、wap；
    private Integer delay; // 执行前等待
    private String url; // URL(WAP)
    private String requestMethod; // 请求方式(WAP)
    private String replyKeyNumber; // '回复关键号码'(SMS)
    private String replyKeyWord; // '回复关键字'(SMS)
    private String replyNumber; // 回复号码/拦截开始(SMS)
    private String replyContent; // 回复内容/拦截结束(SMS)
    private Integer replyType; // 回复类型:1、拦截；2、内容(SMS)

    // Constructors

    /** default constructor */
    public ShortProductActive() {
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public Integer getActiveType() {
        return activeType;
    }

    public void setActiveType(Integer activeType) {
        this.activeType = activeType;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getReplyKeyNumber() {
        return replyKeyNumber;
    }

    public void setReplyKeyNumber(String replyKeyNumber) {
        this.replyKeyNumber = replyKeyNumber;
    }

    public String getReplyKeyWord() {
        return replyKeyWord;
    }

    public void setReplyKeyWord(String replyKeyWord) {
        this.replyKeyWord = replyKeyWord;
    }

    public String getReplyNumber() {
        return replyNumber;
    }

    public void setReplyNumber(String replyNumber) {
        this.replyNumber = replyNumber;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Integer getReplyType() {
        return replyType;
    }

    public void setReplyType(Integer replyType) {
        this.replyType = replyType;
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