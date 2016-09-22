package com.game.ota.service.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.ota.entity.OtaLogPushIvr;
import com.game.ota.service.OtaLogPushIvrManager;

@Service("otaLogPushIvrManager")
public class OtaLogPushIvrManagerImpl extends GenericManagerImpl<OtaLogPushIvr, Long> implements OtaLogPushIvrManager {

    private GenericDao<OtaLogPushIvr, Long> otaLogPushIvrDao;

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public OtaLogPushIvrManagerImpl(SessionFactory sessionFactory) {
        this.otaLogPushIvrDao = new GenericDaoHibernate<OtaLogPushIvr, Long>(OtaLogPushIvr.class, sessionFactory);
        this.dao = this.otaLogPushIvrDao;
    }

}
