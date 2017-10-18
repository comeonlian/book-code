package com.leo.test.file;

import java.io.File;

public class FilePathTest {

	public static void main(String[] args) {
		String filePath = "D:\\Mass\\rootPath\\SBGJ";
		File file = new File(filePath);
		File[] listFiles = file.listFiles();
		for (File f : listFiles) {
			System.out.println(f.getName());
		}
	}

}
