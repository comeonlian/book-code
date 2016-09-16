package com.leo.junit;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leo.dao.IUserMapper;
import com.leo.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CoreTest {
	@Autowired
	private IUserMapper userMapper;
	
	@Test
	public void testSave(){
		User user = new User(-1,"xxoo",new Date(),8999.99);
		userMapper.save(user);
		System.out.println(user.getId());
	}
	
	@Test
	public void testSaveUsers(){
		User user1 = new User(-1,"hehe",new Date(),7878.99);
		User user2 = new User(-1,"heihei",new Date(),9898.99);
		userMapper.save(user1);
		int i = 10/0;
		userMapper.save(user2);
	}
	
	@Test
	public void testFindUser(){
		System.out.println(userMapper.findById(1));
	}
	
	@Test
	public void testFindUserList(){
		System.out.println(userMapper.findAll());
	}
	
}
