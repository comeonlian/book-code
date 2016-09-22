package com.game.docking.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Des {

	private static byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8 };
	private static String[] keys = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
			"w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

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
		byte[] byteMi = new Base64().decode(decryptString);
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		// IvParameterSpec zeroIv = new IvParameterSpec(new byte[8]);
		SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
		byte decryptedData[] = cipher.doFinal(byteMi);

		return new String(decryptedData);
	}

	public static String initkey() {

		StringBuffer kstr = new StringBuffer();
		for (int i = 0; i < 16; i++) {
			int d = new Random().nextInt(62);
			kstr.append(keys[d]);
		}
		return kstr.toString();
	}

	public static void main(String[] args) throws Exception {

	    String str = "460019854007577-863766020061877";
	    System.out.println(encryptDES(str, "22222222"));
	    System.out.println(decryptDES(encryptDES(str, "22222222"), "22222222"));
//		File f = new File("D:/ff.txt");
//		BufferedWriter bw = null;
//		try {
//			bw = new BufferedWriter(new FileWriter(f));
//			String hql = "select sum(anr),sum(imei),sum(imsi),dd from (select nvl(g.ff,0) as anr,nvl(g.cc,0) as imei,nvl(h.cc,0) as imsi,g.dd from (select t.cid,t.cc as ff,f.cc,t.dd from"
//					+ " anruser t left join imeiuser f on t.cid=f.cid and t.dd=f.dd order by t.dd) g left join imsiuser h on h.cid=g.cid and h.dd=g.dd order by h.cid,g.dd) hh  where 1=1";
//
//			bw.write(hql);
//			/*
//			 * bw.write(result1); bw.write("\n"); bw.write(key);
//			 */
//			bw.close();
//		} catch (Exception e) {
//
//		}
	}

}
