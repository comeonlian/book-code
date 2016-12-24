package com.leolian.http.entity;

public class RequestParameter {
	
	private String command;
	private Person parameter;
	
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public Person getParameter() {
		return parameter;
	}
	public void setParameter(Person parameter) {
		this.parameter = parameter;
	}
	
}
