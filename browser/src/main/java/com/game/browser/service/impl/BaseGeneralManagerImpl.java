package com.game.browser.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.game.modules.orm.Page;

public class BaseGeneralManagerImpl {

    /**
     * 执行count查询获得本次sql查询所能获得的对象总数.
     * 
     * 本函数只能自动处理简单的sql语句,复杂的sql查询请另行编写count语句查询.
     */
    protected long countSqlResult(JdbcTemplate jdbcTemplate, String sql, Object... values) {
        Long count = Long.valueOf(0L);
        //sql = StringUtils.lowerCase(sql);

        sql = StringUtils.substringBefore(sql, "order by");
        sql = "from (" + sql + ")dd";

        String countSql = "select count(*) co " + sql;
        List<Map<String, Object>> ob = jdbcTemplate.queryForList(countSql, values);
        if (!CollectionUtils.isEmpty(ob)) {
            count = (Long) ob.get(0).get("co");
        }

        return count.longValue();
    }

    protected Page<Map<String, Object>> findPageOnSql(JdbcTemplate jdbcTemplate, Page<Map<String, Object>> page, String sql, Object... values) {
        Assert.notNull(page, "page不能为空");

        //sql = StringUtils.lowerCase(sql);

        if (page.isAutoCount()) {
            long totalCount = countSqlResult(jdbcTemplate, sql, values);
            page.setTotalCount(totalCount);
        }
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql + " limit " + (page.getFirst() - 1) + "," + page.getPageSize(), values);
        page.setResult(result);
        return page;
    }
}
