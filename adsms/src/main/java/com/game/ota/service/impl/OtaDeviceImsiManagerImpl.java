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
import com.game.ota.entity.OtaDeviceImsi;
import com.game.ota.service.OtaDeviceImsiManager;
import com.game.services.ServiceException;

@Service("otaDeviceImsiManager")
public class OtaDeviceImsiManagerImpl extends GenericManagerImpl<OtaDeviceImsi, Long> implements OtaDeviceImsiManager {

    private GenericDao<OtaDeviceImsi, Long> otaDeviceImsiDao;
    private static Logger logger = LoggerFactory.getLogger(OtaDeviceImsiManagerImpl.class);

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public OtaDeviceImsiManagerImpl(SessionFactory sessionFactory) {
        this.otaDeviceImsiDao = new GenericDaoHibernate<OtaDeviceImsi, Long>(OtaDeviceImsi.class, sessionFactory);
        this.dao = this.otaDeviceImsiDao;
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
    public OtaDeviceImsi getDeviceByImsi(String imsi) {
        OtaDeviceImsi device = null;
        List<OtaDeviceImsi> result = dao.find("from OtaDeviceImsi where imsi='" + imsi + "'");
        if (result != null && result.size() > 0) {
            device = result.get(0);
        }
        return device;
    }
}
