package com.leo.test;

import java.util.Arrays;

import org.junit.Test;

public class CheckSum {

	private static byte[] hexStrToByte(String lineStr, String splitStr) {
		String[] sourceArr = lineStr.split(splitStr);
		byte[] resultArr = new byte[sourceArr.length];
		// 校验从第2个到校验位之前
		for (int i = 0; i < sourceArr.length; i++) {
			resultArr[i] = (byte) (Integer.valueOf(sourceArr[i].trim(), 16) & 0x000000FF);
		}

		byte[] tranedBytes = transdata(resultArr);

		byte sum = getCheckSum(tranedBytes);
		byte[] resultBytes = fillSum(resultArr, sum);

		return resultBytes;
	}

	private static byte[] transdata(byte[] msg) {
		byte[] temp = new byte[msg.length];
		int index = 0;
		// 去除首尾标识符 , 对数据转义
		for (int i = 1; i < msg.length - 1; i++) {
			// 0x7d+0x02 ---> 0x7e
			if (msg[i] == 0x7d && msg[i + 1] == 0x02) {
				temp[index++] = 0x7e;
				i++;
			}
			// 0x7d+0x01 ----> 0x7d
			else if (msg[i] == 0x7d && msg[i + 1] == 0x01) {
				temp[index++] = 0x7d;
				i++;
			} else {
				temp[index++] = msg[i];
			}

		}
		byte[] tempmsg = new byte[index];
		System.arraycopy(temp, 0, tempmsg, 0, index);
		return tempmsg;
	}

	private static byte getCheckSum(byte[] resultArr) {
		byte sum = 0;
		for (int i = 0; i <= resultArr.length - 2; i++) {
			sum ^= resultArr[i];
		}
		return sum;
	}

	private static byte[] fillSum(byte[] resultArr, byte sum) {
		resultArr[resultArr.length - 2] = sum;
		if (sum == 0x7E) {
			byte[] newResult = new byte[resultArr.length + 1];
			System.arraycopy(resultArr, 0, newResult, 0, resultArr.length);
			newResult[newResult.length - 1] = 0x7E;
			newResult[newResult.length - 2] = 0x02;
			newResult[newResult.length - 3] = 0x7d;
			return newResult;
		} else if (sum == 0x7D) {
			byte[] newResult = new byte[resultArr.length + 1];
			System.arraycopy(resultArr, 0, newResult, 0, resultArr.length);
			newResult[newResult.length - 1] = 0X7E;
			newResult[newResult.length - 2] = 0x01;
			newResult[newResult.length - 3] = 0x7d;
			return newResult;
		}
		return resultArr;
	}

	private static short CRC16(byte[] msg) {
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

	private static String[] byteToString(byte[] bytes) {
		String[] resStr = new String[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			resStr[i] = String.format("%02x", bytes[i]);
		}
		return resStr;
	}

	public static String getCheckSumToS100(String lineStr) {
		lineStr = lineStr.trim();
		byte[] messageByte = CheckSum.hexStrToByte(lineStr.trim(), " ");
		String outString = Arrays.toString(CheckSum.byteToString(messageByte));
		outString = outString.replaceAll(",", "");
		outString = outString.replaceAll("\\[", "");
		outString = outString.replaceAll("\\]", "");
		return outString;
	}

	public static String getCheckSumTo911(String lineStr) {
		lineStr = lineStr.trim();
		String[] hexStrs = lineStr.split(" ");
		byte[] resultArr = new byte[hexStrs.length];
		byte[] resultArrNew = new byte[hexStrs.length - 2];
		for (int i = 0; i < hexStrs.length; i++) {
			resultArr[i] = (byte) (Integer.valueOf(hexStrs[i].trim(), 16) & 0x000000FF);
		}
		System.arraycopy(resultArr, 0, resultArrNew, 0, resultArrNew.length);

		short sum = CheckSum.CRC16(resultArrNew);

		resultArr[resultArr.length - 1] = (byte) (sum & 0xff);
		resultArr[resultArr.length - 2] = (byte) ((sum >> 8) & 0xff);

		String outString = Arrays.toString(CheckSum.byteToString(resultArr));
		outString = outString.replaceAll(",", "");
		outString = outString.replaceAll("\\[", "");
		outString = outString.replaceAll("\\]", "");
		return outString;
	}

	public static void main(String[] args) {
		// 7e 00 04 00 12 53 11 11 11 11 11 11 00 00 45 7e
		// 7e 00 20 00 0f 53 11 11 11 11 11 11 00 24 10 03 04 03 14 1c 01 58 1f
		// ee 06 ca 93 c1 05 00 00 02 13 00 00 00 00 00 00 86 00 00 00 00 1d 00
		// 00 00 00 00 fd 7e
		//String strS100 = CheckSum.getCheckSumToS100("7e 00 04 00 12 53 11 11 11 11 11 11 00 00 25 7e");
		//System.out.println("s100:" + strS100);

		// 40 40 00 3d 00 65 45 20 16 06 14 00 03 00 10 01 10 0a 09 01 28 05 01
		// 58 21 22 06 ca 94 69 00 00 00 00 00 00 01 40 08 1e 12 17 93 05 7c 00
		// 00 00 00 00 00 00 00 00 00 00 00 00 00 01 81 57 f9 9f f4 c8 c2
		/*String str911 = CheckSum.getCheckSumTo911(
				"40 40 00 81 00 03 4e 20 16 08 17 15 02 00 24 10 01 10 1e 19 01 c0 aa 31 31 31 31 31 30 30 20 00 00 00 14 00 00 01 01 01 00 02 01 01 02 00 02 01 01 03 00 02 01 01 04 00 02 01 01 05 00 02 01 01 06 00 02 01 01 07 00 02 01 01 08 00 02 01 01 09 00 02 01 01 0A 00 02 01 01 0B 00 02 01 01 30 00 02 01 01 31 00 02 01 01 32 00 02 01 01 40 00 02 01 01 41 00 02 01 01 42 00 02 01 01 43 00 02 01 01 44 00 02 01 01 45 6D");
		System.out.println("911:" + str911);
		
		int i = 129;
		System.out.println((byte)i);*/
		
		String s = "N101AB0101      ";
		System.out.println(Arrays.toString(byteToString(s.getBytes())));
		
	}
	
	@Test
	public void calLength(){
		String s = "00 03 4e 20 16 08 17 15 02 00 24 10 01 10 1e 19 01 c0 aa 31 31 31 31 31 30 30 20 00 00 00 14 00 00 01 01 01 00 02 01 01 02 00 02 01 01 03 00 02 01 01 04 00 02 01 01 05 00 02 01 01 06 00 02 01 01 07 00 02 01 01 08 00 02 01 01 09 00 02 01 01 0a 00 02 01 01 0b 00 02 01 01 30 00 02 01 01 31 00 02 01 01 32 00 02 01 01 40 00 02 01 01 41 00 02 01 01 42 00 02 01 01 43 00 02 01 01 44 00 02 01 01";
		String[] sarr = s.split(" ");
		System.out.println(sarr.length);
		
	}
	
	
}
