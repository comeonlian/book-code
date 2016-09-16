package com.leo.dao;

import java.util.List;

import com.leo.entity.User;

public interface IUserMapper {
	void save(User user);
	
	void update(User user);
	
	void delete(int id);
	
	User findById(int id);
	
	List<User> findAll();
}
