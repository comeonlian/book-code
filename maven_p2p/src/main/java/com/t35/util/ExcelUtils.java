package com.t35.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.CellFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
 
/**
 * Excel工具类
 * @author mason_wan
 * @createtime Jan 7, 2012 4:37:05 PM
 */
public final class ExcelUtils {
	protected static final Log log = LogFactory.getLog(ExcelUtils.class);
	
	public static String getStr(String str){
		String ret = "";
		if(StringUtils.isEmpty(str)) 
			return ret;
		else
			return str;
	}
	
	public static void generateExcel(ExcelObj excelObj) {
		try {
			if (excelObj == null || excelObj.getHeader()==null ||excelObj.getHeader().isEmpty()) {
				log.warn("Excel数据为空!");
				return;
			}
			log.warn("生成的Excel目录："+excelObj.getFileDir()+" 文件名："+ excelObj.getFileName()+"");
			
			//获取excelObj的设置相关信息
			String fileDir = excelObj.getFileDir();//目录
			String fileName = excelObj.getFileName();//文件名
			String title = excelObj.getTitle();//标题
			
			//创建Excel文件
			File dir = new File(fileDir);
			if(dir.isDirectory() && !dir.exists())
				dir.mkdir();
			File file = new File(fileDir+fileName+".xls");
			
			//若文件存在，将文件备份(备份文件名规则：文件名_创建时间)
			if (file.exists()) {
//				String fileCreTime = getFileCreateDate(file);
//				String fileRname = fileName+"_back.xls";
//				if(!StringUtils.isBlank(fileCreTime))
//					fileRname = fileName+"_"+fileCreTime+".xls";
//				file.renameTo(new File(fileDir+fileRname));
				file.createNewFile();
			}
			
			//写入数据到Excel中
			WritableWorkbook wwb = Workbook.createWorkbook(file);
			WritableSheet ws = wwb.createSheet(title,0);
			// 设置Excel的表格头
			setExcTitle(ws, excelObj);
			//设置Excel的表格内容
			setExcelContent(ws, excelObj);
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 设置标题
	 * 
	 * @param ws 
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	private static void setExcTitle(WritableSheet ws, ExcelObj excelObj) throws RowsExceededException, WriteException {
		if(excelObj==null)
			return ;
		String title = excelObj.getTitle();//标题
		String condition = excelObj.getCondition();
		LinkedHashMap<String, String> header = excelObj.getHeader();//表格头
		if(header==null || header.isEmpty())
			return ;
		//设置标题
		Label titleLab = new Label(0, 0, title);
		titleLab.setCellFormat(excelObj.getExcelFormat().getTitleFormat());
		ws.addCell(titleLab);
		ws.mergeCells(0, 0, header.size()-1, 0);//合并单元格(起始列,起始行,终点列,终点行)
		//设置查询条件
		if(StringUtils.isBlank(condition))
			condition = "无";
		condition = "查询条件："+condition;
		Label conLab = new Label(0, 1, condition);
		conLab.setCellFormat(excelObj.getExcelFormat().getBodyFormat());
		ws.addCell(conLab);
		ws.mergeCells(0, 1, header.size()-1, 1);//合并单元格(起始列,起始行,终点列,终点行)
		//设置表格表头
		int i = 0;
		for (String tbHeader : header.keySet()) {
			String value = header.get(tbHeader);
			Label heaLab = new Label(i, 2, value);
			heaLab.setCellFormat(excelObj.getExcelFormat().getHeaderFormat());
			ws.setColumnView(i, value.length()+8);
			ws.addCell(heaLab);
			i++;
		}
	}

	/**
	 * 设置Excel的其它内容
	 * @param list
	 * @param proNames
	 * @param ws
	 */
	private static void setExcelContent(WritableSheet ws, ExcelObj excelObj) throws NoSuchMethodException,
			IllegalAccessException, InvocationTargetException, WriteException, RowsExceededException {
		if(excelObj==null)
			return ;
		LinkedHashMap<String, String> header = excelObj.getHeader();//表格头
		List<Map<String, Object>> data = excelObj.getData();//数据
//		Map<String, Object> otherData = excelObj.getOtherData();//其它数据
		
		//设置表体列表数据
		if(data==null || data.isEmpty() || header==null || header.isEmpty())
			return ;
		int rowNum=3;//行0是标题，行1是条件，行2是表格头
		for (Map<String, Object> map : data) {
			int colNum=0;
			for (String tbHeader : header.keySet()) {
				Object obj = map.get(tbHeader);
				String value = "";
				CellFormat celFor = null;
				if(obj!=null){
					value = String.valueOf(obj);
//					System.out.println(obj.getClass().getSimpleName()+"=="+value);
					if(obj instanceof BigInteger || obj instanceof Integer){
						celFor = excelObj.getExcelFormat().getIntFormat();
						int objInt = Integer.valueOf(value);
						Number labNum = new Number(colNum, rowNum, objInt, celFor);
						ws.addCell(labNum);
					}else if(obj instanceof BigDecimal || obj instanceof Double){
						celFor = excelObj.getExcelFormat().getFloatFormat();
						double objDou = Double.valueOf(value);
						Number labNum = new Number(colNum, rowNum, objDou, celFor);
						ws.addCell(labNum);
					}else if(obj instanceof Timestamp || obj instanceof Date){
						celFor = excelObj.getExcelFormat().getDateFormat();
						Date date = DateUtil.getDateTimeByStr(value);
						DateTime labDate = new DateTime(colNum, rowNum, date, celFor);
						ws.addCell(labDate);
					}else{
						celFor = excelObj.getExcelFormat().getBodyFormat();
						Label bodyLab = new Label(colNum, rowNum, value);
						bodyLab.setCellFormat(celFor);
						ws.addCell(bodyLab);
					}
				}else{
					celFor = excelObj.getExcelFormat().getBodyFormat();
					Label bodyLab = new Label(colNum, rowNum, value);
					bodyLab.setCellFormat(celFor);
					ws.addCell(bodyLab);
				}
				ws.setColumnView(colNum, value.length()+8);
				colNum++;
			}
			rowNum++;
		}
		
//		//设置表体其它数据
//		if(otherData==null || otherData.isEmpty() || otherData==null || otherData.isEmpty())
//			return ;
//		int colTotal = header.size();
//		for (String key : otherData.keySet()) {
//			Object obj = otherData.get(key);
//			String value = String.valueOf(obj);
//			String cellData = key+"："+value;
//			ws.addCell(new Label(colTotal,rowNum,cellData));
//		}
	}
	
	
	public static WritableCellFormat getCellFormatFont(){
		WritableFont font = new WritableFont(WritableFont.TIMES,14,WritableFont.BOLD);
		WritableCellFormat format = new WritableCellFormat(font);
		try {
			format.setAlignment(jxl.format.Alignment.CENTRE);
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return format;
	}
	
	public static WritableCellFormat getCellFormatTitle(){
		WritableFont font = new WritableFont(WritableFont.TIMES,8,WritableFont.BOLD);
		WritableCellFormat format = new WritableCellFormat(font);
		try {
			format.setAlignment(jxl.format.Alignment.CENTRE);
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return format;
	}

	public static String getFileDir(HttpServletRequest request){
		String fileDir=request.getServletContext().getRealPath("/upload/report")+ "/";
		File dirPath = new File(fileDir);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}		
		return  fileDir;
	}
	
	/**
	 * 普通的下载方式
	 * @param path
	 * @param response
	 */
	public static void download(String path, HttpServletResponse response) {
		try {
			// path是指欲下载的文件的路径。
			File file = new File(path);
			if(!file.exists()){
				System.out.println("文件不存在！！");
				return ;
			}
			// 取得文件名。
			String filename = file.getName();
			FileInputStream inSr = new FileInputStream(file);
			response.reset();
			response.setContentType("application/octet-stream;charset=gb2312");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String(filename.getBytes("gb2312"),"iso8859-1")+"\"");
			ServletOutputStream outSr = response.getOutputStream();
			if (inSr != null) {
				int filelen = inSr.available();
				// 文件太大时内存不能一次读出,要循环
				byte a[] = new byte[filelen];
				inSr.read(a);
				outSr.write(a);
			}
			inSr.close();
			outSr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}     
     
     /**
      * 获取文件创建时间
      * @param file file 要获取创建时间的文件对象
      * @return datetime datetime 创建时间
      */
     public static String getFileCreateDate(File file) {
         try {
             Process ls_proc = Runtime.getRuntime().exec("cmd.exe /c dir " + file.getAbsolutePath() + " /tc");
             BufferedReader br = new BufferedReader(new InputStreamReader(ls_proc.getInputStream()));
             for (int i = 0; i < 5; i++) {
                 br.readLine();
             }
             String stuff = br.readLine();
             StringTokenizer st = new StringTokenizer(stuff);
             String dateC = st.nextToken();
             String time = st.nextToken();
             String datetime = dateC.concat(time);
             br.close();
             return datetime;
         } catch (Exception e) {
             return null;
         }
     }



     
    public static void main(String[] args) {
		LinkedHashMap<String, String> header = new LinkedHashMap<String, String>();
		header.put("billid", "账单编号");
		header.put("billdate", "账单日期");
		header.put("corpname", "企业名称");
		header.put("orgname", "机构名称");
		header.put("billtype", "交易类型");
		header.put("outamt", "支出");
		header.put("inamt", "收入");
		for (String key : header.keySet()) {
			System.out.println(key);
		}
	}

}
