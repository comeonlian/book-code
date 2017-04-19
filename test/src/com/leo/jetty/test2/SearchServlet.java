package com.leo.jetty.test2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *  
 * HelloServlet.java 
 * &#64;author kanpiaoxue<br> 
 * &#64;version 1.0 
 * Create Time 2014��9��2�� ����4:55:40<br> 
 * Description : HttpServlet
 * </pre>
 */
public class SearchServlet extends HttpServlet {
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchServlet.class);

	/**
	 * <pre>
	 * </pre>
	 */

	private static final long serialVersionUID = -4012838481920999524L;

	/**
	 * д������Ĵ��붼�� POST ����
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("query");

		LOGGER.info(String.format("receive query: %s", query));

		String result = "welcome to my server. It's a POST request.";
		if (null != query && !query.trim().equals("")) {
			result = query + ", " + result;
		}
		LOGGER.info(String.format("response is: %s", result));

		Tools.printToJson(result, response);
	}

	/**
	 * д������Ĵ��붼�� GET ����
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("query");

		LOGGER.info(String.format("receive query: %s", query));

		String result = "welcome to my server. It's a GET request.";
		if (null != query && !query.trim().equals("")) {
			result = query + ", " + result;
		}
		LOGGER.info(String.format("response is: %s", result));

		Tools.printToJson(result, response);
	}
}