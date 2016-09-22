package com.game.shorts.service.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.shorts.entity.ShortLogErrorMobileTime;
import com.game.shorts.service.ShortLogErrorMobileTimeManager;

@Service("shortLogErrorMobileTimeManager")
public class ShortLogErrorMobileTimeManagerImpl extends GenericManagerImpl<ShortLogErrorMobileTime, Long> implements ShortLogErrorMobileTimeManager {

    private GenericDao<ShortLogErrorMobileTime, Long> shortLogErrorMobileTimeDao;

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public ShortLogErrorMobileTimeManagerImpl(SessionFactory sessionFactory) {
        this.shortLogErrorMobileTimeDao = new GenericDaoHibernate<ShortLogErrorMobileTime, Long>(ShortLogErrorMobileTime.class, sessionFactory);
        this.dao = this.shortLogErrorMobileTimeDao;
    }

}
