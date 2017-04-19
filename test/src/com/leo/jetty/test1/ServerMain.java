package com.leo.jetty.test1;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class ServerMain {
	public static void main(String[] args) throws Exception {
		Server server = new Server(8090);
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/MyServer"); // 这里是请求的上下文，比如http://localhost:8090/MyServer
		server.setHandler(context);
		context.addServlet(new ServletHolder(new HelloWorld()), "/helloWorld"); // 添加servlet，第一是具体的servlet，后面是请求的别名，在http请求中的路径
		context.addServlet(new ServletHolder(new HelloWorld("chan")), "/HellworldWithParams");
		server.start();
		server.join();
	}
}