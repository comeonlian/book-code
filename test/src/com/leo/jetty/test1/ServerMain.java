package com.leo.jetty.test1;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class ServerMain {
	// localhost:8090/myserver/helloWorld
	public static void main(String[] args) throws Exception {
		Server server = new Server(8090);
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/myserver"); 
		server.setHandler(context);
		context.addServlet(new ServletHolder(new HelloWorld()), "/helloWorld"); 
		server.start();
		server.join();
	}
}