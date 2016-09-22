package com.game.ota.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.ota.entity.OtaDevice;
import com.game.ota.service.OtaDeviceManager;
import com.game.services.ServiceException;

@Service("otaDeviceManager")
public class OtaDeviceManagerImpl extends GenericManagerImpl<OtaDevice, Long> implements OtaDeviceManager {

    private GenericDao<OtaDevice, Long> otaDeviceDao;
    private static Logger logger = LoggerFactory.getLogger(OtaDeviceManagerImpl.class);

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public OtaDeviceManagerImpl(SessionFactory sessionFactory) {
        this.otaDeviceDao = new GenericDaoHibernate<OtaDevice, Long>(OtaDevice.class, sessionFactory);
        this.dao = this.otaDeviceDao;
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
    public OtaDevice getDeviceByDeviceid(String deviceid) {
        OtaDevice device = null;
        List<OtaDevice> result = dao.find("from OtaDevice where deviceid='" + deviceid + "'");
        if (result != null && result.size() > 0) {
            device = result.get(0);
        }
        return device;
    }

}
