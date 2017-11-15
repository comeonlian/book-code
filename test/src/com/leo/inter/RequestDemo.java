package com.leo.inter;

import java.util.HashMap;
import java.util.Map;

import com.dragonsoft.node.adapter.comm.RbspCall;
import com.dragonsoft.node.adapter.comm.RbspService;

/**
 * 佛山市公安部-全国在逃人员基本信息表[FS000280]数据查询服务(001)(S44-06000174)
 * @author dfgdfgr
 *
 */
public class RequestDemo {

	public static void main(String[] args) {
		req();
		System.out.println("The End.");
	}
	
	
	public static void req(){
		
		RbspService service = new RbspService("C44-06000104","S44-06000174");
		
		// 记录请求方信息--请填写真实信息
		service.setUserCardId("430626198208312410");
		//设置用户单位
		service.setUserDept("4406");
		//设置用户名
		service.setUserName("叶云");
		//设置PKI编号
		service.setPkiId("");
		
		RbspCall call = service.createCall();
		call.setUrl("http://localhost:8585/node");
		call.setMethod("proxyService");
		
		Map<String,Object> params = new HashMap<String,Object>(); 
		params.put("DataObjectCode", "FS000280");
		params.put("start", 0);
		params.put("limit", 10);
		params.put("params", new String[]{"440603198505144211","余荣行"});
		
		
		String result = call.invoke(params);
		System.out.println(result);
	}

}