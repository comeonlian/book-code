package com.t35.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.t35.dao.BusinessMapper;
import com.t35.pojo.Business;
import com.t35.pojo.query.BusinessQuery;
import com.t35.pojo.query.Page;
import com.t35.service.BusinessService;

@Service("businessService")
public class BusinessServiceImpl implements BusinessService{
	private Logger logger = Logger.getLogger(BusinessServiceImpl.class);
	@Resource
	private BusinessMapper businessMapper;

	@Override
	public List<Business> getBusinessList(Integer userId) throws Exception {
		// TODO Auto-generated method stub
		return businessMapper.getBusinessList(userId);
	}

	@Override
	public Page queryAllBusiness(BusinessQuery businessQuery) {
		// TODO Auto-generated method stub
		System.out.println("11111111111111111111111"+businessQuery.getPageNo());
		
//		Page page=new Page();;
//		if(businessQuery.getPageNo()>page.getTotalPage()){
//			businessQuery.getPageNo()=page.getTotalPage();
//		}else if(businessQuery.getPageNo()<1){
//			
//		}
		businessQuery.setPageIndex((businessQuery.getPageNo()-1)*businessQuery.getPageSize());
		
		List businesses = businessMapper.queryAllBusiness(businessQuery);
		//List businesses = businessMapper.queryBusinessBy(businessQuery);
		//int totalCount = businessMapper.getBusinessList(businessQuery);
		Integer totalCount = businessMapper.getBusinessCountBy();
		System.out.println("111111111111111111111"+totalCount);
		System.out.println("111111111111111111111"+businessQuery.getPageSize());
		//对分页所需要的数据进行分装
		Page page = new Page();
		page.setTotalCount(totalCount);
		page.setList(businesses);
		page.setPageSize(businessQuery.getPageSize());
		page.setPageNo(businessQuery.getPageNo());
		
		//return page;
		return page;
	}

	//@Override
//	public List<Business> getBusinessExcelData(BusinessQuery businessQuery){
//		// TODO Auto-generated method stub
//		List<Business> businesses2 = businessMapper.getBusinessExcelData(businessQuery);
//		businessQuery.getClass();
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		businessQuery.getClass();
			//List<Map<String, Object>> businessMaps = new ArrayList<Map<String,Object>>();		
			//Map<String, Object> map = new HashMap<String, Object>();
//			map.put("a", business.getId());
//			map.put("b", business.getType());
//			map.put("c", business.getCreateTime());
//			map.put("f", business.getMoney());
//			map.put("e", business.getUserId());
//		if(null != businessQuery.getCreateTime()){
//			//map.put("交易记录时间:" + DateUtil.getDateStrByDate(businessQuery.getCreateTime()));
//			map.put(DateUtil.getDateStrByDate(businessQuery.getCreateTime()), "交易记录时间:");
//		}
//		
//		return businessMapper.getBusinessExcelData(businessQuery);
//	}

	@Override
	public int deleteBusinessByUserId(Integer userId) throws Exception{
		// TODO Auto-generated method stub
		//int billCount = -1;		
		//businessMapper.deleteBusinessByUserId(userId);		
		return businessMapper.deleteBusinessByUserId(userId);
	}

	@Override
	public List<Business> findAllExcle() {
		// TODO Auto-generated method stub
//		List<Business> business = businessMapper.findAllExcle();
//		if(null != ((Business) business).getCreateTime()){
//			business.add((Business) business);
//			//business.add(Restrictions.le("createTime", business.getCreateTime()));
//		}
		return businessMapper.findAllExcle();
	}

}
