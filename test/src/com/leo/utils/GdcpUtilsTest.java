package com.leo.utils;

import org.junit.Test;

public class GdcpUtilsTest {

	public static void main(String[] args) {
		
		System.out.println(GdcpUtils.byteToHexString("N".getBytes()));
		
		System.out.println(GdcpUtils.deviceId2HexString("S534100000351"));
		
		
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
