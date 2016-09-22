package com.game.shorts.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.game.comm.action.BaseActionSupport;
import com.game.entity.LabelValue;
import com.game.entity.account.User;
import com.game.modules.orm.Page;
import com.game.modules.orm.PropertyFilter;
import com.game.modules.orm.hibernate.HibernateUtils;
import com.game.modules.security.springsecurity.SpringSecurityUtils;
import com.game.modules.web.CrudActionSupport;
import com.game.modules.web.struts2.Struts2Utils;
import com.game.services.account.AccountManager;
import com.game.shorts.entity.ShortKeyInfo;
import com.game.shorts.entity.ShortKeyTip;
import com.game.shorts.service.ShortKeyInfoManager;

/**
 * 关键字 Action.
 */
@Namespace("/shorts/shortKeyInfo")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "short-key-info.action?authId=${authId}", type = "redirect") })
public class ShortKeyInfoAction extends BaseActionSupport<ShortKeyInfo> {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ShortKeyInfoManager shortKeyInfoManager;

    @Autowired
    private AccountManager accountManager;

    private Long id;
    private Long pid; // 主键id
    private Long tipId; // 子表id
    /**
     * 多选操作使用
     */
    private List<Long> ids;
    private ShortKeyInfo entity;
    private ShortKeyTip keyTip;
    private Page<ShortKeyInfo> page = new Page<ShortKeyInfo>(15);
    private Page<ShortKeyTip> tipPage = new Page<ShortKeyTip>(15);
    private List<LabelValue> resTypeLabelValueList;

    private String treexml; // 省市树

    // 关键字子信息
    private String kbegin;
    private String keyend;
    private Integer del;
    private Integer ret;
    private String smes;
    private String cons;

    @Override
    public ShortKeyInfo getModel() {
        return entity;
    }

    @Override
    protected void prepareModel() throws Exception {
        if (null != id) {
            entity = shortKeyInfoManager.getShortKeyInfo(id);
        } else {
            entity = new ShortKeyInfo();
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
        page = shortKeyInfoManager.searchShortKeyInfo(page, filters);
        // excel导出
        if (page.isExcelExp())
            return toExcel(page);

        return SUCCESS;
    }

    /**
     * 详情
     * 
     * @return
     */
    public String detail() {
        entity = shortKeyInfoManager.get(id);
        return "detail";
    }

    @Override
    public String input() throws Exception {

        return INPUT;
    }

    @Override
    public String save() throws Exception {
        User user = accountManager.getUser(SpringSecurityUtils.getCurrentCoreUser().getId());
        boolean flag = false;
        if (entity.getId() == null) {
            flag = true;
            entity.setCreateTime(new Date());
            entity.setCreateBy(user.getUsername());
        } else {
            entity.setLastModifyBy(user.getUsername());
            entity.setLastModifyTime(new Date());
        }

        shortKeyInfoManager.saveShortKeyInfo(entity);
        addActionMessage("保存关键字成功");
        if (flag) {
            this.logToDB(106, "新增关键字ID：" + entity.getId());
        } else {
            this.logToDB(106, "修改关键字ID：" + entity.getId());
        }
        return RELOAD;
    }

    @Override
    public String delete() throws Exception {
        shortKeyInfoManager.remove(id);
        addActionMessage("删除关键字成功");
        return RELOAD;
    }

    public String delAll() throws Exception {
        try {
            Assert.notEmpty(ids, "没有选择删除列！！！");
            if (shortKeyInfoManager.delAll(ids)) {
                this.addActionMessage("删除成功！！！");
                this.logToDB(106, "删除关键字ID：" + ids);
            } else {
                this.addActionMessage("删除失败！！！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            addActionMessage("关键字使用中无法删除！！！");
        }

        return RELOAD;
    }

    // ********************* 关键字子信息处理开始 ***********************
    /**
     * 关键字子信息列表
     * 
     * @return
     */
    public String tiplist() {

        List<PropertyFilter> filters = HibernateUtils.buildPropertyFilters(Struts2Utils.getRequest());
        // 设置默认排序方式
        if (!tipPage.isOrderBySetted()) {
            tipPage.setOrderBy("id");
            tipPage.setOrder(Page.DESC);
        }

        entity = shortKeyInfoManager.get(pid);

        filters.add(new PropertyFilter("EQL_nfid", String.valueOf(pid)));
        tipPage = shortKeyInfoManager.searchShortKeyTip(tipPage, filters);
        return "tiplist";
    }

    /**
     * 关键字子信息新增
     * 
     * @return
     */
    public String tipinput() {
        if (tipId != null) {
            keyTip = shortKeyInfoManager.getShortKeyTip(tipId);
        }
        return "tipinput";
    }

    public String tipdel() {
        try {
            Assert.notEmpty(ids, "没有选择删除列！！！");
            if (shortKeyInfoManager.delShortKeyTipAll(ids)) {
                this.addActionMessage("删除成功！！！");
                this.logToDB(106, "删除关键字子信息ID：" + ids);
            } else {
                this.addActionMessage("删除失败！！！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            addActionMessage("关键字子信息使用中无法删除！！！");
        }

        return tiplist();
    }

    /**
     * 关键字子信息保存
     * 
     * @return
     */
    public String tipsave() {

        boolean flag = false;
        if (tipId == null) {
            keyTip = new ShortKeyTip();
            keyTip.setCreateTime(new Date());
            keyTip.setCreateBy(getUser().getUsername());
            keyTip.setNfid(pid);
            flag = true;
        } else {
            keyTip = shortKeyInfoManager.getShortKeyTip(tipId);
            keyTip.setLastModifyBy(getUser().getUsername());
            keyTip.setLastModifyTime(new Date());
        }

        keyTip.setKbegin(kbegin);
        keyTip.setKeyend(keyend);
        keyTip.setCons(cons);
        keyTip.setDel(del);
        keyTip.setRet(ret);
        keyTip.setSmes(smes);
        shortKeyInfoManager.saveShortKeyTip(keyTip);

        addActionMessage("保存关键字子信息成功");
        Struts2Utils.getRequest().setAttribute("saveFlag", "保存关键字子信息成功");
        if (flag) {
            this.logToDB(106, "新增关键字子信息ID：" + keyTip.getId());
        } else {
            this.logToDB(106, "修改关键字子信息ID：" + keyTip.getId());
        }
        Struts2Utils.getRequest().setAttribute("pid", pid);
        return tiplist();
    }

    // ********************* 关键字子信息处理end ***********************

    public ShortKeyInfo getEntity() {
        return entity;
    }

    public void setEntity(ShortKeyInfo entity) {
        this.entity = entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Page<ShortKeyInfo> getPage() {
        return page;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public void setPage(Page<ShortKeyInfo> page) {
        this.page = page;
    }

    public List<LabelValue> getResTypeLabelValueList() {
        return resTypeLabelValueList;
    }

    public void setResTypeLabelValueList(List<LabelValue> resTypeLabelValueList) {
        this.resTypeLabelValueList = resTypeLabelValueList;
    }

    public String getTreexml() {
        return treexml;
    }

    public void setTreexml(String treexml) {
        this.treexml = treexml;
    }

    public Page<ShortKeyTip> getTipPage() {
        return tipPage;
    }

    public void setTipPage(Page<ShortKeyTip> tipPage) {
        this.tipPage = tipPage;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public ShortKeyTip getKeyTip() {
        return keyTip;
    }

    public void setKeyTip(ShortKeyTip keyTip) {
        this.keyTip = keyTip;
    }

    public Long getTipId() {
        return tipId;
    }

    public void setTipId(Long tipId) {
        this.tipId = tipId;
    }

    public String getKbegin() {
        return kbegin;
    }

    public void setKbegin(String kbegin) {
        this.kbegin = kbegin;
    }

    public String getKeyend() {
        return keyend;
    }

    public void setKeyend(String keyend) {
        this.keyend = keyend;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public Integer getRet() {
        return ret;
    }

    public void setRet(Integer ret) {
        this.ret = ret;
    }

    public String getSmes() {
        return smes;
    }

    public void setSmes(String smes) {
        this.smes = smes;
    }

    public String getCons() {
        return cons;
    }

    public void setCons(String cons) {
        this.cons = cons;
    }
}