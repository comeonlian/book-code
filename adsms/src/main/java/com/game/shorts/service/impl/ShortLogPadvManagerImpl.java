package com.game.shorts.service.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.shorts.entity.ShortLogPadv;
import com.game.shorts.entity.ShortLogPadvIvr;
import com.game.shorts.service.ShortLogPadvManager;

@Service("shortLogPadvManager")
public class ShortLogPadvManagerImpl extends GenericManagerImpl<ShortLogPadv, Long> implements ShortLogPadvManager {

    private GenericDao<ShortLogPadv, Long> shortLogPadvDao;
    private GenericDao<ShortLogPadvIvr, Long> shortLogPadvIvrDao;

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public ShortLogPadvManagerImpl(SessionFactory sessionFactory) {
        this.shortLogPadvDao = new GenericDaoHibernate<ShortLogPadv, Long>(ShortLogPadv.class, sessionFactory);
        this.shortLogPadvIvrDao = new GenericDaoHibernate<ShortLogPadvIvr, Long>(ShortLogPadvIvr.class, sessionFactory);
        this.dao = this.shortLogPadvDao;
    }

    @Override
    public ShortLogPadvIvr saveLogPadvIvr(ShortLogPadvIvr log) {
        return shortLogPadvIvrDao.save(log);
    }

}
