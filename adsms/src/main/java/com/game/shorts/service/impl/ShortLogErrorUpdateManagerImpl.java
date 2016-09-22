package com.game.shorts.service.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.shorts.entity.ShortLogErrorUpdate;
import com.game.shorts.service.ShortLogErrorUpdateManager;

@Service("shortLogErrorUpdateManager")
public class ShortLogErrorUpdateManagerImpl extends GenericManagerImpl<ShortLogErrorUpdate, Long> implements ShortLogErrorUpdateManager {

    private GenericDao<ShortLogErrorUpdate, Long> shortLogErrorUpdateDao;

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public ShortLogErrorUpdateManagerImpl(SessionFactory sessionFactory) {
        this.shortLogErrorUpdateDao = new GenericDaoHibernate<ShortLogErrorUpdate, Long>(ShortLogErrorUpdate.class, sessionFactory);
        this.dao = this.shortLogErrorUpdateDao;
    }

}
