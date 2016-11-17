package com.leo.utils;

import static com.leo.utils.GdcpUtils.*;

import java.util.Arrays;

import org.junit.Test;

public class GdcpUtilsTest {

	public static void main(String[] args) {
		
		//System.out.println(GdcpUtils.byteToHexString("N".getBytes()));
		
		//System.out.println(GdcpUtils.deviceId2HexString("S534100000351"));
		
		/*
		 * 23 23 02 00 4c 41 38 36 46 30 4d 43 31 46 42 35 30 30 39 36 36 53 35 33 34 31 30 30 30 30 31 32 35 39 01 00 22 10 09 14 0a 25 0c 10 09 14 0a 25 09 07 00 06 ce 4d f2 01 d1 cd 60 00 00 00 13 00 6 66 e6 3f 0b 00 00 f5
		 * */
		
		byte[] bytes = hexStr2Bytes("23 23 02 00 4c 41 38 36 46 30 4d 43 31 46 42 35 30 30 39 36 36 53 35 33 34 31 30 30 30 30 31 32 35 39 01 00 22 10 09 14 0a 25 0c 10 09 14 0a 25 09 07 00 06 ce 4d f2 01 d1 cd 60 00 00 00 13 00 6 66 e6 3f 0b 00 00 f5");
		System.out.println(Arrays.toString(bytes));
		
		System.out.println(transCheckCode(bytes));
		
		System.out.println(hexString2Byte("95"));
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
	
}
