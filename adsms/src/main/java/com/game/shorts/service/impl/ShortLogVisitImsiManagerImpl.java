package com.game.shorts.service.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.shorts.entity.ShortLogVisitImsi;
import com.game.shorts.service.ShortLogVisitImsiManager;

@Service("shortLogVisitImsiManager")
public class ShortLogVisitImsiManagerImpl extends GenericManagerImpl<ShortLogVisitImsi, Long> implements ShortLogVisitImsiManager {

    private GenericDao<ShortLogVisitImsi, Long> shortLogVisitImsiDao;

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public ShortLogVisitImsiManagerImpl(SessionFactory sessionFactory) {
        this.shortLogVisitImsiDao = new GenericDaoHibernate<ShortLogVisitImsi, Long>(ShortLogVisitImsi.class, sessionFactory);
        this.dao = this.shortLogVisitImsiDao;
    }

}
