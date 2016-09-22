package com.game.docking.ota.action;

import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.docking.ota.entity.VisitOta;
import com.game.docking.ota.entity.VisitQuestion;
import com.opensymphony.xwork2.ActionSupport;

public class OtaBaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private static final long serialVersionUID = 4612012020122109270L;
    private static final Logger LOGGER = LoggerFactory.getLogger(OtaBaseAction.class);

    public HttpServletRequest request;

    public HttpServletResponse response;

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

    /**
     * 打印所有头信息
     */
    @SuppressWarnings("rawtypes")
    public void printHeader() {
        System.out.println("===================================================================");
        Enumeration names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            System.out.println(name + ":" + request.getHeader(name));
        }
        System.out.println("now service sessionid :" + request.getSession().getId());
        System.out.println("===================================================================");
    }

    public VisitOta getVisitOta() {
        printHeader(); // TODO 打印header
        VisitOta visit = new VisitOta();
        LOGGER.info(request.getRequestURL().toString() + " " + request.getMethod() + " " + getAllParams());
        visit.setAccesstime(new Date());
        String ip = findRealIp();
        visit.setIp(ip);

        visit.setDeviceid(request.getParameter("pid")); // 设备ID
        visit.setCustomerid(request.getParameter("id")); // String customerid; // 客户id
        visit.setImsi(request.getParameter("IMSI")); // String imsi;
        visit.setSc(request.getParameter("SC")); // String sc;
        visit.setImsi1(request.getParameter("IMSI1")); // String imsi1;
        visit.setSc1(request.getParameter("SC1")); // String sc1;
        visit.setImsi2(request.getParameter("IMSI2")); // String imsi2;
        visit.setSc2(request.getParameter("SC2")); // String sc2;
        visit.setImsi3(request.getParameter("IMSI3")); // String imsi3;
        visit.setSc3(request.getParameter("SC3")); // String sc3;
        visit.setImsi4(request.getParameter("IMSI4")); // String imsi4;
        visit.setSc4(request.getParameter("SC4")); // String sc4;
        visit.setVer(request.getParameter("ver")); // String ver; // 计费版本
        visit.setPlat(request.getParameter("plat")); // String plat; // 平台
        visit.setPot(request.getParameter("pot")); // String pot; // 机器的累积待机时间（半小时为单位）
        visit.setEndian(request.getParameter("endian"));
        visit.setLasterror(request.getParameter("lasterror"));
        visit.setPlatver(request.getParameter("platver"));

        return visit;
    }

    /**
     * 答题访问
     */
    public VisitQuestion getVisitQuestion() {
        printHeader(); // TODO 打印header
        LOGGER.info(request.getRequestURL().toString() + " " + request.getMethod() + " " + getAllParams());
        VisitQuestion visit = new VisitQuestion();
        visit.setAccesstime(new Date());
        String ip = findRealIp();
        visit.setIp(ip);
        visit.setNum(request.getParameter("num"));
        visit.setVer(request.getParameter("ver"));
        visit.setPlat(request.getParameter("plat"));
        visit.setSms(request.getParameter("sms"));
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
     * @param args
     */
    public static void main(String[] args) {
        System.out.println((Integer) null);
        System.out.println((String) null);
        System.out.println(NumberUtils.toInt((String) null));
    }
}
