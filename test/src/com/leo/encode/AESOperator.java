package com.leo.encode;

import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * AES 是一种可逆加密算法，对用户的敏感信息加密处理 对原始数据进行AES加密后，在进行Base64编码转化；
 */
public class AESOperator {
	//加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式
	//key和向量都需要为16位。
	private String sKey = "fhqji26gflzo8byo";
	private String ivParameter = "w3aqkgjjqxf8e7b2";
	private static AESOperator instance = null;
	
	private static char[] chars = new char[]{
			'a', 'b', 'c', 'f', 'e', 'f', 'g',
			'h', 'i', 'j', 'k', 'l', 'm', 'o',
			'p', 'q', 'r', 's', 't', 'u', 'v',
			'w', 'x', 'y', 'z', '0', '1', '2',
			'3', '4', '5', '6', '7', '8', '9',
	};
	private static Random random = new Random();
	
	private AESOperator() { }
	
	public static AESOperator getInstance() {
		if (instance == null)
			instance = new AESOperator();
		return instance;
	}
	
	/**
	 * 随机生成一个key
	 * @return
	 */
	public static String initKey(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 16; i++) {
			int rand = random.nextInt(34);
			sb.append(chars[rand]);
		}
		return sb.toString();
	}
	
	/**
	 * 随机生成一个ivParameter
	 * @return
	 */
	public static String initIvParameter(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 16; i++) {
			int rand = random.nextInt(34);
			sb.append(chars[rand]);
		}
		return sb.toString();
	}
	
	// 加密
	public String encrypt(String sSrc) {
		String result = "";
		try {
			Cipher cipher;
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			byte[] raw = sKey.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
			result = new BASE64Encoder().encode(encrypted);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 此处使用BASE64做转码。
		return result;
	}

	// 解密
	public String decrypt(String sSrc) {
		try {
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original, "utf-8");
			return originalString;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		// 需要加密的字串
		String cSrc = "root";
		System.out.println(cSrc + "  长度为" + cSrc.length());
		// 加密
		long lStart = System.currentTimeMillis();
		String enString = AESOperator.getInstance().encrypt(cSrc);
		System.out.println("加密后的字串是：" + enString + " 长度为" + enString.length());
		
		long lUseTime = System.currentTimeMillis() - lStart;
		System.out.println("加密耗时：" + lUseTime + "毫秒");
		// 解密
		lStart = System.currentTimeMillis();
		String DeString = AESOperator.getInstance().decrypt(enString);
		System.out.println("解密后的字串是：" + DeString);
		lUseTime = System.currentTimeMillis() - lStart;
		System.out.println("解密耗时：" + lUseTime + "毫秒");
		
		//System.out.println(initKey());
		//System.out.println(initIvParameter());
	}
	
	
	
	
}