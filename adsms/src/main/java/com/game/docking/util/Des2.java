package com.game.docking.util;

import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Des2 {

	private static byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8 };

	public static String initkey() {
		String[] keys = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x",
				"y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
		StringBuffer kstr = new StringBuffer();
		for (int i = 0; i < 8; i++) {
			int d = new Random().nextInt(62);
			kstr.append(keys[d]);
		}
		return kstr.toString();
	}

	public static String encryptDES(String encryptString, String encryptKey) throws Exception {
		// IvParameterSpec zeroIv = new IvParameterSpec(new byte[8]);
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
		byte[] encryptedData = cipher.doFinal(encryptString.getBytes());

		return Base64.encode(encryptedData);
	}

	public static String decryptDES(String decryptString, String decryptKey) throws Exception {
		byte[] byteMi = Base64.decode(decryptString);
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		// IvParameterSpec zeroIv = new IvParameterSpec(new byte[8]);
		SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
		byte decryptedData[] = cipher.doFinal(byteMi);

		return new String(decryptedData);
	}

	public static String encryptDES(String encryptString) throws Exception {
		String encryptKey = initkey();
		return encryptKey.concat(encryptDES(encryptString, encryptKey));
	}

	/**
	 * 协议解析串
	 */
	public static String decryptDES(String src) throws Exception {
		String urlKey = src.substring(0, 8);
		String readlyContent = src.substring(8);
		return decryptDES(readlyContent, urlKey);
	}

	public static void main(String[] args) {
		String dd = "{\"gld\":{\"svc\":17,\"avc\":1,\"ino\":1,\"pdk\":\"15354\",\"mod\":\"w9500-A\",\"cpu\":\"mt6572\",\"pkg\":\"com.magic.bubbles\",\"mac\":\"689c5e38a4fb\",\"lt\":1,\"aid\":\"999d2dd4f17e785f\",\"cid\":\"daiji_12345\",\"si\":\"460017644296848\",\"avn\":\"1.0\",\"ei\":\"862768022019979\",\"st\":1371228702505,\"src\":\"480x854\",\"brd\":\"android\"},\"opd\":{\"mhs\":61470,\"mhl\":26,\"wv\":60,\"ops\":[{\"act\":1,\"time\":1371228702351}]}}";
		try {
			System.out.println(encryptDES(dd));
			System.out.println(decryptDES(encryptDES(dd)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
