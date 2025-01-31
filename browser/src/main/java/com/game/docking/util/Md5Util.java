package com.game.docking.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Md5Util {
	private static final Logger LOGGER = LoggerFactory.getLogger(Md5Util.class);
	private static MessageDigest md5 = null;
	static {
			try {
				md5 = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				LOGGER.info("MD5初始化失败：" + e.getMessage());
			}
	}

	/**
	 * 用于获取一个String的md5值
	 * 
	 * @param string
	 * @return
	 */
	public static String getMd5(String str) {
		byte[] bs = md5.digest(str.getBytes());
		StringBuilder sb = new StringBuilder(40);
		for (byte x : bs) {
			if ((x & 0xff) >> 4 == 0) {
				sb.append("0").append(Integer.toHexString(x & 0xff));
			} else {
				sb.append(Integer.toHexString(x & 0xff));
			}
		}
		return sb.toString().toUpperCase();
	}

	public static void main(String[] args) {
	    String fileName = "C:\\Users\\bury12\\Desktop\\a.txt";  
        //readFileByBytes(fileName);  
        //readFileByChars(fileName);  
        //readFileByLines(fileName);  
	    readFileByChars(fileName);  
	}
	
	 /** 
     * 以字符为单位读取文件，常用于读文本，数字等类型的文件 
     */  
    public static void readFileByChars(String fileName) {  
        File file = new File(fileName);  
        Reader reader = null;  
        try {  
            System.out.println("以字符为单位读取文件内容，一次读一个字节：");  
            // 一次读一个字符  
            reader = new InputStreamReader(new FileInputStream(file), "utf-8");  
            int tempchar;  
            while ((tempchar = reader.read()) != -1) {  
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。  
                // 但如果这两个字符分开显示时，会换两次行。  
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。  
                if (((char) tempchar) != '\r') {  
                    System.out.print((char) tempchar);  
                }  
            }  
            reader.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        try {  
            System.out.println("\n以字符为单位读取文件内容，一次读多个字节：");  
            // 一次读多个字符  
            char[] tempchars = new char[30];  
            int charread = 0;  
            reader = new InputStreamReader(new FileInputStream(fileName));  
            // 读入多个字符到字符数组中，charread为一次读取字符数  
            while ((charread = reader.read(tempchars)) != -1) {  
                // 同样屏蔽掉\r不显示  
                if ((charread == tempchars.length)  
                        && (tempchars[tempchars.length - 1] != '\r')) {  
                    System.out.print(tempchars);  
                } else {  
                    for (int i = 0; i < charread; i++) {  
                        if (tempchars[i] == '\r') {  
                            continue;  
                        } else {  
                            System.out.print(tempchars[i]);  
                        }  
                    }  
                }  
            }  
  
        } catch (Exception e1) {  
            e1.printStackTrace();  
        } finally {  
            if (reader != null) {  
                try {  
                    reader.close();  
                } catch (IOException e1) {  
                }  
            }  
        }  
    }  
}