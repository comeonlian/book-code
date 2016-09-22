package com.game.otapartner.service.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.otapartner.entity.OtaFirstVisitDaily;
import com.game.otapartner.service.OtaFirstVisitDailyManager;

/**
 * 每日首次访问
 * 
 */
@Service("otaFirstVisitDailyManager")
public class OtaFirstVisitDailyManagerImpl extends GenericManagerImpl<OtaFirstVisitDaily, Long> implements OtaFirstVisitDailyManager {

    private JdbcTemplate jdbcTemplate;
    private GenericDao<OtaFirstVisitDaily, Long> otaFirstVisitDailyDao;

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public OtaFirstVisitDailyManagerImpl(SessionFactory sessionFactory) {
        this.otaFirstVisitDailyDao = new GenericDaoHibernate<OtaFirstVisitDaily, Long>(OtaFirstVisitDaily.class, sessionFactory);
        jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(sessionFactory));
        this.dao = this.otaFirstVisitDailyDao;
    }

    @Override
    public boolean isDeviceExist(String currentdate, String deviceid) {
        boolean result = false;
        StringBuilder sb = new StringBuilder();
        sb.append(" select * from ota_co_first_visit_daily where deviceid=? and currentdate=? ");
        List<Map<String, Object>> devices = jdbcTemplate.queryForList(sb.toString(), deviceid, currentdate);
        if (!CollectionUtils.isEmpty(devices)) {
            return true;
        }
        return result;
    }
}
