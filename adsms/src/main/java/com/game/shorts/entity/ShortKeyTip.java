package com.game.shorts.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.game.entity.AuditableEntity;

@Entity
@Table(name = "short_keytip")
public class ShortKeyTip extends AuditableEntity {

    /**
	 * 
	 */
    private static final long serialVersionUID = 2091249034844850358L;

    private Long nfid; // 所属关键字主键
    private String kbegin; // 开始
    private String keyend; // 结束
    private Integer del; // 是否回删 1 是，0否
    private Integer ret; // 是否回传 1是，0否
    private String smes; // 号码 逗号分隔
    private String cons; // 内容 逗号
    private Integer status; // 状态:1、生效；0、失效

    public ShortKeyTip() {
    }

    public ShortKeyTip(Long nfid, String kbegin, String keyend) {
        this.nfid = nfid;
        this.kbegin = kbegin;
        this.keyend = keyend;
    }

    @Column(name = "NFID")
    public Long getNfid() {
        return nfid;
    }

    public void setNfid(Long nfid) {
        this.nfid = nfid;
    }

    @Column(name = "KBEGIN", length = 50)
    public String getKbegin() {
        return kbegin;
    }

    public void setKbegin(String kbegin) {
        this.kbegin = kbegin;
    }

    @Column(name = "KEYEND", length = 50)
    public String getKeyend() {
        return keyend;
    }

    public void setKeyend(String keyend) {
        this.keyend = keyend;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public Integer getRet() {
        return ret;
    }

    public void setRet(Integer ret) {
        this.ret = ret;
    }

    public String getSmes() {
        return smes;
    }

    public void setSmes(String smes) {
        this.smes = smes;
    }

    public String getCons() {
        return cons;
    }

    public void setCons(String cons) {
        this.cons = cons;
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
