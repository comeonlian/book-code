package com.game.shorts.service.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.shorts.entity.ShortLogInitarea;
import com.game.shorts.service.ShortLogInitareaManager;

@Service("shortLogInitareaManager")
public class ShortLogInitareaManagerImpl extends GenericManagerImpl<ShortLogInitarea, Long> implements ShortLogInitareaManager {

    private GenericDao<ShortLogInitarea, Long> shortLogInitareaDao;

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public ShortLogInitareaManagerImpl(SessionFactory sessionFactory) {
        this.shortLogInitareaDao = new GenericDaoHibernate<ShortLogInitarea, Long>(ShortLogInitarea.class, sessionFactory);
        this.dao = this.shortLogInitareaDao;
    }

}
