package com.game.shorts.service.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.shorts.entity.ShortLogMessages;
import com.game.shorts.service.ShortLogMessagesManager;

@Service("shortLogMessagesManager")
public class ShortLogMessagesManagerImpl extends GenericManagerImpl<ShortLogMessages, Long> implements ShortLogMessagesManager {

    private GenericDao<ShortLogMessages, Long> shortLogMessagesDao;

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public ShortLogMessagesManagerImpl(SessionFactory sessionFactory) {
        this.shortLogMessagesDao = new GenericDaoHibernate<ShortLogMessages, Long>(ShortLogMessages.class, sessionFactory);
        this.dao = this.shortLogMessagesDao;
    }

}
