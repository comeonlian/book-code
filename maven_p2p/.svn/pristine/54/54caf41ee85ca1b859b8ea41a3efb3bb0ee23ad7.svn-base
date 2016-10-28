package com.t35.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.t35.pojo.Borrow;
import com.t35.pojo.Business;
import com.t35.pojo.User;
import com.t35.pojo.query.BusinessQuery;
import com.t35.pojo.query.Page;


public interface BusinessMapper {
	//列表查询
	public List<Business> getBusinessList(@Param("userId")Integer userId)throws Exception;		
	//分页查询
	public List<Business> queryAllBusiness(BusinessQuery businessQuery);
	
	/**
	 * 分页的总记录数
	 * @param 
	 * @return
	 */
	public int getBusinessCountBy();
	
	//获得excel报表数据
	//public List<Business> getBusinessExcelData(BusinessQuery businessQuery);
	
	//删除
	public int deleteBusinessByUserId(@Param("userId")int userId)throws Exception;
	public List<Business> findAllExcle();
	
}
