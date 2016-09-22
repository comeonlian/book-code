package com.game.shorts.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.game.comm.service.CityManager;
import com.game.modules.orm.Page;
import com.game.modules.orm.PropertyFilter;
import com.game.modules.orm.hibernate.HibernateUtils;
import com.game.modules.web.CrudActionSupport;
import com.game.modules.web.struts2.Struts2Utils;
import com.game.services.account.ResourceManager;
import com.game.shorts.entity.ShortCustomer;
import com.game.shorts.service.ShortCustomerManager;

/**
 * 客户管理Action.
 */
@Namespace("/shorts/customer")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "short-customer.action?authId=${authId}", type = "redirect") })
public class ShortCustomerAction extends CrudActionSupport<ShortCustomer> {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ShortCustomerManager shortCustomerManager;

    @Autowired
    private CityManager cityManager;

    private Long id;
    private Long oldId;
    /**
     * 多选操作使用
     */
    private List<Long> ids;
    private ShortCustomer entity;
    private Page<ShortCustomer> page = new Page<ShortCustomer>(15);

    private String treexml; // 省市树

    @Override
    public ShortCustomer getModel() {
        return entity;
    }

    @Override
    protected void prepareModel() throws Exception {
        if (null != id) {
            entity = shortCustomerManager.get(id);
        } else {
            entity = new ShortCustomer();
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
            page.setOrderBy("customerid");
            page.setOrder(Page.ASC);
        }

        filters.add(new PropertyFilter("INI_status", "0,1"));

        page = shortCustomerManager.searchPage(page, filters);
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

        shortCustomerManager.save(entity);
        addActionMessage("保存客户ID：" + entity.getCustomerid() + " 成功");
        if (flag) {
            this.logToDB(106, "新增ShortCustomer-ID：" + entity.getId());
        } else {
            this.logToDB(106, "修改ShortCustomer-ID：" + entity.getId());
        }
        return RELOAD;
    }

    @Override
    public String delete() throws Exception {
        return RELOAD;
    }

    public String delAll() throws Exception {
        try {
            Assert.notEmpty(ids, "没有选择删除列！！！");
            if (shortCustomerManager.delAll(ids)) {
                this.addActionMessage("删除成功！！！");
                this.logToDB(106, "删除弹框客户-ID：" + ids);
            } else {
                this.addActionMessage("删除失败！！！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            addActionMessage("弹框客户-使用中无法删除！！！");
        }

        return RELOAD;
    }

    /**
     * ajax 校验唯一性
     * 
     * @return
     */
    public String checkCustomid() {
        String customid = Struts2Utils.getParameter("customerid");
        String oldName = null;
        if (oldId != null) {
            entity = shortCustomerManager.get(oldId);
            oldName = entity.getCustomerid();
        }
        if (shortCustomerManager.isCustomidUnique(customid, oldName)) {
            Struts2Utils.renderText("true");
        } else {
            Struts2Utils.renderText("false");
        }
        return null;
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

    // ******************* getting and setting ****************
    public Long getOldId() {
        return oldId;
    }

    public void setOldId(Long oldId) {
        this.oldId = oldId;
    }

    public ShortCustomer getEntity() {
        return entity;
    }

    public void setEntity(ShortCustomer entity) {
        this.entity = entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Page<ShortCustomer> getPage() {
        return page;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public void setPage(Page<ShortCustomer> page) {
        this.page = page;
    }

    public String getTreexml() {
        return treexml;
    }

    public void setTreexml(String treexml) {
        this.treexml = treexml;
    }

}