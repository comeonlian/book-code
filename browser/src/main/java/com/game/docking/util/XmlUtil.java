package com.game.docking.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XmlUtil {
    public void readStringXml(String xml) {
        Document doc = null;
        try {
            // 读取并解析XML文档
            // SAXReader就是一个管道，用一个流的方式，把xml文件读出来
            // SAXReader reader = new SAXReader(); //User.hbm.xml表示你要解析的xml文档
            // Document document = reader.read(new File("User.hbm.xml"));
            // 下面的是通过解析xml字符串的
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML
            Element rootElt = doc.getRootElement(); // 获取根节点
            System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
            Iterator<?> iter = rootElt.elementIterator("head"); // 获取根节点下的子节点head
            // 遍历head节点
            while (iter.hasNext()) {
                Element recordEle = (Element) iter.next();
                String title = recordEle.elementTextTrim("title"); // 拿到head节点下的子节点title值

                System.out.println("title:" + title);

                Iterator<?> iters = recordEle.elementIterator("script"); // 获取子节点head下的子节点script

                // 遍历Header节点下的Response节点

                while (iters.hasNext()) {
                    Element itemEle = (Element) iters.next();
                    String username = itemEle.elementTextTrim("username"); // 拿到head下的子节点script下的字节点username的值
                    String password = itemEle.elementTextTrim("password");
                    System.out.println("username:" + username);
                    System.out.println("password:" + password);
                }
            }
            Iterator<?> iterss = rootElt.elementIterator("body"); // /获取根节点下的子节点body
            // 遍历body节点
            while (iterss.hasNext()) {
                Element recordEless = (Element) iterss.next();
                String result = recordEless.elementTextTrim("result"); // 拿到body节点下的子节点result值
                System.out.println("result:" + result);
                Iterator<?> itersElIterator = recordEless.elementIterator("form"); // 获取子节点body下的子节点form
                // 遍历Header节点下的Response节点
                while (itersElIterator.hasNext()) {
                    Element itemEle = (Element) itersElIterator.next();
                    String banlce = itemEle.elementTextTrim("banlce"); // 拿到body下的子节点form下的字节点banlce的值
                    String subID = itemEle.elementTextTrim("subID");
                    System.out.println("banlce:" + banlce);
                    System.out.println("subID:" + subID);
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @description 将xml字符串转换成map
     * @param xml
     * @return Map
     */
    public static Map<String, String> readStringXmlOut(String xml) {
        Map<String, String> map = new HashMap<String, String>();
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML
            Element rootElt = doc.getRootElement(); // 获取根节点
            System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
            Iterator<?> iter = rootElt.elementIterator("head"); // 获取根节点下的子节点head
            // 遍历head节点
            while (iter.hasNext()) {
                Element recordEle = (Element) iter.next();
                String title = recordEle.elementTextTrim("title"); // 拿到head节点下的子节点title值
                System.out.println("title:" + title);
                map.put("title", title);
                Iterator<?> iters = recordEle.elementIterator("script"); // 获取子节点head下的子节点script
                // 遍历Header节点下的Response节点
                while (iters.hasNext()) {
                    Element itemEle = (Element) iters.next();
                    String username = itemEle.elementTextTrim("username"); // 拿到head下的子节点script下的字节点username的值
                    String password = itemEle.elementTextTrim("password");
                    System.out.println("username:" + username);
                    System.out.println("password:" + password);
                    map.put("username", username);
                    map.put("password", password);
                }
            }
            Iterator<?> iterss = rootElt.elementIterator("body"); // /获取根节点下的子节点body
            // 遍历body节点
            while (iterss.hasNext()) {
                Element recordEless = (Element) iterss.next();
                String result = recordEless.elementTextTrim("result"); // 拿到body节点下的子节点result值
                System.out.println("result:" + result);
                Iterator<?> itersElIterator = recordEless.elementIterator("form"); // 获取子节点body下的子节点form
                // 遍历Header节点下的Response节点
                while (itersElIterator.hasNext()) {
                    Element itemEle = (Element) itersElIterator.next();
                    String banlce = itemEle.elementTextTrim("banlce"); // 拿到body下的子节点form下的字节点banlce的值
                    String subID = itemEle.elementTextTrim("subID");
                    System.out.println("banlce:" + banlce);
                    System.out.println("subID:" + subID);
                    map.put("result", result);
                    map.put("banlce", banlce);
                    map.put("subID", subID);
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 读取DDO的反馈结果集
     */
    public static List<Map<String, Object>> readStringXmlDDO(String xml) {
        if (StringUtils.isBlank(xml)) {
            return null;
        }
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml);
            Element rootElt = doc.getRootElement(); // 获取根节点
            System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
            Iterator<?> iter = rootElt.elementIterator("message"); // 获取根节点下的子节点message
            // 遍历head节点
            while (iter.hasNext()) {
                Map<String, Object> map = new HashMap<String, Object>();
                Element recordMessage = (Element)iter.next();
                map.put("orderid", recordMessage.elementTextTrim("orderid")); // 订单号（订单号不能重复）
                map.put("msisdn", recordMessage.elementTextTrim("msisdn")); // 发送方手机号码
                map.put("serviceId", recordMessage.elementTextTrim("serviceId")); // 充值业务代码
                map.put("province_id", recordMessage.elementTextTrim("province_id")); // 省份代码
                map.put("report_time", recordMessage.elementTextTrim("report_time")); // 状态报告时间（yyyy-MM-dd HH:mm:ss格式）
                map.put("resultCode", recordMessage.elementTextTrim("resultCode")); // 返回结果代码(10000为成功状态，10001为失败状态)
                map.put("authvalue", recordMessage.elementTextTrim("authvalue")); // 鉴权值（MD5加密方式，加密方法：MD5 (orderid +key)，key值： 3)*,cp8j）
                list.add(map);
            }
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
    
    public static void main(String[] args) {
        // 下面是需要解析的xml字符串例子
//        String xmlString = "<html>" + "<head>" + "<title>dom4j解析一个例子</title>" + "<script>" + "<username>yangrong</username>" + "<password>123456</password>" + "</script>"
//                + "</head>" + "<body>" + "<result>0</result>" + "<form>" + "<banlce>1000</banlce>" + "<subID>36242519880716</subID>" + "</form>" + "</body>" + "</html>";
//
//        Map<String, String> map = readStringXmlOut(xmlString);
//        Iterator<String> iters = map.keySet().iterator();
//        while (iters.hasNext()) {
//            String key = iters.next().toString(); // 拿到键
//            String val = map.get(key).toString(); // 拿到值
//            System.out.println(key + "=" + val);
//        }
        String xmlString = "<?xml version=\"1.0\" encoding=\"GB2312\" ?>"
                + "<comic>"
                + "<message>"
                + "<orderid>201501100709393597</orderid>"
                + "    <msisdn>13812341234</msisdn>"
                + "    <serviceId>200000003634</serviceId>"
                + "<province_id>0024</province_id>"
                + "    <report_time>2015-01-12 12:00:00</report_time>"
                + "<resultCode>10000</resultCode>"
                + "<authvalue>b4a83eb84236f18fb90e3c8451132341</authvalue>"
                + "</message>"
                + "<message>"
                + "<orderid>201501100709391234</orderid>"
                + "    <msisdn>13812341234</msisdn>"
                + "    <serviceId>200000003634</serviceId>"
                + "<province_id>0024</province_id>"
                + "    <report_time>2015-01-12 12:00:10</report_time>"
                + "<resultCode>10000</resultCode>"
                + "<authvalue>7886c977a6f3c042f045fc763d78e7d8</authvalue>"
                + "</message>"
                + "</comic>";
        List<Map<String, Object>> list = readStringXmlDDO(xmlString);
        for (Map<String, Object> map : list) {
            System.out.println(map.toString());
        }
    }
}
