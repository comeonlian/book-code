package com.leo.demo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;

import org.junit.Test;

//import com.google.gson.Gson;

public class DemoGson {

//	@SuppressWarnings("unchecked")
//	public static void main(String[] args) {
//		Gson gson = new Gson();
//		Map<String,String> map = gson.fromJson("{\"name\":\"leo\", \"age\":\"24\"}", Map.class);
//		System.out.println(map);
//	}
	
	@Test
	public void testDouble(){
		DecimalFormat df=new DecimalFormat("0.0000");
		Long a = 16L;
		Long b = 93777L;
		double d = (a*1.0)/b;
		System.out.println(d);
		System.out.println(validator(d));
		
		BigDecimal bg = new BigDecimal(d).setScale(4, RoundingMode.UP);
		System.out.println(bg.doubleValue());
	}
	
	private int validator(double value){
		if(value>0.6){
			return 2;
		} else if (value>=0.5){
			return 1;
		} else {
			return 0;
		}
	}
}
