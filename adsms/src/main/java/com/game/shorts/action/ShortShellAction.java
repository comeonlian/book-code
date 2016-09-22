package com.game.shorts.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.game.modules.orm.Page;
import com.game.modules.orm.PropertyFilter;
import com.game.modules.orm.hibernate.HibernateUtils;
import com.game.modules.web.CrudActionSupport;
import com.game.modules.web.struts2.Struts2Utils;
import com.game.services.account.ResourceManager;
import com.game.shorts.entity.ShortShell;
import com.game.shorts.service.ShortShellManager;

/**
 * 测试日志访问 Action.
 */
@Namespace("/shorts/shortShell")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "short-shell.action?authId=${authId}", type = "redirect") })
public class ShortShellAction extends CrudActionSupport<ShortShell> {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ShortShellManager shortShellManager;

    private Long id;
    /**
     * 多选操作使用
     */
    private List<Long> ids;
    private ShortShell entity;
    private Page<ShortShell> page = new Page<ShortShell>(15);

    private String begindate;
    private String enddate;

    @Override
    public ShortShell getModel() {
        return entity;
    }

    @Override
    protected void prepareModel() throws Exception {
        if (null != id) {
            entity = shortShellManager.get(id);
        } else {
            entity = new ShortShell();
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

        page = shortShellManager.searchPage(page, filters);

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
        shortShellManager.save(entity);
        addActionMessage("保存:" + entity.getTitle() + " 成功");
        if (flag) {
            this.logToDB(106, "新增ShortShell-ID：" + entity.getId());
        } else {
            this.logToDB(106, "修改ShortShell-ID：" + entity.getId());
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
        entity = shortShellManager.get(id);
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

    public ShortShell getEntity() {
        return entity;
    }

    public void setEntity(ShortShell entity) {
        this.entity = entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Page<ShortShell> getPage() {
        return page;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public void setPage(Page<ShortShell> page) {
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
}