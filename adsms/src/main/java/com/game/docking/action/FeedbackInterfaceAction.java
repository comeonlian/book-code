package com.game.docking.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.game.comm.entity.ShortLogIvrLi;
import com.game.comm.entity.ShortLogIvrTyxx;
import com.game.comm.entity.ShortLogSdkback;
import com.game.comm.entity.ShortLogSmsDtmo;
import com.game.comm.entity.ShortLogSmsDtmr;
import com.game.comm.entity.ShortLogSmsKy;
import com.game.comm.entity.ShortLogSmsTyxx;
import com.game.comm.service.ShortLogSdkbackManager;
import com.game.docking.service.ShortLogSmsDtmoManager;
import com.game.docking.service.ShortLogSmsDtmrManager;
import com.game.docking.service.ShortLogSmsKyManager;

@ParentPackage("struts-default")
@InterceptorRef("defaultStack")
public class FeedbackInterfaceAction extends PkgBaseAction {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackInterfaceAction.class);

    @Autowired
    private ShortLogSdkbackManager shortLogSdkbackManager;
    @Autowired
    private ShortLogSmsDtmoManager moMnager;
    @Autowired
    private ShortLogSmsDtmrManager mrManger;
    @Autowired
    private ShortLogSmsKyManager kyManager;
    
    /**
     * SMS(LI)
     */
    @Action(value = "sdkback")
    public void sdkback() {
        Date date = new Date();
        String ip = findRealIp();
        String userAgent = request.getHeader("user-agent");
        String queryString = getAllParams();
        LOGGER.info(request.getRequestURL().toString() + " " + request.getMethod() + "--IP:" + ip + "--内容：" + queryString);
        String state = request.getParameter("state");
        String smsId = request.getParameter("smsId");
        String moContent = request.getParameter("moContent");
        String phone = request.getParameter("phone");
        String serviceCode = request.getParameter("serviceCode");
        ShortLogSdkback log = new ShortLogSdkback(date, ip, userAgent, state, smsId, moContent, phone, serviceCode);
        shortLogSdkbackManager.save(log);
        responseOK("OK");
        return;
    }

    /**
     * IVR(LI)
     */
    @Action(value = "ivrLi")
    public void ivrLi() {
        Date date = new Date();
        String ip = findRealIp();
        String userAgent = request.getHeader("user-agent");
        LOGGER.info(request.getRequestURL().toString() + " " + request.getMethod() + "--IP:" + ip + "--内容：" + getAllParams());
        ShortLogIvrLi ivrLi = new ShortLogIvrLi(date, ip, userAgent, request.getParameter("id"), request.getParameter("mobile"), request.getParameter("called_number"),
                request.getParameter("time_begin"), request.getParameter("time_end"), request.getParameter("time"));
        shortLogSdkbackManager.saveIvrLi(ivrLi);
        responseOK("OK");
        return;
    }

    /**
     * SMS(天娱信息)
     */
    @Action(value = "smsTyxx")
    public void smsTyxx() {
        Date date = new Date();
        String ip = findRealIp();
        String userAgent = request.getHeader("user-agent");
        LOGGER.info(request.getRequestURL().toString() + " " + request.getMethod() + "--IP:" + ip + "--内容：" + getAllParams());
        ShortLogSmsTyxx smsTyxx = new ShortLogSmsTyxx(date, ip, userAgent, request.getParameter("spnumber"), request.getParameter("mobile"), request.getParameter("linkid"),
                request.getParameter("msg"), request.getParameter("fee"), request.getParameter("status"));
        shortLogSdkbackManager.saveSmsTyxx(smsTyxx);
        responseOK("ok");
        return;
    }

    /**
     * IVR(天娱信息)
     */
    @Action(value = "ivrTyxx")
    public void ivrTyxx() {
        Date date = new Date();
        String ip = findRealIp();
        String userAgent = request.getHeader("user-agent");
        LOGGER.info(request.getRequestURL().toString() + " " + request.getMethod() + "--IP:" + ip + "--内容：" + getAllParams());
        ShortLogIvrTyxx ivrTyxx = new ShortLogIvrTyxx(date, ip, userAgent, request.getParameter("spnumber"), request.getParameter("mobile"), request.getParameter("linkid"),
                request.getParameter("starttime"), request.getParameter("stoptime"), request.getParameter("feetime"));
        shortLogSdkbackManager.saveIvrTyxx(ivrTyxx);
        responseOK("ok");
        return;
    }

    /**
     * SMS(大唐)上行同步接口
     */
    @Action(value = "smsDtmo")
    public void smsDtmo() {
        Date date = new Date();
        String ip = findRealIp();
        String userAgent = request.getHeader("user-agent");
        LOGGER.info(request.getRequestURL().toString() + " " + request.getMethod() + "--IP:" + ip + "--DT内容：" + getAllParams());
        ShortLogSmsDtmo mo = new ShortLogSmsDtmo(date, ip, userAgent, request.getParameter("mobile"), request.getParameter("spnum"), request.getParameter("linkid"), request.getParameter("statement"), request.getParameter("flag"));
        moMnager.save(mo);
        responseOK("ok");
        return;
    }
    /**
     * SMS(大唐)状态报告同步接口
     */
    @Action(value = "smsDtmr")
    public void smsDtmr() {
    	Date date = new Date();
    	String ip = findRealIp();
    	String userAgent = request.getHeader("user-agent");
    	LOGGER.info(request.getRequestURL().toString() + " " + request.getMethod() + "--IP:" + ip + "--DT内容：" + getAllParams());
    	ShortLogSmsDtmr mr = new ShortLogSmsDtmr(date, ip, userAgent, request.getParameter("mobile"), request.getParameter("linkid"), request.getParameter("status"), request.getParameter("spnum"), request.getParameter("statement"));
    	mrManger.save(mr);
    	responseOK("ok");
    	return;
    }
    
    @Action(value="smsKycp")
    public void smsKycp(){
    	try{
	    	String ip = findRealIp();
	    	LOGGER.info(request.getRequestURL().toString() + " " + request.getMethod() + "--IP:" + ip + "--DT内容：" + getAllParams());
	    	String spnumber = request.getParameter("spnumber");
	    	String mobile = request.getParameter("mobile");
	    	// spnumber 和 mobile 不能为空
	    	if(StringUtils.isBlank(spnumber)||StringUtils.isBlank(mobile)){
	    		responseOK("NO");
	    		return;
	    	}
	    	ShortLogSmsKy ky = new ShortLogSmsKy(new Date(), ip, request.getParameter("spnumber"), request.getParameter("mobile"), request.getParameter("linkid"), request.getParameter("msg"), request.getParameter("status"));
	    	kyManager.save(ky);
	    	responseOK("OK");
    	}catch(Exception e){
    		e.printStackTrace();
    		responseOK("NO");
    	}
    }
    
    
    /**
     * 反馈字符串信息
     */
    private void responseOK(String str) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.print(str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    public static void main(String[] args) {

    }
}
