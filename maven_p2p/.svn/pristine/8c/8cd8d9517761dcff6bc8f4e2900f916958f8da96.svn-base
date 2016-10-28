package com.t35.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.t35.pojo.Investreport;
import com.t35.service.InvestreportService;
import com.t35.util.ExcelFormat;
import com.t35.util.ExcelObj;
import com.t35.util.ExcelUtils;

@Controller
@RequestMapping(value="invest")
public class InvestreportExcelController {
	
	@Resource
	private InvestreportService investreportService;
	
	@RequestMapping(value="/excel")
	public void downExcel(HttpServletRequest request,HttpServletResponse response) throws Exception  {
		ExcelObj excelObj = new ExcelObj();
		excelObj.setFileDir(ExcelUtils.getFileDir(request));
		
		//处理文件名的中文乱码问题
		String fileName;
					fileName = getFileName("123", request);
			excelObj.setFileName(fileName);

		
		
		excelObj.setTitle("投资信息数据报表");
		excelObj.setCondition(null);

		//头部的名称与数据中的名称要一致
		LinkedHashMap<String, String> header = new LinkedHashMap<String, String>();
		header.put("id", "编号");
		header.put("userId", "投资人编号");
		header.put("money", "投资金额");
		header.put("borrowId", "借款人编号");
		//header.put("createTime", "借款时间");

		excelObj.setHeader(header);
		excelObj.setData(getDataList());
		excelObj.setExcelFormat(new ExcelFormat());
		ExcelUtils.generateExcel(excelObj);

		ExcelUtils.download(excelObj.getFileDir() + excelObj.getFileName()
				+ ".xls", response);
	}
	
	public List<Map<String, Object>> getDataList(){
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		List<Investreport> investreportlist = investreportService.findAllExcle();
		for (Investreport investreport : investreportlist) {
			HashMap<String, Object> map  =new HashMap<String, Object>();
			map.put("id", investreport.getId());
			map.put("userId", investreport.getUserId());
			map.put("money", investreport.getMoney());
			map.put("borrowId", investreport.getBorrowId());
			//map.put("createTime", investreport.getCreateTime());
			mapList.add(map);
		}
		return mapList;
	}
	
	/**
	 * 根据浏览器的不同定义对应编码格式
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String getFileName(String fileName,HttpServletRequest request) throws UnsupportedEncodingException
	{
		if (fileName != null)
		{
			String Agent = request.getHeader("User-Agent");
			if (null != Agent)
			{
				Agent = Agent.toLowerCase();
				if (Agent.indexOf("firefox") != -1)
				{
					fileName = new String(fileName.getBytes(), "GBK");
				} else if (Agent.indexOf("msie") != -1)
				{
					fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
					fileName = StringUtils.replace(fileName, "+", "%20");
				} else
				{
					fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
					fileName = StringUtils.replace(fileName, "+", "%20");
				}
			}
			return fileName;
		}
		return "";
	}
}
