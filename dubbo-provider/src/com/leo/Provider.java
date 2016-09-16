package com.leo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		context.start();
		
		System.out.println("dubboe-provider ....");
		
		System.in.read();
	}

}
