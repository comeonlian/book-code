package com.game.shorts.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.shorts.entity.ShortShell;
import com.game.shorts.service.ShortShellManager;

@Service("shortShellManager")
public class ShortShellManagerImpl extends GenericManagerImpl<ShortShell, Long> implements ShortShellManager {

    private GenericDao<ShortShell, Long> shortShellDao;

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public ShortShellManagerImpl(SessionFactory sessionFactory) {
        this.shortShellDao = new GenericDaoHibernate<ShortShell, Long>(ShortShell.class, sessionFactory);
        this.dao = this.shortShellDao;
    }

    @Override
    public List<ShortShell> getListByStatus(int status) {
        StringBuilder sb = new StringBuilder();
        sb.append(" from ShortShell where status=?");
        return dao.find(sb.toString(), status);
    }

}
