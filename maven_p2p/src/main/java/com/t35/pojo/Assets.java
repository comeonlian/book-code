package com.t35.pojo;

/**
 * Assets entity. @author MyEclipse Persistence Tools
 */

public class Assets implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private Integer houseProperty;
	private String housePropertyImg;
	private Integer mortgage;
	private Integer car;
	private String carImg;

	// Constructors

	/** default constructor */
	public Assets() {
	}

	/** full constructor */
	public Assets(Integer userId, Integer houseProperty,
			String housePropertyImg, Integer mortgage, Integer car,
			String carImg) {
		this.userId = userId;
		this.houseProperty = houseProperty;
		this.housePropertyImg = housePropertyImg;
		this.mortgage = mortgage;
		this.car = car;
		this.carImg = carImg;
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

	public Integer getHouseProperty() {
		return this.houseProperty;
	}

	public void setHouseProperty(Integer houseProperty) {
		this.houseProperty = houseProperty;
	}

	public String getHousePropertyImg() {
		return this.housePropertyImg;
	}

	public void setHousePropertyImg(String housePropertyImg) {
		this.housePropertyImg = housePropertyImg;
	}

	public Integer getMortgage() {
		return this.mortgage;
	}

	public void setMortgage(Integer mortgage) {
		this.mortgage = mortgage;
	}

	public Integer getCar() {
		return this.car;
	}

	public void setCar(Integer car) {
		this.car = car;
	}

	public String getCarImg() {
		return this.carImg;
	}

	public void setCarImg(String carImg) {
		this.carImg = carImg;
	}

}