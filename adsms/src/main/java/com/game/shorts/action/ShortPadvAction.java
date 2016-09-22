package com.game.shorts.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.game.comm.action.BaseActionSupport;
import com.game.comm.entity.Province;
import com.game.comm.service.CityManager;
import com.game.comm.service.ProvinceManager;
import com.game.entity.LabelValue;
import com.game.entity.account.User;
import com.game.modules.orm.Page;
import com.game.modules.orm.PropertyFilter;
import com.game.modules.orm.hibernate.HibernateUtils;
import com.game.modules.security.springsecurity.SpringSecurityUtils;
import com.game.modules.utils.LabelValueUtils;
import com.game.modules.web.CrudActionSupport;
import com.game.modules.web.struts2.Struts2Utils;
import com.game.services.account.AccountManager;
import com.game.services.account.ResourceManager;
import com.game.shorts.entity.ShortPadv;
import com.game.shorts.entity.ShortPadvItem;
import com.game.shorts.service.ShortPadvItemManager;
import com.game.shorts.service.ShortPadvManager;

/**
 * 第三方信息 Action.
 */
@Namespace("/shorts/shortPadv")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "short-padv.action?authId=${authId}", type = "redirect") })
public class ShortPadvAction extends BaseActionSupport<ShortPadv> {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ShortPadvManager shortPadvManager;

    @Autowired
    private CityManager cityManager;

    @Autowired
    private AccountManager accountManager;

    @Autowired
    private ProvinceManager provinceManager;

    @Autowired
    private ShortPadvItemManager shortPadvItemManager;

    private Long id;
    private Long oldId;
    private Long parentId; // 父类Id
    /**
     * 多选操作使用
     */
    private List<Long> ids;
    private ShortPadv entity;
    private Page<ShortPadv> page = new Page<ShortPadv>(15);
    private List<LabelValue> resTypeLabelValueList;

    private String treexml; // 省市树
    List<ShortPadvItem> shortPadvItems;

    private List<Province> provincelist; // 省份列表
    private String provinceid; // 省ID

    @Override
    public ShortPadv getModel() {
        return entity;
    }

    @Override
    protected void prepareModel() throws Exception {
        if (null != id) {
            entity = shortPadvManager.getShortPadv(id);
        } else {
            entity = new ShortPadv();
        }
    }

    // -- CRUD Action 函数 --//
    /**
     * list页面显示用户分页列表.
     */
    @Override
    public String list() throws Exception {
        resTypeLabelValueList = LabelValueUtils.getResStringLabelValueList();
        provincelist = provinceManager.getAll(); // 所有省列表
        List<PropertyFilter> filters = HibernateUtils.buildPropertyFilters(Struts2Utils.getRequest());
        // 设置默认排序方式
        if (!page.isOrderBySetted()) {
            page.setOrderBy("id");
            page.setOrder(Page.DESC);
        }
        // 省市过滤
        if (StringUtils.isNotBlank(provinceid)) {
            // 广东省，需要特殊处理
            if ("1008".equals(provinceid)) {
                filters.add(new PropertyFilter("LIKES_citys", "|255|"));
            } else {
                filters.add(new PropertyFilter("LIKES_citys", "|" + provinceid + "|"));
            }
        }
        filters.add(new PropertyFilter("INI_status", "0,1"));
        page = shortPadvManager.searchShortPadv(page, filters);
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

    /**
     * 1、保存第三方信息； 2、保存第三方信息对应下的省市子表
     * 
     */
    @Override
    public String save() throws Exception {
        User user = accountManager.getUser(SpringSecurityUtils.getCurrentCoreUser().getId());
        Date currentDate = new Date();
        String username = user.getUsername();
        boolean flag = false;
        if (entity.getId() == null) {
            flag = true;
            entity.setCreateTime(currentDate);
            entity.setCreateBy(username);
        } else {
            entity.setLastModifyBy(username);
            entity.setLastModifyTime(currentDate);
        }
        shortPadvManager.saveShortPadv(entity); // 保存主表

        // ************* 保存子表 *********************
        if (flag) { // 新增时，才保存子表信息
            List<Province> provinces = provinceManager.getAll();
            for (Province item : provinces) {
                ShortPadvItem shortPadvItem = new ShortPadvItem();
                shortPadvItem.setCreateTime(currentDate);
                shortPadvItem.setCreateBy(username);
                shortPadvItem.setPid(entity.getId());
                shortPadvItem.setProvincecode(String.valueOf(item.getId()));
                shortPadvItem.setProvincename(item.getName());
                shortPadvItem.setPushcounts(0);
                shortPadvItem.setPushtimes(0);
                shortPadvItemManager.save(shortPadvItem);
            }
        }

        addActionMessage("保存shortPadv成功");
        if (flag) {
            this.logToDB(106, "新增shortPadvID：" + entity.getId());
        } else {
            this.logToDB(106, "修改shortPadvID：" + entity.getId());
        }
        return RELOAD;
    }

    @Override
    public String delete() throws Exception {
        shortPadvManager.deleteShortPadv(id);
        addActionMessage("删除shortPadv成功");
        return RELOAD;
    }

    public String delAll() throws Exception {
        try {
            Assert.notEmpty(ids, "没有选择删除列！！！");
            if (shortPadvManager.delAll(ids)) {
                this.addActionMessage("删除成功！！！");
                this.logToDB(106, "删除shortPadvID：" + ids);
            } else {
                this.addActionMessage("删除失败！！！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            addActionMessage("shortPadv使用中无法删除！！！");
        }

        return RELOAD;
    }

    // ************************* 第三方信息 子表 处理 begin ****************
    /**
     * 编辑第三方信息子表
     * 
     * @return
     */
    public String edititem() {
        entity = shortPadvManager.get(parentId);
        shortPadvItems = shortPadvItemManager.findByPid(parentId);
        return "edititem";
    }

    public String saveShortPadvItem() {
        if (shortPadvItems != null) {
            for (ShortPadvItem pitem : shortPadvItems) {
                if (pitem.getPushtimes() != null) {
                    shortPadvItemManager.save(pitem);
                }
            }
        }
        addActionMessage("保存成功！！！");
        return RELOAD;
    }

    // ************************* 第三方信息 子表 处理 end ****************

    /**
     * ajax 校验唯一性
     * 
     * @return
     */
    public String checkName() {

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

    public String getTreexml() {
        return treexml;
    }

    public void setTreexml(String treexml) {
        this.treexml = treexml;
    }

    public ShortPadv getEntity() {
        return entity;
    }

    public void setEntity(ShortPadv entity) {
        this.entity = entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Page<ShortPadv> getPage() {
        return page;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public void setPage(Page<ShortPadv> page) {
        this.page = page;
    }

    public List<LabelValue> getResTypeLabelValueList() {
        return resTypeLabelValueList;
    }

    public void setResTypeLabelValueList(List<LabelValue> resTypeLabelValueList) {
        this.resTypeLabelValueList = resTypeLabelValueList;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<ShortPadvItem> getShortPadvItems() {
        return shortPadvItems;
    }

    public void setShortPadvItems(List<ShortPadvItem> shortPadvItems) {
        this.shortPadvItems = shortPadvItems;
    }

    public List<Province> getProvincelist() {
        return provincelist;
    }

    public void setProvincelist(List<Province> provincelist) {
        this.provincelist = provincelist;
    }

    public String getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid;
    }

    public Long getOldId() {
        return oldId;
    }

    public void setOldId(Long oldId) {
        this.oldId = oldId;
    }
}