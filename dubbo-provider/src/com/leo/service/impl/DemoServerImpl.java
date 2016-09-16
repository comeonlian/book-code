package com.leo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.leo.entity.User;
import com.leo.service.DemoServer;

public class DemoServerImpl implements DemoServer {

	@Override
	public String sayHello(String name) throws Exception {
		return "Hello, "+name;
	}

	@Override
	public List<User> getUser() {
		User user1 = new User("ZhangSan", 23);
		User user2 = new User("LiSi", 25);
		User user3 = new User("WangWu", 18);
		List<User> list = new ArrayList<User>();
		list.add(user1);
		list.add(user2);
		list.add(user3);
		return list;
	}

}
