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
import com.game.shorts.entity.ShortCustomer;
import com.game.shorts.service.ShortCustomerManager;

/**
 * 客户
 * 
 */
@Service("shortCustomerManager")
public class ShortCustomerManagerImpl extends GenericManagerImpl<ShortCustomer, Long> implements ShortCustomerManager {

    private GenericDao<ShortCustomer, Long> shortCustomerDao;
    private static Logger logger = LoggerFactory.getLogger(ShortCustomerManagerImpl.class);

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public ShortCustomerManagerImpl(SessionFactory sessionFactory) {
        this.shortCustomerDao = new GenericDaoHibernate<ShortCustomer, Long>(ShortCustomer.class, sessionFactory);
        this.dao = this.shortCustomerDao;
    }

    public boolean delAll(List<Long> ids) {
        try {
            for (Long id : ids) {
                // 逻辑删除
                ShortCustomer customer = dao.get(id);
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
    public ShortCustomer getByCustomerid(String customerid) {
        ShortCustomer customer = dao.findUniqueBy("customerid", customerid);
        return customer;
    }

    @Override
    public List<ShortCustomer> getAllByPassdevice(int passdevice) {
        return dao.find("from ShortCustomer where passdevice=" + passdevice);
    }

}
