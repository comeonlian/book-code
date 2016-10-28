package com.t35.service;

import java.util.List;


import com.t35.pojo.Borrow;
import com.t35.pojo.Business;
import com.t35.pojo.query.BusinessQuery;
import com.t35.pojo.query.Page;

public interface BusinessService {
	//查询列表
	public List<Business> getBusinessList(Integer userId) throws Exception;
	//分页查询
	public Page queryAllBusiness(BusinessQuery businessQuery);
	//获得excel报表数据
	//public List<Business> getBusinessExcelData(BusinessQuery businessQuery);
	//删除交易记录
	public int deleteBusinessByUserId(Integer userId) throws Exception;
	public List<Business> findAllExcle();
	
}
