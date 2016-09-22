package com.game.shorts.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.game.modules.orm.Page;
import com.game.modules.orm.PropertyFilter;
import com.game.modules.orm.hibernate.HibernateUtils;
import com.game.modules.web.CrudActionSupport;
import com.game.modules.web.struts2.Struts2Utils;
import com.game.shorts.entity.ShortDevice;
import com.game.shorts.service.ShortDeviceManager;
import com.game.services.account.ResourceManager;

/**
 * 设备管理 Action.
 */
@Namespace("/shorts/shortDevice")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "short-device.action?authId=${authId}&selectMac=${selectMac}&selectImei=${selectImei}&selectAndroidid=${selectAndroidid}", type = "redirect") })
public class ShortDeviceAction extends CrudActionSupport<ShortDevice> {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ShortDeviceManager shortDeviceManager;

    private Long id;
    /**
     * 多选操作使用
     */
    private List<Long> ids;
    private ShortDevice entity;
    private Page<ShortDevice> page = new Page<ShortDevice>(15);

    private String begindate;
    private String enddate;
    private String selectMac;
    private String selectImei;
    private String selectAndroidid;

    @Override
    public ShortDevice getModel() {
        return entity;
    }

    @Override
    protected void prepareModel() throws Exception {
        if (null != id) {
            entity = shortDeviceManager.get(id);
        } else {
            entity = new ShortDevice();
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

        int selectFlag = NumberUtils.INTEGER_ZERO;
        if (StringUtils.isNotBlank(selectImei)) {
            filters.add(new PropertyFilter("EQS_imei", selectImei));
            selectFlag = NumberUtils.INTEGER_ONE;
        }
        if (StringUtils.isNotBlank(selectMac)) {
            filters.add(new PropertyFilter("EQS_mac", selectMac));
            selectFlag = NumberUtils.INTEGER_ONE;
        }
        if (StringUtils.isNotBlank(selectAndroidid)) {
            filters.add(new PropertyFilter("EQS_androidid", selectAndroidid));
            selectFlag = NumberUtils.INTEGER_ONE;
        }
        if (selectFlag == NumberUtils.INTEGER_ZERO) {
            filters.add(new PropertyFilter("EQS_mac", "--1"));
        }
        page = shortDeviceManager.searchPage(page, filters);

        // excel导出
        if (page.isExcelExp())
            return toExcel(page);

        return SUCCESS;
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
        }
        shortDeviceManager.save(entity);
        addActionMessage("保存成功");
        if (flag) {
            this.logToDB(106, "新增ShortDevice-ID：" + entity.getId());
        } else {
            this.logToDB(106, "修改ShortDevice-ID：" + entity.getId());
        }
        return RELOAD;
    }

    @Override
    public String delete() throws Exception {
        return RELOAD;
    }

    public String delAll() throws Exception {

        return RELOAD;
    }

    public String detail() {
        entity = shortDeviceManager.get(id);
        return "detail";
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

    public String getBegindate() {
        return begindate;
    }

    public void setBegindate(String begindate) {
        this.begindate = begindate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public ShortDevice getEntity() {
        return entity;
    }

    public void setEntity(ShortDevice entity) {
        this.entity = entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Page<ShortDevice> getPage() {
        return page;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public void setPage(Page<ShortDevice> page) {
        this.page = page;
    }

    public String getSelectMac() {
        return selectMac;
    }

    public void setSelectMac(String selectMac) {
        this.selectMac = selectMac;
    }

    public String getSelectImei() {
        return selectImei;
    }

    public void setSelectImei(String selectImei) {
        this.selectImei = selectImei;
    }

    public String getSelectAndroidid() {
        return selectAndroidid;
    }

    public void setSelectAndroidid(String selectAndroidid) {
        this.selectAndroidid = selectAndroidid;
    }
}