package com.game.bmanager.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.bmanager.entity.HotWords;
import com.game.bmanager.service.HotWordsManager;
import com.game.modules.orm.GenericDao;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.services.ServiceException;

@Service("hotWordsManager")
public class HotWordsManagerImpl extends GenericManagerImpl<HotWords, Long> implements HotWordsManager {

    private GenericDao<HotWords, Long> hotWordsDao;
    private static Logger logger = LoggerFactory.getLogger(HotWordsManagerImpl.class);

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public HotWordsManagerImpl(SessionFactory sessionFactory) {
        this.hotWordsDao = new GenericDaoHibernate<HotWords, Long>(HotWords.class, sessionFactory);
        this.dao = this.hotWordsDao;
    }

    public boolean delAll(List<Long> ids) {
        try {
            for (Long id : ids) {
                // 逻辑删除
                HotWords hotWords = dao.get(id);
                hotWords.setStatus(-1);
                dao.save(hotWords);
            }
            return true;
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public List<HotWords> getByCustomid(String customerid) {
        StringBuilder sb = new StringBuilder();
        sb.append(" from HotWords where status=1 ");
        if (StringUtils.isNotBlank(customerid)) {
            sb.append(" and customers like '%").append(customerid).append("%'");
        }
        return dao.findByNum(sb.toString(), 0, 12);
    }

}
