package com.game.bwlog.service.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.bwlog.entity.LogIndex;
import com.game.bwlog.service.LogIndexManager;
import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;

@Service(value = "logIndexManager")
public class LogIndexManagerImpl extends GenericManagerImpl<LogIndex,Long> implements LogIndexManager {

    private GenericDao<LogIndex, Long> logIndexDao;
    @Autowired
    public LogIndexManagerImpl(SessionFactory sessionFactory) {
        logIndexDao = new GenericDaoHibernate<LogIndex, Long>(LogIndex.class, sessionFactory);
        this.dao = logIndexDao;
    }

}
