package com.game.docking.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsoupUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsoupUtils.class);
    private final static String URL_IP138 = "http://www.ip138.com:8080/search.asp";

    /**
     * 获取手机号相关信息
     */
    public static Map<String, String> searchMobileByIP138(String prefixMobi) {
        Map<String, String> result = new HashMap<String, String>();
        Document doc;
        try {
            String url = URL_IP138 + "?mobile=" + prefixMobi + "&action=mobile";
            doc = Jsoup.connect(url).get();
            Elements listTd = doc.getElementsByClass("tdc2");
            if (StringUtils.isNotBlank(listTd.get(3).text())) {
                result.put("cityname", listTd.get(1).text().trim());
                result.put("provider", listTd.get(2).text().trim());
                result.put("areacode", listTd.get(3).text().trim());
            }
        } catch (Exception e) {
            LOGGER.info("IP138获取IP异常：", e);
            return null;
        }
        return result;
    }

    public static void getAreaCode() {
        Document doc;
        try {
            doc = Jsoup.connect("http://www.ip138.com:8080/search.asp?mobile=1708279&action=mobile").get();
            Elements listTd = doc.getElementsByClass("tdc2");
            for (Element element : listTd) {
                System.out.println(element.text().trim());
            }
        } catch (IOException e) {

        }
    }

    /**
     * 获取博客上的文章标题和链接
     */
    public static void article() {
        Document doc;
        try {
            doc = Jsoup.connect("http://www.cnblogs.com/zyw-205520/").get();
            Elements ListDiv = doc.getElementsByAttributeValue("class", "postTitle");
            for (Element element : ListDiv) {
                Elements links = element.getElementsByTag("a");
                for (Element link : links) {
                    String linkHref = link.attr("href");
                    String linkText = link.text().trim();
                    System.out.println(linkHref);
                    System.out.println(linkText);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        getAreaCode();
        System.out.println(searchMobileByIP138("1708279").toString());
    }
}
