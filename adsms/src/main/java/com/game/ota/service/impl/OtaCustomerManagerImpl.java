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
import com.game.ota.entity.OtaCustomer;
import com.game.ota.service.OtaCustomerManager;
import com.game.services.ServiceException;

@Service("otaCustomerManager")
public class OtaCustomerManagerImpl extends GenericManagerImpl<OtaCustomer, Long> implements OtaCustomerManager {

    private GenericDao<OtaCustomer, Long> otaCustomerDao;
    private static Logger logger = LoggerFactory.getLogger(OtaCustomerManagerImpl.class);

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public OtaCustomerManagerImpl(SessionFactory sessionFactory) {
        this.otaCustomerDao = new GenericDaoHibernate<OtaCustomer, Long>(OtaCustomer.class, sessionFactory);
        this.dao = this.otaCustomerDao;
    }

    public boolean delAll(List<Long> ids) {
        try {
            for (Long id : ids) {
                // 逻辑删除
                OtaCustomer customer = dao.get(id);
                customer.setCustomerid(customer.getCustomerid() == null ? "" : customer.getCustomerid() + "old");
                customer.setStatus(-1);
                dao.save(customer);
            }
            return true;
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    public boolean isCustomidUnique(String customid, String oldName) {
        return dao.isPropertyUnique("customerid", customid, oldName);
    }

    @Override
    public OtaCustomer getByCustomerid(String customerid) {
        OtaCustomer customer = dao.findUniqueBy("customerid", customerid);
        return customer;
    }

    @Override
    public List<OtaCustomer> getAllByPassdevice(int passdevice) {
        return dao.find("from OtaCustomer where passdevice=" + passdevice);
    }

}
