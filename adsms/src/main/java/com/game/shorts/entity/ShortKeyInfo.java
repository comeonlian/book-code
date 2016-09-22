package com.game.shorts.entity;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.game.entity.AuditableEntity;

@Entity
@Table(name = "short_keyinfo")
public class ShortKeyInfo extends AuditableEntity {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1773956965526000132L;

    // Fields
    private String comtent; // 比对内容
    private String keytent; // 关键字
    private String advkey; // 广告键
    private String advtent; // 广告值
    private String advtip; // 广告开始
    private String advend; // 广告结束
    private String delkey; // 禁止删除键
    private String customids; // 第三方信息标志 “1”、移动；“2”、联通

    private List<Map<String, Object>> tipend; // 接口数据

    @Transient
    public List<Map<String, Object>> getTipend() {
        return tipend;
    }

    public void setTipend(List<Map<String, Object>> tipend) {
        this.tipend = tipend;
    }

    public ShortKeyInfo() {
    }

    public ShortKeyInfo(String comtent, String keytent, String advkey, String advtent, String advtip, String advend, String delkey) {
        this.comtent = comtent;
        this.keytent = keytent;
        this.advkey = advkey;
        this.advtent = advtent;
        this.advtip = advtip;
        this.advend = advend;
        this.delkey = delkey;
    }

    @Column(name = "COMTENT")
    public String getComtent() {
        return comtent;
    }

    public void setComtent(String comtent) {
        this.comtent = comtent;
    }

    @Column(name = "KEYTENT")
    public String getKeytent() {
        return keytent;
    }

    public void setKeytent(String keytent) {
        this.keytent = keytent;
    }

    @Column(name = "ADVKEY")
    public String getAdvkey() {
        return advkey;
    }

    public void setAdvkey(String advkey) {
        this.advkey = advkey;
    }

    @Column(name = "ADVTENT")
    public String getAdvtent() {
        return advtent;
    }

    public void setAdvtent(String advtent) {
        this.advtent = advtent;
    }

    @Column(name = "ADVTIP")
    public String getAdvtip() {
        return advtip;
    }

    public void setAdvtip(String advtip) {
        this.advtip = advtip;
    }

    @Column(name = "ADVEND")
    public String getAdvend() {
        return advend;
    }

    public void setAdvend(String advend) {
        this.advend = advend;
    }

    @Column(name = "DELKEY")
    public String getDelkey() {
        return delkey;
    }

    public void setDelkey(String delkey) {
        this.delkey = delkey;
    }

    public String getCustomids() {
        return customids;
    }

    public void setCustomids(String customids) {
        this.customids = customids;
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
