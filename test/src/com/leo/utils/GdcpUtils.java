package com.leo.utils;

import java.awt.image.RescaleOp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class GdcpUtils {
	/**
	 * �������
	 * 
	 * @param originCode
	 * @return
	 */
	public static String entrance(String originCode) {
		try {
			originCode = originCode.trim();
			// �ַ���ת�ɶ���������
			byte[] originBytes = hexStr2Bytes(originCode);
			String resultStr = "";
			byte[] resultBytes = null;
			// �ֿ�����
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
	 * ʮ�������ַ���ת�ɶ���������
	 * 
	 * @param originCode
	 * @return
	 */
	public static byte[] hexStr2Bytes(String originCode) {
		// �ַ����и�
		String[] hexStr = originCode.split(" ");
		// ת��
		byte[] result = new byte[hexStr.length];
		for (int i = 0; i < hexStr.length; i++) {
			result[i] = hexString2Byte(hexStr[i]);
		}
		return result;
	}

	/**
	 * ʮ�������ַ���ת��Ϊbyteֵ
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte hexString2Byte(String hexStr) {
		int val = Integer.valueOf(hexStr, 16);
		return (byte) val;
	}

	/**
	 * ת��S100Э��
	 * 
	 * @param originCode
	 * @return
	 */
	public static byte[] transferToS100(byte[] originBytes) {
		// �ȼ�����Ϣ����
		short len = calLengthS100(originBytes);
		byte[] lenBytes = short2Bytes(len);
		originBytes[12] = lenBytes[0];
		originBytes[13] = lenBytes[1];
		// �ټ���У����
		byte[] tempBytes = new byte[originBytes.length - 2];
		System.arraycopy(originBytes, 1, tempBytes, 0, originBytes.length - 2);
		byte sum = s100(tempBytes);
		originBytes[originBytes.length - 2] = sum;
		return originBytes;
	}

	/**
	 * S100У�����㷨
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
	 * ����s100Э�����ݳ���
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
	 * ת��911Э��
	 * 
	 * @param originCode
	 * @return
	 */
	public static byte[] transferTo911(byte[] originBytes) {
		byte[] tempBytes = new byte[originBytes.length - 2];
		System.arraycopy(originBytes, 0, tempBytes, 0, tempBytes.length);
		// ����У����
		short resultShort = CRC16(tempBytes);
		byte[] crcBytes = short2Bytes(resultShort);
		// �滻У����
		originBytes[originBytes.length - 1] = crcBytes[1];
		originBytes[originBytes.length - 2] = crcBytes[0];
		// ���㳤��
		short len = calLength911(originBytes);
		byte[] lenBytes = short2Bytes(len);
		originBytes[2] = lenBytes[0];
		originBytes[3] = lenBytes[1];

		return originBytes;
	}

	/**
	 * ����911Э�����ݳ���
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
	 * 911У�����㷨
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
	 * shortֵת���ֽ�����
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
	 * �ֽ�����ת16�����ַ���
	 * 
	 * @param bytes
	 * @return
	 */
	public static String byteToHexString(byte... bytes) {
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
	 * ����ת���޷���1�ֽ���
	 * 
	 * @param d
	 * @return
	 */
	public static byte intToUnsigned8(int d) {
		byte result = 0;
		result = (byte) (d & 0xff);
		return result;
	}
	/**
	 * һ���ֽ�ת16�����ַ���
	 * 
	 * @param bytes
	 * @return
	 */
	public static String byteToHexString(byte byteVal) {
		return String.format("%02x", byteVal);
	}

	/**
	 * 
	 * �ֽ�����ת16�����ַ�������
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
	 * ��������ʱ��ʮ�������ַ���
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
		// ���ݲɼ�ʱ��
		byte[] date_col = new byte[] { (byte) (calendar.get(Calendar.YEAR) - 2000),
				(byte) (calendar.get(Calendar.MONTH) + 1), (byte) calendar.get(Calendar.DAY_OF_MONTH),
				(byte) calendar.get(Calendar.HOUR_OF_DAY), (byte) calendar.get(Calendar.MINUTE),
				(byte) calendar.get(Calendar.SECOND) };
		// System.out.println(Arrays.toString(date_col));
		return byteToHexString(date_col);
	}

	/**
	 * �汾ת��ʮ�������ַ���
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
	 * ����IDת��ʮ�������� S100Э���911ͨ�� N201600008888 --> 4e 20 16 00 00 88 88
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
	
	/**
	 * ʮ��������ת�ɳ���ID
	 * 4D201500000001
	 * @param hexStr
	 * @return
	 */
	public static String hexString2DeviceId(String hexStr){
		if(null==hexStr||hexStr.length()<=2)
			return null;
		if(hexStr.contains(" "))
			hexStr.replaceAll(" ", "");
		String chStr = hexStr.substring(0,2);
		char ch = (char) Short.parseShort(chStr, 16);
		//System.out.println(chStr);
		StringBuilder sb = new StringBuilder();
		sb.append(""+ch+hexStr.substring(2,hexStr.length()));
		return sb.toString();
	}
	
	/**
	 * ������ת���޷���4�ֽ���
	 * 
	 * @param d
	 * @return
	 */
	public static byte[] longToUnsigned32(long d) {
		byte[] result = new byte[4];
		result[0] = (byte) ((d >> 24) & 0xff);
		result[1] = (byte) ((d >> 16) & 0xff);
		result[2] = (byte) ((d >> 8) & 0xff);
		result[3] = (byte) (d & 0xff);
		return result;
	}
	
	
	/* **************************************����ת��****************************************** */
	/**
	 * ����ת��У�������
	 * @param bytes
	 * @return
	 */
	public static String transCheckCode(byte[] bytes){
		byte sum = 0;
		for (int i = 2; i <= bytes.length - 2; i++) {
			sum ^= (byte) (bytes[i] & 0xff);
		}
		return byteToHexString(sum);
	}
	
	/**
	 * ���ַ��������ո�
	 * @param s
	 * @return
	 */
	public static String fillSpaceString(String s){
		int len = s.length();
		StringBuilder result = new StringBuilder();
		for(int i=0; i<len; i++){
			result.append(s.charAt(i));
			if((i%2)==0)
				result.append("");
			else 
				result.append(" ");
		}
		return result.toString();
	}
	
	/**
	 * ����17λ�����ݣ����ո�17λ
	 * @param originData
	 * @return
	 */
	public static String appendSpaceTo17(String originData){
		int len = 17 - originData.length();
		StringBuilder result = new StringBuilder(originData);
		for(int i=0; i<len; i++){
			result.append(" ");
		}
		return result.toString();
	}
	
	
	/* *************************************BCD��******************************************** */
	
	/*public static byte[] simToBCD(String deviceId) {
		if(deviceId.length()!=12)
			return null;
		deviceId = "B" + deviceId;
		byte[] deviceIdByte = new byte[7];
		char[] temp = deviceId.toCharArray();
		
		deviceIdByte[0] = (byte) (temp[0] & 0xff);
		for (int i = 1; i < 7; i++) {
			deviceIdByte[i] = (byte) (((temp[2 * i - 1] << 4) & 0xf0) | (temp[2 * i] & 0x0f));
		}
		byte[] result = new byte[6];
		System.arraycopy(deviceIdByte, 1, result, 0, 6);
		return result;
	}
	
	public static String bcdToString(byte[] data) {
		byte[] carid = new byte[13];
		carid[0] = 'B';
		if (carid[0] < 'A' || carid[0] > 'Z') {
			return null;
		}
		byte index = 1;
		// ����ID BCD��ת��
		for (int i = 0; i < 6; i++) {
			carid[index++] = (byte) (((data[i] >> 4) & 0x0f) + 0x30);
			carid[index++] = (byte) ((data[i] & 0x0f) + 0x30);
		}
		String caridString = new String(carid);
		return caridString;
	}*/
	/**
	 * SIM����ת��BCD��
	 * @param sim
	 * @return
	 */
	public static byte[] simToBCD(String sim) {
		if(sim.length()!=12)
			return null;
		byte[] deviceIdByte = new byte[6];
		char[] temp = sim.toCharArray();
		int index = 0;
		for (int i=0; i < 12; i=i+2) {
			deviceIdByte[index++] = (byte) (((temp[i] << 4) & 0xf0) | (temp[i+1] & 0x0f));
		}
		return deviceIdByte;
	}
	/**
	 * BCD��ת��SIM����
	 * @param data
	 * @return
	 */
	public static String bcdToSim(byte[] data) {
		byte[] carid = new byte[12];
		
		byte index = 0;
		// ����ID BCD��ת��
		for (int i = 0; i < 6; i++) {
			carid[index++] = (byte) (((data[i] >> 4) & 0x0f) + 0x30);
			carid[index++] = (byte) ((data[i] & 0x0f) + 0x30);
		}
		String caridString = new String(carid);
		return caridString;
	}
	/**
	 * short����ת��  ��Ϣ������
	 * 0-9λ����ʾ��Ϣ���� 
	 * 10-12λ
	 * 13λ
	 * 14-15λ������
	 * @param i
	 * @return
	 */
	public static String short2MsgProp(short i){
		String s = Integer.toBinaryString(i);
		s = String.format("%16s", s);
		//s = String.format("%-16s", s);
		s = s.replaceAll(" ", "0");
		short j = Short.parseShort(s, 2);
		System.out.println(Arrays.toString(short2Bytes(j)));
		return byteToHexString(short2Bytes(j));
	}
	
	/**
	 * �����ݽ���ת��
	 * 0x7e < �������� > 0x7d �����һ�� 0x02
	 * 0x7d < �������� > 0x7d �����һ�� 0x01
	 * @param originData
	 * @return
	 */
	public static byte[] escapeData(byte[] originData){
		int len = originData.length;
		byte[] temp = new byte[2 * len];
		int index = 0;
		temp[index++] = 0x7E;
		for(int i=1; i<len-1; i++){
			if(originData[i]==0x7E){
				temp[index++] = 0x7D;
				temp[index++] = 0x02;
			}else if(originData[i]==0x7D){
				temp[index++] = 0x7D;
				temp[index++] = 0x01;
			}else{
				temp[index++] = originData[i];
			}
		}
		temp[index++] = 0x7E;
		byte[] result = new byte[index];
		System.arraycopy(temp, 0, result, 0, index);
		return result;
	}
	/**
	 * ʱ��ת��BCD�ַ���
	 * @param date
	 * @return
	 */
	public static byte[] time2BCD(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String res = sdf.format(date);
		return simToBCD(res);
	}
	/**
	 * @param alarmType
	 * @return
	 */
	public static int transAlarmType(int alarmType){
		int result = 0;
		switch (alarmType) {
			case -6:
				result = 6; 
				break;
			case -267:
				result = 267;
				break;
			case -268:
				result = 268;
				break;
			case -270:
				result = 270;
				break;
			case -287:
				result = 287;
				break;
			default:
				result = alarmType;
				break;
		}
		return result;
	}
}
