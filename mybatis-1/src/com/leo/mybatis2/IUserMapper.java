package com.leo.mybatis2;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.leo.entity.User;

public interface IUserMapper {
	
	@Insert("insert users(name,age) values(#{name},#{age})")
	public int add(User user);
	
	@Delete("delete from users where id=#{id}")
	public int del(int id);
	
	@Update("update users set name=#{name},age=#{age} where id=#{id}")
	public int update(User user);
	
	@Select("select * from users where id=#{id}")
	public User getUserById(int id);
	
	@Select("select * from users")
	public List<User> getUserList();
}
