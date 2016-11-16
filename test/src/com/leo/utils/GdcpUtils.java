package com.leo.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GdcpUtils {
	/**
	 * 方法入口
	 * 
	 * @param originCode
	 * @return
	 */
	public static String entrance(String originCode) {
		try {
			originCode = originCode.trim();
			// 字符串转成二进制数组
			byte[] originBytes = hexStr2Bytes(originCode);
			String resultStr = "";
			byte[] resultBytes = null;
			// 分开处理
			if (originCode.startsWith("40"))
				resultBytes = transferTo911(originBytes);
			else if (originCode.startsWith("7e"))
				resultBytes = transferToS100(originBytes);
			resultStr = byteToHexString(resultBytes);
			return resultStr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 十六进制字符串转成二进制数组
	 * 
	 * @param originCode
	 * @return
	 */
	public static byte[] hexStr2Bytes(String originCode) {
		// 字符串切割
		String[] hexStr = originCode.split(" ");
		// 转换
		byte[] result = new byte[hexStr.length];
		for (int i = 0; i < hexStr.length; i++) {
			result[i] = hexString2Byte(hexStr[i]);
		}
		return result;
	}

	/**
	 * 十六进制字符串转换为byte值
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte hexString2Byte(String hexStr) {
		int val = Integer.valueOf(hexStr, 16);
		return (byte) val;
	}

	/**
	 * 转换S100协议
	 * 
	 * @param originCode
	 * @return
	 */
	public static byte[] transferToS100(byte[] originBytes) {
		// 先计算消息属性
		short len = calLengthS100(originBytes);
		byte[] lenBytes = short2Bytes(len);
		originBytes[12] = lenBytes[0];
		originBytes[13] = lenBytes[1];
		// 再计算校验码
		byte[] tempBytes = new byte[originBytes.length - 2];
		System.arraycopy(originBytes, 1, tempBytes, 0, originBytes.length - 2);
		byte sum = s100(tempBytes);
		originBytes[originBytes.length - 2] = sum;
		return originBytes;
	}

	/**
	 * S100校验码算法
	 * 
	 * @param msg
	 * @return
	 */
	public static byte s100(byte[] msg) {
		byte sum = 0;
		for (int i = 0; i < msg.length - 1; i++) {
			sum ^= (byte) (msg[i] & 0xff);
		}
		return sum;
	}

	/**
	 * 计算s100协议数据长度
	 * 
	 * @param originBytes
	 * @return
	 */
	public static short calLengthS100(byte[] originBytes) {
		int len = 0;
		for (int i = 14; i < originBytes.length - 2; i++) {
			len = len + 1;
		}
		return (short) len;
	}

	/**
	 * 转换911协议
	 * 
	 * @param originCode
	 * @return
	 */
	public static byte[] transferTo911(byte[] originBytes) {
		byte[] tempBytes = new byte[originBytes.length - 2];
		System.arraycopy(originBytes, 0, tempBytes, 0, tempBytes.length);
		// 计算校验码
		short resultShort = CRC16(tempBytes);
		byte[] crcBytes = short2Bytes(resultShort);
		// 替换校验码
		originBytes[originBytes.length - 1] = crcBytes[1];
		originBytes[originBytes.length - 2] = crcBytes[0];
		// 计算长度
		short len = calLength911(originBytes);
		byte[] lenBytes = short2Bytes(len);
		originBytes[2] = lenBytes[0];
		originBytes[3] = lenBytes[1];

		return originBytes;
	}

	/**
	 * 计算911协议数据长度
	 * 
	 * @param originBytes
	 * @return
	 */
	public static short calLength911(byte[] originBytes) {
		int len = 0;
		for (int i = 4; i < originBytes.length - 2; i++) {
			len = len + 1;
		}
		return (short) len;
	}

	/**
	 * 911校验码算法
	 * 
	 * @param msg
	 * @return
	 */
	public static short CRC16(byte[] msg) {
		short crc = (short) 0xFFFF;
		int i, j;
		boolean c15, bit;
		for (i = 0; i < msg.length; i++) {
			for (j = 0; j < 8; j++) {
				c15 = ((crc >> 15 & 1) == 1);
				bit = ((msg[i] >> (7 - j) & 1) == 1);
				crc <<= 1;
				if (c15 ^ bit)
					crc ^= 0x1021;
			}
		}
		return crc;
	}

	/**
	 * short值转成字节数组
	 * 
	 * @param value
	 * @return
	 */
	public static byte[] short2Bytes(short value) {
		byte[] result = new byte[2];
		result[0] = (byte) ((value >> 8) & 0xffff);
		result[1] = (byte) (value & 0xffff);
		return result;
	}

	/**
	 * 字节数组转16进制字符串
	 * 
	 * @param bytes
	 * @return
	 */
	public static String byteToHexString(byte[] bytes) {
		StringBuffer resStr = new StringBuffer();
		int len = bytes.length;
		for (int i = 0; i < len; i++) {
			if (i != (len - 1))
				resStr.append(String.format("%02x", bytes[i]) + " ");
			else
				resStr.append(String.format("%02x", bytes[i]));
		}
		return resStr.toString();
	}

	/**
	 * 
	 * 字节数组转16进制字符串数组
	 * 
	 * @param bytes
	 * @return
	 */
	public static String[] byteToHexStringArray(byte[] bytes) {
		int len = bytes.length;
		String[] result = new String[len];
		for (int i = 0; i < len; i++) {
			result[i] = String.format("%02x", bytes[i]);
		}
		return result;
	}

	/**
	 * 返回日期时间十六进制字符串
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String dateHexString(String strdate) throws Exception {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// String strdate1 = "2016-10-20 20:00:00";
		Date date1 = sdf.parse(strdate);
		// System.out.println(date1.toLocaleString());
		calendar.setTime(date1);
		// 数据采集时间
		byte[] date_col = new byte[] { (byte) (calendar.get(Calendar.YEAR) - 2000),
				(byte) (calendar.get(Calendar.MONTH) + 1), (byte) calendar.get(Calendar.DAY_OF_MONTH),
				(byte) calendar.get(Calendar.HOUR_OF_DAY), (byte) calendar.get(Calendar.MINUTE),
				(byte) calendar.get(Calendar.SECOND) };
		// System.out.println(Arrays.toString(date_col));
		return byteToHexString(date_col);
	}

	/**
	 * 版本转成十六进制字符串
	 * 
	 * @return
	 */
	public static String version2HexString(String version) {
		StringBuilder sb = new StringBuilder(version);
		if (sb.length() != 16) {
			int len = 16 - sb.length();
			while ((len--) > 0)
				sb.append(" ");
		}
		byte[] bytes = sb.toString().getBytes();
		return byteToHexString(bytes);
	}

	/**
	 * 车机ID转成十六进制码 S100协议和911通用 N201600008888 --> 4e 20 16 00 00 88 88
	 * 
	 * @param deviceId
	 * @return
	 */
	public static String deviceId2HexString(String deviceId) {
		if (null == deviceId || deviceId.trim().length() == 0)
			return null;
		StringBuilder result = new StringBuilder();
		String firstStr = byteToHexString((deviceId.charAt(0) + "").getBytes());
		result.append(firstStr + " ");
		for (int i = 1; i < deviceId.length(); i++) {
			result.append(deviceId.charAt(i) + "");
			if ((i % 2) == 0)
				result.append(" ");
		}
		return result.toString().trim();
	}

}
