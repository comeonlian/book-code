package com.leo.utils;

import static com.leo.utils.GdcpUtils.*;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hamcrest.core.IsNull;
import org.junit.Test;

public class GdcpUtilsTest {

	public static void main(String[] args) throws Exception {
		
		//System.out.println(GdcpUtils.byteToHexString("N".getBytes()));
		
		//System.out.println(GdcpUtils.deviceId2HexString("S534100000351"));
		
		/*
		 * 23 23 02 00 4c 41 38 36 46 30 4d 43 31 46 42 35 30 30 39 36 36 53 35 33 34 31 30 30 30 30 31 32 35 39 01 00 22 10 09 14 0a 25 0c 10 09 14 0a 25 09 07 00 06 ce 4d f2 01 d1 cd 60 00 00 00 13 00 6 66 e6 3f 0b 00 00 f5
		 * */
		
		//byte[] bytes = hexStr2Bytes("40 40 00 45 00 05 45 10 06 37 10 05 50 00 21 01 01 11 01 06 09 36 1c 01 e5 6b 5b 06 fc cf 6a 00 00 0a 8c 00 00 03 2a 03 35 28 23 13 04 44 00 00 03 65 00 07 46 40 00 03 f4 3d 00 01 b1 59 58 62 03 b3 00 33 00 03 01 00 00 6b 4e");
		//System.out.println(Arrays.toString(bytes));
		
		//System.out.println(entrance("40 40 00 45 00 05 45 10 06 37 10 05 50 00 21 01 01 11 01 06 09 36 1c 01 e5 6b 5b 06 fc cf 6a 00 00 0a 8c 00 00 03 2a 03 35 28 23 13 04 44 00 00 03 65 00 07 46 40 00 03 f4 3d 00 01 b1 59 58 62 03 b3 00 33 00 03 01 00 00 6b 4e"));
		
		//System.out.println(hexString2Byte("FE"));
		
		//byte[] bytes = hexStr2Bytes("45 31 30 30 36 33 37 31 30 30 35 34 39");
		//System.out.println(new String(bytes));
		
		//System.out.println(fillSpaceString("4040004900234D6145000018638007010201010201000304000000000404000015000502006706040000000307020000080200000901700A02005C0B01050C011E0D01030E01050F04000000004A2F0D0A"));
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
		
		
		//System.out.println(fillSpaceString("232302FE4C42394B42384B453345454E4A4C38383701006C1101130F0334010101FF000000023CFF169F233C59FF00FFFF000002010100130000FFFF481644FFFF03FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF04FFFFFFFFFF06FFFF0FFFFFFFFFFF01003E01003C07FF00000000FFFFFFFFFF01FFFFFFFFFFFFFFFFFFFFFFFFFFFF1F"));
		
		//System.out.println(version2HexString("N100AB0102"));
		
		
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
		paraList.add("0");
		
		byte[] result = parseObdAdaptInfo(paraList);
		System.out.println(byteToHexString(result));*/
		
		
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
		int i = 0;
		for(; i<5; i++){
			System.out.print(" hehe ");
		}
		System.out.println("i="+i);
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
