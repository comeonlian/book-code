package com.game.util.apk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;





import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.game.shorts.entity.ApkEntry;
import com.game.util.Constants;

/**
 * Apk反编译解析工具类
 * 
 * @author Crap
 */
public class ApkUtil {
	
	/**
     * 解析获取Apk实体
     *
     * @param apk Part servlet上传文件
     * @param outputPath String 输出文件夹
     * @param filename String 保存文件名
     * 
     * @author Crap
     */
	public static ApkEntry getApkEntry(File apk, String outputPath, String filename){
		final ApkEntry entry = new ApkEntry();
		try {
			String path = outputPath +"/"+filename;
			
			//设置apk 服务器路径
			entry.setApkPath(path);
			entry.setApkSize(apk.length()+"");
			com.game.util.FileUtil.createDirs(outputPath, true);
			File apkFile = new File(path);
			FileUtils.copyFile(apk, apkFile);
			
			unZipApk(outputPath, filename);
			String fileDirName = filename.substring(0, filename.length() - 4);
			String apkDir = outputPath+"/"+fileDirName;
			File manifest = new File(apkDir+"/AndroidManifest.xml");
			
			//查找apktool解析出来的变量
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = builder.parse(manifest);
			
			Node manifestNode = document.getElementsByTagName("manifest").item(0);
			NamedNodeMap  attrs = manifestNode.getAttributes();
			Node tmpNode;
			for(int i=0; i<attrs.getLength(); i++){
				tmpNode = attrs.item(i);
				if(tmpNode.getNodeName().equals("android:versionCode")){
					entry.setVersionCode(tmpNode.getNodeValue());
				}
				if(tmpNode.getNodeName().equals("android:versionName")){
					entry.setApkVersion(tmpNode.getNodeValue());
				}
				if(tmpNode.getNodeName().equals("package")){
					entry.setApkPackage(tmpNode.getNodeValue());
				}
			}
			
			//设置icon,apk MD5
			entry.setApkMd5(OpUtil.encryptMD5File(new FileInputStream(new File(path))));
			//设置icon,apk 相对于服务器路径
			String apkPath = Constants.CONS_PROPERTIES.getValue("apkvisiturl");
			entry.setApkUrl(apkPath +"/"+filename);
			entry.setApkPath(outputPath+"\\"+filename);
			System.out.println(entry);
			//清除目录下其他文件
			String tmpFileName = outputPath + "/" + filename.substring(0, filename.length() - 4);
			File tmpFile = new File(tmpFileName);
			com.game.util.FileUtil.deleteDir(tmpFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entry;
	}
	
	/**
     * apktool反编译apk
     *
     * @param path String
     * @param filename String
     * @throws IOException
     * 
     * @author Crap
     */
	public static void unZipApk(String path, String filename) throws IOException{
		String[] cmd = {"java", "-jar", path+"/apktool.jar", "d", "-f", path+"/"+filename, "-o", path+"/"+filename.substring(0, filename.length() - 4)};
		Process pc = Runtime.getRuntime().exec(cmd);
		BufferedReader err = new BufferedReader(new InputStreamReader(pc.getErrorStream()));
		String line;
		while((line = err.readLine()) != null){
			System.out.println(line);
		}
		err.close();
	}

	public static void main(String[] args) {
		File apk = new File("D:/IDE/apache-tomcat-7.0.57/webapps/kxw/apk/1.apk");
		ApkUtil.getApkEntry(apk, "D:/IDE/apache-tomcat-7.0.57/webapps/kxw/apk", "3.apk");
	}
}
