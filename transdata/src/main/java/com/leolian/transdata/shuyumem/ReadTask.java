/**
 * 
 */
package com.leolian.transdata.shuyumem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Description: 
 * @author lianliang
 * @date 2017年12月11日 下午7:46:14
 */
public class ReadTask implements Runnable{
	
	public static final DateTimeFormatter DATE_FORMAT1 = DateTimeFormat.forPattern("yyyy/MM/dd");
	public static final DateTimeFormatter DATE_FORMAT2 = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss");
	
	private String path;
	private ArrayBlockingQueue<Member> queue;
	
	/**
	 * @param path
	 * @param queue
	 */
	public ReadTask(String path, ArrayBlockingQueue<Member> queue) {
		this.path = path;
		this.queue = queue;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		File file = null;
		BufferedReader read = null;
		try {
			file = new File(path);
			read = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.forName("GB2312")));
			String line = null;
			String[] arr = null;
			Member mem = null;
			String name = null, addTime = null, cardId = null;
			List<String> list = new ArrayList<>();
			int cnt = 0;
			while((line=read.readLine())!=null) {
				try {
					//System.out.println("line: "+line);
					list.clear();
					arr = line.split(",");
					if(arr.length < 6) {
						System.out.println("行数据不合法，跳过："+line);
						continue ;
					}
					for (String s : arr) {
						list.add(s.replaceAll("\"", ""));
					}
					//System.out.println("array list: "+list);
					
					mem = new Member();
					name = list.get(0);
					if(name.contains("（")) {
						name = name.substring(0, name.indexOf("（"));
					}
					if(name.contains("（")) { //校验姓名
						System.out.println("姓名不合法，跳过："+line);
						continue ;
					}
					mem.setName(name); //姓名
					addTime = list.get(3);
					if(StringUtils.isBlank(addTime)) {
						addTime = "";
					} else {
						if(addTime.contains(":")) {
							addTime = String.valueOf(DateTime.parse(addTime, DATE_FORMAT2).getMillis()/1000);
						} else {
							addTime = String.valueOf(DateTime.parse(addTime, DATE_FORMAT1).getMillis()/1000);
						}
					}
					mem.setAddTime(addTime); //业务时间
					cardId = list.get(1);
					if(StringUtils.isBlank(cardId)) {
						cardId = "";
					} else {
						if(cardId.length()!=18) {
							System.out.println("身份证不合法，跳过："+line);
							continue ;
						}
						cardId = cardId.toUpperCase();
					}
					mem.setCardId(cardId);
					mem.setCardType("1021111");
					String phone = list.get(4);
					if(StringUtils.isBlank(phone)) {
						phone = "";
					} else {
						if(phone.length()!=11) {
							System.out.println("手机号不合法，跳过："+line);
							continue ;
						}
					}
					if(cardId.length()==0 && phone.length()==0) {
						System.out.println("身份证，手机号都为空，跳过："+line);
						continue ;
					}
					mem.setPhone(phone);
					mem.setShop(validate(list.get(5)));
					
					queue.put(mem);
					cnt++;
				}catch (Exception e) {
					continue ;
				}
			}
			//System.out.println("count : "+cnt);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(read!=null)
					read.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private String validate(String param) {
		if(StringUtils.isBlank(param)) {
			return "";
		}
		return param;
	}
	
}
