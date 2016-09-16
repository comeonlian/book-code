package com.leo.conform;

import org.hibernate.internal.SessionFactoryImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAndHibernateTest {
	
	private static ApplicationContext context = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@Test
	public void test() {
		SessionFactoryImpl bean = (SessionFactoryImpl) context.getBean("sessionFactory");
		System.out.println(bean);
	}

}
