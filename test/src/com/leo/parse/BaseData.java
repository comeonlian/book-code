package com.leo.parse;

import java.nio.ByteBuffer;

public class BaseData {
	
	public ByteBuffer buf_;
	
	
	public static void main(String[] args) {
		String originStr = "40400043466F4E201606140002001101100B0801021E010E132495119A262310742495107524950F512495122324950F532495057D01F407880009D633000000000000B552EEEEAD44";
		
		
		
	}
	
	public static String exchange(String s){
		StringBuilder sb = new StringBuilder("");
		int len = s.length();
		for(int i=0,index=0; i<len; i++,index++){
			sb.append(s.charAt(i));
			//if((index%2)==0)
		}
		return null;
	}
	
}
