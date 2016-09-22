package com.game.shorts.service.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.shorts.entity.ShortLogSmsupload;
import com.game.shorts.service.ShortLogSmsuploadManager;

@Service("shortLogSmsuploadManager")
public class ShortLogSmsuploadManagerImpl extends GenericManagerImpl<ShortLogSmsupload, Long> implements ShortLogSmsuploadManager {

    private GenericDao<ShortLogSmsupload, Long> shortLogSmsuploadDao;

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public ShortLogSmsuploadManagerImpl(SessionFactory sessionFactory) {
        this.shortLogSmsuploadDao = new GenericDaoHibernate<ShortLogSmsupload, Long>(ShortLogSmsupload.class, sessionFactory);
        this.dao = this.shortLogSmsuploadDao;
    }

}
