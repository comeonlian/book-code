package com.game.comm.entity;

import javax.persistence.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.game.entity.AuditableEntity;

/**
 * The persistent class for the tbl_custom_apk database table.
 * 
 */
@Entity
@Table(name = "common_apk")
public class Apkr extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    @Column(name = "apk_icon", length = 255)
    private String apkIcon;

    @Column(name = "apk_icon_path", length = 255)
    private String apkIconPath;

    @Column(name = "apk_icon_md5", length = 32)
    private String apkIconMd5;

    @Column(name = "apk_md5", length = 32)
    private String apkMd5;

    @Column(name = "apk_name", length = 20)
    private String apkName;

    @Column(name = "apk_package", length = 255)
    private String apkPackage;

    @Column(name = "apk_path", length = 255)
    private String apkPath;

    @Column(name = "apk_size", length = 20)
    private String apkSize;

    @Column(name = "apk_url", length = 255)
    private String apkUrl;

    @Column(name = "apk_version", length = 20)
    private String apkVersion;

    @Column(length = 255)
    private String keyword;

    private Integer status = 1;

    public Apkr() {
    }

    public String getApkIcon() {
        return this.apkIcon;
    }

    public void setApkIcon(String apkIcon) {
        this.apkIcon = apkIcon;
    }

    public String getApkIconMd5() {
        return this.apkIconMd5;
    }

    public void setApkIconMd5(String apkIconMd5) {
        this.apkIconMd5 = apkIconMd5;
    }

    public String getApkMd5() {
        return this.apkMd5;
    }

    public void setApkMd5(String apkMd5) {
        this.apkMd5 = apkMd5;
    }

    public String getApkName() {
        return this.apkName;
    }

    public void setApkName(String apkName) {
        this.apkName = apkName;
    }

    public String getApkPackage() {
        return this.apkPackage;
    }

    public void setApkPackage(String apkPackage) {
        this.apkPackage = apkPackage;
    }

    public String getApkPath() {
        return this.apkPath;
    }

    public void setApkPath(String apkPath) {
        this.apkPath = apkPath;
    }

    public String getApkSize() {
        return this.apkSize;
    }

    public void setApkSize(String apkSize) {
        this.apkSize = apkSize;
    }

    public String getApkUrl() {
        return this.apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public String getApkVersion() {
        return this.apkVersion;
    }

    public void setApkVersion(String apkVersion) {
        this.apkVersion = apkVersion;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer state) {
        this.status = state;
    }

    public String getApkIconPath() {
        return apkIconPath;
    }

    public void setApkIconPath(String apkIconPath) {
        this.apkIconPath = apkIconPath;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
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