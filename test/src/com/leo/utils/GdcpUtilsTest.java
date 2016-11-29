package com.leo.utils;

import static com.leo.utils.GdcpUtils.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;

import org.junit.Test;

public class GdcpUtilsTest {

	public static void main(String[] args) {
		
		//System.out.println(GdcpUtils.byteToHexString("N".getBytes()));
		
		//System.out.println(GdcpUtils.deviceId2HexString("S534100000351"));
		
		/*
		 * 23 23 02 00 4c 41 38 36 46 30 4d 43 31 46 42 35 30 30 39 36 36 53 35 33 34 31 30 30 30 30 31 32 35 39 01 00 22 10 09 14 0a 25 0c 10 09 14 0a 25 09 07 00 06 ce 4d f2 01 d1 cd 60 00 00 00 13 00 6 66 e6 3f 0b 00 00 f5
		 * */
		
		/*byte[] bytes = hexStr2Bytes("23 23 02 00 4c 41 38 36 46 30 4d 43 31 46 42 35 30 30 39 36 36 53 35 33 34 31 30 30 30 30 31 32 35 39 01 00 22 10 09 14 0a 25 0c 10 09 14 0a 25 09 07 00 06 ce 4d f2 01 d1 cd 60 00 00 00 13 00 6 66 e6 3f 0b 00 00 f5");
		System.out.println(Arrays.toString(bytes));
		
		System.out.println(transCheckCode(bytes));*/
		
		//System.out.println(hexString2Byte("FE"));
		
		//byte[] bytes = hexStr2Bytes("45 31 30 30 36 33 37 31 30 30 35 34 39");
		//System.out.println(new String(bytes));
		
		//System.out.println(fillSpaceString("232303004C4B4A47484E4744454A4B46554E43524C45313030363337313030353439010043100B150F0E21100B150F0E210A0B2F0100111AE80002C6F0FFFF03F81CFF6AFFFFFFFFFFFFFF00040004FFFFFFFFFFFFFFFF00FFFFFFFFFFFFFFFF000541DC02E86280A0"));
		/*String result = String.format("%-17s", "E LDM");
		System.out.println(result);
		System.out.println(result.length());*/
		
		/*short i = 0x0200;
		short j = 0x0F01;
		
		System.out.println(byteToHexString(short2Bytes(i)));
		System.out.println(byteToHexString(short2Bytes(j)));*/
		
		
		/*int i = 55;
		String s = Integer.toBinaryString(i);
		System.out.println(s);
		String s1 = String.format("%10s", s);
		System.out.println(s1);
		String s2 = String.format("%-16s", s1);
		System.out.println(s2);
		String s3 = s2.replaceAll(" ", "0");
		System.out.println(s3);
		short j = Short.parseShort(s3, 2);
		System.out.println(j);
		System.out.println(byteToHexString(short2Bytes(j)));*/
		//short i = 55;
		//0d c0
		//System.out.println(short2MsgProp(i));
		
		//byte b = 0x7E;
		// 126
		//System.out.println(Short.parseShort("7E", 16));
		
		//byte[] bytes = new byte[]{126,02,00,02,20,01,01,01,01,01,126,00,01,00,125,00,00,01,01,01,126};
		//byte[] res = escapeData(bytes);
		//System.out.println(Arrays.toString(res));
		
		//20161122171405
		/*byte[] res = time2BCD(new Date());
		System.out.println(Arrays.toString(res));
		String s = bcdToSim(res);
		System.out.println(s);*/
		
		/*System.out.println(byteToHexString(intToUnsigned8(6)));
		System.out.println(byteToHexString(longToUnsigned32(2)));
		System.out.println(byteToHexString(longToUnsigned32(4294967295l)));
		System.out.println(byteToHexString(longToUnsigned32(999999)));*/
		
		
		double f = 4320 / 1000.0;
		System.out.println(f);
		
		
	}
	
	
	/**
	 * 测试读取数据流掩码
	 * 7a -> 01111010
	 */
	@Test
	public void mask2State(){
		byte mask = 0x7a;
		int flag = 0;
		System.out.print(mask & (0x80 >> flag++) & 0xFF);
		System.out.print(mask & (0x80 >> flag++) & 0xFF);
		System.out.print(mask & (0x80 >> flag++) & 0xFF);
		System.out.print(mask & (0x80 >> flag++) & 0xFF);
		System.out.print(mask & (0x80 >> flag++) & 0xFF);
		System.out.print(mask & (0x80 >> flag++) & 0xFF);
		System.out.print(mask & (0x80 >> flag++) & 0xFF);
		System.out.print(mask & (0x80 >> flag++) & 0xFF);
		
	}
	
	
	
	@Test
	public void test(){
		double i = 0.39f * 100;
		System.out.println(i);
	}
	
	@Test
	public void testSwitch(){
		
		String key = "ZH";
		
		String sf = "SF";
		String zh = "ZH";
		
		switch (key) {
			case "SF":
				System.out.println("SF");
				break;
			case "ZH":
				System.out.println("ZH");
				break;
			default:
				break;
		}
		
	}
}
