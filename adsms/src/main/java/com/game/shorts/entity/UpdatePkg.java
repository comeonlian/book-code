package com.game.shorts.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.game.entity.AuditableEntity;

/**
 * 更新包
 * 
 */
@Entity
@Table(name = "short_update_pkg")
public class UpdatePkg extends AuditableEntity {

    private static final long serialVersionUID = -8119039436246769032L;
    private String apkname; // 应用名
    private int status; // 状态， 0 下线 1 上线 -1删除
    private Long apksize; // apk大小
    private String md5; // MD5值
    private String packagename; // apk包名
    private String mainClassName; // APK主类
    private Integer apkVersion; // apk版本
    private String filepath; // apk文件保存路径
    private String downurl; // apk下载路径
    private String customers; // 客户列表

    public String getApkname() {
        return apkname;
    }

    public void setApkname(String apkname) {
        this.apkname = apkname;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getApksize() {
        return apksize;
    }

    public void setApksize(Long apksize) {
        this.apksize = apksize;
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public Integer getApkVersion() {
        return apkVersion;
    }

    public void setApkVersion(Integer apkVersion) {
        this.apkVersion = apkVersion;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getDownurl() {
        return downurl;
    }

    public void setDownurl(String downurl) {
        this.downurl = downurl;
    }

    public String getMainClassName() {
        return mainClassName;
    }

    public void setMainClassName(String mainClassName) {
        this.mainClassName = mainClassName;
    }

    public String getCustomers() {
        return customers;
    }

    public void setCustomers(String customers) {
        this.customers = customers;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append(this.id).append(this.apkname).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UpdatePkg)) {
            return false;
        }

        UpdatePkg apk = (UpdatePkg) o;

        return !(apkname != null ? !apkname.equals(apk.apkname) : apk.apkname != null);
    }

    @Override
    public int hashCode() {
        return (apkname != null ? apkname.hashCode() : 0);
    }

}