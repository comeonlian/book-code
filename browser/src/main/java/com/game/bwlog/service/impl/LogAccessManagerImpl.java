package com.game.bwlog.service.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.bwlog.entity.LogAccess;
import com.game.bwlog.service.LogAccessManager;
import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;

@Service(value = "logAccessManager")
public class LogAccessManagerImpl extends GenericManagerImpl<LogAccess,Long> implements LogAccessManager {

    private GenericDao<LogAccess, Long> logAccessDao;
    @Autowired
    public LogAccessManagerImpl(SessionFactory sessionFactory) {
        logAccessDao = new GenericDaoHibernate<LogAccess, Long>(LogAccess.class, sessionFactory);
        this.dao = logAccessDao;
    }

}
