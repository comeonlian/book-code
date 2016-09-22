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
import com.game.shorts.entity.ShortDevice;
import com.game.shorts.service.ShortDeviceManager;

/**
 * 设备表
 * 
 */
@Service("shortDeviceManager")
public class ShortDeviceManagerImpl extends GenericManagerImpl<ShortDevice, Long> implements ShortDeviceManager {

    private GenericDao<ShortDevice, Long> shortDeviceDao;
    private static Logger logger = LoggerFactory.getLogger(ShortDeviceManagerImpl.class);

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public ShortDeviceManagerImpl(SessionFactory sessionFactory) {
        this.shortDeviceDao = new GenericDaoHibernate<ShortDevice, Long>(ShortDevice.class, sessionFactory);
        this.dao = this.shortDeviceDao;
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
    public ShortDevice getDeviceByDeviceid(String deviceid) {
        ShortDevice device = null;
        List<ShortDevice> result = dao.find("from ShortDevice where deviceid='" + deviceid + "'");
        if (result != null && result.size() > 0) {
            device = result.get(0);
        }
        return device;
    }

    @Override
    public ShortDevice getDeviceByAndroidid(String androidid) {
        ShortDevice device = null;
        List<ShortDevice> result = dao.find("from ShortDevice where androidid='" + androidid + "'");
        if (result != null && result.size() > 0) {
            device = result.get(0);
        }
        return device;
    }
}
