package com.game.browser.service.impl;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.game.browser.service.AnalysisGeneralManager;

@Service(value = "analysisGeneralManager")
public class AnalysisGeneralManagerImpl extends BaseGeneralManagerImpl implements AnalysisGeneralManager {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AnalysisGeneralManagerImpl(DataSource dataSourceWarehouse) {
        jdbcTemplate = new JdbcTemplate(dataSourceWarehouse);
    }

    @Override
    public String testDual() {
        return jdbcTemplate.queryForObject(" SELECT DATE(NOW()) from DUAL ", String.class);
    }

	@Override
	public List<Map<String, Object>> userRetention(String virtu,String begindate, String enddate,String customerid) {
		begindate = "'" + begindate + "'"; 
		enddate = "'" + enddate + "'"; 
		
		String sql = " SELECT t0.date,t0.total,t1.rt1,t2.rt2,t3.rt3,t4.rt4,t5.rt5,t6.rt6,t7.rt7,t14.rt14,t30.rt30 "+
				" FROM ( "+
				" SELECT DATE(register_time) 'date',COUNT("+ virtu +") 'total' "+
				" FROM device_"+ virtu +"_log "+ 
				" WHERE DATE(register_time) BETWEEN " + begindate + " AND " + enddate + " ";
		if(StringUtils.isNotBlank(customerid)){
			customerid = "'" + customerid + "'"; 
			sql = sql + " AND customid=" + customerid;
		}
		sql = sql + " GROUP BY DATE(register_time) "+
				" )t0 LEFT JOIN ( "+
				" SELECT DATE(register_time) 'date',COUNT("+ virtu +") 'rt1' "+
				" FROM device_"+ virtu +"_log "+
				" WHERE DATE(register_time) BETWEEN " + begindate + " AND " + enddate + " AND retention_1=1 ";
		if(StringUtils.isNotBlank(customerid)){
			sql = sql + " AND customid=" + customerid;
		}
		sql = sql + " GROUP BY DATE(register_time) "+
				" )t1 ON t1.date=t0.date LEFT JOIN ( "+
				" SELECT DATE(register_time) 'date',COUNT("+ virtu +") 'rt2' "+
				" FROM device_"+ virtu +"_log "+
				" WHERE DATE(register_time) BETWEEN " + begindate + " AND " + enddate + " AND retention_2=1 ";
		if(StringUtils.isNotBlank(customerid)){
			sql = sql + " AND customid=" + customerid;
		}
		sql = sql + " GROUP BY DATE(register_time) "+
				" )t2 ON t2.date=t0.date LEFT JOIN ( "+
				" SELECT DATE(register_time) 'date',COUNT("+ virtu +") 'rt3' "+
				" FROM device_"+ virtu +"_log "+
				" WHERE DATE(register_time) BETWEEN " + begindate + " AND " + enddate + " AND retention_3=1 ";
		if(StringUtils.isNotBlank(customerid)){
			sql = sql + " AND customid=" + customerid;
		}
		sql = sql + " GROUP BY DATE(register_time) "+
				" )t3 ON t3.date=t0.date LEFT JOIN ( "+
				" SELECT DATE(register_time) 'date',COUNT("+ virtu +") 'rt4' "+
				" FROM device_"+ virtu +"_log "+
				" WHERE DATE(register_time) BETWEEN " + begindate + " AND " + enddate + " AND retention_4=1 ";
		if(StringUtils.isNotBlank(customerid)){
			sql = sql + " AND customid=" + customerid;
		}
		sql = sql + " GROUP BY DATE(register_time) "+
				" )t4 ON t4.date=t0.date LEFT JOIN ( "+
				" SELECT DATE(register_time) 'date',COUNT("+ virtu +") 'rt5' "+
				" FROM device_"+ virtu +"_log "+
				" WHERE DATE(register_time) BETWEEN " + begindate + " AND " + enddate + " AND retention_5=1 ";
		if(StringUtils.isNotBlank(customerid)){
			sql = sql + " AND customid=" + customerid;
		}
		sql = sql + " GROUP BY DATE(register_time) "+
				" )t5 ON t5.date=t0.date LEFT JOIN ( "+
				" SELECT DATE(register_time) 'date',COUNT("+ virtu +") 'rt6' "+
				" FROM device_"+ virtu +"_log "+
				" WHERE DATE(register_time) BETWEEN " + begindate + " AND " + enddate + " AND retention_6=1 ";
		if(StringUtils.isNotBlank(customerid)){
			sql = sql + " AND customid=" + customerid;
		}
		sql = sql + " GROUP BY DATE(register_time) "+
				" )t6 ON t6.date=t0.date LEFT JOIN ( "+
				" SELECT DATE(register_time) 'date',COUNT("+ virtu +") 'rt7' "+
				" FROM device_"+ virtu +"_log "+
				" WHERE DATE(register_time) BETWEEN " + begindate + " AND " + enddate + " AND retention_7=1 ";
		if(StringUtils.isNotBlank(customerid)){
			sql = sql + " AND customid=" + customerid;
		}
		sql = sql + " GROUP BY DATE(register_time) "+
				" )t7 ON t7.date=t0.date LEFT JOIN ( "+
				" SELECT DATE(register_time) 'date',COUNT("+ virtu +") 'rt14' "+
				" FROM device_"+ virtu +"_log "+
				" WHERE DATE(register_time) BETWEEN " + begindate + " AND " + enddate + " AND retention_14=1 ";
		if(StringUtils.isNotBlank(customerid)){
			sql = sql + " AND customid=" + customerid;
		}
		sql = sql + " GROUP BY DATE(register_time) "+
				" )t14 ON t14.date=t0.date LEFT JOIN ( "+
				" SELECT DATE(register_time) 'date',COUNT("+ virtu +") 'rt30' "+
				" FROM device_"+ virtu +"_log "+
				" WHERE DATE(register_time) BETWEEN " + begindate + " AND " + enddate + " AND retention_30=1 ";
		if(StringUtils.isNotBlank(customerid)){
			sql = sql + " AND customid=" + customerid;
		}
		sql = sql + " GROUP BY DATE(register_time) "+
				" )t30 ON t30.date=t0.date ";
		
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<Map<String, Object>> lossUser(String virtu, String begindate,String enddate,String customerid) {
		begindate = "'" + begindate + "'"; 
		enddate = "'" + enddate + "'"; 
		String sql = " SELECT loss_date 'date',COUNT("+ virtu +") 'cnt'"+
				" FROM device_"+ virtu +"_log "+
				" WHERE loss_date BETWEEN "+ begindate +" AND "+ enddate +" ";
		if(StringUtils.isNotBlank(customerid)){
			customerid = "'" + customerid + "'"; 
			sql = sql + " AND customid=" + customerid;
		}
		sql = sql + " GROUP BY loss_date ";
		return jdbcTemplate.queryForList(sql);
	}

	
	@Override
	public List<Map<String, Object>> userCycleBar(String virtu,String customerid) {
		String sql = " SELECT '1日' AS 'datearea',SUM(user_count) 'usercnt' "+
				" FROM user_cycle_"+ virtu +" "+
				" WHERE scope_time='1日' ";
		if(StringUtils.isNotBlank(customerid)){
			customerid = "'" + customerid + "'"; 
			sql = sql + " AND customid=" + customerid;
		}
		sql = sql + " UNION ALL "+
				" SELECT '2日~3日' AS 'datearea',SUM(user_count) 'usercnt' "+
				" FROM user_cycle_"+ virtu +" "+
				" WHERE (scope_time='2日' OR scope_time='3日') ";
		if(StringUtils.isNotBlank(customerid)){
			sql = sql + " AND customid=" + customerid;
		}
		sql = sql + " UNION ALL "+
				" SELECT '4日~7日' AS 'datearea',SUM(user_count) 'usercnt' "+
				" FROM user_cycle_"+ virtu +" "+
				" WHERE (scope_time='4日' OR scope_time='5日' OR scope_time='6日' OR scope_time='7日') ";
		if(StringUtils.isNotBlank(customerid)){
			sql = sql + " AND customid=" + customerid;
		}
		sql = sql + " UNION ALL "+
				" SELECT '8日~14日' AS 'datearea',SUM(user_count) 'usercnt' "+
				" FROM user_cycle_"+ virtu +" "+
				" WHERE (scope_time='8日' OR scope_time='9日' OR scope_time='10日' OR scope_time='11日'  OR scope_time='12日' OR scope_time='13日' OR scope_time='14日') ";
		if(StringUtils.isNotBlank(customerid)){
			sql = sql + " AND customid=" + customerid;
		}
		sql = sql + " UNION ALL "+
				" SELECT '15日~30日' AS 'datearea',SUM(user_count) 'usercnt' "+
				" FROM user_cycle_"+ virtu +" "+
				" WHERE scope_time='15-30日' ";
		if(StringUtils.isNotBlank(customerid)){
			sql = sql + " AND customid=" + customerid;
		}
		sql = sql + " UNION ALL "+
				" SELECT '31日~90日' AS 'datearea',SUM(user_count) 'usercnt' "+
				" FROM user_cycle_"+ virtu +" "+
				" WHERE scope_time='31-90日' ";
		if(StringUtils.isNotBlank(customerid)){
			sql = sql + " AND customid=" + customerid;
		}
		sql = sql + " UNION ALL "+
				" SELECT '91日~180日' AS 'datearea',SUM(user_count) 'usercnt' "+
				" FROM user_cycle_"+ virtu +" "+
				" WHERE scope_time='91-180日' ";
		if(StringUtils.isNotBlank(customerid)){
			sql = sql + " AND customid=" + customerid;
		}
		sql =sql + " UNION ALL "+
				" SELECT '181日~365日' AS 'datearea',SUM(user_count) 'usercnt' "+
				" FROM user_cycle_"+ virtu +" "+
				" WHERE scope_time='181-365日' ";
		if(StringUtils.isNotBlank(customerid)){
			sql = sql + " AND customid=" + customerid;
		}
		sql = sql + " UNION ALL "+
				" SELECT '365日+' AS 'datearea',SUM(user_count) 'usercnt' "+
				" FROM user_cycle_"+ virtu +" "+
				" WHERE scope_time='365日+' "; 
		if(StringUtils.isNotBlank(customerid)){
			sql = sql + " AND customid=" + customerid;
		}
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<Map<String, Object>> userCycleTable(String virtu,String customerid) {
		String sql = " SELECT t1.cycle,t1.cnt,CASE WHEN t1.cnt!=0 THEN ROUND(t1.cnt/t0.total,4) ELSE 0 END 'rate' "+
				" FROM ( "+
				" SELECT SUM(user_count) 'total' FROM user_cycle_"+ virtu +" WHERE 1=1 ";
		if(StringUtils.isNotBlank(customerid)){
			customerid = "'" + customerid + "'"; 
			sql = sql + " AND customid=" + customerid;
		}
		sql = sql + " )t0,( "+
				" 	SELECT '1日' AS 'cycle',SUM(user_count) 'cnt' "+
				" 	FROM user_cycle_"+ virtu +" "+
				" 	WHERE scope_time='1日' ";
		if(StringUtils.isNotBlank(customerid)){
			sql = sql + " AND customid=" + customerid;
		}
		sql = sql + " 	UNION ALL "+
				" 	SELECT '2日~3日' AS 'cycle',SUM(user_count) 'cnt' "+
				" 	FROM user_cycle_"+ virtu +" "+
				" 	WHERE (scope_time='2日' OR scope_time='3日') ";
		if(StringUtils.isNotBlank(customerid)){
			sql = sql + " AND customid=" + customerid;
		}
		sql = sql + " 	UNION ALL "+
				" 	SELECT '4日~7日' AS 'cycle',SUM(user_count) 'cnt' "+
				" 	FROM user_cycle_"+ virtu +" "+
				" 	WHERE (scope_time='4日' OR scope_time='5日' OR scope_time='6日' OR scope_time='7日') ";
		if(StringUtils.isNotBlank(customerid)){
			sql = sql + " AND customid=" + customerid;
		}
		sql = sql + " 	UNION ALL "+
				" 	SELECT '8日~14日' AS 'cycle',SUM(user_count) 'cnt' "+
				" 	FROM user_cycle_"+ virtu +" "+
				" 	WHERE (scope_time='8日' OR scope_time='9日' OR scope_time='10日' OR scope_time='11日'  OR scope_time='12日' OR scope_time='13日' OR scope_time='14日') ";
		if(StringUtils.isNotBlank(customerid)){
			sql = sql + " AND customid=" + customerid;
		}
		sql = sql + " 	UNION ALL "+
				" 	SELECT '15日~30日' AS 'cycle',SUM(user_count) 'cnt' "+
				" 	FROM user_cycle_"+ virtu +" "+
				" 	WHERE scope_time='15-30日' ";
		if(StringUtils.isNotBlank(customerid)){
			sql = sql + " AND customid=" + customerid;
		}
		sql = sql + " 	UNION ALL "+
				" 	SELECT '31日~90日' AS 'cycle',SUM(user_count) 'cnt' "+
				" 	FROM user_cycle_"+ virtu +" "+
				" 	WHERE scope_time='31-90日' ";
		if(StringUtils.isNotBlank(customerid)){
			sql = sql + " AND customid=" + customerid;
		}
		sql = sql + " 	UNION ALL "+
				" 	SELECT '91日~180日' AS 'cycle',SUM(user_count) 'cnt' "+
				" 	FROM user_cycle_"+ virtu +" "+
				" 	WHERE scope_time='91-180日' ";
		if(StringUtils.isNotBlank(customerid)){
			sql = sql + " AND customid=" + customerid;
		}
		sql = sql + " 	UNION ALL "+
				" 	SELECT '181日~365日' AS 'cycle',SUM(user_count) 'cnt' "+
				" 	FROM user_cycle_"+ virtu +" "+
				" 	WHERE scope_time='181-365日' ";
		if(StringUtils.isNotBlank(customerid)){
			sql = sql + " AND customid=" + customerid;
		}
		sql = sql + " 	UNION ALL "+
				" 	SELECT '365日+' AS 'cycle',SUM(user_count) 'cnt' "+
				" 	FROM user_cycle_"+ virtu +" "+
				"	WHERE scope_time='365日+' ";
		if(StringUtils.isNotBlank(customerid)){
			sql = sql + " AND customid=" + customerid;
		}
		sql = sql + " )t1 ";

		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<Map<String, Object>> userBack(String virtu, String begindate,String enddate,String customerid) {
		begindate = "'" + begindate + "'"; 
		enddate = "'" + enddate + "'"; 
		
		String sql = "SELECT back_date 'date',COUNT("+ virtu +") 'cnt' "+ 
				" FROM device_"+ virtu +"_log "+ 
				" WHERE back_date BETWEEN "+ begindate +" AND "+ enddate +" ";
		if(StringUtils.isNotBlank(customerid)){
			customerid = "'" + customerid + "'"; 
			sql = sql + " AND customid=" + customerid;
		}
		sql = sql + " GROUP BY back_date ";
		
		return jdbcTemplate.queryForList(sql);
	}
	
}
