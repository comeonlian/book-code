package com.leo.conform;

import static org.junit.Assert.*;

import java.awt.Container;
import java.util.Date;

import javax.naming.Context;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
	
	private static ApplicationContext context = null;
	
	/*@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}*/

	@Test
	public void test() {
		//Date date = (Date) context.getBean("date");
		//System.out.println(date);
		System.err.println("*****  test git  push *****");
	}

}
