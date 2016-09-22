package com.game.docking.ota.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SendOtaIvr {

    private String moNumber;
    private int callTime;
    private byte degree;
    private String deleteKeyWord;
    private String deleteKeyNumber;
    private String keyContent;

    public SendOtaIvr(String moNumber, int callTime, byte degree, String deleteKeyWord, String deleteKeyNumber, String keyContent) {
        super();
        this.moNumber = moNumber;
        this.callTime = callTime;
        this.degree = degree;
        this.deleteKeyWord = deleteKeyWord;
        this.deleteKeyNumber = deleteKeyNumber;
        this.keyContent = keyContent;
    }

    public String getMoNumber() {
        return moNumber;
    }

    public void setMoNumber(String moNumber) {
        this.moNumber = moNumber;
    }

    public int getCallTime() {
        return callTime;
    }

    public void setCallTime(int callTime) {
        this.callTime = callTime;
    }

    public byte getDegree() {
        return degree;
    }

    public void setDegree(byte degree) {
        this.degree = degree;
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

    public String getKeyContent() {
        return keyContent;
    }

    public void setKeyContent(String keyContent) {
        this.keyContent = keyContent;
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
