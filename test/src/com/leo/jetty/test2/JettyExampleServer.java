package com.leo.jetty.test2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *  
 * JettyExampleServer.java 
 * &#64;author kanpiaoxue<br> 
 * &#64;version 1.0 
 * Create Time 2014��8��28�� ����4:05:25<br> 
 * Description : ����Jettyʵ�ּ򵥵�Ƕ��ʽHttpserver
 * </pre>
 */
public class JettyExampleServer {
	private static final Logger LOGGER = LoggerFactory.getLogger(JettyExampleServer.class);

	/**
	 * <pre>
	 *  
	 * &#64;param args
	 * </pre>
	 */
	public static void main(String[] args) {
		try {
			// ���з���������
			Server server = new Server(8087);
			LOGGER.info("server start.");
			ContextHandler context = new ContextHandler();
			// ����������URL��ַ
			context.setContextPath("/search");
			context.setResourceBase(".");
			context.setClassLoader(Thread.currentThread().getContextClassLoader());
			server.setHandler(context);
			context.setHandler(new HelloHandler());
			// ����������
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}


class HelloHandler extends AbstractHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(JettyExampleServer.class);

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		/**
		 * <pre>
		 *  
		 * �� URL ����õ����ݹ����Ĳ����� 
		 *  http://localhost:8080/search?query=hello 
		 * �������Ҫ���ݸ���Ĳ�����������ôд�� 
		 *  http://localhost:8080/search?query=hello&name=ZhangLili 
		 * �����￪ʼ�������д�Լ��Ĵ����߼��� 
		 *  
		 * [ע�⣺GET����������URL ����󳤶��� 1024���ֽ�]
		 * </pre>
		 */

		String query = request.getParameter("query");
		// String name = request.getParameter("name");

		LOGGER.info(String.format("receive query: %s", query));

		String result = "welcome to my server.";
		if (null != query && query.equals("hello")) {
			result = query + ", " + result;
		}
		LOGGER.info(String.format("response is: %s", result));

		// �������������Ľ�����ظ�����URL�Ŀͻ���
		print(baseRequest, response, result);
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
	private void print(Request baseRequest, HttpServletResponse response, String result) throws IOException {
		response.setContentType("text/json;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);
		response.getWriter().println(result);
	}
}