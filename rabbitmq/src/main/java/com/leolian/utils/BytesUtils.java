package com.leolian.utils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class BytesUtils {
	/**
	 * 字节数组转16进制数组
	 * @param bytes
	 * @return
	 */
	public static String[] byteToString(byte[] bytes) {
		String[] resStr = new String[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			resStr[i] = String.format("%02x", bytes[i]);
		}
		return resStr;
	}
	
	/**
	 * 空中协议
	 * 计算校验码
	 * @param data
	 * @return
	 */
	public static byte calCheckCode1(byte[] data){
		// 计算校验码
		byte sum = 0;
		for (int i = 2; i <= data.length - 2; i++) {
			sum ^= (byte) (data[i] & 0xff);
		}
		data[data.length - 1] = sum;
		return sum;
	}
	/**
	 * 911协议
	 * 计算校验码
	 * @param msg
	 * @return
	 */
	public static short calCheckCode2(byte[] msg)
	{
		short crc = (short) 0xFFFF; 
		int i, j;
		boolean c15, bit;
		
		for (i = 0; i < msg.length; i++) {
			for (j = 0; j < 8; j++) {
				c15 = ((crc >> 15 & 1) == 1);
				bit = ((msg[i] >> (7 - j) & 1) == 1);
				crc <<= 1;
				if (c15 ^ bit) crc ^= 0x1021; 
			}
		}
		return crc;
	}
	
	/**
	 * 十六进制字符串转换为byte值
	 * @param hexStr
	 * @return
	 */
	public static byte hexString2Byte(String hexStr){
		Byte val = Byte.parseByte(hexStr , 16);
		return val;
	}
	
	/**
	 * 16进制字符数组转换为字节数组
	 * @param hexStr
	 * @return
	 */
	public static byte[] hexString2Bytes(String[] hexStr){
		byte[] result = new byte[hexStr.length];
		for(int i=0; i<hexStr.length; i++){
			result[i] = hexString2Byte(hexStr[i]);
		}
		return result;
	}
	
	/**
	 * 一个日期转换成6字节
	 * @param date
	 * @return
	 */
	public static byte[] date2Bytes(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		byte[] head_arr = new byte[] { (byte) (calendar.get(Calendar.YEAR) - 2000),
				(byte) (calendar.get(Calendar.MONTH) + 1), (byte) calendar.get(Calendar.DAY_OF_MONTH),
				(byte) calendar.get(Calendar.HOUR_OF_DAY), (byte) calendar.get(Calendar.MINUTE),
				(byte) calendar.get(Calendar.SECOND)};
		return head_arr;
	}
	
	public static void main(String[] args) {
		/*byte[] bytes = new byte[]{35, 35, 1, 1, 83, 53, 51, 52, 49, 48, 48, 48, 48, 49, 50, 50, 56, 32,
				32, 32, 32, 1, 0, 46, 16, 8, 5, 7, 44, 15, 0, 1, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49,
				49, 49, 49, 49, 49, 49, 49, 49, 49, 1, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48,
				48, 48, 48, 48, 126};
		
		System.out.println(Arrays.toString(byteToString(bytes)));*/
		
		//String[] data = {"23", "23", "01", "01", "4c", "4a", "31", "36", "36", "45", "34", "37", "37", "46", "32", "30", "31", "31", "35", "38", "33", "53", "35", "33", "34", "31", "30", "30", "30", "30", "30", "36", "34", "37", "01", "00", "2e", "10", "0a", "1a", "0f", "1f", "38", "00", "01", "38", "39", "38", "36", "30", "32", "62", "33", "32", "32", "31", "35", "38", "30", "31", "36", "38", "35", "33", "31", "01", "31", "31", "31", "31", "31", "31", "31", "31", "31", "31", "31", "31", "31", "31", "31", "31", "31", "36"};
		//byte[] result = hexString2Bytes(data);
		//System.out.println(Arrays.toString(data));
		//System.out.println(Arrays.toString(result));
		
		//System.out.println(calCheckCode(result));
		
		//System.out.println(Arrays.toString(byteToString(date2Bytes(new Date()))));
		
		/*String[] data = {"40", "40", "00", "2a", "00", "03", "4e", "20", "16", "08", "17", "15", "02", "00", "23", "10", "0a", "1b", "11", "20", "28", "18", "00", "00", "00", "00", "00", "00", "00", "10", "01", "01", "00", "23", "00", "64", "00", "41", "00", "32", "00", "32", "00", "32", "00", "32"};
		byte[] result = hexString2Bytes(data);
		System.out.println(calCheckCode2(result));*/
		
		
		//String s = new String(new byte[]{0x4e,0x31,0x30,0x30,0x41,0x42,0x30,0x30,0x30, 0x30, 0x2e, 0x30, 0x30, 0x30, 0x36, 0x20});
		//String s = new String(new byte[]{0x32, 0x30, 0x31, 0x36, 0x2d, 0x31, 0x30, 0x2d, 0x32, 0x37});
		//String s = new String(new byte[]{0x53,0x49,0x4d,0x38,0x30,0x30,0x4c,0x20,0x20,0x20});
		//System.out.println(s);
		
		
		//System.out.println(Arrays.toString(byteToString("E100AB0304.0101 ".getBytes())));
		
		String s1 = "S100D005V004", s2 = "2016-11-04";
		
		System.out.println(Arrays.toString(byteToString(s1.getBytes())) + " -- "+Arrays.toString(byteToString(s2.getBytes())));
		
	}
}
