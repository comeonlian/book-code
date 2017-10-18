//package com.leo.http.xml;
//
//import java.io.BufferedInputStream;
//import java.io.BufferedReader;
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.net.URL;
//import java.net.URLConnection;
//
///**
// * 测试调用一些meeting第三方接口
// * 
// * @author Jack.Song
// */
//public class TestMeetingInterface {
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//
//		String url = "http://192.168.0.68/integration/xml";
//		TestMeetingInterface tmi = new TestMeetingInterface();
//		System.out.println(tmi.post(url, "listSummaryMeeting.xml"));
//
//		/*
//		 * //判断当前系统是否支持Java AWT Desktop扩展 if(java.awt.Desktop.isDesktopSupported()){ try
//		 * { URI path = tmi.getClass().getResource("/listSummaryMeeting.xml").toURI();
//		 * System.out.println(path); //创建一个URI实例 // java.net.URI uri =
//		 * java.net.URI.create(path); //获取当前系统桌面扩展 java.awt.Desktop dp =
//		 * java.awt.Desktop.getDesktop(); //判断系统桌面是否支持要执行的功能
//		 * if(dp.isSupported(java.awt.Desktop.Action.BROWSE)){ //获取系统默认浏览器打开链接
//		 * dp.browse(path); } } catch (Exception e) { e.printStackTrace(); } }
//		 */
//	}
//	
//	/**
//	 * 用传统的URI类进行请求
//	 * 
//	 * @param urlStr
//	 */
//	public void testPost(String urlStr) {
//		try {
//			URL url = new URL(urlStr);
//			URLConnection con = url.openConnection();
//			con.setDoOutput(true);
//			con.setRequestProperty("Pragma:", "no-cache");
//			con.setRequestProperty("Cache-Control", "no-cache");
//			con.setRequestProperty("Content-Type", "text/xml");
//
//			OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
//			String xmlInfo = getXmlInfo();
//			System.out.println("urlStr=" + urlStr);
//			// System.out.println("xmlInfo=" + xmlInfo);
//			out.write(new String(xmlInfo.getBytes("UTF-8")));
//			out.flush();
//			out.close();
//			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//			String line = "";
//			for (line = br.readLine(); line != null; line = br.readLine()) {
//				System.out.println(line);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private String getXmlInfo() {
//		StringBuilder sb = new StringBuilder();
//		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
//		sb.append("<Message>");
//		sb.append(" <header>");
//		sb.append("     <action>readMeetingStatus</action>");
//		sb.append("     <service>meeting</service>");
//		sb.append("     <type>xml</type>");
//		sb.append("     <userName>admin</userName>");
//		sb.append("     <password>admin</password>");
//		sb.append("     <siteName>box</siteName>");
//		sb.append(" </header>");
//		sb.append(" <body>");
//		sb.append("     <confKey>43283344</confKey>");
//		sb.append(" </body>");
//		sb.append("</Message>");
//
//		return sb.toString();
//	}
//	
//	
//	/**
//	 * 发送xml请求到server端
//	 * 
//	 * @param url
//	 *            xml请求数据地址
//	 * @param xmlString
//	 *            发送的xml数据流
//	 * @return null发送失败，否则返回响应内容
//	 */
//	public static String sendPost(String url, String xmlString) {
//		// 创建httpclient工具对象
//		HttpClient client = new HttpClient();
//		// 创建post请求方法
//		PostMethod myPost = new PostMethod(url);
//		// 设置请求超时时间
//		client.setConnectionTimeout(3000 * 1000);
//		String responseString = null;
//		try {
//			// 设置请求头部类型
//			myPost.setRequestHeader("Content-Type", "text/xml");
//			myPost.setRequestHeader("charset", "utf-8");
//			// 设置请求体，即xml文本内容，一种是直接获取xml内容字符串，一种是读取xml文件以流的形式
//			myPost.setRequestBody(xmlString);
//			int statusCode = client.executeMethod(myPost);
//			// 只有请求成功200了，才做处理
//			if (statusCode == HttpStatus.SC_OK) {
//				InputStream inputStream = myPost.getResponseBodyAsStream();
//				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
//				StringBuffer stringBuffer = new StringBuffer();
//				String str = "";
//				while ((str = br.readLine()) != null) {
//					stringBuffer.append(str);
//				}
//				responseString = stringBuffer.toString();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			myPost.releaseConnection();
//		}
//		return responseString;
//	}
//}