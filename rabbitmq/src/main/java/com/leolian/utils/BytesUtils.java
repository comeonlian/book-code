package com.leolian.utils;

import java.util.Arrays;

public class BytesUtils {
	
	public static String[] byteToString(byte[] bytes) {
		String[] resStr = new String[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			resStr[i] = String.format("%02x", bytes[i]);
		}
		return resStr;
	}
	
	
	public static void main(String[] args) {
		byte[] bytes = new byte[]{35, 35, 1, 1, 83, 53, 51, 52, 49, 48, 48, 48, 48, 49, 50, 50, 56, 32,
				32, 32, 32, 1, 0, 46, 16, 8, 5, 7, 44, 15, 0, 1, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49,
				49, 49, 49, 49, 49, 49, 49, 49, 49, 1, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48,
				48, 48, 48, 48, 126};
		
		System.out.println(Arrays.toString(byteToString(bytes)));
	}
}
