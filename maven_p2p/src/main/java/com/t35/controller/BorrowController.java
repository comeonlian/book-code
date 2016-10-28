package com.t35.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.t35.pojo.User;
import com.t35.service.InvestreportService;

@Controller
@RequestMapping(value="/borrow")
public class BorrowController {
	
	@Resource
	public InvestreportService investreportService;
	
	@RequestMapping(value="/toBorrow")
	public String toBorrow(HttpSession session,Model model){
		if(session.getAttribute("loginUser")!=null){
			User user = (User)session.getAttribute("loginUser");
			user = investreportService.getUser(user);
			model.addAttribute("user", user);
			return"borrow";
		}
		return"index";
	}
	
	@RequestMapping(value="/doBorrow")
	public String doBorrow(){
//		User user = (User)session.getAttribute("loginUser");
//		user = investreportService.getUser(user);
//		System.out.println(user.getCreditsMoney()+"在这");
//		model.addAttribute("user", user);
		return"welcome";
	}
}
