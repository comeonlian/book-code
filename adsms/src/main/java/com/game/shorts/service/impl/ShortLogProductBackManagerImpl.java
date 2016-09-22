package com.game.shorts.service.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.shorts.entity.ShortLogProductBack;
import com.game.shorts.service.ShortLogProductBackManager;

@Service("shortLogProductBackManager")
public class ShortLogProductBackManagerImpl extends GenericManagerImpl<ShortLogProductBack, Long> implements ShortLogProductBackManager {

    private GenericDao<ShortLogProductBack, Long> shortLogProductBackDao;

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public ShortLogProductBackManagerImpl(SessionFactory sessionFactory) {
        this.shortLogProductBackDao = new GenericDaoHibernate<ShortLogProductBack, Long>(ShortLogProductBack.class, sessionFactory);
        this.dao = this.shortLogProductBackDao;
    }

}
