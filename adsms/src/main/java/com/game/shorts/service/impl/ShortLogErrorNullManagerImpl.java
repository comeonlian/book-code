package com.game.shorts.service.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.shorts.entity.ShortLogErrorNull;
import com.game.shorts.service.ShortLogErrorNullManager;

@Service("shortLogErrorNullManager")
public class ShortLogErrorNullManagerImpl extends GenericManagerImpl<ShortLogErrorNull, Long> implements ShortLogErrorNullManager {

    private GenericDao<ShortLogErrorNull, Long> shortLogErrorNullDao;

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public ShortLogErrorNullManagerImpl(SessionFactory sessionFactory) {
        this.shortLogErrorNullDao = new GenericDaoHibernate<ShortLogErrorNull, Long>(ShortLogErrorNull.class, sessionFactory);
        this.dao = this.shortLogErrorNullDao;
    }

}
