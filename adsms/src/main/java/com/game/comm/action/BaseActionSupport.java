package com.game.comm.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.game.entity.account.User;
import com.game.entity.system.Dicitem;
import com.game.modules.orm.Page;
import com.game.modules.security.springsecurity.SpringSecurityUtils;
import com.game.modules.service.GenericManager;
import com.game.modules.utils.ExcelExportUtil;
import com.game.modules.utils.ExcelReader;
import com.game.modules.utils.ReflectionUtils;
import com.game.modules.utils.StrUtils;
import com.game.modules.web.CrudActionSupport;
import com.game.modules.web.struts2.Struts2Utils;
import com.game.services.account.AccountManager;
import com.game.services.common.DicManager;
import com.game.util.Constants;
import com.game.util.StringUtil;

/**
 * 业务action基类
 * 
 * @author Javen
 * 
 * @param <T>
 */
@SuppressWarnings("unchecked")
public abstract class BaseActionSupport<T> extends CrudActionSupport<T> {

    private static final long serialVersionUID = 3077306862531848056L;

    private final static String TABLE_PREFIX = "entity_";

    @Autowired
    private DicManager dicManager;

    @Autowired
    private AccountManager accountManager;

    @Override
    public String execute() throws Exception {
        return super.execute();
    }

    /**
     * 获得标题
     * 
     * @return list{标题,类型,名字}
     */
    public List<String> getPoiStr() {
        List<String> list = new ArrayList<String>();
        String entityName = returnedClass().getSimpleName();
        if (StringUtils.isNotBlank(entityName)) {
            list = (List<String>) dicManager.getPoiMapping(entityName);
        }
        return list;
    }

    /**
     * 获得标题
     * 
     * @param entityName
     *            dic名称
     * @return list{标题,类型,名字}
     */
    public List<String> getPoiStr(String entityName) {
        List<String> list = new ArrayList<String>();
        if (StringUtils.isNotBlank(entityName)) {
            list = (List<String>) dicManager.getPoiMapping(entityName);
        }
        return list;
    }

    /**
     * excel导出
     * 
     * @param page
     * @param excelName
     * @param entityName
     * @return
     */
    public String exportExcel(Page<T> page, String excelName, String entityName) {
        ExcelExportUtil.exportExcelData(page, getPoiStr(entityName), excelName + ".xls");
        return null;
    }

    /**
     * excel导入
     * 
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public String impExcel(GenericManager objManager) throws Exception {
        if (!validateExcelFile())
            return "importexcel";
        ExcelReader<T> er = new ExcelReader<T>(file, returnedClass(), fileFileName, 0, 0);
        List<T> excelList = er.readExcelContent(getPoiStr());
        boolean flag = true;
        for (int a = 0; a < excelList.size(); a++) {
            T t = excelList.get(a);
            dicNametoCode(t);
            try {
                ReflectionUtils.invokeMethod(objManager, "uniqueByKeysAndThrow", new Class[] { Boolean.class, returnedClass() }, new Object[] { true, t });
                objManager.save(t);
                // ReflectionUtils.invokeMethod(objManager, methodName, new
                // Class[] { returnedClass() },
                // new Object[] { t });
                addActionMessage("第" + (a + 1) + "条记录导入成功！");
                logger.info("第{}条记录导入成功！", (a + 1));
            } catch (Exception e) {
                // e.printStackTrace();
                flag = false;
                addActionMessage("第" + (a + 1) + "条记录导入失败,数据重复！");
                logger.error("第" + (a + 1) + "条记录导入失败！", e);
            }
        }
        if (flag) {
            this.setActionMessages(null);
            this.setFileSize(excelList.size());
        }
        return "importexcel";
    }

    /**
     * 导入时把excel中dic的名称转化成code
     * 
     * @param t
     *            当前对象
     */
    public void dicNametoCode(T t) {
        List<Dicitem> dis = dicManager.getDicitem(TABLE_PREFIX + uncap(returnedClass().getSimpleName()));
        for (Dicitem di : dis) {
            if (StringUtils.equals(di.getVal3(), "dic")) {
                String name = (String) ReflectionUtils.invokeGetterMethod(t, di.getCode());
                Dicitem dTemp = dicManager.findItemByName(di.getVal4(), name);
                String code = (dTemp != null ? dTemp.getCode() : "");
                ReflectionUtils.invokeSetterMethod(t, di.getCode(), code);
            }
        }
    }

    private String uncap(String beanName) {
        return StringUtils.uncapitalize(beanName);
    }

    /**
     * 获得导入excel模版名称
     * 
     * @return
     */
    public String getDownLoadName() {
        List<Dicitem> lst = dicManager.getDicitem(TABLE_PREFIX + uncap(returnedClass().getSimpleName()));
        String dname = "XX";
        if (CollectionUtils.isNotEmpty(lst)) {
            dname = lst.get(0).getDic().getName();
        }
        return dname;
    }

    /**
     * 业务日志持久化
     * 
     * @param operationCode
     * @param description
     */
    protected void logToDB(Integer operationCode, String description) {
        User user = SpringSecurityUtils.getCurrentCoreUser();
        if (user != null && user.getDomain() != null) {
            journalManager.record(operationCode, SpringSecurityUtils.getCurrentUserName(), SpringSecurityUtils.getCurrentUserIp(), description, user.getDomain().getId(), user
                    .getDomain().getLabel());
        } else {
            journalManager.record(operationCode, SpringSecurityUtils.getCurrentUserName(), SpringSecurityUtils.getCurrentUserIp(), description, null, null);
        }
    }

    public User getUser() {
        return SpringSecurityUtils.getCurrentCoreUser();
    }

    /**
     * 包装过滤报表所需的请求参数<BR>
     * 参数为数组或集合的情况用逗号分开
     * 
     * @return ocode='000000','244000','244222','244232';authId=129; raq=/factoryInventory.raq;minCode=1000000000000000000; maxCode=1999999999999999999;
     */
    @SuppressWarnings("all")
    public String getParams() {
        StringBuffer paramb = new StringBuffer();
        // 获取参数
        Enumeration paramNames = Struts2Utils.getRequest().getParameterNames();
        List<String> plist = Lists.newLinkedList();
        if (paramNames != null) {
            while (paramNames.hasMoreElements()) {
                String paramName = (String) paramNames.nextElement();
                String paramValue = Struts2Utils.getRequest().getParameter(paramName);
                if (StringUtils.isNotBlank(paramValue)) {
                    plist.add(StrUtils.stringBuilder(paramName, "=", paramValue).toString());
                }
            }
        }
        return StringUtils.join(plist, ";");
    }

    /**
     * 图片保存
     * 
     * @param resource
     *            图片
     * @param resouceName
     *            文件名
     * @param path
     *            生成文件夹
     * @return
     * @throws IOException
     */
    public String saveFile(File resource, String resouceName, String path) throws IOException {
        String picname = "";
        String picpath = "";
        String picdir = Constants.CONS_PROPERTIES.getValue("imagedir") + "/" + path + "/"; // 图片保存物理路径
        String pic = Constants.CONS_PROPERTIES.getValue("imgurl") + "/" + path + "/"; // 图片路径

        File fdir = new File(picdir);
        if (!fdir.exists()) {
            fdir.mkdirs();
        }
        picname = StringUtil.generateFileName(resouceName);
        picpath = pic + picname;
        File targeta = new File(picdir + "/", picname);
        FileUtils.copyFile(resource, targeta);
        return picpath;
    }

}
