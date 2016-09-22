package com.game.comm.service.impl;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.comm.entity.Resolution;
import com.game.comm.service.ResolutionService;
import com.game.modules.orm.GenericDao;
import com.game.modules.orm.Page;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.services.ServiceException;

@Service("resolutionService")
public class ResolutionServiceImpl extends GenericManagerImpl<Resolution, Long> implements ResolutionService{
	private GenericDao<Resolution, Long> adApkDao;
    private static Logger logger = LoggerFactory.getLogger(ResolutionServiceImpl.class);
    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public ResolutionServiceImpl(SessionFactory sessionFactory) {
        this.adApkDao = new GenericDaoHibernate<Resolution, Long>(Resolution.class, sessionFactory);
        this.dao = this.adApkDao;
    }
    public boolean delAll(List<Long> ids) {
		try {
			for (Long id : ids) {
				this.dao.remove(id);
			}
			return true;
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////Resolution
//根据条件 分页展示所有分辨率数据
	public List<Resolution> GetResolutionList(String isshow, int pageno,Page<Resolution> page) {

		String sql = " from Resolution where 1=1 ";		
		if(StringUtils.isBlank(isshow))
		{
			sql=sql+"  and isshow=? ";	
		}		
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder("asc");
		}
		page.setPageNo(pageno);

		if (StringUtils.isBlank(isshow)) {
			@SuppressWarnings("unchecked")
			Page <Resolution> list =this.adApkDao.findPage(page,sql,isshow);	
			return list.getResult();
		}
		return null;
	}
	
	//根据状态获得分辨率
	public List<Resolution> GetResolutionList(String isshow) 
	{
		String sql = " from Resolution where 1=1 ";		
		if(StringUtils.isBlank(isshow))
		{
			sql=sql+"  and isshow='"+isshow+"' ";	
		}	
		List <Resolution> list = this.adApkDao.find(sql);	
		return list;
	}
	
	
	//根据ID 得到具体的分辨率数据
	public List<Resolution> GetResolution(String uiid) {
		String sql = " from Resolution where 1=1  ";	
		if(uiid!=null&&uiid.length()>0)
		{
			sql=sql+" and id=? ";	
		}		
		else
		{return null;}
		List <Resolution> list = this.adApkDao.find(sql+" order by id ",uiid);	
		return list;
	}
	//判断分辨率是否重复
	public List<Resolution> GetResolutionByResol(String resolution,String Uid) {
		String sql = " from Resolution  where 1=1 ";		
		if(resolution!=null&&resolution.length()>0)
		{
			sql=sql+" and resolution=? ";	
		}		
		else 
		{return null;}
		if(Uid!=null&&Uid.length()>0)
		{
			sql=sql+" and id!='"+Uid+"' ";	
		}			
		List <Resolution> list = this.adApkDao.find(sql+" order by id ",resolution);	
		return list;
	}
	//添加分辨率
	public long addResolution(Resolution Ksresolution,String username) {
		try
		{				
			dao.save(Ksresolution);		
			return Ksresolution.getId();
		}
		catch(Exception ee)
		{  
			return 0;
		}
	}
	//修改分辨率
	public long updateResolution(Resolution Ksresolution,String username) {
		try
		{			
			dao.save(Ksresolution);	
			return 1;
		}
		catch(Exception ee)
		{
			//System.out.print(ee.toString());
			return 0;
		}
	}
	//删除分辨率
	public int deleteResolution(String uiid,String username) {
		try
		{			
			if(uiid!=null&&uiid.trim().length()>0)
			{
				dao.remove(Long.parseLong(uiid));
				return 1;
				/*Resolution r=dao.get(Long.parseLong(uiid));				
				String sql = " from Resolution where id=? ";	
				Query query = em.createQuery(sql);			
				long m_iid=Long.parseLong(uiid);
				query.setParameter(1,m_iid);			
				@SuppressWarnings("unchecked")
				List <Resolution> list = query.getResultList();		
				if(list.size()>0)
				{
					em.remove(list.get(0));						
					return 1;
				}*/
			}	
			return 0;
		}
		catch(Exception ee)
		{return 0;}
	}
	

	//根据传入的分辨率获得最接近的数据库分辨率 如果没有匹配的数据 默认返回480x800
	@Override
	public String getTheClosedResolution(String fromresolution)
	{
		String[] re=fromresolution.split("X");
		long width=400,height=800;
		if(re.length==2)
		{
			long mid=0;
			width=Long.parseLong(re[0]);
			height=Long.parseLong(re[1]);
			if(width>height)
			{
				mid=width;
				width=height;
				height=mid;
			}				
		}
		String newres=String.valueOf(width)+"X"+String.valueOf(height);
		List<Resolution> querylist=GetResolutionList("1");
		for(int i=0;i<querylist.size();i++)
		{
			if(querylist.get(i).getResolution().trim().equals(newres))//此分辨率在数据库中存在 直接返回该分辨率
			{
				return newres;
			}
		}
		//此分辨率在数据库中没有 找出最接近的大于此分辨率的数据
		for(int j=0;j<querylist.size();j++)
		{
			if(querylist.get(j).getWidth()>=width)//此分辨率的高度大于等于传入的高度 在返回此数据
			{
				return querylist.get(j).getResolution().trim();
			}
		}
		//最后所有的条件都不符合 返回默认数据
		return "480X800";
	}

	
	

}
