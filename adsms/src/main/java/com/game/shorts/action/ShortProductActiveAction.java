package com.game.shorts.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
import com.game.services.common.DicManager;
import com.game.shorts.entity.ShortProductActive;
import com.game.shorts.entity.ShortProductActiveWap;
import com.game.shorts.entity.ShortProductSms;
import com.game.shorts.service.ShortProductActiveManager;
import com.game.shorts.service.ShortProductSmsManager;

/**
 * 激活Action
 */
@Namespace("/shorts/shortProduct")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "short-product-active.action?authId=${authId}&pid=${pid}", type = "redirect") })
public class ShortProductActiveAction extends CrudActionSupport<ShortProductActive> {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ShortProductActiveManager shortProductActiveManager;
    @Autowired
    private ShortProductSmsManager shortProductSmsManager;
    @Autowired
    private DicManager dicManager;

    private Long id;
    /**
     * 多选操作使用
     */
    private List<Long> ids;
    private ShortProductActive entity;
    private Page<ShortProductActive> page = new Page<ShortProductActive>(15);

    // pushApk参数
    private Long pid; // 资源id
    private ShortProductSms productSms;

    // 模拟点击、模拟按键、模拟划屏子列表
    private List<ShortProductActiveWap> activityWap = new ArrayList<ShortProductActiveWap>();

    @Override
    public ShortProductActive getModel() {
        return entity;
    }

    @Override
    protected void prepareModel() throws Exception {
        if (null != id) {
            entity = shortProductActiveManager.get(id);
        } else {
            entity = new ShortProductActive();
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
            page.setOrderBy("ordernum");
            page.setOrder(Page.ASC);
        }
        productSms = shortProductSmsManager.get(pid);

        filters.add(new PropertyFilter("EQL_pid", String.valueOf(pid)));
        page = shortProductActiveManager.searchPage(page, filters);
        // excel导出
        if (page.isExcelExp())
            return toExcel(page);

        return SUCCESS;
    }

    @Override
    public String input() throws Exception {
        if (id != null) {
            activityWap = shortProductActiveManager.findWapsByPid(id);
        }

        return INPUT;
    }

    public String detail() {
        if (id != null) {
            entity = shortProductActiveManager.get(id);
            activityWap = shortProductActiveManager.findWapsByPid(id);
        }
        return "detail";
    }

    @Override
    public String save() throws Exception {
        boolean flag = false;
        if (entity.getId() == null) {
            flag = true;
        }
        shortProductActiveManager.saveEntityAndItem(entity, activityWap);
        addActionMessage("保存激活信息成功");
        if (flag) {
            this.logToDB(106, "新增激活信息ID：" + entity.getId());
        } else {
            this.logToDB(106, "修改激活信息ID：" + entity.getId());
        }
        return RELOAD;
    }

    @Override
    public String delete() throws Exception {
        shortProductActiveManager.remove(id);
        addActionMessage("删除激活信息成功");
        return RELOAD;
    }

    public String delAll() throws Exception {
        try {
            Assert.notEmpty(ids, "没有选择删除列！！！");
            if (shortProductActiveManager.delAll(ids)) {
                this.addActionMessage("删除成功！！！");
                this.logToDB(106, "删除激活信息ID：" + ids);
            } else {
                this.addActionMessage("删除失败！！！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            addActionMessage("激活信息使用中无法删除！！！");
        }

        return RELOAD;
    }

    /**
     * 
     * 
     * @return
     * @throws Exception
     */
    public String deleteItem() throws Exception {
        final String tid = Struts2Utils.getParameter("tid");
        if (StringUtils.isNotBlank(tid)) {
            long ttid = Long.valueOf(tid);
            try {
                shortProductActiveManager.deleteWap(ttid);
                logToDB(208, "删除WAP子记录ID：" + Long.valueOf(tid));
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return null;
    }

    public ShortProductActive getEntity() {
        return entity;
    }

    public void setEntity(ShortProductActive entity) {
        this.entity = entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Page<ShortProductActive> getPage() {
        return page;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public void setPage(Page<ShortProductActive> page) {
        this.page = page;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public ShortProductSms getProductSms() {
        return productSms;
    }

    public void setProductSms(ShortProductSms productSms) {
        this.productSms = productSms;
    }

    public List<ShortProductActiveWap> getActivityWap() {
        return activityWap;
    }

    public void setActivityWap(List<ShortProductActiveWap> activityWap) {
        this.activityWap = activityWap;
    }

}