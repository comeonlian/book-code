package com.game.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.game.smvc.entity.Test;

public class SessionUtils {

    /**
     * 获取ip
     */
    public static String findRealIp(HttpServletRequest request) {
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
     * 获取session为test的值
     */
    public static Test getSessionTest(HttpServletRequest request) {
        Test test = null;
        HttpSession session = request.getSession();
        if (session != null) {
            Object attributeTest = session.getAttribute("test");
            if (attributeTest instanceof Test) {
                test = (Test) attributeTest;
            }
        }
        return test;
    }

    public static void main(String[] args) {
        Object d = null;
        if (d instanceof Test) {
            System.out.println("yes");
        }
        System.out.println("finish");
    }
}
