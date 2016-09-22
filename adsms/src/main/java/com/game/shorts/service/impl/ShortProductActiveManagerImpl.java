package com.game.shorts.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.services.ServiceException;
import com.game.services.common.DicManager;
import com.game.shorts.entity.ShortProductActive;
import com.game.shorts.entity.ShortProductActiveWap;
import com.game.shorts.service.ShortProductActiveManager;

/**
 * 代理人设置
 * 
 */
@Service("shortProductActiveManager")
public class ShortProductActiveManagerImpl extends GenericManagerImpl<ShortProductActive, Long> implements ShortProductActiveManager {

    @Autowired
    private DicManager dicManager;

    private GenericDao<ShortProductActive, Long> shortProductActiveDao;
    private GenericDao<ShortProductActiveWap, Long> shortProductActiveWapDao;
    private static Logger logger = LoggerFactory.getLogger(ShortProductActiveManagerImpl.class);

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public ShortProductActiveManagerImpl(SessionFactory sessionFactory) {
        this.shortProductActiveDao = new GenericDaoHibernate<ShortProductActive, Long>(ShortProductActive.class, sessionFactory);
        this.shortProductActiveWapDao = new GenericDaoHibernate<ShortProductActiveWap, Long>(ShortProductActiveWap.class, sessionFactory);
        this.dao = this.shortProductActiveDao;
    }

    @Override
    public boolean delAll(List<Long> ids) {
        try {
            for (Long id : ids) {
                this.dao.remove(id);
                // 删除子表列表
                List<ShortProductActiveWap> waps = this.findWapsByPid(id);
                for (ShortProductActiveWap item : waps) {
                    shortProductActiveDao.remove(item.getId());
                }
            }
            return true;
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public List<ShortProductActive> findByPid(Long pid) {

        StringBuffer sb = new StringBuffer();
        sb.append(" from ShortProductActive where 1=1 ");
        if (pid != null) {
            sb.append(" and pid=").append(pid);
        }

        sb.append(" order by ordernum asc ");
        List<ShortProductActive> pushApkActives = dao.find(sb.toString());

        return pushApkActives;
    }

    @Override
    public List<ShortProductActiveWap> findWapsByPid(Long pid) {
        StringBuffer sb = new StringBuffer();
        sb.append(" from ShortProductActiveWap where 1=1 ");
        sb.append(" and pid=").append(pid);
        return shortProductActiveWapDao.find(sb.toString());
    }

    @Override
    public ShortProductActive saveEntityAndItem(ShortProductActive entity, List<ShortProductActiveWap> waps) {
        entity = this.save(entity);
        this.dao.flush();

        for (ShortProductActiveWap wap : waps) {
            if (wap != null) {
                wap.setPid(entity.getId());
                shortProductActiveWapDao.save(wap);
            }
        }
        return entity;
    }

    @Override
    public void deleteWap(Long id) {
        shortProductActiveWapDao.remove(id);
    }
}
