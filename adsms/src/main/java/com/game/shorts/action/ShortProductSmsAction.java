package com.game.shorts.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.game.comm.service.CityManager;
import com.game.modules.orm.Page;
import com.game.modules.orm.PropertyFilter;
import com.game.modules.orm.hibernate.HibernateUtils;
import com.game.modules.web.CrudActionSupport;
import com.game.modules.web.struts2.Struts2Utils;
import com.game.shorts.entity.ShortProductSms;
import com.game.shorts.service.ShortProductSmsManager;

/**
 * 产品 Action.
 */
@Namespace("/shorts/shortProduct")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "short-product-sms.action?authId=${authId}", type = "redirect") })
public class ShortProductSmsAction extends CrudActionSupport<ShortProductSms> {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ShortProductSmsManager shortProductSmsManager;
    @Autowired
    private CityManager cityManager;

    private Long id;
    private String treexml; // 省市树
    /**
     * 多选操作使用
     */
    private List<Long> ids;
    private ShortProductSms entity;
    private Page<ShortProductSms> page = new Page<ShortProductSms>(15);

    private String begindate;
    private String enddate;

    @Override
    public ShortProductSms getModel() {
        return entity;
    }

    @Override
    protected void prepareModel() throws Exception {
        if (null != id) {
            entity = shortProductSmsManager.get(id);
        } else {
            entity = new ShortProductSms();
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

        page = shortProductSmsManager.searchPage(page, filters);

        // excel导出
        if (page.isExcelExp())
            return toExcel(page);

        return SUCCESS;
    }

    @Override
    public String input() throws Exception {
        treexml = cityManager.findTreeXml();
        return INPUT;
    }

    @Override
    public String save() throws Exception {
        boolean flag = false;
        if (entity.getId() == null) {
            flag = true;
        }
        shortProductSmsManager.save(entity);
        addActionMessage("保存:" + entity.getServiceCode() + " 成功");
        if (flag) {
            this.logToDB(106, "新增ShortProductSms-ID：" + entity.getId());
        } else {
            this.logToDB(106, "修改ShortProductSms-ID：" + entity.getId());
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
        entity = shortProductSmsManager.get(id);
        return "detail";
    }

    public ShortProductSms getEntity() {
        return entity;
    }

    public void setEntity(ShortProductSms entity) {
        this.entity = entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Page<ShortProductSms> getPage() {
        return page;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public void setPage(Page<ShortProductSms> page) {
        this.page = page;
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

    public String getTreexml() {
        return treexml;
    }

    public void setTreexml(String treexml) {
        this.treexml = treexml;
    }
}