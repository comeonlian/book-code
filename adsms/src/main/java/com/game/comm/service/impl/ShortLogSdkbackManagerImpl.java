package com.game.comm.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.comm.entity.ShortLogIvrLi;
import com.game.comm.entity.ShortLogIvrTyxx;
import com.game.comm.entity.ShortLogSdkback;
import com.game.comm.entity.ShortLogSmsTyxx;
import com.game.comm.service.ShortLogSdkbackManager;
import com.game.modules.orm.GenericDao;
import com.game.modules.orm.Page;
import com.game.modules.orm.PropertyFilter;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;

@Service("shortLogSdkbackManager")
public class ShortLogSdkbackManagerImpl extends GenericManagerImpl<ShortLogSdkback, Long> implements ShortLogSdkbackManager {

    private GenericDao<ShortLogSdkback, Long> shortLogSdkbackDao;
    private GenericDao<ShortLogIvrLi, Long> shortLogIvrLiDao;
    private GenericDao<ShortLogIvrTyxx, Long> shortLogIvrTyxxDao;
    private GenericDao<ShortLogSmsTyxx, Long> shortLogSmsTyxxDao;

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public ShortLogSdkbackManagerImpl(SessionFactory sessionFactory) {
        this.shortLogSdkbackDao = new GenericDaoHibernate<ShortLogSdkback, Long>(ShortLogSdkback.class, sessionFactory);
        this.shortLogIvrLiDao = new GenericDaoHibernate<ShortLogIvrLi, Long>(ShortLogIvrLi.class, sessionFactory);
        this.shortLogIvrTyxxDao = new GenericDaoHibernate<ShortLogIvrTyxx, Long>(ShortLogIvrTyxx.class, sessionFactory);
        this.shortLogSmsTyxxDao = new GenericDaoHibernate<ShortLogSmsTyxx, Long>(ShortLogSmsTyxx.class, sessionFactory);
        this.dao = this.shortLogSdkbackDao;
    }

    @Override
    public ShortLogIvrLi saveIvrLi(ShortLogIvrLi ivrLi) {
        return shortLogIvrLiDao.save(ivrLi);
    }

    @Override
    public ShortLogSmsTyxx saveSmsTyxx(ShortLogSmsTyxx smsTyxx) {
        return shortLogSmsTyxxDao.save(smsTyxx);
    }

    @Override
    public ShortLogIvrTyxx saveIvrTyxx(ShortLogIvrTyxx ivrTyxx) {
        return shortLogIvrTyxxDao.save(ivrTyxx);
    }

    @Override
    public Page<ShortLogIvrLi> searchPageIvrLi(Page<ShortLogIvrLi> page, List<PropertyFilter> filters) {
        return shortLogIvrLiDao.findPage(page, filters);
    }

    @Override
    public Page<ShortLogSmsTyxx> searchPageSmsTyxx(Page<ShortLogSmsTyxx> page, List<PropertyFilter> filters) {
        return shortLogSmsTyxxDao.findPage(page, filters);
    }

    @Override
    public Page<ShortLogIvrTyxx> searchPageIvrTyxx(Page<ShortLogIvrTyxx> page, List<PropertyFilter> filters) {
        return shortLogIvrTyxxDao.findPage(page, filters);
    }

}
