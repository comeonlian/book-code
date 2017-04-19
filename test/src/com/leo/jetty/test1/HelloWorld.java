package com.leo.jetty.test1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorld extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2271797150647771294L;

	private String msg = "hello world~~~~~~";

	public HelloWorld() {
	}

	public HelloWorld(String msg) {
		this.msg = msg;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		resp.setStatus(HttpServletResponse.SC_OK);
		PrintWriter pWriter = resp.getWriter();
		pWriter.println("<h1>" + msg + "</h1>");
		pWriter.println("测试中文信息:" + req.getSession(true).getId());
		pWriter.println("<h3>用户信息:" + userName + "</h3>");
		pWriter.println("<h3>用户密码:" + password + "</h3>");
	}
}