package com.leo.test;

import java.util.concurrent.atomic.LongAdder;

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
}
