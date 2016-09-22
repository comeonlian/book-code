package com.game.copartner.quartz3;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.game.modules.utils.DateUtil;
import com.game.shorts.entity.ShortCustomer;
import com.game.shorts.service.ShortCustomerManager;
import com.game.util.Constants;

@Service("dailyJobService")
public class DailyJobService {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private ShortCustomerManager shortCustomerManager;

    public void job1() {
        System.out.println(new Date() + "任务进行中。。。");
    }

    public void initProviderDaily() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        insertJobExeLog(jdbcTemplate, Constants.JOB_RUNNING_BEGIN);
        System.out.println(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss") + "  开始执行初始化开放客户数据：");
        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT IGNORE INTO co_provider_daily(customer_id,currentdate,realnum,opennum,create_time,status,unitprice)                                           ")
                .append(" SELECT * FROM (  SELECT pc.customerid customid,bb.accessdate,bb.realnum,CASE WHEN pc.openrate is NULL THEN bb.realnum ELSE CEIL(bb.realnum * pc.openrate / 100) END opennum,NOW(),0,pc.unitprice FROM   ")
                .append(" (SELECT customid,countryid,DATE(accesstime) accessdate,count(deviceid)realnum from co_device WHERE 1=1 AND accesstime<CURDATE() and accesstime>(CURDATE() - interval 30 day)  ")
                .append(" GROUP BY customid,DATE(accesstime),countryid) bb   LEFT JOIN  short_customer pc ON pc.customerid=bb.customid ) hh WHERE hh.customid is not null ");

        int result = jdbcTemplate.update(sb.toString());
        insertJobExeLog(jdbcTemplate, Constants.JOB_RUNNING_END);
        System.out.println(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss") + "  执行开放客户数据结束！执行成功：" + result);
    }

    /**
     * 客户永久黑名单状态变更
     */
    public void convertCustomer() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        insertJobExeLog(jdbcTemplate, Constants.JOB_RUNNING_BEGIN);
        System.out.println(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss") + "  开始执行客户最少启用数job：");
        List<ShortCustomer> customers = shortCustomerManager.getAllByPassdevice(NumberUtils.INTEGER_ZERO);
        for (ShortCustomer customer : customers) {
            String customerid = customer.getCustomerid();
            int devPasscount = jdbcTemplate.queryForInt(" SELECT count(*) sum FROM short_device where customerid=?", customerid);
            int cusPasscount = customer.getDevicecount() == null ? 0 : customer.getDevicecount();
            if (devPasscount >= cusPasscount) {
                // 设置永久黑名单
                int blacklist = jdbcTemplate.update(" update short_device set status=0 where customerid=? limit ? ", customerid, cusPasscount);
                customer.setPassdevice(1);
                shortCustomerManager.save(customer);
                // 写数据库日志
                jdbcTemplate.update("INSERT INTO job_log_passdevice(runtime,customerid,blacklist) values(?,?,?)", new Date(), customerid, blacklist);
                insertJobExeLog(jdbcTemplate, Constants.JOB_RUNNING_END);
                System.out.println("----客户ID：" + customerid + "  启用数状态变更成功；");
            }
        }
        System.out.println(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss") + "  执行客户最少启用数job结束！");
    }

    /**
     * 释放黑名单
     */
    public void freeDevice() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        insertJobExeLog(jdbcTemplate, Constants.JOB_RUNNING_BEGIN);
        System.out.println(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss") + "  释放黑名单job执行开始：");
        List<ShortCustomer> customers = shortCustomerManager.getAllByPassdevice(NumberUtils.INTEGER_ONE);
        for (ShortCustomer customer : customers) {
            int deviceDay = customer.getDeviceday() == null ? 0 : customer.getDeviceday();
            StringBuilder sb = new StringBuilder();
            sb.append(" update short_device ").append(" set black=1 ").append(" where customerid=? and black=0  and  DATEDIFF( now(),accesstime)>=? ");
            int result = jdbcTemplate.update(sb.toString(), customer.getCustomerid(), deviceDay);
            if (result > 0) {
                System.out.println("++++客户ID：" + customer.getCustomerid() + "  释放黑名单数为：" + result);
            }
        }
        insertJobExeLog(jdbcTemplate, Constants.JOB_RUNNING_END);
        System.out.println(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss") + "  释放黑名单job执行结束！");
    }

    /**
     * 执行脚本日志入库
     * 
     * @param jobRunning
     */
    public void insertJobExeLog(JdbcTemplate jdbcTemplate, int jobRunning) {
        String clazzName = Thread.currentThread().getStackTrace()[2].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        jdbcTemplate.update("insert into job_log_exe(clazz_name,method_name,type,runtime) values(?,?,?,?)", clazzName, methodName, jobRunning, new Date());
    }
}
