package com.t35.util;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DateUtil
{
	
   //共20位　如：20081002 002221 683 332
	public static String getDateRandomStr()
	{
	  return   DateUtil.getDateTimeStr3ByDate( new Date() )
	          + String.valueOf( new Random().nextInt( 999 ) );
	}
	
	//返回当前日期 yyyy-MM-dd HH:mm:ss
	public static Date getNow()
	{
		return   getDateTimeByDate(new Date());
	}
	

	

	// 返回日期型yyyy-MM-dd
	public static Date getDateByStr( String date )
	{
		Date date1 =null;
		try
		
		{
			SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
			if(date!=null)date1 = sdf.parse(date);
		}
		catch ( Exception e )
		{
			System.out.println( "将字符串"+date+"转换成日期出错" );
			e.printStackTrace();
		}
		return date1;
	}
	

	
	
	// 返回日期型yyyy-MM-dd HH:mm:ss
	public static Date getDateTimeByStr( String date )
	{
		Date date1 = null;
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
			if(date!=null)date1 = sdf.parse(date);
		}
		catch ( Exception e )
		{
			System.out.println( "将字符串转换成yyyy-MM-dd HH:mm:ss日期出错" );
			e.printStackTrace();
		}
		return date1;
	}
	// 返回日期型yyyy-MM-dd HH:mm:ss.sss
	public static Date getDateTimeByStrs( String date )
	{
		Date date1 = null;
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" );
			if(date!=null)date1 = sdf.parse(date);
		}
		catch ( Exception e )
		{
			System.out.println( "将字符串转换成yyyy-MM-dd HH:mm:ss.SSS日期出错" );
			e.printStackTrace();
		}
		return date1;
	}

	
	
	
	
	// 返回日期型yyyy-MM-dd
	public static Date getDateByDate( Date date )
	{
		Date date1 = null;
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
			if(date!=null)date1 = sdf.parse( sdf.format( date ) );
		}
		catch ( Exception e )
		{
			System.out.println( "将日期转换成yyyy-MM-dd日期出错" );
			e.printStackTrace();
		}
		return date1;
	}

	// 返回日期型yyyy-MM-dd HH:mm:ss
	public static Date getDateTimeByDate( Date date )
	{
		Date date1 =null;
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
			if(date!=null)date1 = sdf.parse( sdf.format( date ) );
		}
		catch ( Exception e )
		{
			System.out.println( "将日期转换成yyyy-MM-dd HH:mm:ss日期出错" );
			e.printStackTrace();
		}
		return date1;
	}

	// 返回日期型yyyy-MM-dd HH:mm:ss.SSS
	public static Date getDateTimeByDates( Date date )
	{
		Date date1 =null;
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" );
			if(date!=null)date1 = sdf.parse( sdf.format( date ) );
		}
		catch ( Exception e )
		{
			System.out.println( "将日期转换成yyyy-MM-dd HH:mm:ss.SSS日期出错" );
			e.printStackTrace();
		}
		return date1;
	}
	
	// 返回日期型hh:mm a 样式:12:08 PM
	public static Date getTimeByDate( Date date )
	{
		Date date1=null;
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat( "hh:mm a" );
			if(date!=null)date1 = sdf.parse( sdf.format( date ) );
		}
		catch ( Exception e )
		{
			System.out.println( "将日期转换成hh:mm a日期出错" );
			e.printStackTrace();
		}
		return date1;
	}
	// 返回字符串型hh:mm a 样式:12:08 PM
	public static String getTimeStrByDate( Date date )
	{
		return new SimpleDateFormat( "hh:mm a" ).format( date );
	}

	
	
	// 返回字符串"yyyy-MM-dd"
	public static String getDateStrByDate( Date date )
	{
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		return sdf.format( date );
	}

	// 返回字符串"yyyy-MM-dd HH:mm:ss"
	public static String getDateTimeStrByDate( Date date )
	{
		return new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ).format( date );
	}
	
//	 返回字符串"yyyyMMdd"
	public static String getDateStrByDate()
	{
		return new SimpleDateFormat("yyyyMMdd").format( new Date() );
	}

//	 返回字符串"yyyyMMddHHmmss"
	public static String getDateTimeStr2ByDate( Date date )
	{
		return new SimpleDateFormat("yyyyMMddHHmmss").format( date );
	}
	

//	 返回字符串"yyyyMMddHHmmssSSS"
	public static String getDateTimeStr3ByDate( Date date )
	{
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format( date );
	}
	
	
	
	// 根据参数模板返回日期字符串 pattern模板
	public static String getStrByPattern( Date date, String pattern )
	{
		return new SimpleDateFormat( pattern ).format( date );
	}

	// 根据参数模板返回日期
	public static Date getDateByPattern( Date date, String pattern )
	{
		Date date1 = null;
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat( pattern );
			if(date!=null)date1 = sdf.parse( sdf.format( date ) );
		}
		catch ( Exception e )
		{
			System.out.println( "将日期转换成" + pattern + "日期出错" );
			e.printStackTrace();
		}
		return date1;
	}
	/**
	 * 返回date2-date1 相差的秒数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long compareTime(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return 0;

		Calendar c = Calendar.getInstance();

		c.setTime(date1);
		long l1 = c.getTimeInMillis();

		c.setTime(date2);
		long l2 = c.getTimeInMillis();

		return (l2 - l1) / 1000;
	}
}
