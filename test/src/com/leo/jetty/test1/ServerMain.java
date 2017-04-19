package com.leo.jetty.test1;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class ServerMain {
	public static void main(String[] args) throws Exception {
		Server server = new Server(8090);
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/MyServer"); // ����������������ģ�����http://localhost:8090/MyServer
		server.setHandler(context);
		context.addServlet(new ServletHolder(new HelloWorld()), "/helloWorld"); // ���servlet����һ�Ǿ����servlet������������ı�������http�����е�·��
		context.addServlet(new ServletHolder(new HelloWorld("chan")), "/HellworldWithParams");
		server.start();
		server.join();
	}
}