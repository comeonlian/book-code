package com.game.browser.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.game.browser.service.UserAnalysisManager;
import com.game.modules.orm.Page;

@Service(value = "userAnalysisManager")
public class UserAnalysisManagerImpl extends BaseGeneralManagerImpl implements UserAnalysisManager {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserAnalysisManagerImpl(DataSource dataSourceWarehouse) {
        jdbcTemplate = new JdbcTemplate(dataSourceWarehouse);
    }

	@Override
	public List<Map<String, Object>> newActiveAnalysisDetail(String virtu,
			String begindate, String enddate, String country, String customid) {
		StringBuilder sb = new StringBuilder();
		List<String> list = new ArrayList<String>();
		sb.append("SELECT access_date 'date',SUM(new_user) 'newUser',SUM(active_user) 'active' ");
		sb.append("FROM daily_new_active_"+ virtu +" ");
		sb.append("WHERE access_date BETWEEN ? AND ? ");
		list.add(begindate);
		list.add(enddate);
		if(country.equals("in"))
			sb.append(" AND city<=10000  ");
		if(country.equals("out"))
			sb.append(" AND city>10000  ");
		if(StringUtils.isNotBlank(customid)){
			sb.append("  AND  customid=?  ");
			list.add(customid);
		}
		sb.append(" GROUP BY access_date  ");
		return jdbcTemplate.queryForList(sb.toString(), list.toArray());
	}

	@Override
	public Long newActiveAnalysisCount(String virtu,
			String date, String country, String customid) {
		StringBuilder sb = new StringBuilder();
		List<String> list = new ArrayList<String>();
		sb.append("SELECT SUM(new_user) 'count' FROM daily_new_active_"+ virtu +"   WHERE  1=1");
		sb.append(" AND  access_date<DATE(?) ");
		list.add(date);
		if(country.equals("in"))
			sb.append(" AND city<=10000  ");
		if(country.equals("out"))
			sb.append(" AND city>10000  ");
		if(StringUtils.isNotBlank(customid)){
			sb.append("  AND  customid=?  ");
			list.add(customid);
		}
		return jdbcTemplate.queryForObject(sb.toString(),list.toArray(), Long.class);
	}

	@Override
	public Long newActiveAnalysisTotal(String virtu,
			String begindate, String enddate, String country, String customid) {
		StringBuilder sb = new StringBuilder();
		List<String> list = new ArrayList<String>();
		sb.append("SELECT SUM(new_user) 'total' FROM daily_new_active_"+virtu+" ");
		sb.append("WHERE access_date BETWEEN ? AND ?  ");
		list.add(begindate);
		list.add(enddate);
		
		if(country.equals("in"))
			sb.append(" AND city<=10000  ");
		if(country.equals("out"))
			sb.append(" AND city>10000  ");
		if(StringUtils.isNotBlank(customid)){
			sb.append("  AND  customid=?  ");
			list.add(customid);
		}
		return jdbcTemplate.queryForObject(sb.toString(),list.toArray(), Long.class);
	}
	@Override
	public Long newActiveAnalysisInTotal(String virtu, String begindate,
			String enddate, String country, String customid) {
		StringBuilder sb = new StringBuilder();
		List<String> list = new ArrayList<String>();
		sb.append("SELECT SUM(new_user) 'total' FROM daily_new_active_"+virtu+" ");
		sb.append("WHERE  city<=10000  AND   access_date BETWEEN ? AND ?  ");
		list.add(begindate);
		list.add(enddate);
		
		if(StringUtils.isNotBlank(customid)){
			sb.append("  AND  customid=?  ");
			list.add(customid);
		}
		return jdbcTemplate.queryForObject(sb.toString(),list.toArray(), Long.class);
	}

	@Override
	public Long newActiveAnalysisOutTotal(String virtu, String begindate,
			String enddate, String country, String customid) {
		StringBuilder sb = new StringBuilder();
		List<String> list = new ArrayList<String>();
		sb.append("SELECT SUM(new_user) 'total' FROM daily_new_active_"+virtu+" ");
		sb.append("WHERE  city>10000  AND  access_date BETWEEN ? AND ?  ");
		list.add(begindate);
		list.add(enddate);
		
		if(StringUtils.isNotBlank(customid)){
			sb.append("  AND  customid=?  ");
			list.add(customid);
		}
		return jdbcTemplate.queryForObject(sb.toString(),list.toArray(), Long.class);
	}
	@Override
	public List<Map<String, Object>> analysisGrahNew(String virtu,
			String begindate, String enddate, String country, String customid) {
		StringBuilder sb = new StringBuilder();
		List<String> list = new ArrayList<String>();
		sb.append("SELECT access_date 'date',SUM(new_user) 'newUser' FROM daily_new_active_"+ virtu +" ");
		sb.append("WHERE access_date BETWEEN ? AND ?");
		list.add(begindate);
		list.add(enddate);
		
		if(country.equals("in"))
			sb.append(" AND city<=10000  ");
		if(country.equals("out"))
			sb.append(" AND city>10000  ");
		if(StringUtils.isNotBlank(customid)){
			sb.append("  AND  customid=?  ");
			list.add(customid);
		}
		sb.append(" GROUP BY access_date ");
		return jdbcTemplate.queryForList(sb.toString(),list.toArray());
	}

	@Override
	public List<Map<String, Object>> analysisGrahActive(String virtu,
			String begindate, String enddate, String country, String customid) {
		StringBuilder sb = new StringBuilder();
		List<String> list = new ArrayList<String>();
		sb.append("SELECT access_date 'date',SUM(active_user) 'active' FROM daily_new_active_"+ virtu +" ");
		sb.append("WHERE access_date BETWEEN ? AND ?");
		list.add(begindate);
		list.add(enddate);
		
		if(country.equals("in"))
			sb.append(" AND city<=10000  ");
		if(country.equals("out"))
			sb.append(" AND city>10000  ");
		if(StringUtils.isNotBlank(customid)){
			sb.append("  AND  customid=?  ");
			list.add(customid);
		}
		sb.append(" GROUP BY access_date ");
		return jdbcTemplate.queryForList(sb.toString(),list.toArray());
	}

	@Override
	public Page<Map<String,Object>> newActiveAnalysisAreaDetail(Page<Map<String,Object>> page,String virtu,
			String begindate, String enddate, String area, String customid) {
		StringBuilder sb = new StringBuilder();
		List<String> list = new ArrayList<String>();
		sb.append("SELECT d.access_date 'date',c.name 'area',CASE WHEN SUM(d.new_user) IS NULL THEN 0 ELSE SUM(d.new_user)END 'newUser',SUM(d.active_user) 'active'  ");
		sb.append("FROM daily_new_active_androidid d LEFT JOIN common_city c ON c.id=d.city  ");
		sb.append("WHERE d.access_date BETWEEN ? AND ?   AND id>=1000 AND id<=10000 AND id NOT IN (1035,1036)  ");
		list.add(begindate);
		list.add(enddate);
		
		if(StringUtils.isNotBlank(area)){
			sb.append("  AND  c.name LIKE CONCAT('%',?,'%') ");
			list.add(area);
		}
		if(StringUtils.isNotBlank(customid)){
			sb.append("  AND  customid=?  ");
			list.add(customid);
		}
		
		sb.append(" GROUP BY access_date,city  ");
		// 查询总数
		if (page.isAutoCount()) {
            long totalCount = countSqlResult(jdbcTemplate, sb.toString(), list.toArray());
            page.setTotalCount(totalCount);
        }
		
		sb.append(" ORDER BY access_date DESC ");
		
		List<Map<String, Object>> result = jdbcTemplate.queryForList(sb.toString() + " LIMIT " + (page.getFirst() - 1) + "," + page.getPageSize(), list.toArray());
        page.setResult(result);
        return page;
	}

	@Override
	public List<String> queryAllArea() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT `name` FROM common_city WHERE id>=1000 AND id NOT IN (1035,1036,10000,19999) ");
		return jdbcTemplate.queryForList(sb.toString(), String.class);
	}

	@Override
	public List<Map<String, Object>> areaInNewAjax(String virtu,
			String begindate, String enddate, String area, String customid) {
		List<String> list = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT c.name 'area',CASE WHEN SUM(d.new_user) IS NULL THEN 0 ELSE SUM(d.new_user)END 'newUser' "); 
		sb.append(" FROM daily_new_active_"+ virtu +" d ");
		sb.append(" LEFT JOIN common_city c ON c.id=d.city ");
		sb.append(" WHERE d.access_date BETWEEN ? AND ?  ");
		list.add(begindate);
		list.add(enddate);
		sb.append(" AND id>=1000 AND id<=10000 AND id NOT IN (1035,1036) ");
		if(StringUtils.isNotBlank(customid)){
			sb.append("  AND  customid=?  ");
			list.add(customid);
		}
		sb.append("GROUP BY city  ORDER BY newUser DESC   LIMIT 0,10");
		return jdbcTemplate.queryForList(sb.toString(), list.toArray());
	}

	@Override
	public List<Map<String, Object>> areaInActiveAjax(String virtu,
			String begindate, String enddate, String area, String customid) {
		List<String> list = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT c.name 'area',SUM(d.active_user) 'active'   "); 
		sb.append(" FROM daily_new_active_"+ virtu +" d ");
		sb.append(" LEFT JOIN common_city c ON c.id=d.city ");
		sb.append(" WHERE d.access_date BETWEEN ? AND ?  ");
		list.add(begindate);
		list.add(enddate);
		sb.append(" AND id>=1000 AND id<=10000 AND id NOT IN (1035,1036) ");
		if(StringUtils.isNotBlank(customid)){
			sb.append("  AND  customid=?  ");
			list.add(customid);
		}
		sb.append("GROUP BY city  ORDER BY active DESC   LIMIT 0,10");
		return jdbcTemplate.queryForList(sb.toString(), list.toArray());
	}

	@Override
	public Page<Map<String, Object>> newActiveAnalysisAreaOut(
			Page<Map<String, Object>> page, String virtu, String begindate,
			String enddate, String area, String customid) {
		StringBuilder sb = new StringBuilder();
		List<String> list = new ArrayList<String>();
		sb.append("SELECT d.access_date 'date',c.name 'area',CASE WHEN SUM(d.new_user) IS NULL THEN 0 ELSE SUM(d.new_user)END 'newUser',SUM(d.active_user) 'active'  ");
		sb.append("FROM daily_new_active_androidid d LEFT JOIN common_city c ON c.id=d.city  ");
		sb.append("WHERE d.access_date BETWEEN ? AND ?  AND id>10000  AND id NOT IN (19999)  ");
		list.add(begindate);
		list.add(enddate);
		
		if(StringUtils.isNotBlank(area)){
			sb.append("  AND  c.name LIKE CONCAT('%',?,'%') ");
			list.add(area);
		}
		if(StringUtils.isNotBlank(customid)){
			sb.append("  AND  customid=?  ");
			list.add(customid);
		}
		
		sb.append(" GROUP BY access_date,city  ");
		// 查询总数
		if (page.isAutoCount()) {
            long totalCount = countSqlResult(jdbcTemplate, sb.toString(), list.toArray());
            page.setTotalCount(totalCount);
        }
		
		sb.append(" ORDER BY access_date DESC ");
		
		List<Map<String, Object>> result = jdbcTemplate.queryForList(sb.toString() + " LIMIT " + (page.getFirst() - 1) + "," + page.getPageSize(), list.toArray());
        page.setResult(result);
        return page;
	}

	@Override
	public List<Map<String, Object>> areaOutNewAjax(String virtu,
			String begindate, String enddate, String area, String customid) {
		List<String> list = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT c.name 'area',CASE WHEN SUM(d.new_user) IS NULL THEN 0 ELSE SUM(d.new_user)END 'newUser' "); 
		sb.append(" FROM daily_new_active_"+ virtu +" d ");
		sb.append(" LEFT JOIN common_city c ON c.id=d.city ");
		sb.append(" WHERE d.access_date BETWEEN ? AND ?  ");
		list.add(begindate);
		list.add(enddate);
		sb.append("  AND id>10000  AND id NOT IN (19999)  ");
		if(StringUtils.isNotBlank(customid)){
			sb.append("  AND  customid=?  ");
			list.add(customid);
		}
		sb.append("GROUP BY city  ORDER BY newUser DESC   LIMIT 0,10");
		return jdbcTemplate.queryForList(sb.toString(), list.toArray());
	}

	@Override
	public List<Map<String, Object>> areaOutActiveAjax(String virtu,
			String begindate, String enddate, String area, String customid) {
		List<String> list = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT c.name 'area',SUM(d.active_user) 'active'   "); 
		sb.append(" FROM daily_new_active_"+ virtu +" d ");
		sb.append(" LEFT JOIN common_city c ON c.id=d.city ");
		sb.append(" WHERE d.access_date BETWEEN ? AND ?  ");
		list.add(begindate);
		list.add(enddate);
		sb.append("  AND id>10000  AND id NOT IN (19999)  ");
		if(StringUtils.isNotBlank(customid)){
			sb.append("  AND  customid=?  ");
			list.add(customid);
		}
		sb.append("GROUP BY city  ORDER BY active DESC   LIMIT 0,10");
		return jdbcTemplate.queryForList(sb.toString(), list.toArray());
	}
	
	
}
