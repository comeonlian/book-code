package com.game.comm.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="short_log_sms_ky")
public class ShortLogSmsKy implements Serializable{
	/**
	 */
	private static final long serialVersionUID = -6461353622766570664L;
	//id,accesstime,ip,spnumber,mobile,linkid,msg,status
	private Integer id;
	private Date accesstime;
	private String ip;
	private String spnumber;
	private String mobile;
	private String linkid;
	private String msg;
	private String status;
	
	public ShortLogSmsKy() {
		super();
	}
	
	public ShortLogSmsKy(Date accesstime, String ip,
			String spnumber, String mobile, String linkid, String msg,
			String status) {
		super();
		this.accesstime = accesstime;
		this.ip = ip;
		this.spnumber = spnumber;
		this.mobile = mobile;
		this.linkid = linkid;
		this.msg = msg;
		this.status = status;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getAccesstime() {
		return accesstime;
	}
	public void setAccesstime(Date accesstime) {
		this.accesstime = accesstime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getSpnumber() {
		return spnumber;
	}
	public void setSpnumber(String spnumber) {
		this.spnumber = spnumber;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLinkid() {
		return linkid;
	}
	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}
	
}
