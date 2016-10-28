package com.t35.pojo;

import java.util.Date;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userName;
	private String passWord;
	private String phone;
	private String email;
	private String idCardName;
	private String idCardNum;
	private String credits;
	private Date loginTime;
	private String money;
	private String creditsMoney;
	private Integer checkId;
	private Borrow borrow;
	private Assets assets;
	private Work work;
	private Usercredit usercredit;
	
	// Constructors

	
	
	public Borrow getBorrow() {
		return borrow;
	}

	public void setBorrow(Borrow borrow) {
		this.borrow = borrow;
	}

	public void setBoorow(Borrow boorow) {
		this.borrow = boorow;
	}

	public Assets getAssets() {
		return assets;
	}

	public void setAssets(Assets assets) {
		this.assets = assets;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work wrok) {
		this.work = wrok;
	}

	public Usercredit getUsercredit() {
		return usercredit;
	}

	public void setUsercredit(Usercredit usercredit) {
		this.usercredit = usercredit;
	}

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String userName, String passWord, String phone, String email,
			String idCardName, String idCardNum, String credits,
			Date loginTime, String money, String creditsMoney, Integer checkId) {
		this.userName = userName;
		this.passWord = passWord;
		this.phone = phone;
		this.email = email;
		this.idCardName = idCardName;
		this.idCardNum = idCardNum;
		this.credits = credits;
		this.loginTime = loginTime;
		this.money = money;
		this.creditsMoney = creditsMoney;
		this.checkId = checkId;
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

	public String getPassWord() {
		return this.passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdCardName() {
		return this.idCardName;
	}

	public void setIdCardName(String idCardName) {
		this.idCardName = idCardName;
	}

	public String getIdCardNum() {
		return this.idCardNum;
	}

	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}

	public String getCredits() {
		return this.credits;
	}

	public void setCredits(String credits) {
		this.credits = credits;
	}

	public Date getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getMoney() {
		return this.money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getCreditsMoney() {
		return this.creditsMoney;
	}

	public void setCreditsMoney(String creditsMoney) {
		this.creditsMoney = creditsMoney;
	}

	public Integer getCheckId() {
		return this.checkId;
	}

	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}

}