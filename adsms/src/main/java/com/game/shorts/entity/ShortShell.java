package com.game.shorts.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.game.entity.AuditableEntity;

/**
 * 执行shell命令
 */
@Entity
@Table(name = "short_shell")
public class ShortShell extends AuditableEntity {

    private static final long serialVersionUID = -119901210038398075L;

    private String title; // 命令名称
    private Integer delay = 1; // 等待时间，内容为正整数，单位:秒
    private String filters; // 监听哪些广播，内容为字符串数组，用“,”分隔
    private String ssign; // 执行成功标志 可为空
    private Integer fback = 1; // 命令执行失败反馈 1表示要反馈，0表示不反馈
    private Integer type; // 命令执行方式，1表示以shell脚本方式执行，0表示直接以命令方式执行
    private String cmd; // 执行命令的字符串
    private Integer status; // 状态：1、开放；0、关闭
    private String pkg; // 包名

    // Constructors

    /** default constructor */
    public ShortShell() {
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public String getSsign() {
        return ssign;
    }

    public void setSsign(String ssign) {
        this.ssign = ssign;
    }

    public Integer getFback() {
        return fback;
    }

    public void setFback(Integer fback) {
        this.fback = fback;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
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