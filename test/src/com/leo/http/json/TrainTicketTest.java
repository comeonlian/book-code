package com.leo.http.json;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

/**
 * @Description: 荆州火车票信息
 * @author lianliang
 * @date 2017年9月1日 上午10:32:22
 */
public class TrainTicketTest {
	public static final DateTimeFormatter FORMAT_P = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
	public static final DateTimeFormatter FORMAT_S = DateTimeFormat.forPattern("yyyyMMddHHmmss");
	
	
	public static void main(String[] args) {
		//jzxx();
		
		//spxx();
		
		//hbspxx();
		
		xjrdd();
		
		//get();
	}
	
	// 20170920120000','JSSJ':'20170922110000
	private static void xjrdd() {
		String url = "http://localhost:8001/api/cloudapi/tielu";
		
		String startTime = DateTime.parse("2017-09-20 12:00:00", FORMAT_P).toString(FORMAT_S);
		String endTime = DateTime.parse("2017-09-22 11:00:00", FORMAT_P).toString(FORMAT_S);
		String params = "{data:{'appkey':'JZ0716AN-P6N4-JI8X-32IM-UC3CLQNQU6T1','apptype':'xytl_xjrdd.cz','KSSJ':'"+startTime+"','JSSJ':'"+endTime+"','CURPAGE':1,'PAGESIZE':100}}";
		
		String res = httpPostAPI(url, params);
		System.out.println(res);
	}

	/**
	 * 售票信息
	 */
	public static void spxx() {
		String url = "http://localhost:8001/api/cloudapi/tielu";
		
		String startTime = DateTime.parse("2017-01-01 00:00:00", FORMAT_P).toString(FORMAT_S);
		String endTime = DateTime.parse("2017-08-31 23:59:59", FORMAT_P).toString(FORMAT_S);
		String params = "{data:{'appkey':'JZ0716AN-P6N4-JI8X-32IM-UC3CLQNQU6T1','apptype':'xytl_spxx.cz','KSSJ':'"+startTime+"','JSSJ':'"+endTime+"','CURPAGE':1,'PAGESIZE':100}}";
		
		String res = httpPostAPI(url, params);
		System.out.println(res);
	}
	
	/**
	 * 进站信息
	 */
	public static void jzxx() {
		String url = "http://localhost:8001/api/cloudapi/tielu";
		
		String startTime = DateTime.parse("2017-09-08 08:00:00", FORMAT_P).toString(FORMAT_S);
		String endTime = DateTime.parse("2017-09-08 21:00:00", FORMAT_P).toString(FORMAT_S);
		String params = "{data:{'appkey':'JZ0716AN-P6N4-JI8X-32IM-UC3CLQNQU6T1','apptype':'xytl_jzxx.cz','KSSJ':'"+startTime+"','JSSJ':'"+endTime+"','CURPAGE':1,'PAGESIZE':100}}";
		
		String res = httpPostAPI(url, params);
		System.out.println(res);
	}
	
	/**
	 * 湖北售票信息
	 */
	public static void hbspxx() {
		String url = "http://localhost:8001/api/cloudapi/tielu";
		
		String startTime = DateTime.parse("2017-09-20 00:00:00", FORMAT_P).toString(FORMAT_S);
		String endTime = DateTime.parse("2017-09-20 23:59:59", FORMAT_P).toString(FORMAT_S);
		String params = "{data:{'appkey':'JZ0716AN-P6N4-JI8X-32IM-UC3CLQNQU6T1','apptype':'xytl_hbspxx.cz','KSSJ':'"+startTime+"','JSSJ':'"+endTime+"','CURPAGE':1,'PAGESIZE':10000}}";
		
		String res = httpPostAPI(url, params);
		System.out.println(res);
	}
	
	
	public static String httpPostAPI(String host, String data) {
		CloseableHttpClient httpClient = null;
		try {
			httpClient = HttpClients.createDefault();
			HttpPost request = new HttpPost(host);
			CloseableHttpResponse response = null;
			RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(120 * 1000)
					.setConnectTimeout(120 * 1000)
					.build();
			request.setConfig(requestConfig);
			request.addHeader("Content-Type", "application/json;charset=utf-8");

			StringEntity requestEntity = new StringEntity(data, "UTF-8");
			request.setEntity(requestEntity);
			response = httpClient.execute(request, new BasicHttpContext());
			if (response.getStatusLine().getStatusCode() != 200) {
				System.out.println("request url failed, http code=" + response.getStatusLine().getStatusCode() + ", url=" + host+", entity="+response.getEntity());
				return null;
			}
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String resultStr = EntityUtils.toString(entity, "UTF-8");
				return resultStr;
			}
		} catch (Exception e) {
			System.out.println("request url=" + host + ", exception, msg=" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Test
	public void stringFormat() {
		String s = "{data:{'appkey':'%s','apptype':'%s','KSSJ':'%s','JSSJ':'%s','CURPAGE':1,'PAGESIZE':%s}}";
		String appkey = "JZ0716AN-P6N4-JI8X-32IM-UC3CLQNQU6T1";
		String apptype = "xytl_jzxx.cz";
		String kssj = "20170817120000";
		String jssj = "20170817122000";
		String pageSzie = String.valueOf(10000);
		
		String format = String.format(s, appkey, apptype, kssj, jssj, pageSzie);
		System.out.println(format);
	}
	
	@Test
	public void numCal() {
		int i = 12;
		
		int r = (i%100)==0? i/100 : (i/100)+1;
		
		System.out.println(r);
	}
	
	/**
	 * GET请求
	 */
	public static void get() {
		String host = "http://localhost:8090/myserver/helloWorld?username=hehe&password=7896";
		String res = httpGetAPI(host);
		System.out.println(res);
	}
	
	
	public static String httpGetAPI(String url) {
		CloseableHttpClient httpClient = null;
		try {
			httpClient = HttpClients.createDefault();
			HttpGet get = new HttpGet(url);
			CloseableHttpResponse response = null;
			RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(120 * 1000)
					.setConnectTimeout(120 * 1000)
					.build();
			get.setConfig(requestConfig);
			get.addHeader("Content-Type", "application/json;charset=utf-8");
			
			response = httpClient.execute(get, new BasicHttpContext());
			if (response.getStatusLine().getStatusCode() != 200) {
				System.out.println("request url failed, http code=" + response.getStatusLine().getStatusCode() + ", ulr=" + url+", entity="+response.getEntity());
				return null;
			}
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String resultStr = EntityUtils.toString(entity, "UTF-8");
				return resultStr;
			}
		} catch (Exception e) {
			System.out.println("request url=" + url + ", exception msg=" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
}
