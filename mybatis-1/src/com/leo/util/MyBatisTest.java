package com.leo.util;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.leo.mybatis1.MyBatisTest1;

@RunWith(BlockJUnit4ClassRunner.class)
public class MyBatisTest {
	
	private String resource;
	
	public MyBatisTest(String resource) {
		this.resource = resource;
	}
	
	public SqlSession getSqlSession(){
		InputStream is = MyBatisTest1.class.getClassLoader().getResourceAsStream(resource);
		SqlSessionFactory  sessionFactory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = sessionFactory.openSession(true);
		return session;
	}
	
	public SqlSessionFactory getSqlSessionFactory(){
		InputStream is = MyBatisTest1.class.getClassLoader().getResourceAsStream(resource);
		SqlSessionFactory  sessionFactory = new SqlSessionFactoryBuilder().build(is);
		return sessionFactory;
	}
}
