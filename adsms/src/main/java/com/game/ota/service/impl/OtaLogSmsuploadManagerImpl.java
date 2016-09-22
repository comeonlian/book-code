package com.game.ota.service.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.ota.entity.OtaLogSmsupload;
import com.game.ota.service.OtaLogSmsuploadManager;

@Service("otaLogSmsuploadManager")
public class OtaLogSmsuploadManagerImpl extends GenericManagerImpl<OtaLogSmsupload, Long> implements OtaLogSmsuploadManager {

    private GenericDao<OtaLogSmsupload, Long> otaLogSmsuploadDao;

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public OtaLogSmsuploadManagerImpl(SessionFactory sessionFactory) {
        this.otaLogSmsuploadDao = new GenericDaoHibernate<OtaLogSmsupload, Long>(OtaLogSmsupload.class, sessionFactory);
        this.dao = this.otaLogSmsuploadDao;
    }

}
