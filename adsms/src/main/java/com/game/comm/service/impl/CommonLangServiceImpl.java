package com.game.comm.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.comm.entity.CommonLang;
import com.game.comm.service.CommonLangService;
import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.services.ServiceException;

@Service("commonLangService")
public class CommonLangServiceImpl  extends GenericManagerImpl<CommonLang, Long>  implements CommonLangService
{
	private GenericDao<CommonLang, Long> commonLangDao;

	private static Logger logger = LoggerFactory.getLogger(CommonLangServiceImpl.class);
    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public CommonLangServiceImpl(SessionFactory sessionFactory) {
        this.commonLangDao = new GenericDaoHibernate<CommonLang, Long>(CommonLang.class, sessionFactory);
        this.dao = this.commonLangDao;
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
	public List<CommonLang> queryAllLang() {
		String hql  = " from CommonLang ";
			return dao.find(hql, new Object[]{});
	}
}
