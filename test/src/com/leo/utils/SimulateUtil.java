package com.leo.utils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class SimulateUtil {
	
	/**
	 * �������
	 * @param originCode
	 * @return
	 */
	public static String entrance(String originCode){
		try{
			originCode = originCode.trim();
			// �ַ���ת�ɶ���������
			byte[] originBytes = hexStr2Bytes(originCode);
			String resultStr = "";
			byte[] resultBytes = null;
			//�ֿ�����
			if(originCode.startsWith("40"))
				resultBytes = transferTo911(originBytes);
			else if(originCode.startsWith("7e"))
				resultBytes = transferToS100(originBytes);
			resultStr = byteToString(resultBytes);
			return resultStr;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * ʮ�������ַ���ת�ɶ���������
	 * @param originCode
	 * @return
	 */
	private static byte[] hexStr2Bytes(String originCode) {
		//�ַ����и�
		String[] hexStr = originCode.split(" ");
		//ת��
		byte[] result = new byte[hexStr.length];
		for(int i=0; i<hexStr.length; i++){
			result[i] = hexString2Byte(hexStr[i]);
		}
		return result;
	}
	/**
	 * ʮ�������ַ���ת��Ϊbyteֵ
	 * @param hexStr
	 * @return
	 */
	private static byte hexString2Byte(String hexStr){
		int val = Integer.valueOf(hexStr , 16);
		return (byte) val;
	}
	/**
	 * ת��S100Э��
	 * @param originCode
	 * @return
	 */
	private static byte[] transferToS100(byte[] originBytes) {
		// �ȼ�����Ϣ����
		short len = calLengthS100(originBytes);
		byte[] lenBytes = short2Bytes(len);
		originBytes[12] = lenBytes[0];
		originBytes[13] = lenBytes[1];
		// �ټ���У����
		byte[] tempBytes = new byte[originBytes.length-2];
		System.arraycopy(originBytes, 1, tempBytes, 0, originBytes.length-2);
		byte sum = s100(tempBytes);
		originBytes[originBytes.length-2] = sum;
		return originBytes;
	}
	/**
	 * S100У�����㷨
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
	 * @param originBytes
	 * @return
	 */
	private static short calLengthS100(byte[] originBytes){
		int len = 0;
		for(int i=14; i<originBytes.length-2; i++){
			len = len + 1;
		}
		return (short) len;
	}
	/**
	 * ת��911Э��
	 * @param originCode
	 * @return
	 */
	private static byte[] transferTo911(byte[] originBytes) {
		byte[] tempBytes = new byte[originBytes.length - 2];
		System.arraycopy(originBytes, 0, tempBytes, 0, tempBytes.length);
		// ����У����
		short resultShort = CRC16(tempBytes);
		byte[] crcBytes = short2Bytes(resultShort);
		// �滻У����
		originBytes[originBytes.length - 1] = crcBytes[1];
		originBytes[originBytes.length - 2] = crcBytes[0];
		//���㳤��
		short len = calLength911(originBytes);
		byte[] lenBytes = short2Bytes(len);
		originBytes[2] = lenBytes[0];
		originBytes[3] = lenBytes[1];
		
		return originBytes;
	}
	/**
	 * ����911Э�����ݳ���
	 * @param originBytes
	 * @return
	 */
	private static short calLength911(byte[] originBytes){
		int len = 0;
		for(int i=4; i<originBytes.length-2; i++){
			len = len + 1;
		}
		return (short) len;
	}
	
	/**
	 * 911У�����㷨
	 * @param msg
	 * @return
	 */
	private static short CRC16(byte[] msg){
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
	 * shortֵת���ֽ�����
	 * @param value
	 * @return
	 */
	private static byte[] short2Bytes(short value){
		byte[] result = new byte[2];
		result[0] = (byte) ((value >> 8) & 0xffff);
		result[1] = (byte) (value & 0xffff);
		return result;
	}
	
	/**
	 * �ֽ�����ת16�����ַ���
	 * @param bytes
	 * @return
	 */
	private static String byteToString(byte[] bytes) {
		StringBuffer resStr = new StringBuffer();
		int len = bytes.length;
		for (int i = 0; i<len; i++) {
			if(i!=(len-1))
				resStr.append(String.format("%02x", bytes[i]) + " ");
			else 
				resStr.append(String.format("%02x", bytes[i]));
		}
		return resStr.toString();
	}
	
	
	public static void main(String[] args) {
		/*String s = "7e 00 ff 00 01 53 11 11 11 11 11 11 00 00 93 7e";
		
		System.out.println(entrance(s));*/
		
		/*byte[] srcBytes = new byte[]{126, 0, -1, 0, 1, 83, 17, 17, 17, 17, 17, 17, 0, 0, -109, 126};
		byte[] tempBytes = new byte[srcBytes.length-2];
		System.arraycopy(srcBytes, 1, tempBytes, 0, srcBytes.length-2);
		System.out.println(Arrays.toString(tempBytes));*/
		//System.out.println(hexString2Byte("ad"));
		byte[] b = hexStr2Bytes("4e 31 30 30 41 42 30 30 30 30 2e 30 30 30 36 20");
		System.out.println(new String(b));
	}
	
	
	@Test
	public void testDateUtil() throws Exception{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strdate1 = "2016-10-20 20:00:00";
		String strdate2 = "2016-11-20 19:00:00";
		Date date1 = sdf.parse(strdate1);
		Date date2 = sdf.parse(strdate2);
		System.out.println(date1.toLocaleString());
		System.out.println(date2.toLocaleString());
		calendar.setTime(date1);
		// ���ݲɼ�ʱ��
		byte[] date_col = new byte[] { 
				(byte) (calendar.get(Calendar.YEAR) - 2000), 
				(byte) (calendar.get(Calendar.MONTH) + 1),
				(byte) calendar.get(Calendar.DAY_OF_MONTH), 
				(byte) calendar.get(Calendar.HOUR_OF_DAY),
				(byte) calendar.get(Calendar.MINUTE), 
				(byte) calendar.get(Calendar.SECOND) };
		System.out.println(Arrays.toString(date_col));
	}
	
}
