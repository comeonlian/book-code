package com.leo.test;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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
	
	@Test
	public void testDate() {
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		long startTime = DateTime.parse("2018-4-15 00:00:00", format).getMillis() / 1000;
		long endTime = DateTime.parse("2018-4-15 23:59:59", format).getMillis() / 1000;
		System.out.println(startTime + "-" + endTime);
	}
	
	@Test
	public void testMills() {
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		long startTime = DateTime.parse("2018-4-15 00:00:00", format).getMillis() / 1000;
		long endTime = DateTime.parse("2018-4-15 24:00:00", format).getMillis() / 1000;
		System.out.println(startTime+" - "+endTime);
	}
	
	@Test
	public void testCurrentMills() {
		// 1535942526
		System.out.println(System.currentTimeMillis() / 1000);
	}
	
	
}
