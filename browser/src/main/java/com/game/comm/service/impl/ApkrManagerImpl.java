package com.game.comm.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.comm.entity.Apkr;
import com.game.comm.service.IApkrManager;
import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.services.ServiceException;

@Service("apkrManager")
public class ApkrManagerImpl extends GenericManagerImpl<Apkr, Long> implements IApkrManager {
    private GenericDao<Apkr, Long> apkrDao;
    private static Logger logger = LoggerFactory.getLogger(ApkrManagerImpl.class);

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public ApkrManagerImpl(SessionFactory sessionFactoryFolder) {
        this.apkrDao = new GenericDaoHibernate<Apkr, Long>(Apkr.class, sessionFactoryFolder);
        this.dao = this.apkrDao;
    }

    public boolean delAll(List<Long> ids) {
        try {
            for (Long id : ids) {
                // 逻辑删除
                dao.remove(id);
            }
            return true;
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }
}
