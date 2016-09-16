package com.leo.service;

import java.util.List;

import com.leo.entity.User;

public interface DemoServer {
	String sayHello(String name) throws Exception;
	
	List<User> getUser();
}
