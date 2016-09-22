package com.game.docking.service.impl;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.comm.entity.ShortLogSmsDtmr;
import com.game.docking.service.ShortLogSmsDtmrManager;
import com.game.modules.orm.GenericDao;
import com.game.modules.orm.Page;
import com.game.modules.orm.PropertyFilter;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;

@Service("mrManger")
public class ShortLogSmsDtmrImpl extends GenericManagerImpl<ShortLogSmsDtmr, Integer> implements
		ShortLogSmsDtmrManager {
	private GenericDao<ShortLogSmsDtmr, Integer> mrDao;
	
	@Autowired
	public ShortLogSmsDtmrImpl(SessionFactory sessionFactory,DataSource dataSource) {
		this.mrDao = new GenericDaoHibernate<ShortLogSmsDtmr,Integer>(ShortLogSmsDtmr.class,sessionFactory);
		this.dao = this.mrDao;
	}

	@Override
	public Page<ShortLogSmsDtmr> searchPageSmsDtmr(Page<ShortLogSmsDtmr> page,
			List<PropertyFilter> filters) {
		return mrDao.findPage(page, filters);
	}
	
	
}
