package com.game.bmanager.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "browser_customer_url")
public class CustomerUrl implements Serializable{
	/**
	 */
	private static final long serialVersionUID = 6316769209676209501L;

	private Long id;
	private String customid;
	private String urlname;
	private String url;
	private String citys;
	private String createby;
	private Date createtime;
	private String modifyby;
	private Date modifytime;
	private Integer status;
	
	public CustomerUrl() {
		super();
	}
	
	public CustomerUrl(Long id, String customid, String urlname, String url,
			String citys, String createby, Date createtime, String modifyby,
			Date modifytime, Integer status) {
		super();
		this.id = id;
		this.customid = customid;
		this.urlname = urlname;
		this.url = url;
		this.citys = citys;
		this.createby = createby;
		this.createtime = createtime;
		this.modifyby = modifyby;
		this.modifytime = modifytime;
		this.status = status;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCustomid() {
		return customid;
	}
	public void setCustomid(String customid) {
		this.customid = customid;
	}
	public String getUrlname() {
		return urlname;
	}
	public void setUrlname(String urlname) {
		this.urlname = urlname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCitys() {
		return citys;
	}
	public void setCitys(String citys) {
		this.citys = citys;
	}
	public String getCreateby() {
		return createby;
	}
	public void setCreateby(String createby) {
		this.createby = createby;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getModifyby() {
		return modifyby;
	}
	public void setModifyby(String modifyby) {
		this.modifyby = modifyby;
	}
	public Date getModifytime() {
		return modifytime;
	}
	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
