package com.game.ota.service.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.ota.entity.OtaLogPushSms;
import com.game.ota.service.OtaLogPushSmsManager;

@Service("otaLogPushSmsManager")
public class OtaLogPushSmsManagerImpl extends GenericManagerImpl<OtaLogPushSms, Long> implements OtaLogPushSmsManager {

    private GenericDao<OtaLogPushSms, Long> otaLogPushSmsDao;

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public OtaLogPushSmsManagerImpl(SessionFactory sessionFactory) {
        this.otaLogPushSmsDao = new GenericDaoHibernate<OtaLogPushSms, Long>(OtaLogPushSms.class, sessionFactory);
        this.dao = this.otaLogPushSmsDao;
    }

}
