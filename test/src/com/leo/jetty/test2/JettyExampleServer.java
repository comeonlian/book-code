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
 * Create Time 2014年8月28日 下午4:05:25<br> 
 * Description : 利用Jetty实现简单的嵌入式Httpserver
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
			// 进行服务器配置
			Server server = new Server(8087);
			LOGGER.info("server start.");
			ContextHandler context = new ContextHandler();
			// 设置搜索的URL地址
			context.setContextPath("/search");
			context.setResourceBase(".");
			context.setClassLoader(Thread.currentThread().getContextClassLoader());
			server.setHandler(context);
			context.setHandler(new HelloHandler());
			// 启动服务器
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
		 * 从 URL 里面得到传递过来的参数： 
		 *  http://localhost:8080/search?query=hello 
		 * 如果你需要传递更多的参数，可以这么写： 
		 *  http://localhost:8080/search?query=hello&name=ZhangLili 
		 * 从这里开始，你可以写自己的代码逻辑。 
		 *  
		 * [注意：GET方法的请求，URL 的最大长度是 1024个字节]
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

		// 将服务器处理后的结果返回给调用URL的客户端
		print(baseRequest, response, result);
	}

	/**
	 * <pre>
	 *  
	 * &#64;param baseRequest 
	 * &#64;param response 
	 * &#64;param result 需要返回给客户的结果 
	 * &#64;throws IOException 
	 * 将结果 result 返回给客户
	 * </pre>
	 */
	private void print(Request baseRequest, HttpServletResponse response, String result) throws IOException {
		response.setContentType("text/json;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);
		response.getWriter().println(result);
	}
}