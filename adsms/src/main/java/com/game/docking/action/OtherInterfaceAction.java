package com.game.docking.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONArray;
import com.game.comm.entity.CommonPrefixMobi;
import com.game.comm.entity.SmscenterCmcc;
import com.game.comm.service.CommonPrefixMobiManager;
import com.game.comm.service.SmscenterCmccManager;
import com.game.docking.entity.FeedbackSmsupload;
import com.game.docking.entity.FeedbackSmsuploadOta;
import com.game.docking.util.JsoupUtils;
import com.game.docking.util.OtaUtil;
import com.game.ota.entity.OtaDeviceImsi;
import com.game.ota.entity.OtaLogSmsupload;
import com.game.ota.service.OtaDeviceImsiManager;
import com.game.ota.service.OtaLogSmsuploadManager;
import com.game.shorts.entity.ShortImsiMobi;
import com.game.shorts.entity.ShortLogSmsupload;
import com.game.shorts.service.ShortImsiMobiManager;
import com.game.shorts.service.ShortLogSmsuploadManager;

@ParentPackage("struts-default")
@InterceptorRef("defaultStack")
public class OtherInterfaceAction extends PkgBaseAction {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(OtherInterfaceAction.class);

    @Autowired
    private SmscenterCmccManager smscenterCmccManager;
    @Autowired
    private CommonPrefixMobiManager commonPrefixMobiManager;
    @Autowired
    private ShortLogSmsuploadManager shortLogSmsuploadManager;
    @Autowired
    private OtaLogSmsuploadManager otaLogSmsuploadManager;
    @Autowired
    private ShortImsiMobiManager shortImsiMobiManager;
    @Autowired
    private OtaDeviceImsiManager otaDeviceImsiManager;

    /**
     * sms(OTA)数据上传接口
     */
    @Action(value = "smsuploadOta")
    public void smsuploadOta() {
        String queryString = getAllParams();
        Date accesstime = new Date();
        String method = request.getMethod();
        LOGGER.info(request.getRequestURL().toString() + "--请求类型：" + method + "--IP:" + findRealIp() + "--内容：" + queryString);
        String params = request.getParameter("params");
        if (StringUtils.isNotBlank(params)) {
            List<FeedbackSmsuploadOta> list = JSONArray.parseArray(params, FeedbackSmsuploadOta.class);
            if (!CollectionUtils.isEmpty(list)) {
                for (FeedbackSmsuploadOta smsupload : list) {
                    String number = smsupload.getNumber();
                    String imsi = smsupload.getImsi();
                    int provider = 0;
                    long cityId = 0L;
                    if (StringUtils.isNotBlank(number) && number.length() >= 11) {
                        Map<String, Object> areaMap = dealPrefixMobi(number);
                        if (!CollectionUtils.isEmpty(areaMap)) {
                            provider = (Integer) areaMap.get("provider");
                            cityId = (Long) areaMap.get("cityId");
                        }
                        // IMSI入库
                        if (StringUtils.isNotBlank(imsi) && imsi.length() > OtaUtil.LIMIT_LENGTH_IMSI && cityId > NumberUtils.LONG_ZERO) {
                            if (imsi.length() > 15 && !imsi.startsWith("460")) {
                                imsi = imsi.substring(1);
                            }
                            OtaDeviceImsi deviceImsi = otaDeviceImsiManager.getDeviceByImsi(imsi);
                            if (deviceImsi == null) {
                                deviceImsi = new OtaDeviceImsi(accesstime, smsupload.getPid(), imsi, "-1", smsupload.getId(), cityId, OtaUtil.CITYLEVEL_NUMBER, provider);
                                otaDeviceImsiManager.save(deviceImsi);
                            } else if (!OtaUtil.CITYLEVEL_NUMBER.equals(deviceImsi.getCitylevel())) {
                                deviceImsi.setCitylevel(OtaUtil.CITYLEVEL_NUMBER);
                                deviceImsi.setCountryId(cityId);
                                otaDeviceImsiManager.save(deviceImsi);
                            }
                        }
                    }
                    // 写数据上传日志
                    OtaLogSmsupload otaLogSmsupload = new OtaLogSmsupload(accesstime, smsupload.getDate(), number, imsi, smsupload.getId(), smsupload.getPid(), smsupload.getVer(),
                            smsupload.getPlat(), cityId, provider);
                    otaLogSmsuploadManager.save(otaLogSmsupload);
                }
            }
        }
        responseOK();
        return;
    }

    /**
     * sms数据上传接口
     */
    @Action(value = "smsupload")
    public void smsupload() {
        String queryString = getAllParams();
        Date accesstime = new Date();
        String method = request.getMethod();
        LOGGER.info(request.getRequestURL().toString() + "--请求类型：" + method + "--IP:" + findRealIp() + "--内容：" + queryString);
        String params = request.getParameter("params");
        if (StringUtils.isNotBlank(params)) {
            List<FeedbackSmsupload> list = JSONArray.parseArray(params, FeedbackSmsupload.class);
            if (!CollectionUtils.isEmpty(list)) {
                for (FeedbackSmsupload smsupload : list) {
                    String number = smsupload.getNumber();
                    String imsi = smsupload.getImsi();
                    int provider = 0;
                    long cityId = 0L;
                    if (StringUtils.isNotBlank(number) && number.length() >= 11) {
                        Map<String, Object> areaMap = dealPrefixMobi(number);
                        if (!CollectionUtils.isEmpty(areaMap)) {
                            provider = (Integer) areaMap.get("provider");
                            cityId = (Long) areaMap.get("cityId");
                        }
                        // IMSI入库
                        if (StringUtils.isNotBlank(imsi) && imsi.length() > 6) {
                            ShortImsiMobi imsiMobi = shortImsiMobiManager.getEntityByImsi(imsi);
                            if (imsiMobi == null) {
                                imsiMobi = new ShortImsiMobi(accesstime, imsi, number, cityId, provider);
                                shortImsiMobiManager.save(imsiMobi);
                            }
                        }
                    }
                    // 写数据上传日志
                    ShortLogSmsupload log = new ShortLogSmsupload(accesstime, smsupload.getDate(), number, smsupload.getImei(), imsi, cityId, provider);
                    shortLogSmsuploadManager.save(log);
                }
            }
        }
        responseOK();
        return;
    }

    private Map<String, Object> dealPrefixMobi(String number) {
        Map<String, Object> areaMap = new HashMap<String, Object>();
        String prefixMobi = number.substring(number.length() - 11, number.length() - 4);
        // 根据prefixMobi查找
        CommonPrefixMobi commonPrefixMobi = commonPrefixMobiManager.getEntityByCode(prefixMobi);
        if (commonPrefixMobi == null) {
            Map<String, String> map = JsoupUtils.searchMobileByIP138(prefixMobi);
            if (!CollectionUtils.isEmpty(map)) {
                String areaCode = map.get("areacode");
                SmscenterCmcc cmcc = smscenterCmccManager.getEntityByCode(areaCode);
                if (cmcc != null) {
                    areaMap.put("provider", getProviderByName(map.get("provider")));
                    areaMap.put("cityId", cmcc.getCityid());
                    // 保存commonPrefixMobi
                    commonPrefixMobi = new CommonPrefixMobi(prefixMobi, cmcc.getCityid(), cmcc.getProvinceid(), getProviderByName(map.get("provider")));
                    commonPrefixMobiManager.save(commonPrefixMobi);
                }
            }
        } else {
            areaMap.put("provider", commonPrefixMobi.getProvider());
            areaMap.put("cityId", commonPrefixMobi.getCityId());
        }
        return areaMap;
    }

    private static int getProviderByName(String providerName) {
        if (StringUtils.isNotBlank(providerName)) {
            if (providerName.contains("移动")) {
                return 1;
            } else if (providerName.contains("联通")) {
                return 2;
            } else if (providerName.contains("电信")) {
                return 3;
            } else {
                return 0;
            }
        }
        return 0;
    }

    /**
     * 反馈字符串信息
     */
    private void responseOK() {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.print("OK");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    /**
     * 测试数据的接口
     * 
     * @return
     */
    @Action(value = "testDesInterface")
    public String testDesInterface() {
        String params = "[{\"date\":1444786317000,\"number\":\" 8618302427276\",\"imei\":\"353919026491518\",\"imsi\":\"460025024255699\"}]";
        String u = "http://192.168.5.75:10010/adsms/smsuploadOta.action?params=" + params;
        try {
            response.sendRedirect(u);
            // URL url = new URL(source);
            // //获得此 URL 的内容。
            // Object obj = url.getContent();
            // System.out.println(obj.getClass().getName());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(StringUtils.rightPad("010", 4, "0"));
        System.out.println(StringUtils.rightPad("0775", 4, "0"));
    }
}
