package com.game.docking.ota.action;

import java.io.IOException;
import java.nio.ByteBuffer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.game.docking.ota.entity.SendOtaHeader;
import com.game.docking.ota.entity.VisitOta;
import com.game.docking.ota.entity.VisitQuestion;
import com.game.docking.ota.service.OtaGeneralManager;
import com.game.docking.util.OtaUtil;
import com.game.ota.entity.OtaCustomer;
import com.game.ota.entity.OtaDevice;
import com.game.ota.service.OtaCustomerManager;
import com.game.util.DateUtils;

@ParentPackage("struts-default")
@InterceptorRef("defaultStack")
@Namespace("/otaI")
public class OtaInterfaceAction extends OtaBaseAction {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(OtaInterfaceAction.class);

    @Autowired
    private OtaGeneralManager otaGeneralManager;
    @Autowired
    private OtaCustomerManager otaCustomerManager;

    public static void writerByte(ByteBuffer ob, HttpServletResponse response) {
        try {
            ServletOutputStream out = response.getOutputStream();
            out.write(ob.array());
        } catch (IOException e) {
            LOGGER.error("Error while write:{}", e.getMessage());
        }
    }

    /**
     * 请求配置文件接口
     */
    @Action("initConfig")
    public void initConfig() {
        dealDatas();
        return;
    }

    private void dealDatas() {
        VisitOta visit = getVisitOta();
        SendOtaHeader otaHeader = new SendOtaHeader();
        if (visit == null) {
            writerByte(OtaUtil.generateOtaDatasNull(otaHeader), response);
            LOGGER.info("无访问参数为空！");
            return;
        }
        String currentdate = DateUtils.parseDate(visit.getAccesstime(), "yyyy-MM-dd");
        String deviceid = visit.getDeviceid();
        String customerid = visit.getCustomerid();
        if (StringUtils.isBlank(customerid) || customerid.length() < OtaUtil.LIMIT_LENGTH_CUSTOMERID) {
            writerByte(OtaUtil.generateOtaDatasNull(otaHeader), response);
            LOGGER.info("客户ID不存在或长度不够：{}", visit.toString());
            return;
        }
        if (StringUtils.isBlank(deviceid) || deviceid.length() < OtaUtil.LIMIT_LENGTH_DEVICEID) {
            writerByte(OtaUtil.generateOtaDatasNull(otaHeader), response);
            LOGGER.info("设备ID不存在或长度不够：{}", visit.toString());
            return;
        }
        OtaDevice device = otaGeneralManager.getDevice(visit);
        OtaCustomer customer = otaCustomerManager.getByCustomerid(customerid);
        if (customer != null) {
            otaHeader.setInterval(customer.getLinkinterval());
        }
        if (device != null) {
            otaGeneralManager.dealPartnerDatas(visit, currentdate);
        }
        ByteBuffer productBuffer = otaGeneralManager.dealProduct(otaHeader, visit, device, customer);
        // 写访问日志
        otaGeneralManager.insertLogAccess(visit);
        writerByte(productBuffer, response);
        return;
    }

    /**
     * 请求配置文件接口
     */
    @Action("testSms")
    public void testBuffer() {
        testDatas(OtaUtil.SEND_HTTP_SMS);
    }

    @Action("testIvr")
    public void testIvr() {
        testDatas(OtaUtil.SEND_HTTP_IVR7);
    }

    public void testDatas(byte testType) {
        VisitOta visit = getVisitOta();
        SendOtaHeader otaHeader = new SendOtaHeader();
        if (visit == null) {
            writerByte(OtaUtil.generateOtaDatasNull(otaHeader), response);
            LOGGER.info("无访问参数为空！");
            return;
        }
        String currentdate = DateUtils.parseDate(visit.getAccesstime(), "yyyy-MM-dd");
        String deviceid = visit.getDeviceid();
        String customerid = visit.getCustomerid();
        if (StringUtils.isBlank(customerid) || customerid.length() < OtaUtil.LIMIT_LENGTH_CUSTOMERID) {
            writerByte(OtaUtil.generateOtaDatasNull(otaHeader), response);
            LOGGER.info("客户ID不存在或长度不够：{}", visit.toString());
            return;
        }
        if (StringUtils.isBlank(deviceid) || deviceid.length() < OtaUtil.LIMIT_LENGTH_IMSI) {
            writerByte(OtaUtil.generateOtaDatasNull(otaHeader), response);
            LOGGER.info("设备ID不存在或长度不够：{}", visit.toString());
            return;
        }
        otaGeneralManager.getDevice(visit);
        otaGeneralManager.dealPartnerDatas(visit, currentdate);
        OtaCustomer customer = otaCustomerManager.getByCustomerid(customerid);
        if (customer != null) {
            otaHeader.setInterval(customer.getLinkinterval());
        }
        if (OtaUtil.SEND_HTTP_SMS == testType) {
            writerByte(testSms(otaHeader, testType), response);
        } else if (OtaUtil.SEND_HTTP_IVR7 == testType) {
            writerByte(testIvr(otaHeader, testType), response);
        }
        return;
    }

    private ByteBuffer testSms(SendOtaHeader otaHeader, byte sendType) {
        String moNumber = "13530769781";
        String moContent = "111111";
        byte degree = 2;
        byte betInterval = 2;
        String deleteKeyWord = "增值业务体验区|手机流量套餐|您正申请办理手机流量套餐";
        String deleteKeyNumber = "10086|1008610";
        byte replyType = 2;
        String replyContent = "手机流量套餐|；更多业务请发送";
        otaHeader.setSendtype(sendType);
        ByteBuffer sendResult = OtaUtil.generateOtaDatasSmsTest(otaHeader, moNumber, moContent, degree, betInterval, deleteKeyWord, deleteKeyNumber, replyType, replyContent);
        return sendResult;
    }

    private ByteBuffer testIvr(SendOtaHeader otaHeader, byte sendType) {
        String moNumber = "13530769781";
        int callTime = 60;
        byte degree = 3;
        String deleteKeyWord = "增值业务体验区|手机流量套餐|您正申请办理手机流量套餐";
        String deleteKeyNumber = "10086|1008610";
        String keyContent = "3,12#|5,23#";
        otaHeader.setSendtype(sendType);
        ByteBuffer sendResult = OtaUtil.generateOtaDatasIvr(otaHeader, moNumber, callTime, degree, deleteKeyWord, deleteKeyNumber, keyContent);
        return sendResult;
    }

    /**
     * 请求配置文件接口
     */
    @Action("question")
    public void question() {
        VisitQuestion visit = getVisitQuestion();
        SendOtaHeader otaHeader = new SendOtaHeader();
        if (StringUtils.isBlank(visit.getNum())) {
            writerByte(OtaUtil.generateOtaDatasNull(otaHeader), response);
            LOGGER.info("号码为空:{}" + visit.toString());
            return;
        }
        if (StringUtils.isBlank(visit.getSms()) || visit.getSms().length() < 6) {
            writerByte(OtaUtil.generateOtaDatasNull(otaHeader), response);
            LOGGER.info("短信为空:{}" + visit.toString());
            return;
        }
        // TODO
        writerByte(OtaUtil.generateOtaDatasNull(otaHeader), response);
        return;
    }

    public static void main(String[] args) {
//        while (true) {
            String url = "http://183.63.53.103:14110/otaI/initConfig.action?endian=SMALL&IMSI1=9460021172140193&SC1=13800931500&IMSI2=0&SC2=0&IMSI3=null&SC3=null&IMSI4=null&SC4=null&IMSI=9460021172140193&SC=13800931500&pid=94600255023501110000&id=10000&ver=6.6&plat=MTK&pot=39&lasterror=0&platver=1212";

            DefaultHttpClient httpclient = new DefaultHttpClient();
            String body = null;

            HttpGet get = new HttpGet(url);
            try {
                httpclient.execute(get);
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            httpclient.getConnectionManager().shutdown();
//        }

    }
}
