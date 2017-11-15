package com.leo.inter;

import java.util.Map;

import com.dragonsoft.node.adapter.comm.RbspCall;
import com.dragonsoft.node.adapter.comm.RbspService;

/**
 * 通用请求方法
 * @Description: 
 * @author lianliang
 * @date 2017年10月18日 下午5:26:10
 */
public class CommonRequest {
	private static final String USER_CODE = "C44-06000104";
	private static final String USER_CARD = "430626198208312410";
	private static final String USER_DEPT = "4406";
	private static final String USER_NAME = "叶云";
	private static final String REQ_URL = "http://localhost:8585/node";
	
	
	public static String request(String code, String method, Map<String, Object> params) {
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
		call.setUrl(REQ_URL);
		call.setMethod(method);
		String result = call.invoke(params);
		return result;
	}
	
}
