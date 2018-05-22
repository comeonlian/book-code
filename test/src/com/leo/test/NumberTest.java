package com.leo.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;

public class NumberTest {
	
	public static void main(String[] args) {
		System.out.println(new NumberTest().parseNumber(Integer.class, "125"));
		Short parseNumber = new NumberTest().parseNumber(Short.class, "3233232320");
		System.out.println(parseNumber);
		
	}
	
	
	@SuppressWarnings("unchecked")
	public <T> T parseNumber(Class<T> clazz,String s){
		if(clazz==Integer.class){
			Integer v = Integer.parseInt(s);
			return (T) v;
		} else if(clazz==Long.class){
			Long v = Long.parseLong(s);
			return (T) v;
		}
		return null;
	}
	
	
	@Test
	public void testLongAdder(){
		LongAdder la = new LongAdder();
		//la.increment();
		System.out.println(la.intValue());
	}
	
	@Test
	public void testTimer(){
		long period = 3000;
		if (Math.abs(period) > (Long.MAX_VALUE >> 1))
			period >>= 1;
			System.out.println(period);
	}
	
	
	@Test
	public void testByte() {
		int day = LocalDateTime.now().getDayOfMonth();
		Integer x = 548;
		Integer y = (int) Math.pow(2, (day - 1));

		Integer result = x ^ y;
		System.out.println(result);
	}
	
	@Test
	public void testPow() {
		int pow = (int) Math.pow(2, 30);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(pow);
	}
	
	@Test
	public void calDateTime() {
		org.joda.time.format.DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		long start = DateTime.parse("2018-04-19 00:00:00", format).getMillis()/1000;
		long end = DateTime.parse("2018-04-20 00:00:00", format).getMillis()/1000;
		System.out.println(start+" - "+end);
	}
	
	@Test
	public void testArrayList() {
		/*List<String> list = new ArrayList<>(Arrays.asList("hehe", "www", "keke", "heihei", "lili", "dcdc", "azaz"));
		List<String> subList = list.subList(0, 2); //[hehe, www]
		System.out.println(subList);*/
		System.out.println(Integer.toBinaryString(1073741824));
	}
	
	
}
