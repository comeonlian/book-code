package com.game.docking.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActionResponseUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(ActionResponseUtils.class);

	public static void writer(Object ob, PrintWriter out) throws IOException {
		out.print(ob);
	}

	public static void wirteAndEnd(Object ob, String charset, HttpServletResponse response) {
		response.setContentType("text/html; charset=" + charset);
		PrintWriter out;
		try {
			out = response.getWriter();
			writer(ob, out);
			out.flush();
			out.close();
		} catch (IOException e) {
			LOGGER.error("Error while write:{}", e.getMessage());
		}
	}

	public static void wirteAndEnd(Object ob, HttpServletResponse response) {
		if (ob != null) {
			wirteAndEnd(ob, "UTF-8", response);
		} else {
			wirteAndEnd("{\"ret\":\"没有符合条件的数据!\"}", "UTF-8", response);
		}
	}

	/**
	 * 直接下载
	 */
	public static void writeAndDownload(String filename, String ob, HttpServletResponse response) {
		if (ob != null) {

			response.setContentType("application/x-msdownload");
			response.setContentType("application/wnd.wap.wmlc");
			// response.setHeader("Content-disposition","attachment; filename=\"silenceinstall.xml\"");
			response.setHeader("Content-disposition", "attachment; filename=\"" + filename + "\"");
			try {
				response.setContentLength(ob.getBytes("utf-8").length);

				PrintWriter out = response.getWriter();
				writer(ob, out);
				out.flush();
				out.close();
			} catch (UnsupportedEncodingException e) {
				LOGGER.error("Error while encoding2UTF8:{}", e.getMessage());
			} catch (IOException e) {
				LOGGER.error("Error while write:{}", e.getMessage());
			}
		} else {
			wirteAndEnd("{\"ret\":\"没有符合条件的数据!\"}", "UTF-8", response);
		}
	}

	public static void writer(Object ob, HttpServletResponse response) {
	    PrintWriter out = null;
	    try {
	        out = response.getWriter();
			writer(ob, out);
		} catch (IOException e) {
			LOGGER.error("Error while write:{}", e.getMessage());
		} finally{
		    if(out!=null){
		        out.close();
		    }
		}
	}
}
