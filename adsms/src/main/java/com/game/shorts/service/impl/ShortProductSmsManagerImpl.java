package com.game.shorts.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.shorts.entity.ShortProductSms;
import com.game.shorts.service.ShortProductSmsManager;

@Service("shortProductSmsManager")
public class ShortProductSmsManagerImpl extends GenericManagerImpl<ShortProductSms, Long> implements ShortProductSmsManager {

    private GenericDao<ShortProductSms, Long> shortProductSmsDao;

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public ShortProductSmsManagerImpl(SessionFactory sessionFactory) {
        this.shortProductSmsDao = new GenericDaoHibernate<ShortProductSms, Long>(ShortProductSms.class, sessionFactory);
        this.dao = this.shortProductSmsDao;
    }

    @Override
    public ShortProductSms getProduct(String customerid, String cityid, int providerCode, int deviceDays, String currentDate, String currentTime, int restValue) {
        StringBuilder sb = new StringBuilder();
        sb.append(" from ShortProductSms where 1=1 and status=1 and customid like '%").append(customerid).append("%' and downnum<confignum and feeDays<=? and provider=? and begindate<='").append(currentDate)
                .append("' and enddate>='").append(currentDate).append("' and begintime<='").append(currentTime).append("' and endtime>='").append(currentTime)
                .append(" and price<=").append(restValue).append("' and citys like '%" + cityid + "%' ").append(" order by priority asc ");
        List<ShortProductSms> list = dao.find(sb.toString(), deviceDays, providerCode);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

}
