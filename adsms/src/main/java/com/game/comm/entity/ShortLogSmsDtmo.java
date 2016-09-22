package com.game.comm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name="short_log_sms_dtmo")
public class ShortLogSmsDtmo  implements java.io.Serializable {
	/**
	 */
	private static final long serialVersionUID = -6188345446952543424L;
	//id,accesstime,ip,user_agent,mobile,spnum,linkid,statement,flag
	private Integer id;
	private Date accesstime;
	private String ip;
	@Column(name="user_agent")
	private String userAgent;
	private String mobile;
	private String spnum;
	private String linkid;
	private String statement;
	private String flag;
	
	public ShortLogSmsDtmo() {
		super();
	}

	public ShortLogSmsDtmo(Date accesstime, String ip,
			String userAgent, String mobile, String spnum, String linkid,
			String statement, String flag) {
		this.accesstime = accesstime;
		this.ip = ip;
		this.userAgent = userAgent;
		this.mobile = mobile;
		this.spnum = spnum;
		this.linkid = linkid;
		this.statement = statement;
		this.flag = flag;
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

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSpnum() {
		return spnum;
	}

	public void setSpnum(String spnum) {
		this.spnum = spnum;
	}

	public String getLinkid() {
		return linkid;
	}

	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
	
}
