package com.game.shorts.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.shorts.entity.ShortKeyTip;
import com.game.shorts.service.ShortKeyTipManager;

@Service("shortShortKeyTipManager")
public class ShortKeyTipManagerImpl extends GenericManagerImpl<ShortKeyTip, Long> implements ShortKeyTipManager {

    private GenericDao<ShortKeyTip, Long> shortShortKeyTipDao;

    @Autowired
    public ShortKeyTipManagerImpl(SessionFactory sessionFactory) {
        this.shortShortKeyTipDao = new GenericDaoHibernate<ShortKeyTip, Long>(ShortKeyTip.class, sessionFactory);
        this.dao = this.shortShortKeyTipDao;
    }

    @Override
    public boolean delAll(List<Long> ids) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<ShortKeyTip> getListByPid(Long pid) {
        return dao.find(" from ShortKeyTip where nfid=" + pid);
    }

}
