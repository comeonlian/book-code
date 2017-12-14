/**
 * 
 */
package com.leolian.transdata.shuyumem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Description: 
 * @author lianliang
 * @date 2017年12月11日 下午7:46:04
 */
public class WriteTask implements Runnable {
	public static final DateTimeFormatter DATEFORMAT1 = DateTimeFormat.forPattern("yyyyMMddHHmmssSSS");
	public static final DateTimeFormatter DATEFORMAT2 = DateTimeFormat.forPattern("yyyyMMdd");
	public static final DateTimeFormatter DATEFORMAT3 = DateTimeFormat.forPattern("yyyyMMddHHmmss");
	
	private String path;
	private ArrayBlockingQueue<Member> queue;
	private String fileMark = "143" + "_" + "370112" + "_" + "999000028" + "_" + "104" + ".log";
	
	/**
	 * @param path
	 * @param queue
	 */
	public WriteTask(String path, ArrayBlockingQueue<Member> queue) {
		super();
		this.path = path;
		this.queue = queue;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while(true) {
			try {
				List<Member> list = new ArrayList<>(2000);
				int res = queue.drainTo(list);
				if(res>0) {
					System.out.println("size: "+res);
					JSONArray jsonArray = new JSONArray(10000);
					JSONObject jsonObject = null;
					for (Member member : list) {
						jsonObject = new JSONObject();
						jsonObject.put("uuid", "");
						jsonObject.put("addTime", member.getAddTime());
						jsonObject.put("name", member.getName());
						jsonObject.put("cardId", member.getCardId());
						jsonObject.put("cardType", member.getCardType());
						jsonObject.put("phone", member.getPhone());
						jsonObject.put("shop", member.getShop());
						jsonObject.put("address", "");
						jsonObject.put("wechat", "");
						jsonObject.put("lcFlag", "0");
						
						if(jsonArray.size()>10000) {
							genFile(path, fileMark, jsonArray);
							jsonArray.clear();
						}
						jsonArray.add(jsonObject);
					}
					if(jsonArray.size()>0) {
						genFile(path, fileMark, jsonArray);
						jsonArray.clear();
					}
				}
				TimeUnit.SECONDS.sleep(2);
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
		
	}
	
	/**
	 * JSON数据写入到文件
	 * @param path 文件路径
	 * @param fileMark 文件名
	 * @param jsonArray 插入的JSON数据
	 * @throws Exception
	 */
	public static void genFile(String path, String fileMark, JSONArray jsonArray) throws IOException{
		String fileName = null;
		String fileFullPath = genFullPath(path);
		DateTime dt = new DateTime();
		String date = dt.toString(DATEFORMAT1);
		fileName = fileFullPath + date + "_" + fileMark;
		File file = new File(fileName);
		if (file.exists() && file.isFile()) {
			FileUtils.deleteQuietly(file);
		} else {
			if (!file.getParentFile().exists()) {
				FileUtils.forceMkdir(file.getParentFile());
			}
			file.createNewFile();
		}
		FileUtils.writeStringToFile(file, jsonArray.toJSONString(), false);
		FileUtils.touch(new File(fileName + ".ok"));
		System.out.println("json writer service to file: "+jsonArray.size()+" : "+fileName);
	}
	
	/**
	 * 生成目录
	 * @param dir
	 * @return
	 */
	public static String genFullPath(String dir) {
		StringBuilder sb = new StringBuilder();
		DateTime dt = new DateTime();
		int hour = dt.getHourOfDay();
		int mins = (dt.getMinuteOfHour() / 5) * 5;
		String yearDate = dt.toString(DATEFORMAT2);
		String hourStr = null;
		String minStr = null;
		if (hour < 10) {
			hourStr = "0" + hour;
		} else {
			hourStr = "" + hour;
		}
		if (mins < 10) {
			minStr = "0" + mins;
		} else {
			minStr = "" + mins;
		}
		sb.append(dir).append(File.separator).append(yearDate).append(File.separator).append(hourStr)
				.append(File.separator).append(minStr).append(File.separator);
		return sb.toString();
	}
}
