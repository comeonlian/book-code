/**
 * 
 */
package com.leo.test;

import org.joda.time.DateTime;
import org.junit.Test;

/**
 * @Description: 
 * @Author lianliang
 * @Date 2018年5月15日 上午9:39:36
 */
public class TimeTest {
	
	@Test
	public void timestamp2Date() {
		long t = 1526349022914l;
		DateTime dateTime = DateTime.now().withMillis(t);
		System.out.println(dateTime);
	}
	
}
