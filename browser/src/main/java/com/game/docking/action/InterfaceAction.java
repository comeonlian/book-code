package com.game.docking.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.game.bmanager.entity.Customer;
import com.game.bmanager.entity.CustomerUrl;
import com.game.bmanager.service.CustomerManager;
import com.game.bmanager.service.CustomerUrlManager;
import com.game.docking.entity.ConstantCode;
import com.game.docking.entity.VisitAdsms;
import com.game.docking.service.DockingGeneralManager;
import com.game.docking.util.JsonUtil;
import com.game.docking.util.pkg.PacketUtils;
import com.game.util.DateUtils;

@ParentPackage("struts-default")
@InterceptorRef("defaultStack")
public class InterfaceAction extends BaseAction {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(InterfaceAction.class);

    @Autowired
    private DockingGeneralManager dockingGeneralManager;
    @Autowired
    private CustomerManager customerManager;
    @Autowired
    private CustomerUrlManager urlManager;
    
    /**
     * 请求配置文件接口
     */
    @Action(value = "getConfig")
    public void getConfig() {
        Map<String, Object> result = new HashMap<String, Object>();
        VisitAdsms visit = getVisitAdsms();
        if (visit == null) {
            PacketUtils.sendMessage(response, generateResultData(ConstantCode.ERROR_NOTEXIST_BODY, result), false);
            LOGGER.info("包体为空！");
            return;
        }
        String currentdate = DateUtils.parseDate(visit.getAccesstime(), "yyyy-MM-dd");
        String deviceid = visit.getDeviceid();
        String customerid = visit.getCustomerid();
        boolean isGzip = visit.isGzip();
        if (StringUtils.isBlank(customerid) || StringUtils.isBlank(visit.getAndroidid())) {
            if (StringUtils.isBlank(customerid)) {
                PacketUtils.sendMessage(response, generateResultData(ConstantCode.ERROR_NOTEXIST_CID, result), isGzip);
            }
            if (StringUtils.isBlank(deviceid)) {
                PacketUtils.sendMessage(response, generateResultData(ConstantCode.ERROR_NOTEXIST_DEVICEID, result), isGzip);
            }
            // 写访问错误日志
            dockingGeneralManager.insertLogErrorNull(visit);
            return;
        }
        String currentTime = visit.getMobileCurrentTime();
        Date mobileDate = null;
        boolean saveErrorTime = false;
        if (StringUtils.isNotBlank(currentTime)) {
            try {
                mobileDate = new Date(Long.valueOf(currentTime));
            } catch (Exception e) {
                saveErrorTime = true;
            }
        } else {
            saveErrorTime = true;
        }
        if (saveErrorTime) { // 写手机时间参数错误
            // 写错误日志
            dockingGeneralManager.insertLogErrorMobileTime(visit);
            PacketUtils.sendMessage(response, generateResultData(ConstantCode.ERROR_UNDEFINE, result), isGzip);
            return;
        }

        // 合作客户数据处理
        dockingGeneralManager.dealPartnerDatas(visit, currentdate);
        // 获取客户
        Customer customer = customerManager.getByCustomerid(customerid);
        Integer isrun = 0;
        String url = "https://www.hao123.com/";
        if(customer != null && customer.getStatus()!=0){
        	String res= urlManager.queryUrl(customer.getCustomerid());
        	if(null != res && !"".equals(res)){
        		url = res;
        	}
        	isrun = customer.getIsrun();
        }
        /*String sessionid = visit.getSessionid();
        if (StringUtils.isNotBlank(sessionid)) {
            url = url + "?sessionid=" + sessionid;
        }*/
        result.put("url", url);
        result.put("isrun", isrun);
        // 写访问日志
        dockingGeneralManager.insertLogVisit(visit);
        
        //System.out.println(result);
        
        PacketUtils.sendMessage(response, generateResultData(ConstantCode.OK, result), isGzip);
        return;
    }

    /**
     * 跳转
     */
    @Action(value = "jump")
    public void jump() {
        LOGGER.info(getAllParams());
        long id = request.getParameter("id") == null ? 0 : NumberUtils.toLong(request.getParameter("id"));
        int type = request.getParameter("t") == null ? 0 : NumberUtils.toInt(request.getParameter("t"));
        try {
            String url = dockingGeneralManager.getDownUrl(id, type);
            if (StringUtils.isNotBlank(url)) {
                response.sendRedirect(url);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return;
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String, Object>();
        (new Date()).getTime();
        map.put("re", 0);
        System.out.println(map.toString());
        System.out.println(generateResultData(ConstantCode.ERROR_NOTEXIST_DEVICEID, map));
    }
}
