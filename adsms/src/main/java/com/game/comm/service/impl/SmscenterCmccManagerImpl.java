package com.game.comm.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.game.comm.entity.SmscenterCmcc;
import com.game.comm.service.SmscenterCmccManager;
import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;

@Service("smscenterCmccManager")
public class SmscenterCmccManagerImpl extends GenericManagerImpl<SmscenterCmcc, String> implements SmscenterCmccManager {
    private GenericDao<SmscenterCmcc, String> smscenterCmccDao;

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public SmscenterCmccManagerImpl(SessionFactory sessionFactory) {
        this.smscenterCmccDao = new GenericDaoHibernate<SmscenterCmcc, String>(SmscenterCmcc.class, sessionFactory);
        this.dao = this.smscenterCmccDao;
    }

    @Override
    public SmscenterCmcc getEntityByCode(String areacode) {
        if (StringUtils.isNotBlank(areacode)) {
            List<SmscenterCmcc> lists = dao.find(" from SmscenterCmcc where areacode=?", StringUtils.rightPad(areacode, 4, "0"));
            if (!CollectionUtils.isEmpty(lists)) {
                return lists.get(0);
            }
        }
        return null;
    }

}
