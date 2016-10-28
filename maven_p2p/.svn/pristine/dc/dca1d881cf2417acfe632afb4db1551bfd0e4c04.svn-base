package com.t35.util;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.DateFormat;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;

/**
 * @author mason_wan
 * @description 用户格式化Excel的初始化格式，可继承后重写initExcelFormat方法，设置Excel的初始化格式
 */
public class ExcelFormat {
	/** 标题字体 */
	WritableFont titleFont = null;
	/** 表头字体 */
	WritableFont headerFont = null;
	/** 表体字体 */
	WritableFont bodyFont = null;
	
	/** 标题格式 */
	private WritableCellFormat titleFormat = null;
	/** 表头格式 */
	private WritableCellFormat headerFormat = null;
	/** 表格数据格式 */
	private WritableCellFormat bodyFormat = null;
	/** 浮点数格式 */
	private WritableCellFormat floatFormat = null;
	/** 整数格式 */
	private WritableCellFormat intFormat = null;
	/** 日期格式 */
	private WritableCellFormat dateFormat = null;
	
	/** 实例化时，初始化Excel格式 */
	public ExcelFormat(){
//		initExcelFormat();
	}

	/**
	 * 初始化Excel的格式(标题、表头、表体，以及各种数据格式的单元格格式)
	 * @throws Exception
	 */
//	public void initExcelFormat() {
//		try {
//			//标题字体：Arial字体，18号，粗体
//			titleFont = new WritableFont(WritableFont.ARIAL, 18, WritableFont.BOLD, false);
//			//表头字体：Arial字体，12号，粗体
//			headerFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false);
//			//表体字体：Arial字体，8号
//			bodyFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false);
//			
//			//设置标题格式（默认：田字边框、居中对齐）
//			titleFormat = new WritableCellFormat(titleFont);
//			titleFormat.setBorder(Border.ALL, BorderLineStyle.THIN);//田字边框
//			titleFormat.setAlignment(Alignment.CENTRE);//居中对齐
//			
//			//设置表头格式（默认：灰色背景、田字边框、居中对齐）
//			headerFormat = new WritableCellFormat(headerFont);
//			headerFormat.setBackground(Colour.GRAY_25);//背景
//			headerFormat.setBorder(Border.ALL, BorderLineStyle.THIN);//田字边框
//			headerFormat.setAlignment(Alignment.CENTRE);//居中对齐
//			
//			//设置表体格式（默认：田字边框、居中对齐）
//			bodyFormat = new WritableCellFormat(bodyFont);
//			bodyFormat.setBorder(Border.ALL, BorderLineStyle.THIN);//田字边框
//			bodyFormat.setAlignment(Alignment.LEFT);//左对齐
//			
//			//设置浮点数单元格格式（默认：田字边框、居中对齐）
////			NumberFormat floatFor = new NumberFormat("#.##");
//			floatFormat = new WritableCellFormat(bodyFont, NumberFormats.FLOAT);
//			floatFormat.setBorder(Border.ALL, BorderLineStyle.THIN);//田字边框
//			floatFormat.setAlignment(Alignment.LEFT);//左对齐
//			//设置整数单元格格式（默认：田字边框、居中对齐）
//			intFormat = new WritableCellFormat(bodyFont, NumberFormats.INTEGER);
//			intFormat.setBorder(Border.ALL, BorderLineStyle.THIN);//田字边框
//			intFormat.setAlignment(Alignment.LEFT);//左对齐
//			//设置日期单元格格式（默认：田字边框、居中对齐、日期显示yyyy-MM-dd hh:mm:ss）
//			DateFormat dateFor = new DateFormat("yyyy-MM-dd hh:mm:ss");
//			dateFormat = new WritableCellFormat(bodyFont, dateFor);
//			dateFormat.setBorder(Border.ALL, BorderLineStyle.THIN);//田字边框
//			dateFormat.setAlignment(Alignment.LEFT);//左对齐
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	

	/** 标题字体（默认：Arial字体，18号，粗体） */
	public WritableFont getTitleFont() {
		if(titleFont==null)
			titleFont = new WritableFont(WritableFont.ARIAL, 18, WritableFont.BOLD, false);
		return titleFont;
	}

	/** 标题字体（默认：Arial字体，18号，粗体） */
	public void setTitleFont(WritableFont titleFont) {
		this.titleFont = titleFont;
	}
	
	/** 表头字体（默认：Arial字体，12号，粗体） */
	public WritableFont getHeaderFont() {
		if(headerFont==null)
			headerFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false);
		return headerFont;
	}

	/** 表头字体（默认：Arial字体，12号，粗体） */
	public void setHeaderFont(WritableFont headerFont) {
		this.headerFont = headerFont;
	}

	/** 表体字体（默认：Arial字体，8号） */
	public WritableFont getBodyFont() {
		if(bodyFont==null)
			bodyFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false);
		return bodyFont;
	}

	/** 表体字体（默认：Arial字体，8号） */
	public void setBodyFont(WritableFont bodyFont) {
		this.bodyFont = bodyFont;
	}

	/** 标题格式（默认：田字边框、居中对齐） */
	public WritableCellFormat getTitleFormat() {
		if(titleFormat==null){
			try {
				titleFormat = new WritableCellFormat(this.getTitleFont());
				titleFormat.setBorder(Border.ALL, BorderLineStyle.THIN);//田字边框
				titleFormat.setAlignment(Alignment.CENTRE);//居中对齐
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}
		return titleFormat;
	}

	/** 标题格式（默认：田字边框、居中对齐） */
	public void setTitleFormat(WritableCellFormat titleFormat) {
		this.titleFormat = titleFormat;
	}
	
	/** 表头格式（默认：灰色背景、田字边框、居中对齐） */
	public WritableCellFormat getHeaderFormat() {
		if(headerFormat==null){
			try {
				headerFormat = new WritableCellFormat(this.getHeaderFont());
				headerFormat.setBackground(Colour.GRAY_25);//背景
				headerFormat.setBorder(Border.ALL, BorderLineStyle.THIN);//田字边框
				headerFormat.setAlignment(Alignment.CENTRE);//居中对齐
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return headerFormat;
	}

	/** 表头格式（默认：灰色背景、田字边框、居中对齐） */
	public void setHeaderFormat(WritableCellFormat headerFormat) {
		this.headerFormat = headerFormat;
	}

	/** 表体格式（默认：田字边框、居中对齐） */
	public WritableCellFormat getBodyFormat() {
		if(bodyFormat==null){
			try {
				bodyFormat = new WritableCellFormat(this.getBodyFont());
				bodyFormat.setBorder(Border.ALL, BorderLineStyle.THIN);//田字边框
				bodyFormat.setAlignment(Alignment.LEFT);//左对齐
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bodyFormat;
	}

	/** 表体格式（默认：田字边框、居中对齐） */
	public void setBodyFormat(WritableCellFormat bodyFormat) {
		this.bodyFormat = bodyFormat;
	}

	/** 浮点数单元格格式（默认：田字边框、居中对齐） */
	public WritableCellFormat getFloatFormat() {
		if(floatFormat==null){
			try {
//				NumberFormat floatFor = new NumberFormat("#.##");
				floatFormat = new WritableCellFormat(this.getBodyFont(), NumberFormats.FLOAT);
				floatFormat.setBorder(Border.ALL, BorderLineStyle.THIN);//田字边框
				floatFormat.setAlignment(Alignment.LEFT);//左对齐
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return floatFormat;
	}

	/** 浮点数单元格格式（默认：田字边框、居中对齐） */
	public void setFloatFormat(WritableCellFormat floatFormat) {
		this.floatFormat = floatFormat;
	}

	/** 整数单元格格式（默认：田字边框、居中对齐） */
	public WritableCellFormat getIntFormat() {
		if(intFormat==null){
			try {
				intFormat = new WritableCellFormat(this.getBodyFont(), NumberFormats.INTEGER);
				intFormat.setBorder(Border.ALL, BorderLineStyle.THIN);//田字边框
				intFormat.setAlignment(Alignment.LEFT);//左对齐
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return intFormat;
	}

	/** 整数单元格格式（默认：田字边框、居中对齐） */
	public void setIntFormat(WritableCellFormat intFormat) {
		this.intFormat = intFormat;
	}

	/** 日期单元格格式（默认：田字边框、居中对齐、日期显示yyyy-MM-dd hh:mm:ss） */
	public WritableCellFormat getDateFormat() {
		if(dateFormat==null){
			try {
				DateFormat dateFor = new DateFormat("yyyy-MM-dd hh:mm:ss");
				dateFormat = new WritableCellFormat(this.getBodyFont(), dateFor);
				dateFormat.setBorder(Border.ALL, BorderLineStyle.THIN);//田字边框
				dateFormat.setAlignment(Alignment.LEFT);//左对齐
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dateFormat;
	}

	/** 日期单元格格式（默认：田字边框、居中对齐、日期显示yyyy-MM-dd hh:mm:ss） */
	public void setDateFormat(WritableCellFormat dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	
	
}
