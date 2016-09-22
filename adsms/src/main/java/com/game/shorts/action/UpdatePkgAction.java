package com.game.shorts.action;

import java.io.File;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.game.modules.orm.Page;
import com.game.modules.orm.PropertyFilter;
import com.game.modules.orm.hibernate.HibernateUtils;
import com.game.modules.web.CrudActionSupport;
import com.game.modules.web.struts2.Struts2Utils;
import com.game.services.account.ResourceManager;
import com.game.shorts.entity.ApkEntry;
import com.game.shorts.entity.ShortCustomer;
import com.game.shorts.entity.UpdatePkg;
import com.game.shorts.service.ShortCustomerManager;
import com.game.shorts.service.UpdatePkgManager;
import com.game.util.Constants;
import com.game.util.apk.ApkUtil;

/**
 * apk更新管理Action.
 */
@Namespace("/shorts/updatePkg")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "update-pkg.action?authId=${authId}", type = "redirect") })
public class UpdatePkgAction extends CrudActionSupport<UpdatePkg> {

    private static final String PATH_SEPA = File.separator;

    private static final long serialVersionUID = 1L;

    @Autowired
    private UpdatePkgManager updatePkgManager;
    @Autowired
    private ShortCustomerManager customerManager;

    private Long id;

    /**
     * 多选操作使用
     */
    private List<Long> ids;
    private UpdatePkg entity;
    private Page<UpdatePkg> page = new Page<UpdatePkg>(15);
    private Page<ShortCustomer> pageCustomer = new Page<ShortCustomer>(100);

    private File reso;
    private String resoFileName;

    @Override
    public UpdatePkg getModel() {
        return entity;
    }

    @Override
    protected void prepareModel() throws Exception {
        if (null != id) {
            entity = updatePkgManager.get(id);
        } else {
            entity = new UpdatePkg();
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

        page = updatePkgManager.searchPage(page, filters);
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
        entity = updatePkgManager.get(id);
        return "detail";
    }

    @Override
    public String input() throws Exception {
        return INPUT;
    }

    @Override
    public String save() throws Exception {
    	try{
	        boolean flag = false;
	        if (entity.getId() == null) {
	            flag = true;
	            updatePkgManager.save(entity);
	        }
	        ApkEntry entry = null;
	        // 文件处理
	        if (reso != null) {
	            entity.setApksize(reso.length());
	            String path = Constants.CONS_PROPERTIES.getValue("filedir");
	            Long apkid = entity.getId();
	            // 重命名文件
	            String lastSub = resoFileName.substring(resoFileName.lastIndexOf("."));
	            String newFileName = String.valueOf(apkid) + lastSub;
	            entry = ApkUtil.getApkEntry(reso, path, newFileName);
	            String version = entry.getVersionCode()==null? "0":entry.getVersionCode();
	            entity.setApkVersion(Integer.valueOf(version));
	            entity.setPackagename(entry.getApkPackage());
	            entity.setDownurl(entry.getApkUrl());
	            entity.setMd5(entry.getApkMd5());
	            entity.setFilepath(entry.getApkPath());
	        }
	        
	        updatePkgManager.save(entity);
	        addActionMessage("保存:" + entity.getApkname() + " 成功");
	        if (flag) {
	            this.logToDB(106, "新增adapkID：" + entity.getId());
	        } else {
	            this.logToDB(106, "修改adapkID：" + entity.getId());
	        }
	        return RELOAD;
    	}catch(Exception e){
    		addActionMessage("上传失败，联系管理员！！！");
    		return RELOAD;
    	}
    }

    /**
     * 选择客户
     * 
     * @return
     * @throws Exception
     */
    public String selectcustom() throws Exception {
        List<PropertyFilter> filters = HibernateUtils.buildPropertyFilters(Struts2Utils.getRequest());
        // 设置默认排序方式
        if (!pageCustomer.isOrderBySetted()) {
            pageCustomer.setOrderBy("id");
            pageCustomer.setOrder(Page.DESC);
        }

        filters.add(new PropertyFilter("EQI_status", "1"));

        pageCustomer = customerManager.searchPage(pageCustomer, filters);

        return "selectcustom";
    }

    @Override
    public String delete() throws Exception {
        // adapkManager.delete(id);
        addActionMessage("删除Apk成功");
        return RELOAD;
    }

    public String delAll() throws Exception {
        try {
            Assert.notEmpty(ids, "没有选择删除列！！！");
            if (updatePkgManager.delAll(ids)) {
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
    public UpdatePkg getEntity() {
        return entity;
    }

    public void setEntity(UpdatePkg entity) {
        this.entity = entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Page<UpdatePkg> getPage() {
        return page;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public void setPage(Page<UpdatePkg> page) {
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

    public Page<ShortCustomer> getPageCustomer() {
        return pageCustomer;
    }

    public void setPageCustomer(Page<ShortCustomer> pageCustomer) {
        this.pageCustomer = pageCustomer;
    }

}