package com.leo.mybatis2;

import java.util.List;

import org.junit.Test;

import com.leo.entity.Order;
import com.leo.entity.User;
import com.leo.util.MyBatisTest;

public class MyBatisTest2 extends MyBatisTest{

	public MyBatisTest2() {
		super("mybatis.xml");
	}
	
	/* **********************		基于配置文件的增删改查			******************** */
	@Test
	public void testAdd(){
		String statement = "com.leo.mybatis2.userMapper.addUser";
		int result = getSqlSession().update(statement, new User(0,"kkk",24));
		System.out.println(result);
	}
	
	@Test
	public void testDel(){
		String statement = "com.leo.mybatis2.userMapper.delUser";
		int result = getSqlSession().delete(statement, 1);
		System.out.println(result);
	}
	
	@Test
	public void testUpdate(){
		String statement = "com.leo.mybatis2.userMapper.updateUser";
		int result = getSqlSession().update(statement, new User(3,"xxoo",38));
		System.out.println(result);
	}
	
	@Test
	public void testSelectOne(){
		String statement = "com.leo.mybatis2.userMapper.getUserById";
		User user = getSqlSession().selectOne(statement, 3);
		System.out.println(user);
	}
	
	@Test
	public void testSelectList(){
		String statement = "com.leo.mybatis2.userMapper.getUserList";
		List<User> users = getSqlSession().selectList(statement);
		System.out.println(users);
	}
	
	/* **********************		基于注解的增删改查			******************** */
	@Test
	public void testAddUser(){
		IUserMapper mapper = getSqlSession().getMapper(IUserMapper.class);
		int result = mapper.add(new User(-1,"Leo",24));
		System.out.println(result);
	}
	
	/* **********************		属性名与字段名不匹配			******************** */
	@Test
	public void testFieldAlais(){
		String statement = "com.leo.mybatis2.orderMapper.getOrderById";
		Order order = getSqlSession().selectOne(statement, 2);
		System.out.println(order);
	}
}
