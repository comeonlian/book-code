package com.game.otapartner.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.otapartner.entity.OtaPartnerDevice;
import com.game.otapartner.service.OtaPartnerDeviceManager;

/**
 * 开放客户的设备
 * 
 */
@Service("otaPartnerDeviceManager")
public class OtaPartnerDeviceManagerImpl extends GenericManagerImpl<OtaPartnerDevice, Long> implements OtaPartnerDeviceManager {

    private GenericDao<OtaPartnerDevice, Long> otaPartnerDeviceDao;

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public OtaPartnerDeviceManagerImpl(SessionFactory sessionFactory) {
        this.otaPartnerDeviceDao = new GenericDaoHibernate<OtaPartnerDevice, Long>(OtaPartnerDevice.class, sessionFactory);
        this.dao = this.otaPartnerDeviceDao;
    }

    @Override
    public OtaPartnerDevice getByDeviceid(String deviceid) {

        List<OtaPartnerDevice> devices = dao.find("from OtaPartnerDevice where deviceid='" + deviceid + "'");
        if (!CollectionUtils.isEmpty(devices)) {
            return devices.get(0);
        }
        return null;
    }

}
