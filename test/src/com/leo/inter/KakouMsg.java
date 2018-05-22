/**
 * 
 */
package com.leo.inter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dragonsoft.node.adapter.comm.RbspCall;
import com.dragonsoft.node.adapter.comm.RbspService;

import redis.clients.jedis.Jedis;

/**
 * Description: 
 * @author lianliang
 * @date 2018年1月25日 下午4:09:52
 */
public class KakouMsg {
	private static final String USER_CODE = "C44-06000104";
	private static final String USER_CARD = "430626198208312410";
	private static final String USER_DEPT = "4406";
	private static final String USER_NAME = "叶云";
	private String url = "http://localhost:10024/node";
	private String redisHost = "192.168.0.112";
	
	public static void main(String[] args) throws Exception {
		KakouMsg obj = new KakouMsg();
		
		if(args.length==2) {
			obj.setUrl(args[0]);
			obj.setRedisHost(args[1]);
		}
		
		Map<String, String> res = obj.execute();
		System.out.println("map size: "+res.size()+", map data: "+res);
		obj.write2Redis(res);
	}
	
	public Map<String, String> execute() throws Exception {
		Map<String, String> map = new HashMap<String, String>(2000);
		String code = "S44-06000529";
		String method = "proxyService";
		Map<String,Object> params = new HashMap<String,Object>(); 
		params.put("DataObjectCode", "FS201203");
		params.put("start", 0);
		params.put("limit", 10);
		params.put("params", new String[]{"", "", "2009-01-01 00:00:00", "2018-12-31 00:00:00"});
		String res = request(code, method, params);
		JSONObject jsonObject = getJson(res);
		Integer total = jsonObject.getInteger("records");
		int page = (total%100)==0 ? (total/100):(total/100)+1;
		// System.out.println("page: "+page);
		int index = 0;
		int length = 100;
		for(int i=0; i<page; i++) {
			index = i * length;
			params.remove("start");
			params.remove("limit");
			params.put("start", index);
			params.put("limit", length);
			// System.out.println("param: " + params);
			jsonObject = getJson(request(code, method, params));
			getData(map, jsonObject);
			TimeUnit.SECONDS.sleep(10);
		}
		return map;
	}
	
	/**
	 * @param map
	 * @param jsonObject
	 */
	public void getData(Map<String, String> map, JSONObject jsonObject) {
		JSONObject json = null;
		JSONArray jsonArray = jsonObject.getJSONArray("rows");
		for(int i=0; i<jsonArray.size(); i++) {
			json = jsonArray.getJSONObject(i);
			String kkbh = json.getString("kkbh"); // 卡口编号
			String jd = json.getString("kkwd"); // 经度
			String wd = json.getString("kkjd"); // 维度
			map.put(kkbh, jd+"-"+wd);
		}
	}
	
	
	public JSONObject getJson(String param) throws Exception {
		Document doc = DocumentHelper.parseText(param);
		String json = doc.getRootElement().element("Method").element("Items").element("Item").element("Value").element("Data").getText();
		JSONObject jsonObject = JSONObject.parseObject(json);
		return jsonObject;
	}
	
	
	public void write2Redis(Map<String, String> res) {
		Jedis jedis = null;
		try {
			// redis
			jedis = new JedisPoolUtil(redisHost, 6379).getJedis();
			jedis.hmset("nanhai-kkmsg", res);
		}catch (Exception e) {
			try {
				if(null!=jedis)
					jedis.close();
			}catch (Exception ex) {
				e.printStackTrace();
			}
		}
	}
	
	public String request(String code, String method, Map<String, Object> params) {
		String url = this.getUrl();
		RbspService service = new RbspService(USER_CODE, code);
		// 记录请求方信息--请填写真实信息
		service.setUserCardId(USER_CARD);
		//设置用户单位
		service.setUserDept(USER_DEPT);
		//设置用户名
		service.setUserName(USER_NAME);
		//设置PKI编号
		service.setPkiId("");
		RbspCall call = service.createCall();
		call.setUrl(url);
		call.setMethod(method);
		String result = call.invoke(params);
		return result;
	}

	
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the redisHost
	 */
	public String getRedisHost() {
		return redisHost;
	}

	/**
	 * @param redisHost the redisHost to set
	 */
	public void setRedisHost(String redisHost) {
		this.redisHost = redisHost;
	}
	
}
