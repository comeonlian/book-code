package com.game.bmanager.service.impl;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.bmanager.entity.Customer;
import com.game.bmanager.entity.CustomerUrl;
import com.game.bmanager.service.CustomerUrlManager;
import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;

@Service("urlManager")
public class CustomerUrlManagerImpl extends GenericManagerImpl<CustomerUrl, Long> implements CustomerUrlManager{
	private GenericDao<CustomerUrl, Long> customerDao;
    private static Logger logger = LoggerFactory.getLogger(CustomerManagerImpl.class);

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public CustomerUrlManagerImpl(SessionFactory sessionFactory) {
        this.customerDao = new GenericDaoHibernate<CustomerUrl, Long>(CustomerUrl.class, sessionFactory);
        this.dao = this.customerDao;
    }
    
    @Override
    public String queryUrl(String customid) {
    	StringBuffer sb = new StringBuffer();
    	sb.append(" from  CustomerUrl where customid='").append(customid).append("' ");
    	CustomerUrl url =  dao.findOne(sb.toString());
    	//String res= url.getUrl();
    	//System.out.println(res);
    	if(null == url || url.getStatus()==0)
    		return null;
    	return url.getUrl();
    }
    
    
}
