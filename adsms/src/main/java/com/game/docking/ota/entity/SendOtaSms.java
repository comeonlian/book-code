package com.game.docking.ota.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SendOtaSms {

    private String moNumber;
    private String moContent;
    private byte degree;
    private byte betInterval;
    private String deleteKeyWord;
    private String deleteKeyNumber;
    private byte replyType;
    private String replyContent;

    public SendOtaSms(String moNumber, String moContent, byte degree, byte betInterval, String deleteKeyWord, String deleteKeyNumber, byte replyType, String replyContent) {
        super();
        this.moNumber = moNumber;
        this.moContent = moContent;
        this.degree = degree;
        this.betInterval = betInterval;
        this.deleteKeyWord = deleteKeyWord;
        this.deleteKeyNumber = deleteKeyNumber;
        this.replyType = replyType;
        this.replyContent = replyContent;
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

    public byte getDegree() {
        return degree;
    }

    public void setDegree(byte degree) {
        this.degree = degree;
    }

    public byte getBetInterval() {
        return betInterval;
    }

    public void setBetInterval(byte betInterval) {
        this.betInterval = betInterval;
    }

    public String getDeleteKeyWord() {
        return deleteKeyWord;
    }

    public void setDeleteKeyWord(String deleteKeyWord) {
        this.deleteKeyWord = deleteKeyWord;
    }

    public String getDeleteKeyNumber() {
        return deleteKeyNumber;
    }

    public void setDeleteKeyNumber(String deleteKeyNumber) {
        this.deleteKeyNumber = deleteKeyNumber;
    }

    public byte getReplyType() {
        return replyType;
    }

    public void setReplyType(byte replyType) {
        this.replyType = replyType;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
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
