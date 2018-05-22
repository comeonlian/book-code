/**
 * 
 */
package com.leo.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

/**
 * @Description: 
 * @Author lianliang
 * @Date 2018年5月12日 下午3:43:07
 */
public class JarsExtract {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		/*String f = "F:/Java/CDH/source/source.txt";
		List<String> allLines = Files.readAllLines(Paths.get(f));
		Set<String> jarNames = new HashSet<>();
		for (String s : allLines) {
			String sub = s.substring(s.indexOf("lib/"), s.lastIndexOf("!"));
			int first = sub.indexOf("/");
			String jarName = sub.substring(first+1);
			jarNames.add(jarName);
		}
		
		System.out.println(jarNames.size()); // 109
		
		String p = "F:/Java/CDH/jars";
		String t = "F:/Java/CDH/observer";
		File jars = Paths.get(p).toFile();
		File target = Paths.get(t).toFile();
		File[] files = jars.listFiles();
		for (File file : files) {
			String fileName = file.getName();
			if(jarNames.contains(fileName)) {
				FileUtils.copyFileToDirectory(file, target);
			}
		}*/
		
		String t = "F:/Java/CDH/observer";
		File jars = Paths.get(t).toFile();
		File[] files = jars.listFiles();
		StringBuilder sb = new StringBuilder();
		for (File file : files) {
			String fileName = file.getName();
			String prefix = fileName.substring(0, fileName.lastIndexOf("."));
			sb.append("<dependency> "); 
			sb.append("    <groupId>com.surfilter.define</groupId> "); 
			sb.append("    <artifactId>"+prefix+"</artifactId> "); 
			sb.append("    <version>1.0</version> "); 
			sb.append("    <scope>system</scope> "); 
			sb.append("    <systemPath>${basedir}/src/main/lib/"+fileName+"</systemPath> "); 
			sb.append("</dependency>  "); 
		}
		System.out.println(sb.toString());
	}

}
