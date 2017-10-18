package com.leo.test.entity;

public class TestJsonBean {
	
	private String stat_time_b; 
	private String stat_time_e;
	
	public String getStat_time_b() {
		return stat_time_b;
	}
	public void setStat_time_b(String stat_time_b) {
		this.stat_time_b = stat_time_b;
	}
	public String getStat_time_e() {
		return stat_time_e;
	}
	public void setStat_time_e(String stat_time_e) {
		this.stat_time_e = stat_time_e;
	}
	
	@Override
	public String toString() {
		return "TestJsonBean [stat_time_b=" + stat_time_b + ", stat_time_e=" + stat_time_e + "]";
	} 
	
}
