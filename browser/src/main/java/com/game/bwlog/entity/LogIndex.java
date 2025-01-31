package com.game.bwlog.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "browser_log_index")
public class LogIndex implements Serializable{
    /**
	 */
	private static final long serialVersionUID = 7731140728680520460L;
	
	private Long id;
    private Date accesstime; // 访问时间
    private String ip;
    private Long countryId; // 国家ID
    private String countryName; // 国家
    private String dockingSessionid;
    private String cookieSessionid;
    private String sessionid;

    public LogIndex() {
        super();
    }

    public LogIndex(Date accesstime, String ip, Long countryId, String countryName, String dockingSessionid, String cookieSessionid, String sessionid) {
        super();
        this.accesstime = accesstime;
        this.ip = ip;
        this.countryId = countryId;
        this.countryName = countryName;
        this.dockingSessionid = dockingSessionid;
        this.cookieSessionid = cookieSessionid;
        this.sessionid = sessionid;
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getDockingSessionid() {
        return dockingSessionid;
    }

    public void setDockingSessionid(String dockingSessionid) {
        this.dockingSessionid = dockingSessionid;
    }

    public String getCookieSessionid() {
        return cookieSessionid;
    }

    public void setCookieSessionid(String cookieSessionid) {
        this.cookieSessionid = cookieSessionid;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

}
