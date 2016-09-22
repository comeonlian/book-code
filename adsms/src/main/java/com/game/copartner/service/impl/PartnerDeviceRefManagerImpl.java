package com.game.copartner.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.game.copartner.entity.PartnerDeviceRef;
import com.game.copartner.service.PartnerDeviceRefManager;
import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.services.ServiceException;

/**
 * 开放客户的设备
 * 
 */
@Service("partnerDeviceRefManager")
public class PartnerDeviceRefManagerImpl extends GenericManagerImpl<PartnerDeviceRef, Long> implements PartnerDeviceRefManager {

    private JdbcTemplate jdbcTemplate;
    private GenericDao<PartnerDeviceRef, Long> partnerDeviceRefDao;
    private static Logger logger = LoggerFactory.getLogger(PartnerDeviceRefManagerImpl.class);

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public PartnerDeviceRefManagerImpl(SessionFactory sessionFactory) {
        this.partnerDeviceRefDao = new GenericDaoHibernate<PartnerDeviceRef, Long>(PartnerDeviceRef.class, sessionFactory);
        jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(sessionFactory));
        this.dao = this.partnerDeviceRefDao;
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
    public PartnerDeviceRef getByDeviceid(String deviceid) {
        StringBuilder sb = new StringBuilder();
        sb.append(" select * from co_device_ref where deviceid=? ");
        List<PartnerDeviceRef> devices = dao.find("from PartnerDeviceRef where deviceid=?", deviceid);
        // List<PartnerDeviceRef> devices = jdbcTemplate.queryForList(sb.toString(), PartnerDeviceRef.class, deviceid);
        if (!CollectionUtils.isEmpty(devices)) {
            return devices.get(0);
        }
        return null;
    }

    @Override
    public boolean isMacExist(String mac) {
        boolean result = false;
        if (StringUtils.isNotBlank(mac)) {
            StringBuilder sb = new StringBuilder();
            sb.append(" select * from co_device_ref where mac=? ");
            List<Map<String, Object>> devices = jdbcTemplate.queryForList(sb.toString(), mac);
            if (!CollectionUtils.isEmpty(devices)) {
                return true;
            }
        }
        return result;
    }

    @Override
    public boolean isImeiExist(String imei) {
        boolean result = false;
        if (StringUtils.isNotBlank(imei)) {
            StringBuilder sb = new StringBuilder();
            sb.append(" select * from co_device_ref where imei=? ");
            List<Map<String, Object>> devices = jdbcTemplate.queryForList(sb.toString(), imei);
            if (!CollectionUtils.isEmpty(devices)) {
                return true;
            }
        }
        return result;
    }

    @Override
    public boolean isAndroididExist(String androidid) {
        boolean result = false;
        if (StringUtils.isNotBlank(androidid)) {
            StringBuilder sb = new StringBuilder();
            sb.append(" select * from co_device_ref where androidid=? ");
            List<Map<String, Object>> devices = jdbcTemplate.queryForList(sb.toString(), androidid);
            if (!CollectionUtils.isEmpty(devices)) {
                return true;
            }
        }
        return result;
    }
}
