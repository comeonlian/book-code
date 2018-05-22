package com.leo.inter;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * @Description: 
 * @author lianliang
 * @date 2017年10月18日 下午5:27:03
 */
public class RequestAnalyze {
	
	/**
	 * 佛山市公安部-全国在逃人员基本信息表[FS000280]数据查询服务(001)(S44-06000174)
	 */
	@Test
	public void S4406000174() {
		String code = "S44-06000174";
		
		String method = "proxyService";
		
		Map<String,Object> params = new HashMap<String,Object>(); 
		params.put("DataObjectCode", "FS000280");
		params.put("start", 0);
		params.put("limit", 10);
		params.put("params", new String[]{"440603198505144211","余荣行"});
		
		CommonRequest.request(code, method, params);
	}
	
	/**
	 * 佛山市公安部-全国在逃人员基本信息问题表[FS000300]数据查询服务(001)  S44-06000193
	 */
	@Test
	public void S4406000193() {
		String code = "S44-06000193";
		
		String method = "proxyService";
		
		Map<String,Object> params = new HashMap<String,Object>(); 
		params.put("DataObjectCode", "FS000300");
		params.put("start", 0);
		params.put("limit", 200);
		params.put("params", new String[]{"余荣行", "440603198505144211"});
		
		CommonRequest.request(code, method, params);
	}
	
	/**
	 * 佛山市公安部-三非重点人员信息[FS000299]数据查询服务(001)  S44-06000192
	 */
	@Test
	public void S4406000192() {
		String code = "S44-06000192";
		
		String method = "proxyService";
		
		Map<String,Object> params = new HashMap<String,Object>(); 
		params.put("DataObjectCode", "FS000299");
		params.put("start", 0);
		params.put("limit", 200);
		params.put("params", new String[]{"440603198505144211"});
		
		CommonRequest.request(code, method, params);
	}
	
	/**
	 * 佛山市省公安厅-涉毒人员[FS000278]数据查询服务(001)  S44-06000172
	 */
	@Test
	public void S4406000172() {
		String code = "S44-06000172";
		
		String method = "proxyService";
		
		Map<String,Object> params = new HashMap<String,Object>(); 
		params.put("DataObjectCode", "FS000278");
		params.put("start", 0);
		params.put("limit", 200);
		params.put("params", new String[]{"440603198505144211", "余荣行"});
		
		CommonRequest.request(code, method, params);
	}
	
	/**
	 * 佛山市省公安厅-吸毒人员信息[FS000244]数据查询服务(001)  S44-06000147
	 */
	@Test
	public void S4406000147() {
		String code = "S44-06000147";
		
		String method = "proxyService";
		
		Map<String,Object> params = new HashMap<String,Object>(); 
		params.put("DataObjectCode", "FS000244");
		params.put("start", 0);
		params.put("limit", 200);
		params.put("params", new String[]{"440603198505144211"});
		
		CommonRequest.request(code, method, params);
	}
	
	/**
	 * 佛山市市警综办-家庭成员关系[FS000483]数据查询服务(001)  S44-06000362
	 */
	@Test
	public void S4406000362() {
		String code = "S44-06000362";
		
		String method = "proxyService";
		
		Map<String,Object> params = new HashMap<String,Object>(); 
		params.put("DataObjectCode", "FS000483");
		params.put("start", 0);
		params.put("limit", 200);
		params.put("params", new String[]{});
		
		CommonRequest.request(code, method, params);
	}
	
	/**
	 * 佛山市市警综办-在逃人员[FS000247]数据查询服务(001)  S44-06000149
	 */
	@Test
	public void S4406000149() {
		String code = "S44-06000149";
		
		String method = "proxyService";
		
		Map<String,Object> params = new HashMap<String,Object>(); 
		params.put("DataObjectCode", "FS000247");
		params.put("start", 0);
		params.put("limit", 200);
		params.put("params", new String[]{"440603198505144211"});
		
		CommonRequest.request(code, method, params);
	}
	
	/**
	 * 佛山市佛山卡口数据表[FS201202]数据查询服务(001)  S44-06000528
	 */
	@Test
	public void S4406000528() {
		String code = "S44-06000528";
		
		String method = "proxyService";
		
		Map<String,Object> params = new HashMap<String,Object>(); 
		params.put("DataObjectCode", "FS201202");
		params.put("start", 0);
		params.put("limit", 200);
		//params.put("params", new String[]{"粤A00001", "G3612042", "2017-01-01", "2017-12-31"});
		params.put("params", new String[]{"2017-01-01", "2017-12-31"});
		
		CommonRequest.request(code, method, params);
	}
	
	/**
	 * 佛山市公安部-临控人员信息[FS000288]数据查询服务(001)  S44-06000182
	 */
	@Test
	public void S4406000182() {
		String code = "S44-06000182";
		
		String method = "proxyService";
		
		Map<String,Object> params = new HashMap<String,Object>(); 
		params.put("DataObjectCode", "FS000288");
		params.put("start", 0);
		params.put("limit", 200);
		params.put("params", new String[]{"李强", "440603198505144211"});
		
		CommonRequest.request(code, method, params);
	}
	
	/**
	 * 佛山市公安部-重点人员信息表[FS000389]数据查询服务(001)  S44-06000277
	 */
	@Test
	public void S4406000277() {
		String code = "S44-06000277";
		
		String method = "proxyService";
		
		Map<String,Object> params = new HashMap<String,Object>(); 
		params.put("DataObjectCode", "FS000389");
		params.put("start", 0);
		params.put("limit", 200);
		params.put("params", new String[]{});
		
		CommonRequest.request(code, method, params);
	}
	
	
	/**
	 * 佛山市卡口信息[FS201203]数据查询服务(001)  S44-06000529
	 */
	@Test
	public void S4406000529() {
		String code = "S44-06000529";
		String method = "proxyService";
		Map<String,Object> params = new HashMap<String,Object>(); 
		params.put("DataObjectCode", "FS201203");
		params.put("start", 0);
		params.put("limit", 200);
		//params.put("params", new String[]{"440605650512"});
		params.put("params", new String[]{"440607500703", "", "", ""});
		
		CommonRequest.request(code, method, params);
	}
	
	/**
	 * 佛山市快递数据-顺丰快递[FS000430]数据查询服务(001)  S44-06000318
	 */
	@Test
	public void S4406000318() {
		String code = "S44-06000318";
		
		String method = "proxyService";
		
		Map<String,Object> params = new HashMap<String,Object>(); 
		params.put("DataObjectCode", "FS000430");
		params.put("start", 0);
		params.put("limit", 200);
		params.put("params", new String[]{});
		
		CommonRequest.request(code, method, params);
	}
	
	/**
	 * 佛山市南海分局-涉毒人员信息[FS000681]数据查询服务(001)  S44-06000422
	 */
	@Test
	public void S4406000422() {
		String code = "S44-06000422";
		
		String method = "proxyService";
		
		Map<String,Object> params = new HashMap<String,Object>(); 
		params.put("DataObjectCode", "FS000681");
		params.put("start", 0);
		params.put("limit", 200);
		params.put("params", new String[]{"440603198505144211","余荣行"});
		
		CommonRequest.request(code, method, params);
	}
	
	/**
	 * 佛山市省公安厅-吸毒人员[FS000333]数据查询服务(001)  S44-06000226
	 */
	@Test
	public void S4406000226() {
		String code = "S44-06000226";
		
		String method = "proxyService";
		
		Map<String,Object> params = new HashMap<String,Object>(); 
		params.put("DataObjectCode", "FS000333");
		params.put("start", 0);
		params.put("limit", 200);
		params.put("params", new String[]{});
		
		CommonRequest.request(code, method, params);
	}
	
	/**
	 * 佛山市省厅嫌疑人信息[FS000842]数据查询服务(001)  S44-06000454
	 */
	@Test
	public void S4406000454() {
		String code = "S44-06000454";
		
		String method = "proxyService";
		
		Map<String,Object> params = new HashMap<String,Object>(); 
		params.put("DataObjectCode", "FS000842");
		params.put("start", 0);
		params.put("limit", 200);
		params.put("params", new String[]{});
		
		CommonRequest.request(code, method, params);
	}
	
	/**
	 * 佛山市市警综办-本地重点人员管控信息[FS000350]数据查询服务(001)  S44-06000242
	 */
	@Test
	public void S4406000242() {
		String code = "S44-06000242";
		
		String method = "proxyService";
		
		Map<String,Object> params = new HashMap<String,Object>(); 
		params.put("DataObjectCode", "FS000350");
		params.put("start", 0);
		params.put("limit", 200);
		params.put("params", new String[]{});
		
		CommonRequest.request(code, method, params);
	}

	/**
	 * 佛山市市警综办-本地重点人员在控登记表[FS000337]数据查询服务(001)  S44-06000230
	 */
	@Test
	public void S4406000230() {
		String code = "S44-06000230";
		
		String method = "proxyService";
		
		Map<String,Object> params = new HashMap<String,Object>(); 
		params.put("DataObjectCode", "FS000337");
		params.put("start", 0);
		params.put("limit", 200);
		params.put("params", new String[]{});
		
		CommonRequest.request(code, method, params);
	}
	
	/**
	 * 佛山市市警综办-流入辖区重点人员预警信息[FS000482]数据查询服务(001)  S44-06000361
	 */
	@Test
	public void S4406000361() {
		String code = "S44-06000361";
		
		String method = "proxyService";
		
		Map<String,Object> params = new HashMap<String,Object>(); 
		params.put("DataObjectCode", "FS000482");
		params.put("start", 0);
		params.put("limit", 200);
		params.put("params", new String[]{});
		
		CommonRequest.request(code, method, params);
	}
	
	/**
	 * 佛山市市警综办-涉恐人员信息[FS000343]数据查询服务(001)  S44-06000236
	 */
	@Test
	public void S4406000236() {
		String code = "S44-06000236";
		
		String method = "proxyService";
		
		Map<String,Object> params = new HashMap<String,Object>(); 
		params.put("DataObjectCode", "FS000343");
		params.put("start", 0);
		params.put("limit", 200);
		params.put("params", new String[]{});
		
		CommonRequest.request(code, method, params);
	}
	
	/**
	 * 佛山市市警综办-维族人员居住信息[FS000339]数据查询服务(001)  S44-06000232
	 */
	@Test
	public void S4406000232() {
		String code = "S44-06000232";
		
		String method = "proxyService";
		
		Map<String,Object> params = new HashMap<String,Object>(); 
		params.put("DataObjectCode", "FS000339");
		params.put("start", 0);
		params.put("limit", 200);
		params.put("params", new String[]{});
		
		CommonRequest.request(code, method, params);
	}
	
	/**
	 * 佛山市市警综办-嫌疑人信息[FS000246]数据查询服务(001)  S44-06000148
	 */
	@Test
	public void S4406000148() {
		String code = "S44-06000148";
		
		String method = "proxyService";
		
		Map<String,Object> params = new HashMap<String,Object>(); 
		params.put("DataObjectCode", "FS000246");
		params.put("start", 0);
		params.put("limit", 200);
		params.put("params", new String[]{"440603198505144211"});
		
		CommonRequest.request(code, method, params);
	}
	
	/**
	 * 佛山市顺德分局-嫌疑人信息[FS000744]数据查询服务(001)  S44-06000437
	 */
	@Test
	public void S4406000437() {
		String code = "S44-06000437";
		
		String method = "proxyService";
		
		Map<String,Object> params = new HashMap<String,Object>(); 
		params.put("DataObjectCode", "FS000744");
		params.put("start", 0);
		params.put("limit", 200);
		params.put("params", new String[]{"李强", "2017-01-01", "2017-12-01"});
		
		CommonRequest.request(code, method, params);
	}
	
}
