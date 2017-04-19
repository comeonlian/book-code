package com.leo.jetty.test2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;

/**
 * <pre>
 *  
 * Tools.java 
 * &#64;author kanpiaoxue<br> 
 * &#64;version 1.0 
 * Create Time 2014��9��2�� ����4:57:06<br> 
 * Description : ������
 * </pre>
 */
public class Tools {

	private Tools() {
		super();
	}

	/**
	 * <pre>
	 *  
	 * &#64;param baseRequest 
	 * &#64;param response 
	 * &#64;param result ��Ҫ���ظ��ͻ��Ľ�� 
	 * &#64;throws IOException 
	 * ����� result ���ظ��ͻ�
	 * </pre>
	 */
	public static void print(Request baseRequest, HttpServletResponse response, String result) throws IOException {
		response.setContentType("text/json;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);
		response.getWriter().println(result);
	}

	/**
	 * ֱ����� json �ַ���
	 * 
	 * @param json
	 */
	public static void printToJson(String json, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/json");
			response.setDateHeader("Expires", 0);
			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}