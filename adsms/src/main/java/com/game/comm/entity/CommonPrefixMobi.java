package com.game.comm.entity;

import java.io.Serializable;

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
 * 手机号
 */
@Entity
@Table(name = "common_prefix_mobi")
public class CommonPrefixMobi implements Serializable {

    private static final long serialVersionUID = -5567779058266190768L;
    private Long id;
    private String prefixMobi;
    private Long cityId;
    private Long provinceId;
    private Integer provider; // 运营商

    public CommonPrefixMobi() {
        super();
    }

    public CommonPrefixMobi(String prefixMobi, Long cityId, Long provinceId, Integer provider) {
        super();
        this.prefixMobi = prefixMobi;
        this.cityId = cityId;
        this.provinceId = provinceId;
        this.provider = provider;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrefixMobi() {
        return prefixMobi;
    }

    public void setPrefixMobi(String prefixMobi) {
        this.prefixMobi = prefixMobi;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getProvider() {
        return provider;
    }

    public void setProvider(Integer provider) {
        this.provider = provider;
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