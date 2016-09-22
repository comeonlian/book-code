package com.game.browser.action;  
  
import java.util.ArrayList;  
import java.util.List;  
import java.net.URI;  
  
import org.apache.http.Header;  
import org.apache.http.HeaderElement;  
import org.apache.http.HeaderElementIterator;  
import org.apache.http.HeaderIterator;  
import org.apache.http.HttpEntity;  
import org.apache.http.NameValuePair;  
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.CloseableHttpResponse;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.client.utils.URIBuilder;  
import org.apache.http.impl.client.CloseableHttpClient;  
import org.apache.http.impl.client.HttpClients;  
import org.apache.http.message.BasicHeaderElementIterator;  
import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.util.EntityUtils;  
  
  
public class HttpPostTest {  
  
    public static void main(String[] args) throws Exception {  
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        try {  
            
  
            //POST方法  
            HttpPost httpPost = new HttpPost("http://192.168.5.75:10010/browser/lmuch/index.action");  
//            HttpPost httpPost = new HttpPost("http://183.63.53.103:21080/lmuch/index.action");  
//            List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
//            nvps.add(new BasicNameValuePair("dd", "12"));  
//            nvps.add(new BasicNameValuePair("gg", "32"));  
//            httpPost.setEntity(new UrlEncodedFormEntity(nvps));  
            // httpPost.setHeader("content-type", "application/octet-stream");
            CloseableHttpResponse response3 = httpclient.execute(httpPost);  

            try {  
                System.out.println("Http Post Request:**********");  
                System.out.println(httpPost.toString());  
//              System.out.println(httpPost.);  
                System.out.println("Http Post Response:**********");  
                System.out.println(response3.getStatusLine());  
                  
                HttpEntity entity2 = response3.getEntity();  
                EntityUtils.consume(entity2);  
            } finally {  
                response3.close();  
            }  
        } finally {  
            httpclient.close();  
        }  
    }  
  
} 