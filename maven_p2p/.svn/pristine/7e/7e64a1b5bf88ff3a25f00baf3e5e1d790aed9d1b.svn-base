package com.t35.controller;



import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.t35.pojo.Business;
import com.t35.pojo.query.BusinessQuery;
import com.t35.pojo.query.Page;
import com.t35.service.BusinessService;

@Controller
@RequestMapping(value="/record")
public class RecordController {	
	@Resource
	private BusinessService businessService;
	private Business business;	//接收交易记录主表数据
	private List<Business> businessList;	//接收交易记录数据
	
	
//	public String getConditionData(BusinessQuery businessQuery){
//		StringBuffer condition = new StringBuffer();
//		if(null != businessQuery.getCreateTime()){
//			condition.append("交易记录时间:" + DateUtil.getDateStrByDate(businessQuery.getCreateTime()));
//		}
//		return condition.toString();
//	}	
	//查询显示list分页查询
	@RequestMapping(value="/toRecord")
	//@RequestMapping(value="/toRecord")
	public String getBusinessList(Model model,Integer userId,BusinessQuery businessQuery){
		if(businessQuery == null){
			businessQuery = new BusinessQuery();
		}
		Page page = businessService.queryAllBusiness(businessQuery);
		//分页	
		model.addAttribute("page", page);
		return"record";
	}
	
	//删除信息
		@RequestMapping(value="/delete/{id}")
		public String delete(Model model,@PathVariable String id) throws Exception{
			//businessList.delete(userId);
			System.out.println("userId"+id);
			Integer userId = Integer.parseInt(id);
			 businessService.deleteBusinessByUserId(userId);

			 System.out.println("userId"+userId);
			//重定向
			return "redirect:/record/toRecord";
		}
}
