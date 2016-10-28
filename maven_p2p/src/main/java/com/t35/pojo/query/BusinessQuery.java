package com.t35.pojo.query;

import java.util.Date;

public class BusinessQuery extends BaseQuery{
	private Integer id;
	private Integer type;
	private Date createTime;
	private String money;
	private Integer userId;
	public BusinessQuery() {
		
	}
	public BusinessQuery(Integer id, Integer type, Date createTime,
			String money, Integer userId) {
		super();
		this.id = id;
		this.type = type;
		this.createTime = createTime;
		this.money = money;
		this.userId = userId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
