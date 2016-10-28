package com.t35.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * @ClassName: ExcelObj
 * @Description: Excel自定义对象，可简化Excel表格的操作。 
 * @author mason_wan
 * @date 2012-4-26 下午06:26:07 
 */
public class ExcelObj {
	/** excel文件名 */
	private String fileName;
	/** 文件目录 */
	private String fileDir;
	/** excel标题 */
	private String title;
	/** 报表条件 */
	private String condition;
	/** excel表格表头 */
	private LinkedHashMap<String,String> header;
	/** excel表格列表数据 */
	private List<Map<String, Object>> data;
	/** Excel格式 */
	private ExcelFormat excelFormat = new ExcelFormat();
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		if(StringUtils.isBlank(fileName))
			fileName = "临时报表";
		fileName += new SimpleDateFormat("yyyyMMddhhmmssSS").format(new Date());
		this.fileName = fileName;
	}
	public String getFileDir() {
		return fileDir;
	}
	public void setFileDir(String fileDir) {
		this.fileDir = fileDir;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public LinkedHashMap<String, String> getHeader() {
		return header;
	}
	public void setHeader(LinkedHashMap<String, String> header) {
		this.header = header;
	}
	public List<Map<String, Object>> getData() {
		return data;
	}
	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}
	public ExcelFormat getExcelFormat() {
		return excelFormat;
	}
	public void setExcelFormat(ExcelFormat excelFormat) {
		this.excelFormat = excelFormat;
	}
	
	
}
