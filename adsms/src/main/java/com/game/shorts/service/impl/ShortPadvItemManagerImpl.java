package com.game.shorts.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.shorts.entity.ShortPadvItem;
import com.game.shorts.service.ShortPadvItemManager;

@Service("shortShortPadvItemManager")
public class ShortPadvItemManagerImpl extends GenericManagerImpl<ShortPadvItem, Long> implements ShortPadvItemManager {

    private GenericDao<ShortPadvItem, Long> shortShortPadvItemDao;

    @Autowired
    public ShortPadvItemManagerImpl(SessionFactory sessionFactory) {
        this.shortShortPadvItemDao = new GenericDaoHibernate<ShortPadvItem, Long>(ShortPadvItem.class, sessionFactory);
        this.dao = this.shortShortPadvItemDao;
    }

    @Override
    public List<ShortPadvItem> findByPid(Long parentId) {
        StringBuffer sb = new StringBuffer();
        sb.append(" from ShortPadvItem where 1=1 ");
        sb.append(" and pid=").append(parentId);
        sb.append(" order by provincecode asc ");
        return dao.find(sb.toString());
    }

    @Override
    public ShortPadvItem findByProvinceAndPid(String provincecode, Long pid) {
        StringBuffer sb = new StringBuffer();
        sb.append(" from ShortPadvItem where 1=1 ");
        sb.append(" and pid=").append(pid);
        sb.append(" and provincecode = '").append(provincecode).append("'");
        sb.append(" order by provincecode asc ");
        List<ShortPadvItem> shortShortPadvItems = dao.find(sb.toString());
        if (shortShortPadvItems != null && shortShortPadvItems.size() > 0) {
            return shortShortPadvItems.get(0);
        }

        return null;
    }

    @Override
    public List<ShortPadvItem> findByProvince(String provinceCode) {

        StringBuffer sb = new StringBuffer();
        sb.append(" from ShortPadvItem where 1=1 ");
        sb.append(" and provincecode ='").append(provinceCode).append("'");
        sb.append(" and pushcounts < pushtimes");

        return dao.find(sb.toString());

    }

}
