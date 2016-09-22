package com.game.shorts.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


public class FkApk implements Serializable{
	
	private static final long serialVersionUID = 4672743768994156182L;
	
	private Integer id;
	private String apkDigest;
	private String apkName;
	private String apkMd5;
	private String apkVersion;
	private String systemVersion;
	private String apkPath;
	private String apkUrl;
	private String apkIcon;
	private String iconMd5;
	private String apkSize;
	private Date createTime;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getApkName() {
		return apkName;
	}
	public void setApkName(String apkName) {
		this.apkName = apkName;
	}
	public String getApkVersion() {
		return apkVersion;
	}
	public void setApkVersion(String apkVersion) {
		this.apkVersion = apkVersion;
	}
	public String getApkPath() {
		return apkPath;
	}
	public void setApkPath(String apkPath) {
		this.apkPath = apkPath;
	}
	public String getApkUrl() {
		return apkUrl;
	}
	public void setApkUrl(String apkUrl) {
		this.apkUrl = apkUrl;
	}
	public String getApkSize() {
		return apkSize;
	}
	public void setApkSize(String apkSize) {
		this.apkSize = apkSize;
	}
	public String getSystemVersion() {
		return systemVersion;
	}
	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getApkIcon() {
		return apkIcon;
	}
	public void setApkIcon(String apkIcon) {
		this.apkIcon = apkIcon;
	}
	public String getApkDigest() {
		return apkDigest;
	}
	public void setApkDigest(String apkDigest) {
		this.apkDigest = apkDigest;
	}
	public String getApkMd5() {
		return apkMd5;
	}
	public void setApkMd5(String apkMd5) {
		this.apkMd5 = apkMd5;
	}
	public String getIconMd5() {
		return iconMd5;
	}
	public void setIconMd5(String iconMd5) {
		this.iconMd5 = iconMd5;
	}
	
}
