package com.game.docking.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.game.comm.entity.City;
import com.game.comm.service.OscacheManager;
import com.game.comm.service.impl.OscacheManagerImpl;
import com.game.docking.entity.ErrorCode;
import com.game.docking.entity.VisitAdsms;
import com.game.docking.service.DockingGeneralManager;
import com.game.docking.util.JsonUtil;
import com.game.docking.util.pkg.Packet;
import com.game.docking.util.pkg.PacketFactory;
import com.game.docking.util.pkg.PacketUtils;
import com.game.util.SessionUtils;
import com.game.util.ip.sina.JsonIPInfo;
import com.game.util.ip.sina.SinaIpUtils;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private static final long serialVersionUID = 4612012020122109270L;
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseAction.class);

    public HttpServletRequest request;

    public HttpServletResponse response;

    private OscacheManager oscacheManager = new OscacheManagerImpl();
    @Autowired
    private DockingGeneralManager dockingGeneralManager;

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    /**
     * 获取所有头信息
     */
    public String getAllParams() {
        Map<String, String[]> params = request.getParameterMap();
        String queryString = "";
        for (String key : params.keySet()) {
            String[] values = params.get(key);
            for (int i = 0; i < values.length; i++) {
                String value = values[i];
                queryString += key + "=" + value + "&";
            }
        }
        // 去掉最后一个空格
        if (StringUtils.isNotBlank(queryString))
            queryString = queryString.substring(0, queryString.length() - 1);
        return queryString;
    }

    public VisitAdsms getVisitAdsms() {
        String sessionId = request.getSession().getId();
        LOGGER.info(" URL: " + request.getRequestURL().toString() + " params:" + getAllParams());
        VisitAdsms visit = new VisitAdsms();
        visit.setSessionid(sessionId);
        visit.setAccesstime(new Date());
        String ip = SessionUtils.findRealIp(request);
        visit.setIp(ip);
        JsonIPInfo info = SinaIpUtils.getAddress(ip);
        if (info != null) {
            City city = dockingGeneralManager.getByJsonIPInfo(info);
            if (city != null) {
                visit.setCountryId(city.getId());
                visit.setCountryName(city.getName());
            } else {
                visit.setCountryId(19999L);
                visit.setCountryName("其他");
            }
        } else {
            Map<String, Object> cityMap = getCity(ip);
            if (!CollectionUtils.isEmpty(cityMap)) {
                Long cityid = (Long) cityMap.get("cityCode");
                if (cityid == null || cityid == 0L) {
                    visit.setCountryId(19999L);
                    visit.setCountryName("其他");
                } else {
                    visit.setCountryId(cityid);
                    visit.setCountryName((String) cityMap.get("cityName"));
                }
            }
        }
        InputStream in = null;
        try {
            in = request.getInputStream();
        } catch (IOException e1) {
            LOGGER.info("获取流异常：{}", e1);
            return null;
        }
        int contentLength = request.getContentLength();
        byte[] data = PacketUtils.entityToBytes(in, contentLength);
        Packet packet = null;
        boolean isgzip = StringUtils.isBlank(request.getHeader("ISGZIP")) ? false : Boolean.valueOf(request.getHeader("ISGZIP"));
        packet = PacketFactory.createFromNet(data, isgzip);
        visit.setGzip(isgzip);
        if (packet == null) {
            LOGGER.info("获取的包体为空！");
            return null;
        }
        String originalData = new String(packet.originalData);
        LOGGER.info("原始数据：" + originalData); // TODO
        Map<String, Object> map = JsonUtil.parseJSON2Map(originalData);
        String mac = map.get("mac") == null ? "" : map.get("mac").toString();
        visit.setMac(mac);
        String imei = map.get("imei") == null ? "" : map.get("imei").toString();
        visit.setImei(imei);
        String imsi = (String) map.get("imsi");
        visit.setImsi(imsi);
        String androidid = map.get("aid") == null ? "" : map.get("aid").toString();
        visit.setAndroidid(androidid);
        String customerid = (String) map.get("cid"); // 客户id
        visit.setCustomerid(customerid);
        String osVersionCode = map.get("ovc") == null ? "" : map.get("ovc").toString(); // 手机系统版本号
        visit.setOsVersionCode(osVersionCode);
        String osVersionName = (String) map.get("ovn"); // 手机系统版本名
        visit.setOsVersionName(osVersionName);
        String inline = map.get("inl") == null ? "" : map.get("inl").toString(); // 平台是否内置 1：内置 0：否
        visit.setInline(inline);
        String langCountry = (String) map.get("lg"); // 系统语言国家编码
        visit.setLangCountry(langCountry);
        String netType = map.get("nt") == null ? "" : map.get("nt").toString(); // 联网方式
        visit.setNetType(netType);
        String cpu = (String) map.get("pn"); // CPU型号
        visit.setCpu(cpu);
        String romLess = map.get("rl") == null ? "" : map.get("rl").toString(); // ROM剩余空间
        visit.setRomLess(romLess);
        String sdcardLess = map.get("sdl") == null ? "" : map.get("sdl").toString(); // sdcar剩余空间
        visit.setSdcardLess(sdcardLess);
        String romTotal = map.get("rt") == null ? "" : map.get("rt").toString(); // ROM 总大小
        visit.setRomTotal(romTotal);
        String sdcardTotal = map.get("sdt") == null ? "" : map.get("sdt").toString(); // sdcard 总大小
        visit.setSdcardTotal(sdcardTotal);
        String appVersionCode = map.get("vc") == null ? "" : map.get("vc").toString(); // app版本号
        visit.setAppVersionCode(appVersionCode);
        String appVersionName = (String) map.get("vn"); // app版本名
        visit.setAppVersionName(appVersionName);
        String initVersionName = (String) map.get("ivn"); // 启动应用版本名
        visit.setInitVersionName(initVersionName);
        String initPackageName = (String) map.get("ipkg"); // 启动应用包名
        visit.setInitPackageName(initPackageName);
        String wifi = map.get("wf") == null ? "" : map.get("wf").toString(); // 是否wifi
        visit.setWifi(wifi);
        String src = (String) map.get("src"); // 手机分辨率
        visit.setSrc(src);
        String brand = (String) map.get("brd"); // 手机商标
        visit.setBrand(brand);
        String modle = (String) map.get("mdl"); // 手机型号
        visit.setModle(modle);
        String mobileCurrentTime = map.get("ct") == null ? "" : map.get("ct").toString(); // 手机当前时间
        visit.setMobileCurrentTime(mobileCurrentTime);
        String sign = map.get("sign") == null ? "" : map.get("sign").toString(); // 是否有签名权限
        visit.setSign(sign);
        if (StringUtils.isNotBlank(mac) || StringUtils.isNotBlank(imei) || StringUtils.isNotBlank(androidid)) {
            String deviceId = mac + "_" + imei + "_" + androidid;
            visit.setDeviceid(deviceId);
        }
        return visit;
    }

    /**
     * 获取地市(如果找不到，默认为0L)
     */
    public Map<String, Object> getCity(String ip) {
        Map<String, Object> map = oscacheManager.findCityByCDN(ip);
        Long cityCode = (Long) map.get("cityCode");
        if (cityCode == null) {
            map.put("cityCode", 0L);
        }
        return map;
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
     * @param args
     */
    public static void main(String[] args) {
        System.out.println((Integer) null);
        System.out.println((String) null);
        System.out.println(NumberUtils.toInt((String)null));
    }
}
