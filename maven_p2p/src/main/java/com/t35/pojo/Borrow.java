package com.t35.pojo;

import java.util.Date;

/**
 * Borrow entity. @author MyEclipse Persistence Tools
 */

public class Borrow implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private Double interestRate;
	private Date finishDate;
	private String borrowMoney;
	private Integer borrowType;
	private Date createDate;
	private Integer reimbursement;
	private Integer userId;
	private Integer bankCardId;
	private String tagetMoney;

	// Constructors

	/** default constructor */
	public Borrow() {
	}

	/** full constructor */
	public Borrow(String title, Double interestRate, Date finishDate,
			String borrowMoney, Integer borrowType, Date createDate,
			Integer reimbursement, Integer userId, Integer bankCardId,
			String tagetMoney) {
		this.title = title;
		this.interestRate = interestRate;
		this.finishDate = finishDate;
		this.borrowMoney = borrowMoney;
		this.borrowType = borrowType;
		this.createDate = createDate;
		this.reimbursement = reimbursement;
		this.userId = userId;
		this.bankCardId = bankCardId;
		this.tagetMoney = tagetMoney;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getInterestRate() {
		return this.interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	public Date getFinishDate() {
		return this.finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public String getBorrowMoney() {
		return this.borrowMoney;
	}

	public void setBorrowMoney(String borrowMoney) {
		this.borrowMoney = borrowMoney;
	}

	public Integer getBorrowType() {
		return this.borrowType;
	}

	public void setBorrowType(Integer borrowType) {
		this.borrowType = borrowType;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getReimbursement() {
		return this.reimbursement;
	}

	public void setReimbursement(Integer reimbursement) {
		this.reimbursement = reimbursement;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getBankCardId() {
		return this.bankCardId;
	}

	public void setBankCardId(Integer bankCardId) {
		this.bankCardId = bankCardId;
	}

	public String getTagetMoney() {
		return this.tagetMoney;
	}

	public void setTagetMoney(String tagetMoney) {
		this.tagetMoney = tagetMoney;
	}

}