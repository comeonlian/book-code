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

	private String msg = "hello world, heheda";

	public HelloWorld() {
	}

	public HelloWorld(String msg) {
		this.msg = msg;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println("param: username="+username+" ,password="+password);
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=utf-8");
		resp.setStatus(HttpServletResponse.SC_OK);
		String jsonStr = "{\"username\":\""+username+"\",\"password\":\""+password+"\"}";
		PrintWriter out = null;
		try {
			out = resp.getWriter();
			out.write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
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
		pWriter.println("����������Ϣ:" + req.getSession(true).getId());
		pWriter.println("<h3>�û���Ϣ:" + userName + "</h3>");
		pWriter.println("<h3>�û�����:" + password + "</h3>");
	}
}