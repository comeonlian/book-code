package com.game.ota.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.ota.entity.OtaProductSms;
import com.game.ota.service.OtaProductSmsManager;

@Service("otaProductSmsManager")
public class OtaProductSmsManagerImpl extends GenericManagerImpl<OtaProductSms, Long> implements OtaProductSmsManager {

    private GenericDao<OtaProductSms, Long> otaProductSmsDao;

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public OtaProductSmsManagerImpl(SessionFactory sessionFactory) {
        this.otaProductSmsDao = new GenericDaoHibernate<OtaProductSms, Long>(OtaProductSms.class, sessionFactory);
        this.dao = this.otaProductSmsDao;
    }

    @Override
    public OtaProductSms getProduct(String customerid, String cityid, int providerCode, int deviceDays, String currentDate, String currentTime, int restValue) {
        StringBuilder sb = new StringBuilder();
        sb.append(" from OtaProductSms where 1=1 and status=1 and customid like '%").append(customerid)
                .append("%' and downnum<confignum and feeDays<=? and provider=? and begindate<='").append(currentDate).append("' and enddate>='").append(currentDate)
                .append("' and begintime<='").append(currentTime).append("' and endtime>='").append(currentTime).append(" and price<=").append(restValue)
                .append("' and citys like '%" + cityid + "%' ").append(" order by priority asc ");
        List<OtaProductSms> list = dao.find(sb.toString(), deviceDays, providerCode);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

}
