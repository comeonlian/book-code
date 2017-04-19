package com.leo.utils;

import java.util.Arrays;

import org.junit.Test;

import net.sourceforge.pinyin4j.PinyinHelper;

public class CommonTest {

	public static void main(String[] args) {
		double val = 0d;
		int val2 = 0;
		System.out.println(String.valueOf((int)val));
		System.out.println(String.valueOf(val2));
	}
	
	/*
	 */
	@Test
	public void testChinese2Pinyin(){
		String[] sarr = PinyinHelper.toHanyuPinyinStringArray('a');
		for (int i = 0; i < sarr.length; i++) {
			System.out.println(sarr[i]);
		}
	}
	
}
