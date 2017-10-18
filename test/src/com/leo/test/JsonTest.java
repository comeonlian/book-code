package com.leo.test;

import org.junit.Test;

import com.google.gson.Gson;
import com.leo.test.entity.Person;
import com.leo.test.entity.TestJsonBean;

public class JsonTest {
	
	@Test
	public void testJson(){
		Gson gson = new Gson();
		String s = "{stat_time_b:\"2017-06-01 00:00:00\",stat_time_e:\"2017-06-02 00:00:00\"}";
		TestJsonBean map = gson.fromJson(s, TestJsonBean.class);
		System.out.println(map);
	}
	
	
	@Test
	public void testPerson(){
		Person person = new Person();
		person.getId();
	}
	
	@Test
	public void testNum() {
		String s = "01";
		System.out.println(Integer.valueOf(s));
	}
}
