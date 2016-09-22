package com.game.comm.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Resolution entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "common_resolution")
public class Resolution implements java.io.Serializable {
	// Fields
	private Long id;
	private String resolution;
	private Integer isshow;
	private Integer width;
	private Integer height;

	// Constructors

	/** default constructor */
	public Resolution() {
	}

	/** full constructor */
	public Resolution(String resolution, Integer isshow, Integer width,
			Integer height) {
		this.resolution = resolution;
		this.isshow = isshow;
		this.width = width;
		this.height = height;
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

	@Column(name = "RESOLUTION", length = 20)
	public String getResolution() {
		return this.resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	@Column(name = "ISSHOW")
	public Integer getIsshow() {
		return this.isshow;
	}

	public void setIsshow(Integer isshow) {
		this.isshow = isshow;
	}

	@Column(name = "WIDTH")
	public Integer getWidth() {
		return this.width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	@Column(name = "HEIGHT")
	public Integer getHeight() {
		return this.height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

}