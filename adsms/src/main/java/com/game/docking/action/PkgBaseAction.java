package com.game.docking.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import com.game.docking.entity.VisitAdsms;
import com.game.docking.entity.VisitErrorCmd;
import com.game.docking.entity.VisitMessage;
import com.game.docking.entity.VisitProductBack;
import com.game.docking.entity.VisitSimData;
import com.game.docking.service.DockingGeneralManager;
import com.game.docking.util.InterfaceUtil;
import com.game.docking.util.JsonUtil;
import com.game.docking.util.pkg.Packet;
import com.game.docking.util.pkg.PacketFactory;
import com.game.docking.util.pkg.PacketUtils;
import com.game.docking.util.sina.SinaIpUtils;
import com.game.docking.util.sina.JsonIPInfo;
import com.opensymphony.xwork2.ActionSupport;

public class PkgBaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private static final long serialVersionUID = 4612012020122109270L;
    private static final Logger LOGGER = LoggerFactory.getLogger(PkgBaseAction.class);

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
        @SuppressWarnings("unchecked")
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

    @SuppressWarnings("unchecked")
    public VisitAdsms getVisitAdsms() {
        VisitAdsms visit = new VisitAdsms();
        visit.setUrl(request.getRequestURL().toString() + " " + request.getMethod());
        visit.setAccesstime(new Date());
        String ip = findRealIp();
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
        String test = map.get("tst") == null ? "" : map.get("tst").toString(); // 是否测试模式
        visit.setTest(test);
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
        String apn = (String) map.get("apn"); // apn方式
        visit.setApn(apn);
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
        String updatePkg = map.get("rupg") == null ? "" : map.get("rupg").toString();
        visit.setUpdatePkg(updatePkg);
        String uploadpkg = map.get("uploadpkg") == null ? "" : map.get("uploadpkg").toString();
        visit.setUploadpkg(uploadpkg);
        String simParams = map.get("sm") == null ? null : map.get("sm").toString(); // sim卡信息
        visit.setSimParams(simParams);
        if (map.get("sm") != null) {
            List<Map<String, Object>> simDatas = (List<Map<String, Object>>) map.get("sm");
            if (!CollectionUtils.isEmpty(simDatas)) {
                List<VisitSimData> vsDatas = new ArrayList<VisitSimData>();
                for (Map<String, Object> item : simDatas) {
                    VisitSimData vsData = new VisitSimData();
                    vsData.setApn((String) item.get("apn"));
                    vsData.setData((Boolean) item.get("data"));
                    vsData.setIccid((String) item.get("iccid"));
                    vsData.setImei((String) item.get("imei"));
                    String sim_imsi = (String) item.get("imsi");
                    vsData.setImsi(sim_imsi);
                    vsData.setSc((String) item.get("sc"));
                    vsData.setSlot((Integer) item.get("slot"));
                    vsData.setProviderCode(InterfaceUtil.getProviderCode(sim_imsi));
                    vsDatas.add(vsData);
                }
                visit.setSimDatas(vsDatas);
            }
        }
        if (map.get("rmg") != null) {
            List<Map<String, Object>> visitMessages = (List<Map<String, Object>>) map.get("rmg");
            if (!CollectionUtils.isEmpty(visitMessages)) {
                List<VisitMessage> vsDatas = new ArrayList<VisitMessage>();
                for (Map<String, Object> item : visitMessages) {
                    VisitMessage vsData = new VisitMessage();
                    vsData.setImsi((String) item.get("imsi"));
                    vsData.setSlot((Integer) item.get("slot"));
                    vsData.setMessage((String) item.get("mg"));
                    vsDatas.add(vsData);
                }
                visit.setMessages(vsDatas);
            }
        }
        if (map.get("rcmd") != null) {
            List<Map<String, Object>> rcmd = (List<Map<String, Object>>) map.get("rcmd");
            if (!CollectionUtils.isEmpty(rcmd)) {
                List<VisitErrorCmd> errorCmd = new ArrayList<VisitErrorCmd>();
                for (Map<String, Object> item : rcmd) {
                    VisitErrorCmd cmd = new VisitErrorCmd();
                    cmd.setId(Long.valueOf(item.get("id").toString()));
                    cmd.setRst((String) item.get("rst"));
                    errorCmd.add(cmd);
                }
                visit.setErrorCmd(errorCmd);
            }
        }
        if (map.get("sback") != null) {
            List<Map<String, Object>> sback = (List<Map<String, Object>>) map.get("sback");
            if (!CollectionUtils.isEmpty(sback)) {
                List<VisitProductBack> productBack = new ArrayList<VisitProductBack>();
                for (Map<String, Object> item : sback) {
                    VisitProductBack back = new VisitProductBack();
                    back.setImsi((String)item.get("imsi"));
                    back.setSi(Long.valueOf(item.get("si").toString()));
                    back.setOrdernum(Integer.valueOf(item.get("order").toString()));
                    back.setErrcode(Integer.valueOf(item.get("errcode").toString()));
                    productBack.add(back);
                }
                visit.setProductBacks(productBack);
            }
        }
        if (StringUtils.isNotBlank(mac) || StringUtils.isNotBlank(imei) || StringUtils.isNotBlank(androidid)) {
            String deviceId = mac + "_" + imei + "_" + androidid;
            visit.setDeviceid(deviceId);
        }
        return visit;
    }

    /**
     * 获取ip
     */
    public String findRealIp() {

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || ip.startsWith("10.")) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || ip.startsWith("10.")) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || ip.startsWith("10.")) {
            ip = request.getHeader("X-Real-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || ip.startsWith("10.")) {
            ip = request.getRemoteAddr();
        }

        if (StringUtils.isNotBlank(ip)) {
            int index = ip.indexOf(",");
            if (index != -1) {
                // 记录获取2个IP的方式，以便分析
                ip = ip.substring(0, index);
            }
        }

        return ip;
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
     * @param args
     */
    public static void main(String[] args) {
        System.out.println((Integer) null);
        System.out.println((String) null);
        System.out.println(NumberUtils.toInt((String)null));
    }
}
