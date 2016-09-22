package com.game.util;

import com.game.util.PropertiesUtil;

public class Constants {

    // ***************** 作业相关监控 *********************
    /** 作业执行开始：JOB_RUNNING_BEGIN = 1 */
    public static final int JOB_RUNNING_BEGIN = 1;
    /** 作业执行结束：JOB_RUNNING_END = 2 */
    public static final int JOB_RUNNING_END = 2;
    // ***************** 作业相关监控 *********************

    // ***************** 监控：无卡信息
    public static final int RUNLEVEL_INTERCEPT_SM_NULL = -1;
    // ***************** 监控：客户拦截
    public static final int RUNLEVEL_INTERCEPT_CUSTOMER_NULL = 1;
    public static final int RUNLEVEL_INTERCEPT_CUSTOMER_STATUS = 2;
    public static final int RUNLEVEL_INTERCEPT_CUSTOMER_TIME = 3;
    public static final int RUNLEVEL_INTERCEPT_CUSTOMER_CITY = 4;
    public static final int RUNLEVEL_INTERCEPT_CUSTOMER_PROVIDER = 5;

    // ***************** 监控：padv
    /** 卡0月值拦截 */
    public static final int RUNLEVEL_INTERCEPT_PADV_MVALUE_IMSI0 = 8;
    /** 卡1月值拦截 */
    public static final int RUNLEVEL_INTERCEPT_PADV_MVALUE_IMSI1 = 9;
    /** 单卡：卡0时间间隔拦截 */
    public static final int RUNLEVEL_INTERCEPT_PADV_IMSI0_TIME = 11;
    /** 单卡：卡0没有符合条件的数据 */
    public static final int RUNLEVEL_INTERCEPT_PADV_IMSI0_NODATA = 12;

    public static final int RUNLEVEL_INTERCEPT_PADV_IMSI1_TIME = 21;
    public static final int RUNLEVEL_INTERCEPT_PADV_IMSI1_NODATA = 22;
    /** 没有符合条件的padv */
    public static final int RUNLEVEL_INTERCEPT_PADV_NODATA = 32;

    // ***************** 监控：成功
    public static final int RUNLEVEL_SUCCESS_IMSI0 = 1000;
    public static final int RUNLEVEL_SUCCESS_IMSI1 = 1001;

    /** 合作客户编码：DOMAIN_PARTNER = "c_paner" */
    public static final String DOMAIN_PARTNER = "c_paner";

    /** 商务编码：DOMAIN_BUSINESS = "c_business" */
    public static final String DOMAIN_BUSINESS = "c_business";

    public static final String CONSTANT_FILENAME = "proconstants.properties";
    /*
     * properties 常量对象
     */
    public static final PropertiesUtil CONS_PROPERTIES_PRO = new PropertiesUtil(CONSTANT_FILENAME);

    /*
     * properties 常量对象
     */
    public static final PropertiesUtil CONS_PROPERTIES = new PropertiesUtil(CONSTANT_FILENAME);

    /**
     * 文件存储总目录
     * 
     * @return
     */
    public static String getFileDir() {
        return CONS_PROPERTIES_PRO.getValue("filedir");
    }

    /**
     * 文件访问的baseurl
     * 
     * @return
     */
    public static String getImageurl() {
        return CONS_PROPERTIES_PRO.getValue("imageurl");
    }

}
