package com.game.docking.service.impl;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.comm.entity.ShortLogSmsKy;
import com.game.docking.service.ShortLogSmsKyManager;
import com.game.modules.orm.GenericDao;
import com.game.modules.orm.Page;
import com.game.modules.orm.PropertyFilter;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;

@Service("kyManger")
public class ShortLogSmsKyManagerImpl extends GenericManagerImpl<ShortLogSmsKy, Integer>
		implements ShortLogSmsKyManager {
	private GenericDao<ShortLogSmsKy, Integer> mrDao;
	
	@Autowired
	public ShortLogSmsKyManagerImpl(SessionFactory sessionFactory,DataSource dataSource) {
		this.mrDao = new GenericDaoHibernate<ShortLogSmsKy,Integer>(ShortLogSmsKy.class,sessionFactory);
		this.dao = this.mrDao;
	}
	
	@Override
	public Page<ShortLogSmsKy> searchPageSmsKy(Page<ShortLogSmsKy> page,
			List<PropertyFilter> filters) {
		return mrDao.findPage(page, filters);
	}
}
