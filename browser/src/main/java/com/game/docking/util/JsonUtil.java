package com.game.docking.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

	@SuppressWarnings("rawtypes")
	public static String object2JsonText(Object obj) {
		if (obj == null)
			return null;
		String result = null;
		if (obj instanceof List) {
			result = list2JsonArray((List) obj).toString();
		} else if (obj instanceof Map) {
			result = map2JsonObject((Map) obj).toString();
		} else {
			result = object2JsonObject(obj).toString();
		}

		return result;

	}

	/**
	 * 将json对象转换成实体bean
	 */
	@SuppressWarnings("rawtypes")
	public static Object jsonObject2Bean(String json, Class c) {
		JSONObject jb = JSONObject.fromObject(json);
		return JSONObject.toBean(jb, c);
	}

	/**
	 * 输出JSON格式数据
	 */
	@SuppressWarnings("rawtypes")
	public static void outJson(Object obj, HttpServletResponse response, String format) {
		if (obj == null)
			return;
		String result = null;
		try {

			if (obj instanceof List) {
				result = list2JsonArray((List) obj).toString();
			} else if (obj instanceof Map) {

				JsonValueProcessor jsonProcessor = new DateJsonValueProcessor(format);
				JsonConfig jsonConfig = new JsonConfig();
				// 注册值处理器
				jsonConfig.registerJsonValueProcessor(Date.class, jsonProcessor);
				result = JSONSerializer.toJSON(obj, jsonConfig).toString();

			} else {
				result = object2JsonObject(obj).toString();
			}

			ActionResponseUtils.wirteAndEnd(result, response);
		} catch (Exception e) {
			LOGGER.error("输出JSON错误", e.getMessage());
		}

	}

	/**
	 * 输出JSON格式数据
	 */
	@SuppressWarnings("rawtypes")
	public static void outJson(Object obj, HttpServletResponse response) {
		if (obj == null)
			return;
		String result = null;
		try {

			if (obj instanceof List) {
				result = list2JsonArray((List) obj).toString();
			} else if (obj instanceof Map) {

				JsonValueProcessor jsonProcessor = new DateJsonValueProcessor();
				JsonConfig jsonConfig = new JsonConfig();
				// 注册值处理器
				jsonConfig.registerJsonValueProcessor(Date.class, jsonProcessor);
				result = JSONSerializer.toJSON(obj, jsonConfig).toString();

			} else {
				result = object2JsonObject(obj).toString();
			}
			ActionResponseUtils.wirteAndEnd(result, response);
		} catch (Exception e) {
			LOGGER.error("输出JSON错误", e.getMessage());
		}

	}

	@SuppressWarnings("rawtypes")
	public static void outSecretJson(Object obj, HttpServletResponse response) {

		if (obj == null)
			return;

		Map<String, Object> smap = new HashMap<String, Object>();
		String keystr = Des.initkey();
		String key = keystr.substring(3, 11);
		smap.put("jabc", keystr);

		String sresult = null;
		try {
			String result = null;
			if (obj instanceof List) {
				result = list2JsonArray((List) obj).toString();
			} else if (obj instanceof Map) {

				JsonValueProcessor jsonProcessor = new DateJsonValueProcessor();
				JsonConfig jsonConfig = new JsonConfig();
				// 注册值处理器
				jsonConfig.registerJsonValueProcessor(Date.class, jsonProcessor);
				result = JSONSerializer.toJSON(obj, jsonConfig).toString();

			} else {
				result = object2JsonObject(obj).toString();
			}

			String text = Des.encryptDES(result, key);
			smap.put("stext", text);
			sresult = map2JsonObject(smap).toString();
			ActionResponseUtils.wirteAndEnd(sresult, response);

		} catch (Exception e) {
			LOGGER.error("输出JSON错误", e.getMessage());
		}

	}

	// ***************** 返回值为JSONObject begin ***********************
	/**
	 * 将obj用JSONObject来描述
	 */
	public static JSONObject object2JsonObject(Object obj) {
		JSONObject jsonObject = JSONObject.fromObject(obj);
		return jsonObject;
	}

	/**
	 * 将数组转为JSONArray格式
	 */
	public static JSONArray array2JsonArray(Object[] objs) {
		JSONArray jsonArray = JSONArray.fromObject(objs); 
		return jsonArray;
	}

	/**
	 * 将Json格式的字符串转为Json数组 格式:("['json','is','easy']")
	 */
	public static JSONArray jsonText2JsonArray(String text) {
		JSONArray jsonArray = JSONArray.fromObject(text);
		return jsonArray;
	}

	/**
	 * 将Json格式的字符串转为Json对象 格式:("{'json','is','easy'}")
	 */
	public static JSONObject jsonText2JsonObject(String text) {
		JSONObject jsonObject = JSONObject.fromObject(text);
		return jsonObject;
	}

	/**
	 * 将List集合转为JSONArray格式
	 */
	@SuppressWarnings("rawtypes")
	public static JSONArray list2JsonArray(List list) {
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray;
	}

	/**
	 * 将Map集合转为Json格式数据
	 */
	@SuppressWarnings("rawtypes")
	public static JSONObject map2JsonObject(Map map) {
		JSONObject json = JSONObject.fromObject(map);
		return json;
	}

	// ***************** 由JSON转化为其他类型 **************************
	/**
	 * 将JSON转换成domain对象,其中beanClass为domain对象的Class
	 */
	public static Object json2Object(String json, Class beanClass) {
		return JSONObject.toBean(JSONObject.fromObject(json), beanClass);
	}

	/**
	 * 将JSON转换成String
	 */
	public static String json2String(String json, String key) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		return jsonObject.get(key).toString();
	}

	/**
	 * 将JSON转换成数组,其中valueClass为数组中存放对象的Class
	 */
	public static Object json2Array(String json, Class valueClass) {
		JSONArray jsonArray = JSONArray.fromObject(json);
		return JSONArray.toArray(jsonArray, valueClass);
	}

	/**
	 * 将JSON转换成Map,其中valueClass为Map中value的Class,keyArray为Map的key
	 */
	public static Map json2Map(Object[] keyArray, String json, Class valueClass) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		Map classMap = new HashMap();

		for (int i = 0; i < keyArray.length; i++) {
			classMap.put(keyArray[i], valueClass);
		}
		return (Map) JSONObject.toBean(jsonObject, Map.class, classMap);
	}

	public static List<Map<String, Object>> parseJSON2List(String jsonStr) {
		JSONArray jsonArr = JSONArray.fromObject(jsonStr);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Iterator<JSONObject> it = jsonArr.iterator();
		while (it.hasNext()) {
			JSONObject json2 = it.next();
			list.add(parseJSON2Map(json2.toString()));
		}
		return list;
	}

	public static Map<String, Object> parseJSON2Map(String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 最外层解析
		JSONObject json = JSONObject.fromObject(jsonStr);
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			// 如果内层还是数组的话，继续解析
			if (v instanceof JSONArray) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Iterator<JSONObject> it = ((JSONArray) v).iterator();
				while (it.hasNext()) {
					JSONObject json2 = it.next();
					list.add(parseJSON2Map(json2.toString()));
				}
				map.put(k.toString(), list);
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}

	public static List<Map<String, Object>> getListByUrl(String url) {
		try {
			// 通过HTTP获取JSON数据
			InputStream in = new URL(url).openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			return parseJSON2List(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Map<String, Object> getMapByUrl(String url) {
		try {
			// 通过HTTP获取JSON数据
			InputStream in = new URL(url).openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			return parseJSON2Map(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		// 请求参数格式
		String req = "{\"gld\":{\"adid\":\"1111111111sd\",\"si\":\"12345\",\"ei\":\"1456\",\"cid\":\"dfettt\"},\"pay\":{\"time\":145676,\"pro\":15,\"ok\":1,\"err\":506},\"opd\":{\"mhs\":4567787,\"mhl\":50,\"wv\":300,\"ops\":{}}}";
		Integer ii = null;
		System.out.println(String.valueOf(ii));
		
	}
}
