package com.leo.http.json;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;

public class HdHttpXml {
	
	public static void main(String[] args) throws Exception {
		String[] ids = {
				"1011100019", "1011100041", "1011100053", 
				"1011100055", "1011100110", "1011100119", 
				"01011100014", "010201320004", "0101032000003"
		};
		String url = "http://localhost:8280/EZSPservice/GetDataResourceInfo";
		String xml = null;
		String result = null;
		
		String id = "0101032000003";
		xml = getXmlInfo(id);
		result = sendPost(url, xml);
		System.out.println(result);
	}
	
	/**
	 * 发送xml请求到server端
	 * @param url
	 *            接口地址
	 * @param xml
	 *            发送的xml数据流
	 * @return null发送失败，否则返回响应内容
	 */
	public static String sendPost(String url, String xml) {
		CloseableHttpClient httpClient = null;
		try {
			httpClient = HttpClients.createDefault();
			HttpPost request = new HttpPost(url);
			CloseableHttpResponse response = null;
			RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(120 * 1000)
					.setConnectTimeout(120 * 1000)
					.build();
			request.setConfig(requestConfig);
			request.addHeader("Content-Type", "text/xml;charset=utf-8");
			StringEntity requestEntity = new StringEntity(xml, "UTF-8");
			request.setEntity(requestEntity);
			response = httpClient.execute(request, new BasicHttpContext());
			if (response.getStatusLine().getStatusCode() != 200) {
				System.out.println("request url failed, http code=" + response.getStatusLine().getStatusCode() + ", url=" + url+", entity="+response.getEntity());
				return null;
			}
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String resultStr = EntityUtils.toString(entity, "UTF-8");
				return resultStr;
			}
		} catch (Exception e) {
			System.out.println("request url=" + url + ", exception, msg=" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	
	private static String getXmlInfo(String interfaceId) {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append("<Request>");
		sb.append("<SenderID>0FEA697E-0B59-4C17-951A-9843C1895DDB</SenderID>");
		sb.append("<Method>");
		sb.append("<Name>GetDataResourceInfo</Name>");
		sb.append("<Security Algorithm=\"\"></Security>");
		sb.append("<Items>");
		sb.append("<Item>");
		sb.append("<Name>DataResourceID</Name>");
		sb.append("<Value Type=\"string\">");
		sb.append("<Data>"+interfaceId+"</Data>");
		sb.append("</Value>");
		sb.append("</Item>");
		sb.append("</Items>");
		sb.append("</Method>");
		sb.append("</Request>");
		return sb.toString();
	}
}
