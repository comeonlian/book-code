package com.game.shorts.service.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.shorts.entity.ShortLogAccess;
import com.game.shorts.service.ShortLogAccessManager;

@Service("shortLogAccessManager")
public class ShortLogAccessManagerImpl extends GenericManagerImpl<ShortLogAccess, Long> implements ShortLogAccessManager {

    private GenericDao<ShortLogAccess, Long> shortLogAccessDao;

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public ShortLogAccessManagerImpl(SessionFactory sessionFactory) {
        this.shortLogAccessDao = new GenericDaoHibernate<ShortLogAccess, Long>(ShortLogAccess.class, sessionFactory);
        this.dao = this.shortLogAccessDao;
    }

}
