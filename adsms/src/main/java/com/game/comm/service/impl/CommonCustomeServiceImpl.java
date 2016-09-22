package com.game.comm.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.comm.entity.CommonCustom;
import com.game.comm.service.CommonCustomeService;
import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.services.ServiceException;
import com.game.util.CustomIdCache;

@Service("commonCustomeService")
public class CommonCustomeServiceImpl  extends GenericManagerImpl<CommonCustom, Long>  implements CommonCustomeService 
{
	private GenericDao<CommonCustom, Long> customDao;
	
	private static Logger logger = LoggerFactory.getLogger(ResolutionServiceImpl.class);
    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public CommonCustomeServiceImpl(SessionFactory sessionFactory) {
    	
        this.customDao = new GenericDaoHibernate<CommonCustom, Long>(CommonCustom.class, sessionFactory);
        this.dao = this.customDao;
    }
    
    
    public boolean delAll(List<Long> ids) {
		try {
			for (Long id : ids){
				this.dao.remove(id);
			}
			return true;
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}


	@Override
	public boolean checkcustomid(String customid) {
		String hql = " from CommonCustom where customid = ? ";
		CommonCustom commonCustom = customDao.findOne(hql, customid);
		if(commonCustom == null || "".equals(commonCustom.getCustomid())){
			return true;
		}
		return false;
	}
	@Override
	public int editdefaultcustom(long id)
	{
		try 
		{
			//update CommonCustom set isdefault=0
			customDao.batchExecute("update CommonCustom set isdefault=0 where isdefault=1 and id !=? ",id);
			return 1;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return -1;
		}
	}
	
	public void queryCustomid(){
		String hql = "from CommonCustom where isdefault=1";
		List<CommonCustom> commList = dao.find(hql, new String[]{});
		if(commList != null && commList.size() > 0){
			CustomIdCache.getInstance().setCUSTOMID(commList.get(0).getCustomid()) ;
		}
	}

	@Override
	public List<CommonCustom> queryAllCustom() {
		String hql = "from CommonCustom ";
		return customDao.find(hql, new String[]{});
	}
   
}
