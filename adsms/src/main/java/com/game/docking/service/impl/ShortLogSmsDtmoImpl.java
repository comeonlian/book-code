package com.game.docking.service.impl;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.comm.entity.ShortLogSmsDtmo;
import com.game.docking.service.ShortLogSmsDtmoManager;
import com.game.modules.orm.GenericDao;
import com.game.modules.orm.Page;
import com.game.modules.orm.PropertyFilter;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;

@Service("moMnager")
public class ShortLogSmsDtmoImpl extends GenericManagerImpl<ShortLogSmsDtmo, Integer> implements
		ShortLogSmsDtmoManager {
	private GenericDao<ShortLogSmsDtmo, Integer> moDao;
	
	@Autowired
	public ShortLogSmsDtmoImpl(SessionFactory sessionFactory,DataSource dataSource) {
		this.moDao = new GenericDaoHibernate<ShortLogSmsDtmo,Integer>(ShortLogSmsDtmo.class,sessionFactory);
		this.dao = this.moDao;
	}
	
	@Override
	public Page<ShortLogSmsDtmo> searchPageSmsDtmo(Page<ShortLogSmsDtmo> page,
			List<PropertyFilter> filters) {
		return moDao.findPage(page, filters);
	}
}
