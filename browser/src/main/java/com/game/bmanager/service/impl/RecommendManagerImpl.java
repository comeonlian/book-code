package com.game.bmanager.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.bmanager.entity.Recommend;
import com.game.bmanager.service.RecommendManager;
import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.services.ServiceException;

@Service("recommendManager")
public class RecommendManagerImpl extends GenericManagerImpl<Recommend, Long> implements RecommendManager {

    private GenericDao<Recommend, Long> recommendDao;
    private static Logger logger = LoggerFactory.getLogger(RecommendManagerImpl.class);

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public RecommendManagerImpl(SessionFactory sessionFactory) {
        this.recommendDao = new GenericDaoHibernate<Recommend, Long>(Recommend.class, sessionFactory);
        this.dao = this.recommendDao;
    }

    public boolean delAll(List<Long> ids) {
        try {
            for (Long id : ids) {
                // 逻辑删除
                Recommend recommend = dao.get(id);
                recommend.setStatus(-1);
                dao.save(recommend);
            }
            return true;
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public List<Recommend> getByCustomid(String customerid) {
        StringBuilder sb = new StringBuilder();
        sb.append(" from Recommend where status=1 ");
        if (StringUtils.isNotBlank(customerid)) {
            sb.append(" and customers like '%").append(customerid).append("%'");
        }
        return dao.findByNum(sb.toString(), 0, 12);
        // return dao.find(sb.toString());
    }

}
