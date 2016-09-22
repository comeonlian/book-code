package com.game.copartner.service.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.game.copartner.entity.FirstVisitDaily;
import com.game.copartner.service.FirstVisitDailyManager;
import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.services.ServiceException;

/**
 * 每日首次访问
 * 
 */
@Service("firstVisitDailyManager")
public class FirstVisitDailyManagerImpl extends GenericManagerImpl<FirstVisitDaily, Long> implements FirstVisitDailyManager {

    private JdbcTemplate jdbcTemplate;
    private GenericDao<FirstVisitDaily, Long> firstVisitDailyDao;
    private static Logger logger = LoggerFactory.getLogger(FirstVisitDailyManagerImpl.class);

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public FirstVisitDailyManagerImpl(SessionFactory sessionFactory) {
        this.firstVisitDailyDao = new GenericDaoHibernate<FirstVisitDaily, Long>(FirstVisitDaily.class, sessionFactory);
        jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(sessionFactory));
        this.dao = this.firstVisitDailyDao;
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
    public boolean isDeviceExist(String currentdate, String deviceid) {
        boolean result = false;
        StringBuilder sb = new StringBuilder();
        sb.append(" select * from co_first_visit_daily where deviceid=? and currentdate=? ");
        List<Map<String, Object>> devices = jdbcTemplate.queryForList(sb.toString(), deviceid, currentdate);
        if (!CollectionUtils.isEmpty(devices)) {
            return true;
        }
        return result;
    }
}
