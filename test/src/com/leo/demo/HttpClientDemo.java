package com.leo.demo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

public class HttpClientDemo {

	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost("http://localhost:8686/monitor/validator");
			List<NameValuePair> reqParam = new ArrayList<NameValuePair>();
			reqParam.add(new BasicNameValuePair("dateTime", "1492420995277"));
			httpPost.setEntity(new UrlEncodedFormEntity(reqParam));
			CloseableHttpResponse response = httpclient.execute(httpPost);

			try {
				System.out.println(response.getStatusLine());
				HttpEntity entity = response.getEntity();
				// do something useful with the response body
				// and ensure it is fully consumed
				//EntityUtils.consume(entity2);
				InputStream content = entity.getContent();
				byte[] bytes = new byte[2048];
				StringBuilder sb = new StringBuilder();
				while(content.read(bytes)!=-1){
					sb.append(new String(bytes));
					bytes = new byte[1024];
				}
				System.out.println(sb.toString());
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
	}
	
	@Test
	public void testDate(){
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		System.out.println(calendar.getTimeInMillis());
	}
	
}
