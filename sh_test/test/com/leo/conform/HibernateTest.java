package com.leo.conform;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.Test;

import com.leo.dao.HibernateSessionFactory;

public class HibernateTest {

	@Test
	public void test() {
		Session session = HibernateSessionFactory.getSession();
		System.out.println(session);
	}

}
