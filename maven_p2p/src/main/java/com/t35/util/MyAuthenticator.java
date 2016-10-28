package com.t35.util;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


public class MyAuthenticator extends Authenticator {
	private String userName;
	private String password;
	
	
	
	public MyAuthenticator(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		// TODO 自动生成的方法存根
		return new PasswordAuthentication(userName,password); 
	}
 
}
