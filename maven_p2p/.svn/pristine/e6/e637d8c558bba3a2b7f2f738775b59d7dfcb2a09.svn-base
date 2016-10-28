package com.t35.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.t35.pojo.Borrow;
import com.t35.pojo.Investreport;
import com.t35.pojo.User;
import com.t35.pojo.query.BasePage;
import com.t35.pojo.query.Condition;
import com.t35.service.InvestreportService;

@Controller
@RequestMapping(value="/invest")
public class InvestController {
	
	
	@Resource
	public InvestreportService investreportService;

	@RequestMapping(value="/toInvest")
	public String toInvest(Model model,Borrow borrow,Condition condition,BasePage basePage){
		List<Investreport> investreport = null;
		List<Borrow> query = investreportService.queryPage();
		for (Borrow borrow2 : query) {
			borrow.setId(borrow2.getId());
			investreport = investreportService.getInvestreport(borrow);
		}
		Integer page = query.size();
		System.out.println(basePage.getPageNo()+"pageNa");
		basePage.setTotalCount(page);
		if (page % basePage.getPageSize() != 0) {
			basePage.setTotalPage(page / basePage.getPageSize() + 1);
		} else {
			basePage.setTotalPage(page / basePage.getPageSize());
		}
		List<Borrow> list = investreportService.queryAllBorrow(basePage);
		model.addAttribute("borrow", list);
		model.addAttribute("investreport", investreport);
		model.addAttribute("basePage", basePage);
		return"invest";
	}


	@RequestMapping(value="/{time}/time")
	public String time(Model model,Condition condition){
		List<Borrow> list = investreportService.query(condition);
		model.addAttribute("borrow", list);
		return"invest";
		
	}
	

	@RequestMapping(value="/{borrowType}/borrowType")
	public String borrowType(Model model,Condition condition){
		List<Borrow> list = investreportService.query(condition);
		model.addAttribute("borrow", list);
		return"invest";
	}
	
	@RequestMapping(value="/{interestRate}/interestRate")
	public String interestRate(Model model,Condition condition){
		List<Borrow> list = investreportService.query(condition);
		model.addAttribute("borrow", list);
		return"invest";
	}
	
	@RequestMapping(value="/{id}/toDetail")
	public String toDetail(Model model,Borrow borrow,HttpSession session){
		User user = investreportService.getAllUserInFo(borrow);
		borrow = investreportService.queryById(borrow);
		System.out.println(borrow.getId());
		session.setAttribute("loginUser", user);
		List<Investreport> investreport =investreportService.getInvestreport(borrow);
		String ok =user.getIdCardNum().substring(0,14)+"****";
		user.setIdCardNum(ok);
		model.addAttribute("user", user);
		model.addAttribute("borrow", borrow);
		model.addAttribute("investreport", investreport);
		return"detail";
	}
	
	@RequestMapping(value="/toInvestment")
	public String toInvestment(Model model,Borrow borrow,Investreport investreport,HttpSession session){
		System.out.println("进来投资");
		Date time =null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		try {
			time = df.parse(df.format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(borrow.getId());
		User user = (User)session.getAttribute("loginUser");
		investreport.setUserId(user.getId());
		investreport.setBorrowId(borrow.getId());
		investreport.setCreateTime(time);
//		investreport.setMoney(money);
		investreportService.saveInvestreport(investreport);
		return"index";
	}
	
//	@RequestMapping(value="/toDetail")
//	public String toDetail(){
//
//		return"detail";
//	}
}
