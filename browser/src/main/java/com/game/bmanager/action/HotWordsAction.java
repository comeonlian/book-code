package com.game.bmanager.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.game.bmanager.entity.HotWords;
import com.game.bmanager.service.HotWordsManager;
import com.game.comm.service.CityManager;
import com.game.modules.orm.Page;
import com.game.modules.orm.PropertyFilter;
import com.game.modules.orm.hibernate.HibernateUtils;
import com.game.modules.web.CrudActionSupport;
import com.game.modules.web.struts2.Struts2Utils;
import com.game.util.Constants;
import com.game.util.JumpType;

/**
 * 客户管理Action.
 */
@Namespace("/bmanager/hotWords")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "hot-words.action?authId=${authId}", type = "redirect") })
public class HotWordsAction extends CrudActionSupport<HotWords> {

    private static final long serialVersionUID = 1L;

    @Autowired
    private HotWordsManager hotWordsManager;

    @Autowired
    private CityManager cityManager;

    private Long id;
    /**
     * 多选操作使用
     */
    private List<Long> ids;
    private HotWords entity;
    private Page<HotWords> page = new Page<HotWords>(15);

    private String treexml; // 省市树

    @Override
    public HotWords getModel() {
        return entity;
    }

    @Override
    protected void prepareModel() throws Exception {
        if (null != id) {
            entity = hotWordsManager.get(id);
        } else {
            entity = new HotWords();
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

        filters.add(new PropertyFilter("INI_status", "0,1"));

        page = hotWordsManager.searchPage(page, filters);
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
            hotWordsManager.save(entity);
            String dumpDownUrl = Constants.JUMP_URL + "?id=" + entity.getId() + "&t=" + JumpType.TYPE_HOTWORD.getJtype();
            entity.setDumpDownUrl(dumpDownUrl);
        }

        hotWordsManager.save(entity);
        addActionMessage("保存：" + entity.getTitle() + " 成功");
        if (flag) {
            this.logToDB(106, "新增HotWords-ID：" + entity.getId());
        } else {
            this.logToDB(106, "修改HotWords-ID：" + entity.getId());
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
            if (hotWordsManager.delAll(ids)) {
                this.addActionMessage("删除成功！！！");
                this.logToDB(106, "删除HotWords-ID：" + ids);
            } else {
                this.addActionMessage("删除失败！！！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            addActionMessage("HotWords-使用中无法删除！！！");
        }

        return RELOAD;
    }

    // ******************* getting and setting ****************
    public HotWords getEntity() {
        return entity;
    }

    public void setEntity(HotWords entity) {
        this.entity = entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Page<HotWords> getPage() {
        return page;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public void setPage(Page<HotWords> page) {
        this.page = page;
    }

    public String getTreexml() {
        return treexml;
    }

    public void setTreexml(String treexml) {
        this.treexml = treexml;
    }

}