package com.game.comm.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.game.comm.entity.SmscenterUnion;
import com.game.comm.service.SmscenterUnionManager;
import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;

@Service("smscenterUnionManager")
public class SmscenterUnionManagerImpl extends GenericManagerImpl<SmscenterUnion, String> implements SmscenterUnionManager {
    private GenericDao<SmscenterUnion, String> smscenterUnionDao;

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public SmscenterUnionManagerImpl(SessionFactory sessionFactory) {
        this.smscenterUnionDao = new GenericDaoHibernate<SmscenterUnion, String>(SmscenterUnion.class, sessionFactory);
        this.dao = this.smscenterUnionDao;
    }

    @Override
    public SmscenterUnion getEntityByCode(String areacode) {
        List<SmscenterUnion> lists = dao.find(" from SmscenterUnion where areacode=?", areacode);
        if (!CollectionUtils.isEmpty(lists)) {
            return lists.get(0);
        }
        return null;
    }

}
