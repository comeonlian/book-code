package com.game.comm.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.game.comm.entity.CommonPrefixMobi;
import com.game.comm.service.CommonPrefixMobiManager;
import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;

@Service("commonPrefixMobiManager")
public class CommonPrefixMobiManagerImpl extends GenericManagerImpl<CommonPrefixMobi, String> implements CommonPrefixMobiManager {
    private GenericDao<CommonPrefixMobi, String> commonPrefixMobiDao;

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public CommonPrefixMobiManagerImpl(SessionFactory sessionFactory) {
        this.commonPrefixMobiDao = new GenericDaoHibernate<CommonPrefixMobi, String>(CommonPrefixMobi.class, sessionFactory);
        this.dao = this.commonPrefixMobiDao;
    }

    @Override
    public CommonPrefixMobi getEntityByCode(String prefixMobi) {
        if (StringUtils.isNotBlank(prefixMobi)) {
            List<CommonPrefixMobi> lists = dao.find(" from CommonPrefixMobi where prefixMobi=?", prefixMobi);
            if (!CollectionUtils.isEmpty(lists)) {
                return lists.get(0);
            }
        }
        return null;
    }

}
