package com.leo.utils;

import static com.leo.utils.GdcpUtils.*;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hamcrest.core.IsNull;
import org.junit.Test;

public class GdcpUtilsTest {

	public static void main(String[] args) throws Exception {
		
		//System.out.println(GdcpUtils.byteToHexString("N".getBytes()));
		
		//System.out.println(GdcpUtils.deviceId2HexString("E100637101320"));
		
		/*
		 * 23 23 02 00 4c 41 38 36 46 30 4d 43 31 46 42 35 30 30 39 36 36 53 35 33 34 31 30 30 30 30 31 32 35 39 01 00 22 10 09 14 0a 25 0c 10 09 14 0a 25 09 07 00 06 ce 4d f2 01 d1 cd 60 00 00 00 13 00 6 66 e6 3f 0b 00 00 f5
		 * */
		
		//byte[] bytes = hexStr2Bytes("40 40 00 45 00 05 45 10 06 37 10 05 50 00 21 01 01 11 01 06 09 36 1c 01 e5 6b 5b 06 fc cf 6a 00 00 0a 8c 00 00 03 2a 03 35 28 23 13 04 44 00 00 03 65 00 07 46 40 00 03 f4 3d 00 01 b1 59 58 62 03 b3 00 33 00 03 01 00 00 6b 4e");
		//System.out.println(Arrays.toString(bytes));
		
		//System.out.println(entrance("40 40 00 45 00 05 45 10 06 37 10 05 50 00 21 01 01 11 01 06 09 36 1c 01 e5 6b 5b 06 fc cf 6a 00 00 0a 8c 00 00 03 2a 03 35 28 23 13 04 44 00 00 03 65 00 07 46 40 00 03 f4 3d 00 01 b1 59 58 62 03 b3 00 33 00 03 01 00 00 6b 4e"));
		///byte[] bytes = new byte[]{64, 64, 0, 85, 80, -103, 83, 101, 1, 0, 0, 2, 118, 0, 2, 48, 48, 53, 49, 48, 48, 52, 67, 51, 51, 51, 53, 53, 49, 49, 57, 51, 49, 51, 51, 51, 55, 51, 50, 52, 54, 48, 48, 52, 48, 52, 57, 52, 48, 48, 51, 49, 51, 55, 56, 57, 56, 54, 48, 50, 98, 52, 50, 50, 49, 54, 99, 48, 51, 48, 51, 49, 49, 55, 56, 54, 49, 52, 52, 53, 48, 51, 53, 55, 52, 54, 48, 48, 50, -52, -31};
		//System.out.println(byteToHexString(bytes));
		
		//byte[] bytes = hexStr2Bytes("45 31 30 30 36 33 37 31 30 30 35 34 39");
		//System.out.println(new String(bytes));
		
		System.out.println(fillSpaceString("4040016000615353410000070400130111030D09382BE00DFFFFFFFFFFFFFFFFFFFF01000000000020000000789B601FDAC000000001778036000001010000000000C8000000000014D77D0D000000000001DC7363000103000000000000000000000000010735A4001237D2010082DC80200000011C04002710000A000105FB000000FFFFFFFFFFFFFFFFFFFF0000000000000000000000000000000000210E0000000000000000000000000000210B000000000000000000000000003202013501010E5B01010A73020101000000000000000000000001000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000FFFFFFFFFF7FFFFF0101013D4E209C4039150E7D0D00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001E9FD2906B0DF6A00000000000003DE9302010114D77D0D009E00019E0E5B0A730C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670C670101001C35303333333333333333333333333333333333333333333333020000CBC9"));
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
		
		
		//double f = 4320 / 1000.0;
		//System.out.println(f);
		
		
		//System.out.println(hexString2DeviceId("4D201500000001"));
		
		
		//System.out.println(fillSpaceString("404001600050535341000007040013011103070A0410E00D010000000000F000000001000000000020000000789B601FDAC000000001778036000001010000000000C8000000000014D77D16000000000001DC6A63000103000000000000000000000000010735A4001237AA010082DC80200000011404002710000A000105FB000000FFFFFFFFFFFFFFFFFFFF0000000000000000000000000000000000210E00000000000000000000000000002108000000000000000000000000003201023503010D1608080C9E0407010000000000000000000000010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009B000000007F00000101003D4E209C4039150EFFFF00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001E9FCDB06B0DF8C000000000000058C13020000C527"));
		
		//System.out.println(version2HexString("E100AB0103"));
		
		
		//System.out.println(transAlarmType(-6));
		
		
		//System.out.println(String.format("%02x", 57360));
		
		/*byte[] result = new byte[]{};
		for(int i=0; i<10; i++){
			result = join(
				result, 
				new byte[]{ intToUnsigned8(i) },
				new byte[]{ intToUnsigned8(i+1) }
			);
		}
		System.out.println(Arrays.toString(result));*/
		
		
		//System.out.println(hexString2DeviceId("E100637101320"));
		//System.out.println(hexString2DeviceId("E100637100550"));
		
		//System.out.println(Double.valueOf("12.1526"));
		
		//List<String> list = new ArrayList<String>();
		//list.add(null);
		//list.add("1");
		//System.out.println(list.get(0));
		//System.out.println(list.get(1));
		
		//System.out.println(isNumeric("a23s.0"));
		/*String sTime = "2017-01-13 14:00:00";
		String eTime = "2017-01-13 15:00:00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		Date startDate = sdf.parse(sTime);
		Date endDate = sdf.parse(eTime);
		calendar.setTime(startDate);
		System.out.println(calendar.getTimeInMillis());
		calendar.setTime(endDate);
		System.out.println(calendar.getTimeInMillis());*/
		
		/*String protocolType = "0X0101"; //协议类型
		String readType = "0x01"; //读码方式
		String guardProtocol = "0x00000005"; //防盗协议
		String carTypeID = "0X00001500"; //车型ID
		String interval = "350";  //帧与帧间隔
		String ecu = "0x00000111"; //ECU地址 
		String fuel = "50"; //油耗系数
		String distance = "10";  //里程系数
		String displacement = "60";  //排量
		String fuelOilType = "737";  //油品密度
		String fuelOilMethod = "0x04"; //油耗计算方法
		String streamReadTime = "50"; //数据流读取时间
		String changeCarFlag = "0x02"; //换车标志
		String powerType = "0x01"; //车辆动力类型
		String maxTorque = "1000"; //最大扭矩
		String engineNum = "8"; //发动机缸
		String fullPower = "400"; //满载电量
		List<String> paraList = new ArrayList<String>();
		paraList.add(protocolType);
		paraList.add(readType);
		paraList.add(guardProtocol);
		paraList.add(carTypeID);
		paraList.add(interval);
		paraList.add(ecu);
		paraList.add(fuel);
		paraList.add(distance);
		paraList.add(displacement);
		paraList.add(fuelOilType);
		paraList.add(fuelOilMethod);
		paraList.add(streamReadTime);
		paraList.add(changeCarFlag);
		paraList.add(powerType);
		paraList.add(maxTorque);
		paraList.add(engineNum);
		paraList.add(fullPower);
		paraList.add("0");*/
		
		//byte[] result = new byte[]{64, 64, 0, 47, 0, 87, 78, 32, 22, 6, 20, 0, 2, 1, 2, 78, 49, 48, 48, 65, 66, 48, 49, 48, 50, 46, 48, 50, 48, 48, 32, 50, 48, 49, 54, 45, 49, 49, 45, 48, 55, 83, 73, 77, 56, 48, 48, 76, 32, 32, 32, -98, -91};
		//System.out.println(byteToHexString(result));
		
		
		//System.out.println(getPlatformVin());
		
		//String uName = "13391919685";
		//uName = String.format("%-12s", uName);
		//System.out.println(Arrays.toString(uName.getBytes()));
		//int d = 255;
		//System.out.println(byteToHexString(intToUnsigned8(d)));
		/*byte[] temp = new byte[]{};
		List<Integer> list = new ArrayList<Integer>();
		list.add(8);
		for (Integer integer : list) {
			temp = join(
				temp,
				new byte[]{intToUnsigned8(integer)}
			);
		}
		System.out.println(Arrays.toString(temp));*/
		
		//System.out.println(byteToHexString("java".getBytes()));
		/*int i = 0;
		for(; i<5; i++){
			System.out.print(" hehe ");
		}
		System.out.println("i="+i);*/
		
		/*String s = "[17, 245]";
		s = s.replaceAll("\\[", "");
		s = s.replaceAll("\\]", "");
		String[] strArray  = s.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (String t : strArray) {
			t = t.trim();
			list.add(Integer.valueOf(t));
		}
		System.out.println(list);*/
		//System.out.println(Integer.MAX_VALUE);
		//4294967295
		//100 0000 000
		//long l = 50000000l;
		//float f = l * 0.001f;
		//System.out.println(f);
		///float t = (float) (27 / 10.0);
		//System.out.println(t);
		//System.out.println(735 / 100.0);
		
		/*float f = 999999900;
		double t = f * 0.001;
		System.out.println(t);
		double x = f * 1000;
		System.out.println(x);
		long l = (long) (t * 10);
		System.out.println(l);*/
		//char c = 83;
		//System.out.println((char)c);
		
		/*byte[] bytes = new byte[]{0x4c, 0x41, 0x39, 0x58, 0x44, 0x32, 0x41, 0x36, 0x30, 0x47, 0x30, 0x57, 0x53, 0x48, 0x30, 0x30, 0x31};
		String s = new String(bytes);
		System.out.println(s);*/
		
		
		//byte[] bytes = buildFullCommand("S000000000001", 0x8101, new byte[]{});
		//System.out.println(byteToHexString(bytes));
		
		
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
		double i = 0.21;
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
	
	@Test
	public void testByteTool(){
		
		byte[] bytes = new byte[]{0x01, 0x00, 0x15, 0x01, 0x20, 0x01, 0x01, (byte) 0x92, 0x01, 0x00, 0x05, 0x0F, 0x04, 0x01, 0x00, 0x01, 0x02, 0x00, 0x15, 0x01, 0x20, 0x01, 0x01, (byte) 0x92, 0x02, 0x00, 0x05, 0x0F, 0x04, 0x01, 0x00, 0x02, 0x03, 0x00, 0x15, 0x01, 0x20, 0x01, 0x01, (byte) 0x92, 0x03, 0x00, 0x05, 0x0F, 0x04, 0x01, 0x00, 0x03, 0x04, 0x00, 0x15, 0x01, 0x20, 0x01, 0x01, (byte) 0x92, 0x04, 0x00, 0x05, 0x0F, 0x04, 0x01, 0x00, 0x04}; 
		System.out.println(Arrays.toString(bytes));
		List<Byte> list = new ArrayList<>();
		for (Byte b : bytes) {
			list.add(b);
		}
		System.out.println(list);
		Arrays.asList(bytes);
		
		byte i = -110;
		System.out.println(byteToHexString(intToUnsigned16(i)));
		
	}
	
	@Test
	public void testException(){
		/*byte[] bytes = new byte[]{0x38, 0x39, 0x38, 0x36, 0x30, 0x32, 0x42, 0x33, 0x32, 0x32, 0x31, 0x35, 0x43, 0x30, 0x30, 0x30, 0x36, 0x31, 0x31, 0x35};
		String s = new String(bytes);
		System.out.println(s);*/
		
		String s1 = "shjikedfrjhyedkiwoskcmvn";
		String s2 = "shfgkedhjjherdk";
		String s3 = "jikowlefdqnjizsewdbg";
		String s4 = "okmwdcfwwqajhgr";
		
		System.out.println(byteToHexString(s1.getBytes()));
		System.out.println(byteToHexString(s2.getBytes()));
		System.out.println(byteToHexString(s3.getBytes()));
		System.out.println(byteToHexString(s4.getBytes()));
		
	}
	
	@Test
	public void testArrayCopy(){
		byte[] result = new byte[]{0, 0, 1, 2, 0, 1, 0, 3, 4, 5, 6, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int len = result.length-10;
		byte[] temp = new byte[len];
		System.arraycopy(result, 0, temp, 0, len);
		System.out.println(Arrays.toString(temp));
	}
	
}
