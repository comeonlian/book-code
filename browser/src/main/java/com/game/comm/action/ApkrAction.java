package com.game.comm.action;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.game.comm.entity.Apkr;
import com.game.comm.service.IApkrManager;
import com.game.modules.orm.Page;
import com.game.modules.orm.PropertyFilter;
import com.game.modules.orm.hibernate.HibernateUtils;
import com.game.modules.web.CrudActionSupport;
import com.game.modules.web.struts2.Struts2Utils;
import com.game.services.account.ResourceManager;
import com.game.util.Constants;
import com.game.util.Md5Encoder;

/**
 * apk管理Action.
 * 
 * @author wst
 */
@Namespace("/comm/apkr")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "apkr.action?authId=${authId}", type = "redirect") })
public class ApkrAction extends CrudActionSupport<Apkr> {

    private static final String PATH_SEPA = File.separator;

    private static final long serialVersionUID = 1L;

    @Autowired
    private IApkrManager apkrManager;
    private Long id;

    /**
     * 多选操作使用
     */
    private List<Long> ids;
    private Apkr entity;
    private Page<Apkr> page = new Page<Apkr>(15);

    private File reso;
    private String resoFileName;

    private File iconfile;
    private String iconfileFileName;

    @Override
    public Apkr getModel() {
        return entity;
    }

    @Override
    protected void prepareModel() throws Exception {
        if (null != id) {
            entity = apkrManager.get(id);
        } else {
            entity = new Apkr();
        }
    }

    // -- CRUD Action 函数 --//
    /**
     * list页面显示用户分页列表.
     */
    @Override
    public String list() throws Exception {
        List<PropertyFilter> filters = HibernateUtils.buildPropertyFilters(Struts2Utils.getRequest());
        // 设置默认排序方式
        if (!page.isOrderBySetted()) {
            page.setOrderBy("id");
            page.setOrder(Page.DESC);
        }

        page = apkrManager.searchPage(page, filters);
        // excel导出
        if (page.isExcelExp())
            return toExcel(page);

        return SUCCESS;
    }

    /**
     * 详情界面
     * 
     * @return
     */
    public String detail() {
        entity = apkrManager.get(id);
        return "detail";
    }

    @Override
    public String input() throws Exception {
        return INPUT;
    }

    @Override
    public String save() throws Exception {
        boolean flag = false;
        if (entity.getId() == null) {
            flag = true;
            apkrManager.save(entity);
        }

        // APK处理
        /*if (reso != null) {
            entity.setApkSize(String.valueOf(reso.length()));
            String apkMd5 = Md5Encoder.md5(reso);
            entity.setApkMd5(apkMd5);
            String path = Constants.getFileDir();
            String downurl = Constants.CONS_PROPERTIES_PRO.getValue("apkdownload");

            Long apkid = entity.getApkId();

            // 重命名文件
            String newFileName = apkMd5 + resoFileName.substring(resoFileName.lastIndexOf("."));

            // 保存路径
            String basepath = PATH_SEPA + apkid + PATH_SEPA;
            String filepath = path + basepath + newFileName;
            entity.setApkPath(filepath);
            File targeta = new File(path + basepath, newFileName);
            FileUtils.copyFile(reso, targeta);
            entity.setApkUrl(downurl + "/" + apkid + "/" + newFileName); // 下载路径

            List<String> ver = ApkUtil.getPackageVersion(filepath, path + PATH_SEPA +  "unzip" + PATH_SEPA +  apkid + PATH_SEPA);

            if (ver != null && ver.size() > 0) {
                entity.setApkPackage(ver.get(2).trim());
                entity.setApkVersion(ver.get(1).trim());
            }
        }
        
        // 图片处理
        if (iconfile != null) {
            String iconPath = Constants.CONS_PROPERTIES_PRO.getValue("icondir");
            String iconUrl = Constants.CONS_PROPERTIES_PRO.getValue("iconurl");
            String iconMd5 = Md5Encoder.md5(iconfile);
            entity.setApkIconMd5(iconMd5);
            // 重命名文件
            String newFileName = iconMd5 + iconfileFileName.substring(iconfileFileName.lastIndexOf("."));
            
            // 保存路径
            String filepath = iconPath + PATH_SEPA + newFileName;
            entity.setApkIconPath(filepath);
            File targeta = new File(iconPath + PATH_SEPA, newFileName);
            FileUtils.copyFile(iconfile, targeta);
            entity.setApkIcon(iconUrl + "/" + newFileName); // 下载路径
        }*/

        apkrManager.save(entity);
        addActionMessage("保存Apk成功");
        if (flag) {
            this.logToDB(106, "新增ApkID：" + entity.getId());
        } else {
            this.logToDB(106, "修改ApkID：" + entity.getId());
        }
        return RELOAD;
    }

    @Override
    public String delete() throws Exception {
        // ApkManager.delete(id);
        addActionMessage("删除Apk成功");
        return RELOAD;
    }

    public String delAll() throws Exception {
        try {
            Assert.notEmpty(ids, "没有选择删除列！！！");
            if (apkrManager.delAll(ids)) {
                this.addActionMessage("删除成功！！！");
                this.logToDB(106, "删除ApkID：" + ids);
            } else {
                this.addActionMessage("删除失败！！！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            addActionMessage("Apk使用中无法删除！！！");
        }

        return RELOAD;
    }

    /**
     * excel导入
     * 
     * @return
     * @throws Exception
     */
    public String importExcel() throws Exception {
        return importExcel(ResourceManager.class, "saveResource");
    }

    // ************* getting and setting *****************
    public Apkr getEntity() {
        return entity;
    }

    public void setEntity(Apkr entity) {
        this.entity = entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Page<Apkr> getPage() {
        return page;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public void setPage(Page<Apkr> page) {
        this.page = page;
    }

    public File getReso() {
        return reso;
    }

    public void setReso(File reso) {
        this.reso = reso;
    }

    public String getResoFileName() {
        return resoFileName;
    }

    public void setResoFileName(String resoFileName) {
        this.resoFileName = resoFileName;
    }

    public File getIconfile() {
        return iconfile;
    }

    public void setIconfile(File iconfile) {
        this.iconfile = iconfile;
    }

    public String getIconfileFileName() {
        return iconfileFileName;
    }

    public void setIconfileFileName(String iconfileFileName) {
        this.iconfileFileName = iconfileFileName;
    }

}