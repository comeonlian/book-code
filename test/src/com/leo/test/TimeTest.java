/**
 * 
 */
package com.leo.test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
	
	@Test
	public void currentTimestamp() {
		System.out.println(DateTime.now().getMillis()/1000);
	}
	
	@Test
	public void testDays() {
		LocalDateTime startDate = LocalDateTime.parse("20181002 00:00:00", DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss"));
		LocalDateTime endDate = LocalDateTime.parse("20181005 00:00:00", DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss"));
		long days = Duration.between(startDate, endDate).toDays();
		for (int i = 0; i <= days; i++) {
			System.out.println(startDate.plusDays(i).format(DateTimeFormatter.ofPattern("yyyyMMdd")));
		}
	}
}
