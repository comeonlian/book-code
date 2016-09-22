package com.game.copartner.service.impl;

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
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.game.copartner.entity.ProviderDaily;
import com.game.copartner.service.ProviderDailyManager;
import com.game.modules.orm.GenericDao;
import com.game.modules.orm.Page;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.services.ServiceException;
import com.game.util.Constants;
import com.game.util.StringUtil;

/**
 * 开放客户的设备
 * 
 */
@Service("providerDailyManager")
public class ProviderDailyManagerImpl extends GenericManagerImpl<ProviderDaily, Long> implements ProviderDailyManager {

    private JdbcTemplate jdbcTemplate;
    private GenericDao<ProviderDaily, Long> providerDailyDao;
    private static Logger logger = LoggerFactory.getLogger(ProviderDailyManagerImpl.class);

    /**
     * 注入hibernate的sessionFactory构建dao
     * 
     * @param sessionFactory
     */
    @Autowired
    public ProviderDailyManagerImpl(SessionFactory sessionFactory) {
        this.providerDailyDao = new GenericDaoHibernate<ProviderDaily, Long>(ProviderDaily.class, sessionFactory);
        jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(sessionFactory));
        this.dao = this.providerDailyDao;
    }

    /**
     * 执行count查询获得本次sql查询所能获得的对象总数.
     * 
     * 本函数只能自动处理简单的sql语句,复杂的sql查询请另行编写count语句查询.
     */
    protected long countSqlResult(String sql, Object... values) {
        Long count = Long.valueOf(0L);
        sql = StringUtils.lowerCase(sql);

        sql = StringUtils.substringBefore(sql, "order by");
        sql = "from (" + sql + ")dd";

        String countSql = "select count(*) co " + sql;
        List<Map<String, Object>> ob = jdbcTemplate.queryForList(countSql, values);
        if (!CollectionUtils.isEmpty(ob)) {
            count = (Long) ob.get(0).get("co");
        }

        return count.longValue();
    }

    private Page<Map<String, Object>> findPageOnSql(Page<Map<String, Object>> page, String sql, Object... values) {
        Assert.notNull(page, "page不能为空");

        sql = StringUtils.lowerCase(sql);

        if (page.isAutoCount()) {
            long totalCount = countSqlResult(sql, values);
            page.setTotalCount(totalCount);
        }
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql + " limit " + (page.getFirst() - 1) + "," + page.getPageSize(), values);
        page.setResult(result);
        return page;
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
    public Page<Map<String, Object>> getRetentions(Page<Map<String, Object>> page, String begindate, String enddate, String customerid) {
        String conditionStr = "";
        String conditionStrAct = "";
        if (StringUtils.isNotBlank(begindate)) {
            conditionStr = conditionStr + " AND accesstime>='" + begindate + "' ";
            conditionStrAct = conditionStrAct + " AND currentdate>='" + begindate + "' ";
        }
        if (StringUtils.isNotBlank(enddate)) {
            conditionStr = conditionStr + " AND accesstime<'" + enddate + "' ";
            conditionStrAct = conditionStrAct + " AND currentdate<'" + enddate + "' ";
        }
        if (StringUtils.isNotBlank(customerid)) {
            conditionStr = conditionStr + " AND customid='" + customerid + "' ";
            conditionStrAct = conditionStrAct + " AND customid='" + customerid + "' ";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT hh.* FROM                                                                                                                           ")
                .append("     (SELECT addc.customid,addc.accessdate,addc.realnum,twoc.tworet,threec.threeret,sevenc.sevenret                    ")
                .append("     FROM                                                                                                                             ")
                .append("         (SELECT customid,DATE(accesstime) accessdate,count(deviceid) realnum FROM co_device WHERE 1=1 ").append(conditionStr)
                .append(" GROUP BY customid,DATE(accesstime)) addc                 ")
                .append("     LEFT JOIN                                                                                                                        ")
                .append("         (SELECT customid,DATE(accesstime) accessdate,count(deviceid) tworet FROM co_device WHERE nextretention=1 ").append(conditionStr)
                .append("  GROUP BY customid,DATE(accesstime)) twoc      ")
                .append("         ON addc.customid=twoc.customid AND addc.accessdate=twoc.accessdate                                                            ")
                .append("     LEFT JOIN                                                                                                                         ")
                .append("         (SELECT customid,DATE(accesstime) accessdate,count(deviceid) threeret FROM co_device WHERE thirdretention=1 ").append(conditionStr)
                .append("  GROUP BY customid,DATE(accesstime)) threec ")
                .append("         ON addc.customid=threec.customid AND addc.accessdate=threec.accessdate                                                        ")
                .append("     LEFT JOIN                                                                                                                         ")
                .append("         (SELECT customid,DATE(accesstime) accessdate,count(deviceid) sevenret FROM co_device WHERE sevenretention=1 ").append(conditionStr)
                .append("  GROUP BY customid,DATE(accesstime)) sevenc ")
                .append("         ON addc.customid=sevenc.customid AND addc.accessdate=sevenc.accessdate                                                        ")
                .append("     )hh ORDER BY hh.accessdate desc,hh.customid asc                                                                                   ");
        page = findPageOnSql(page, sb.toString());
        return page;
    }

    @Override
    public Page<Map<String, Object>> getOverviewUser(Page<Map<String, Object>> page, String customerid) {
        StringBuilder sb = new StringBuilder();
        String conditionStr = "";
        if (StringUtils.isNotBlank(customerid)) {
            conditionStr = conditionStr + " AND customid='" + customerid + "' ";
        }
        sb.append(" SELECT customid,count(*) total from co_device where 1=1 ").append(conditionStr).append(" GROUP BY customid  ORDER BY customid ");
        page = findPageOnSql(page, sb.toString());
        return page;
    }

    @Override
    public Integer getTotalByCusid(String customerid) {
        StringBuilder sb = new StringBuilder();
        String conditionStr = "";
        if (StringUtils.isNotBlank(customerid)) {
            conditionStr = conditionStr + " AND customid='" + customerid + "' ";
        }
        sb.append("SELECT count(*) total from co_device where 1=1 ").append(conditionStr);
        Integer result = jdbcTemplate.queryForObject(sb.toString(), Integer.class);
        return result;
    }

    @Override
    public Page<Map<String, Object>> getActivitiusers(Page<Map<String, Object>> page, String begindate, String enddate, String customerid) {
        StringBuilder sb = new StringBuilder();
        String conditionStr = "";
        if (StringUtils.isNotBlank(begindate)) {
            conditionStr = conditionStr + " AND currentdate>='" + begindate + "'";
        }
        if (StringUtils.isNotBlank(enddate)) {
            conditionStr = conditionStr + " AND currentdate<='" + enddate + "'";
        }
        if (StringUtils.isNotBlank(customerid)) {
            conditionStr = conditionStr + " AND customid='" + customerid + "' ";
        }
        sb.append(" SELECT currentdate,customid,count(1) total FROM co_first_visit_daily WHERE 1=1 ").append(conditionStr).append(" GROUP BY currentdate,customid  ")
                .append(" ORDER BY currentdate desc,customid desc ");
        page = findPageOnSql(page, sb.toString());
        return page;
    }

    @Override
    public Integer getOpenPrice(String begindate, String enddate, String userCustomerId, String dominCode) {
        StringBuilder sb = new StringBuilder();
        List<Object> list = new ArrayList<Object>();
        sb.append(" SELECT sum(opennum * unitprice) activitys from co_provider_daily WHERE status=1 ");
        if (StringUtils.isNotBlank(begindate)) {
            sb.append(" and currentdate>=? ");
            list.add(begindate);
        }
        if (StringUtils.isNotBlank(enddate)) {
            sb.append(" and currentdate<=? ");
            list.add(enddate);
        }
        if (StringUtils.isNotBlank(userCustomerId)) {
            if (Constants.DOMAIN_PARTNER.equals(dominCode)) {
                sb.append(" AND customer_id=? ");
                list.add(userCustomerId);
            } else if (Constants.DOMAIN_BUSINESS.equals(dominCode)) {
                sb.append(" AND customer_id in(?)");
                list.add(StringUtil.catMark(dominCode));
            }
        }
        int result = jdbcTemplate.queryForObject(sb.toString(), list.toArray(), Integer.class);
        return result;
    }

    @Override
    public Integer getOpenActivity(String begindate, String enddate, String userCustomerId, String dominCode) {
        StringBuilder sb = new StringBuilder();
        List<Object> list = new ArrayList<Object>();
        sb.append(" SELECT sum(opennum) activitys from co_provider_daily WHERE status=1 ");
        if (StringUtils.isNotBlank(begindate)) {
            sb.append(" and currentdate>=? ");
            list.add(begindate);
        }
        if (StringUtils.isNotBlank(enddate)) {
            sb.append(" and currentdate<=? ");
            list.add(enddate);
        }
        if (StringUtils.isNotBlank(userCustomerId)) {
            if (Constants.DOMAIN_PARTNER.equals(dominCode)) {
                sb.append(" AND customer_id=? ");
                list.add(userCustomerId);
            } else if (Constants.DOMAIN_BUSINESS.equals(dominCode)) {
                sb.append(" AND customer_id in(?)");
                list.add(StringUtil.catMark(dominCode));
            }
        }
        int result = jdbcTemplate.queryForObject(sb.toString(), list.toArray(), Integer.class);
        return result;
    }
}
