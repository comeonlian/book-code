package com.leo.mybatis1;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.leo.entity.User;

public class MyBatisTest1 {
	
	public static void main(String[] args) {
		String resource = "mybatis.xml";
		InputStream is = MyBatisTest1.class.getClassLoader().getResourceAsStream(resource);
		SqlSessionFactory  sessionFactory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = sessionFactory.openSession();
		String sql = "com.leo.mybatis1.entity.userMapper.getUserById";
		User user = session.selectOne(sql, 2);
		System.out.println(user);
	}
	
}
