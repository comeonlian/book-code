package com.game.shorts.service.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.shorts.entity.ShortLogErrorCmd;
import com.game.shorts.service.ShortLogErrorCmdManager;

@Service("shortLogErrorCmdManager")
public class ShortLogErrorCmdManagerImpl extends GenericManagerImpl<ShortLogErrorCmd, Long> implements ShortLogErrorCmdManager {

    private GenericDao<ShortLogErrorCmd, Long> shortLogErrorCmdDao;

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public ShortLogErrorCmdManagerImpl(SessionFactory sessionFactory) {
        this.shortLogErrorCmdDao = new GenericDaoHibernate<ShortLogErrorCmd, Long>(ShortLogErrorCmd.class, sessionFactory);
        this.dao = this.shortLogErrorCmdDao;
    }

}
