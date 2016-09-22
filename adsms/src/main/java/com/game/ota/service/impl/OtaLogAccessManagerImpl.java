package com.game.ota.service.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.ota.entity.OtaLogAccess;
import com.game.ota.service.OtaLogAccessManager;

@Service("otaLogAccessManager")
public class OtaLogAccessManagerImpl extends GenericManagerImpl<OtaLogAccess, Long> implements OtaLogAccessManager {

    private GenericDao<OtaLogAccess, Long> otaLogAccessDao;

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public OtaLogAccessManagerImpl(SessionFactory sessionFactory) {
        this.otaLogAccessDao = new GenericDaoHibernate<OtaLogAccess, Long>(OtaLogAccess.class, sessionFactory);
        this.dao = this.otaLogAccessDao;
    }

}
