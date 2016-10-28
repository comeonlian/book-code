package com.t35.pojo;

/**
 * Userdetia entity. @author MyEclipse Persistence Tools
 */

public class Userdetia implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private String idCardName;
	private String idCardNum;
	private String idCardImg;

	// Constructors

	/** default constructor */
	public Userdetia() {
	}

	/** full constructor */
	public Userdetia(Integer userId, String idCardName, String idCardNum,
			String idCardImg) {
		this.userId = userId;
		this.idCardName = idCardName;
		this.idCardNum = idCardNum;
		this.idCardImg = idCardImg;
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

	public String getIdCardImg() {
		return this.idCardImg;
	}

	public void setIdCardImg(String idCardImg) {
		this.idCardImg = idCardImg;
	}

}