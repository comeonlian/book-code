package com.t35.pojo;

import java.util.Date;

/**
 * Backstage entity. @author MyEclipse Persistence Tools
 */

public class Backstage implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userName;
	private String password;
	private Date createTime;
	private Integer userType;
	private String userNum;
	private String realName;

	// Constructors

	/** default constructor */
	public Backstage() {
	}

	/** full constructor */
	public Backstage(String userName, String password, Date createTime,
			Integer userType, String userNum, String realName) {
		this.userName = userName;
		this.password = password;
		this.createTime = createTime;
		this.userType = userType;
		this.userNum = userNum;
		this.realName = realName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUserType() {
		return this.userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getUserNum() {
		return this.userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

}