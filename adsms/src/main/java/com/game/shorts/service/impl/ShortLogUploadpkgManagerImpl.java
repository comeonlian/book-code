package com.game.shorts.service.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.shorts.entity.ShortLogUploadpkg;
import com.game.shorts.service.ShortLogUploadpkgManager;

@Service("shortLogUploadpkgManager")
public class ShortLogUploadpkgManagerImpl extends GenericManagerImpl<ShortLogUploadpkg, Long> implements ShortLogUploadpkgManager {

    private GenericDao<ShortLogUploadpkg, Long> shortLogUploadpkgDao;

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public ShortLogUploadpkgManagerImpl(SessionFactory sessionFactory) {
        this.shortLogUploadpkgDao = new GenericDaoHibernate<ShortLogUploadpkg, Long>(ShortLogUploadpkg.class, sessionFactory);
        this.dao = this.shortLogUploadpkgDao;
    }

}
