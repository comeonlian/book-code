package com.leo.encode;

import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * AES ��һ�ֿ�������㷨�����û���������Ϣ���ܴ��� ��ԭʼ���ݽ���AES���ܺ��ڽ���Base64����ת����
 */
public class AESOperator {
	//�����õ�Key ������26����ĸ��������� �˴�ʹ��AES-128-CBC����ģʽ
	//key����������ҪΪ16λ��
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
	 * �������һ��key
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
	 * �������һ��ivParameter
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
	
	// ����
	public String encrypt(String sSrc) {
		String result = "";
		try {
			Cipher cipher;
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			byte[] raw = sKey.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// ʹ��CBCģʽ����Ҫһ������iv�������Ӽ����㷨��ǿ��
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
			result = new BASE64Encoder().encode(encrypted);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// �˴�ʹ��BASE64��ת�롣
		return result;
	}

	// ����
	public String decrypt(String sSrc) {
		try {
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// ����base64����
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original, "utf-8");
			return originalString;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		// ��Ҫ���ܵ��ִ�
		String cSrc = "root";
		System.out.println(cSrc + "  ����Ϊ" + cSrc.length());
		// ����
		long lStart = System.currentTimeMillis();
		String enString = AESOperator.getInstance().encrypt(cSrc);
		System.out.println("���ܺ���ִ��ǣ�" + enString + " ����Ϊ" + enString.length());
		
		long lUseTime = System.currentTimeMillis() - lStart;
		System.out.println("���ܺ�ʱ��" + lUseTime + "����");
		// ����
		lStart = System.currentTimeMillis();
		String DeString = AESOperator.getInstance().decrypt(enString);
		System.out.println("���ܺ���ִ��ǣ�" + DeString);
		lUseTime = System.currentTimeMillis() - lStart;
		System.out.println("���ܺ�ʱ��" + lUseTime + "����");
		
		//System.out.println(initKey());
		//System.out.println(initIvParameter());
	}
	
	
	
	
}