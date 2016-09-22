package com.game.comm.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CommonCustom entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "common_custom")
public class CommonCustom implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//公共渠道客户表
	private Long id;
	private String name;//名称
	private String phone;//电话号码
	private String connectname;//联系人
	private String createtime;//创建时间
	private Integer status;//状态 1:开放 0：关闭
	private String customid;//厂商ID
	private Integer isdefault;//是否是默认取数据的客户   0：否   1：是
	
	
	// Constructors
	public String getCustomid() {
		return customid;
	}

	public void setCustomid(String customid) {
		this.customid = customid;
	}

	/** default constructor */
	public CommonCustom() {
	}

	/** full constructor */
	public CommonCustom(String name, String phone, String connectname,
			String createtime, Integer status) {
		this.name = name;
		this.phone = phone;
		this.connectname = connectname;
		this.createtime = createtime;
		this.status = status;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "phone")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "connectname")
	public String getConnectname() {
		return this.connectname;
	}

	public void setConnectname(String connectname) {
		this.connectname = connectname;
	}

	@Column(name = "createtime")
	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name = "isdefault")
	public Integer getIsdefault() {
		return isdefault;
	}

	public void setIsdefault(Integer isdefault) {
		this.isdefault = isdefault;
	}

}