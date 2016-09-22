package com.game.shorts.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.game.modules.orm.GenericDao;
import com.game.modules.orm.Page;
import com.game.modules.orm.PropertyFilter;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.security.springsecurity.CustomizeInterceptor;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.services.ServiceException;
import com.game.shorts.entity.ShortPadv;
import com.game.shorts.service.ShortPadvManager;

/**
 * 代理人设置
 * 
 */
@Service("shortPadvManager")
public class ShortPadvManagerImpl extends GenericManagerImpl<ShortPadv, Long> implements ShortPadvManager {

    private GenericDao<ShortPadv, Long> shortPadvDao;
    private static Logger logger = LoggerFactory.getLogger(ShortPadvManagerImpl.class);
    private JdbcTemplate jdbcTemplate;

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public ShortPadvManagerImpl(SessionFactory sessionFactory) {
        this.shortPadvDao = new GenericDaoHibernate<ShortPadv, Long>(ShortPadv.class, sessionFactory);
        jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(sessionFactory));
        this.dao = this.shortPadvDao;

    }

    /**
     * 检查资源字符串是否唯一.
     * 
     * @return resString在数据库中唯一或等于oldResString时返回true.
     */
    @Transactional(readOnly = true)
    public boolean isResStringUnique(String newResString, String oldResString) {
        return shortPadvDao.isPropertyUnique("resString", newResString, oldResString);
    }

    /**
     * 使用属性过滤条件查询apk.
     */
    @Transactional(readOnly = true)
    public Page<ShortPadv> searchShortPadv(final Page<ShortPadv> page, final List<PropertyFilter> filters) {
        return shortPadvDao.findPage(page, filters);
    }

    @Transactional(readOnly = true)
    public ShortPadv getShortPadv(Long id) {
        return shortPadvDao.get(id);
    }

    public ShortPadv saveShortPadv(ShortPadv object) {
        object = shortPadvDao.save(object);
        // Rebuilt SpringSecurity
        CustomizeInterceptor.refresh();
        return object;
    }

    public void deleteShortPadv(Long id) {
        shortPadvDao.remove(id);
        // Rebuilt SpringSecurity
        CustomizeInterceptor.refresh();
    }

    public boolean delAll(List<Long> ids) {
        try {
            for (Long id : ids) {
                // this.shortPadvManagerDao.remove(id);
                // 物理删除改为逻辑删除
                ShortPadv shortPadv = shortPadvDao.get(id);
                shortPadv.setStatus(-1);
                shortPadvDao.save(shortPadv);
            }
            return true;
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public List<ShortPadv> findByCustomid(String customid) {

        StringBuffer sb = new StringBuffer();
        sb.append(" from ShortPadv where status = 1");
        sb.append(" and customid= '").append(customid).append("'");
        List<ShortPadv> shortPadvs = dao.find(sb.toString());

        return shortPadvs;
    }

    @Override
    public List<Map<String, Object>> searchObjList(String customid, String cityid, String provinceCode) {
        if (StringUtils.isNotBlank(customid) && StringUtils.isNotBlank(cityid) && StringUtils.isNotBlank(provinceCode)) {
            List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(); // 返回的结果集
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT pi.id, pi.pid, pi.content, pi.thsms, p.customid, p.value ");
            sql.append(" ,case when pi.pushtimes is null then 0 else pi.pushtimes end - case when pi.pushcounts is null then 0 else pi.pushcounts end restime ");
            sql.append(" from t_shortPadv p, t_shortPadv_item pi ");
            sql.append(" where p.id = pi.pid and p.status = 1 and pi.content is not null ");
            sql.append(" and customid='").append(customid).append("'");
            sql.append(" and provincecode='").append(provinceCode).append("'");
            sql.append(" and p.citys like '%").append(cityid).append("%'");
            sql.append(" and case when pi.pushtimes is null then 0 else pi.pushtimes end - case when pi.pushcounts is null then 0 else pi.pushcounts end > 0 ");
            sql.append(" order by p.value desc ");
            result = jdbcTemplate.queryForList(sql.toString());
            return result;
        }
        return null;
    }

    @Override
    public List<ShortPadv> searchShortPadvList(String city, String imsi, String customid) {

        int provider = 1;
        if ("46001".equals(imsi)) {
            provider = 2;
        }

        boolean customeflag = true;
        if (customid != null && !"".equals(customid.trim())) {
            List<ShortPadv> cstlist = findByCustomid(customid);
            if (cstlist != null && cstlist.size() > 0) {
                customeflag = false;
            }
        }

        StringBuffer sb = new StringBuffer();
        sb.append(" from ShortPadv where status = 1");
        sb.append(" and citys like '%").append(city).append("%'");
        sb.append(" and provider =").append(provider);
        sb.append(" order by ordernum desc ");
        List<ShortPadv> shortPadvs = dao.find(sb.toString());

        List<ShortPadv> alist = new ArrayList<ShortPadv>(); // 客户ID为空的第三方信息
        List<ShortPadv> clist = new ArrayList<ShortPadv>(); // 客户ID为指定客户的第三方信息

        for (ShortPadv p : shortPadvs) {
            if (p.getCustomid() == null || "".equals(p.getCustomid().trim())) {
                alist.add(p);
            } else if (p.getCustomid() != null && !"".equals(p.getCustomid().trim()) && p.getCustomid().equals(customid)) {
                clist.add(p);
            }
        }

        if (clist.size() > 0) {
            return clist;
        }

        if (!customeflag) {
            return null;
        }
        return alist;

    }

    @Override
    public boolean isNameUnique(String shortPadvName, String oldName) {
        return dao.isPropertyUnique("padvname", shortPadvName, oldName);
    }

    @Override
    public ShortPadv getPadv(String customerid, String cityid, int providerCode) {
        StringBuilder sb = new StringBuilder();
        sb.append(" from ShortPadv where status=1 and provider=? and citys like '%" + cityid + "%' ").append(" order by ordernum asc ");
        List<ShortPadv> list = dao.find(sb.toString(), providerCode);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

}
