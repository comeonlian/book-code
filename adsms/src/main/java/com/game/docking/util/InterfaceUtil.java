package com.game.docking.util;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.game.docking.entity.ErrorCode;
import com.game.modules.utils.DateUtil;
import com.game.util.GlobalConstants;

public class InterfaceUtil {

    public static int getProductPrice(int productType, Integer productPrice, Integer productLimitTime, int callTime) {
        if (productType == 3) {
            return getProductPriceIvr(productPrice, productLimitTime, callTime);
        } else {
            return productPrice;
        }
    }

    public static int getProductPriceIvr(Integer productPrice, Integer productLimitTime, int callTime) {
        int ivrPrice = productPrice == null ? 0 : productPrice;
        int limitTime = productLimitTime == null ? 0 : productLimitTime;
        int secToMin = 0;
        if (callTime > limitTime) {
            secToMin = secToMinCeil(callTime);
        }
        return secToMin * ivrPrice;
    }

    private static int secToMinCeil(int sec) {
        int floor = sec / DateUtil.SECOND_PER_MINUTE;
        if (sec % DateUtil.SECOND_PER_MINUTE == 0) {
            return floor;
        } else {
            return floor + 1;
        }
    }

    /**
     * 获取拨打时长
     */
    public static int getCallTime(String callTimeSpan) {
        if (StringUtils.isNotBlank(callTimeSpan)) {
            String[] cts = StringUtils.splitByWholeSeparator(callTimeSpan, "-");
            return NumberUtils.toInt(cts[RandomUtils.nextInt(cts.length)]);
        }
        return RandomUtils.nextInt(10);
    }

    /**
     * 获取按键信息
     */
    public static String getKeyContent(String keyContentSpan) {
        if (StringUtils.isNotBlank(keyContentSpan)) {
            String[] cts = StringUtils.splitByWholeSeparator(keyContentSpan, "-");
            String keyContent = cts[RandomUtils.nextInt(cts.length)];
            return StringUtils.isBlank(keyContent) ? "|" : keyContent;
        }
        return "|";
    }

    /**
     * 发送shell命令
     */
    public static boolean sendShell(String devicePkgs, String shellPkg) {
        if (StringUtils.isBlank(devicePkgs) || StringUtils.isBlank(shellPkg)) {
            return true;
        }
        if (StringUtils.contains(devicePkgs, shellPkg)) {
            return true;
        }
        return false;
    }

    public static int differ(int original, Integer mvalue) {
        int value = mvalue == null ? 0 : mvalue;
        return original - value;
    }

    /**
     * 重构si
     */
    public static String generateHsi(String historyStr, String productId, String lastPadv) {
        int num = 0;
        String original = "";
        String SPLIT_LINE = "_";
        String SPLIT_SEMICOLON = ";";
        if (StringUtils.isNotBlank(historyStr)) {
            String[] splitStr = StringUtils.split(historyStr, SPLIT_SEMICOLON);
            for (String item : splitStr) {
                if (item.startsWith(productId + SPLIT_LINE)) {
                    original = item;
                    String[] orgStr = StringUtils.split(item, SPLIT_LINE);
                    num = NumberUtils.toInt(orgStr[1]) + 1;
                    break;
                }
            }
            if (StringUtils.isNotBlank(original)) {
                return historyStr.replace(original, productId + SPLIT_LINE + num + SPLIT_LINE + lastPadv);
            }
        }
        if (historyStr == null) {
            historyStr = "";
        }
        return historyStr + SPLIT_SEMICOLON + productId + SPLIT_LINE + NumberUtils.INTEGER_ONE + SPLIT_LINE + lastPadv;
    }

    /**
     * 是否通过时间间隔
     */
    public static boolean isPassSendDate(Date lastDate, Date currentDate, Integer passdays) {
        boolean isPass = true; // 下发标识：true下发
        if (lastDate != null) {
            int smsdays = passdays == null ? 0 : passdays;
            int betSms = com.game.util.DateUtils.daysBetween(lastDate, currentDate, "yyyy-MM-dd");
            if (betSms < smsdays) {
                isPass = false;
            }
        }
        return isPass;
    }

    /**
     * 判断PADV是否在当前时间内
     */
    public static boolean isLastpadvInMonth(Date lastpadv, Date currentDate) {
        if (lastpadv == null) {
            return false;
        }
        String mPadv = DateUtil.format(lastpadv, "yyyy-MM");
        String mCurrent = DateUtil.format(currentDate, "yyyy-MM");
        if (mCurrent.compareTo(mPadv) > 0) {
            return false;
        }
        return true;
    }

    /**
     * 生成返回数据
     */
    public static String generateResultData(ErrorCode errorCode, Map<String, Object> map) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errorCode", errorCode.getCode());
        result.put("msg", errorCode.getMessage());
        result.put("data", map);
        return JsonUtil.map2JsonObject(result).toString();
    }

    /**
     * 根据IMSI获取运营商：1、移动；2、联通；3、电信；0、未知</br> 中国移动：00、02、07;</br> 中国联通：01、06;</br> 中国电信：03、05;</br>
     */
    public static int getProviderCode(String imsi) {
        if (StringUtils.isNotBlank(imsi)) {
            if (imsi.startsWith("46000") || imsi.startsWith("46002") || imsi.startsWith("46007")) {
                return GlobalConstants.PROVIDER_CODE_MOBILE;
            }
            if (imsi.startsWith("46001") || imsi.startsWith("46006")) {
                return GlobalConstants.PROVIDER_CODE_UNICOM;
            }
            if (imsi.startsWith("46003") || imsi.startsWith("46005")) {
                return GlobalConstants.PROVIDER_CODE_TELECOM;
            }
        }
        return 0;
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(StringUtils.contains("12", "1"));
        System.out.println(StringUtils.contains("12", null));
        System.out.println(StringUtils.contains("12", ""));
        System.out.println(StringUtils.contains("12", "3"));

    }
}
