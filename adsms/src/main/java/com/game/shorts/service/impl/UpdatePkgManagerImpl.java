package com.game.shorts.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.services.ServiceException;
import com.game.shorts.entity.UpdatePkg;
import com.game.shorts.service.UpdatePkgManager;

@Service("updatePkgManager")
public class UpdatePkgManagerImpl extends GenericManagerImpl<UpdatePkg, Long> implements UpdatePkgManager {

    private GenericDao<UpdatePkg, Long> updatePkgDao;
    private static Logger logger = LoggerFactory.getLogger(UpdatePkgManagerImpl.class);

    @Autowired
    public UpdatePkgManagerImpl(SessionFactory sessionFactory) {
        this.updatePkgDao = new GenericDaoHibernate<UpdatePkg, Long>(UpdatePkg.class, sessionFactory);
        this.dao = this.updatePkgDao;
    }

    @Override
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
    public UpdatePkg getEntityByCustomid(String customerid) {
        List<UpdatePkg> pkgs = dao.find(" from UpdatePkg where status=1 and customers like '%" + customerid + "%' order by apkVersion desc ");
        if (!CollectionUtils.isEmpty(pkgs)) {
            return pkgs.get(0);
        }
        return null;
    }

}
