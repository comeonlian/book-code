package com.t35.controller;

import java.net.HttpCookie;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.t35.util.JsonMessge;
import com.t35.util.Mail;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@RequestMapping(value="/index")
	public String toIndex(){
		return"index";
	}
	
	@RequestMapping(value="/login")
	public String toLogin(){
		return"login";
	}
	
	@RequestMapping(value="/toMyUser")
	public String toMyUser(){
		return"myUser";
	}
	
	@RequestMapping(value="/doLogin")
	public String doLogin(){
		return"welcome";
	}
	
	@RequestMapping(value="/register")
	public String toRegister(){
		return"register";
	}
	
	@ResponseBody
	@RequestMapping(value="/sendEmail")
	public JsonMessge doSendEmail(@RequestParam(value="email")String email,HttpSession session){
		Integer random = 0;
		while(random.toString().length()!=6){
			random = (int)(Math.random()*1000000);
		}
		Mail.sendTxtMail(random.toString(), email);
		session.setAttribute("checkCode",random);
		session.setMaxInactiveInterval(300);
		return new JsonMessge("发送成功");
	}
	
	@RequestMapping(value="/doRegister")
	public String doRegister(){
		return"index";
	}
	
}
