package com.t35.pojo;

/**
 * Usercredit entity. @author MyEclipse Persistence Tools
 */

public class Usercredit implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private Integer creditLine;
	private Integer borrowTime;
	private Integer borrowSuccessTime;
	private Integer borrowFinishTime;
	private String creditMoney;
	private String useCreditMoney;
	private String payBack;
	private Integer overTime;

	// Constructors

	/** default constructor */
	public Usercredit() {
	}

	/** full constructor */
	public Usercredit(Integer userId, Integer creditLine, Integer borrowTime,
			Integer borrowSuccessTime, Integer borrowFinishTime,
			String creditMoney, String useCreditMoney, String payBack,
			Integer overTime) {
		this.userId = userId;
		this.creditLine = creditLine;
		this.borrowTime = borrowTime;
		this.borrowSuccessTime = borrowSuccessTime;
		this.borrowFinishTime = borrowFinishTime;
		this.creditMoney = creditMoney;
		this.useCreditMoney = useCreditMoney;
		this.payBack = payBack;
		this.overTime = overTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCreditLine() {
		return this.creditLine;
	}

	public void setCreditLine(Integer creditLine) {
		this.creditLine = creditLine;
	}

	public Integer getBorrowTime() {
		return this.borrowTime;
	}

	public void setBorrowTime(Integer borrowTime) {
		this.borrowTime = borrowTime;
	}

	public Integer getBorrowSuccessTime() {
		return this.borrowSuccessTime;
	}

	public void setBorrowSuccessTime(Integer borrowSuccessTime) {
		this.borrowSuccessTime = borrowSuccessTime;
	}

	public Integer getBorrowFinishTime() {
		return this.borrowFinishTime;
	}

	public void setBorrowFinishTime(Integer borrowFinishTime) {
		this.borrowFinishTime = borrowFinishTime;
	}

	public String getCreditMoney() {
		return this.creditMoney;
	}

	public void setCreditMoney(String creditMoney) {
		this.creditMoney = creditMoney;
	}

	public String getUseCreditMoney() {
		return this.useCreditMoney;
	}

	public void setUseCreditMoney(String useCreditMoney) {
		this.useCreditMoney = useCreditMoney;
	}

	public String getPayBack() {
		return this.payBack;
	}

	public void setPayBack(String payBack) {
		this.payBack = payBack;
	}

	public Integer getOverTime() {
		return this.overTime;
	}

	public void setOverTime(Integer overTime) {
		this.overTime = overTime;
	}

}