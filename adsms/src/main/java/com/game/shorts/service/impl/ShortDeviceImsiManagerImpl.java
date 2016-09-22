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
import com.game.shorts.entity.ShortDeviceImsi;
import com.game.shorts.service.ShortDeviceImsiManager;

/**
 * 设备表
 * 
 */
@Service("shortDeviceImsiManager")
public class ShortDeviceImsiManagerImpl extends GenericManagerImpl<ShortDeviceImsi, Long> implements ShortDeviceImsiManager {

    private GenericDao<ShortDeviceImsi, Long> shortDeviceImsiDao;
    private static Logger logger = LoggerFactory.getLogger(ShortDeviceImsiManagerImpl.class);

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public ShortDeviceImsiManagerImpl(SessionFactory sessionFactory) {
        this.shortDeviceImsiDao = new GenericDaoHibernate<ShortDeviceImsi, Long>(ShortDeviceImsi.class, sessionFactory);
        this.dao = this.shortDeviceImsiDao;
    }

    public boolean delAll(List<Long> ids) {
        try {
            for (Long id : ids) {
                this.dao.remove(id);
            }
            return true;
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public ShortDeviceImsi getDeviceByImsi(String imsi) {
        ShortDeviceImsi device = null;
        List<ShortDeviceImsi> result = dao.find("from ShortDeviceImsi where imsi='" + imsi + "'");
        if (result != null && result.size() > 0) {
            device = result.get(0);
        }
        return device;
    }
}
