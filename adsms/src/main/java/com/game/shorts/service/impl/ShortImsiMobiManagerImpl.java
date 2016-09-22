package com.game.shorts.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.shorts.entity.ShortImsiMobi;
import com.game.shorts.service.ShortImsiMobiManager;

@Service("shortImsiMobiManager")
public class ShortImsiMobiManagerImpl extends GenericManagerImpl<ShortImsiMobi, String> implements ShortImsiMobiManager {
    private GenericDao<ShortImsiMobi, String> shortImsiMobiDao;

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public ShortImsiMobiManagerImpl(SessionFactory sessionFactory) {
        this.shortImsiMobiDao = new GenericDaoHibernate<ShortImsiMobi, String>(ShortImsiMobi.class, sessionFactory);
        this.dao = this.shortImsiMobiDao;
    }

    @Override
    public ShortImsiMobi getEntityByImsi(String imsi) {
        if (StringUtils.isNotBlank(imsi)) {
            List<ShortImsiMobi> lists = dao.find(" from ShortImsiMobi where imsi=?", imsi);
            if (!CollectionUtils.isEmpty(lists)) {
                return lists.get(0);
            }
        }
        return null;
    }

}
