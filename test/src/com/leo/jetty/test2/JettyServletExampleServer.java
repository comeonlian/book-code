package com.leo.jetty.test2;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *  
 * JettyServletExampleServer.java 
 * &#64;author kanpiaoxue<br> 
 * &#64;version 1.0 
 * Create Time 2014��9��2�� ����4:53:49<br> 
 * Description : ����Jettyʵ�ּ򵥵�Ƕ��ʽHttpserver������HttpServlet���б��
 * </pre>
 */
public class JettyServletExampleServer {
	private static final Logger LOGGER = LoggerFactory.getLogger(JettyServletExampleServer.class);

	/**
	 * <pre>
	 *  
	 * &#64;param args
	 * </pre>
	 */
	public static void main(String[] args) {
		try {
			Server server = new Server(8086);

			ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
			context.setContextPath("/");
			server.setHandler(context);

			context.addServlet(new ServletHolder(new SearchServlet()), "/search");
			LOGGER.info("server start.");
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}