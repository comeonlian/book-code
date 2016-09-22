package com.game.copartner.action;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.game.comm.service.CityManager;
import com.game.copartner.entity.ProviderDaily;
import com.game.copartner.service.ProviderDailyManager;
import com.game.entity.account.User;
import com.game.modules.orm.Page;
import com.game.modules.orm.PropertyFilter;
import com.game.modules.orm.hibernate.HibernateUtils;
import com.game.modules.security.springsecurity.SpringSecurityUtils;
import com.game.modules.utils.DateUtil;
import com.game.modules.web.CrudActionSupport;
import com.game.modules.web.struts2.Struts2Utils;
import com.game.services.account.AccountManager;
import com.game.services.account.ResourceManager;
import com.game.util.Constants;
import com.game.util.DateUtils;

/**
 * 合作客户数据管理Action.
 * 
 * @author srain12
 */
@Namespace("/copartner/providerDaily")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "provider-daily.action?authId=${authId}&oldcustomid=${oldcustomid}&cflag=${cflag}", type = "redirect") })
public class ProviderDailyAction extends CrudActionSupport<ProviderDaily> {

    private static final long serialVersionUID = 1L;
    /** 开放客户列表长度：客户 */
    private static final int OPEN_VIEW_TDLENTH_PARTNER = 3;
    /** 开放客户列表长度：其他 */
    private static final int OPEN_VIEW_TDLENTH_OTHER = 4;

    @Autowired
    private ProviderDailyManager providerDailyManager;

    @Autowired
    private CityManager cityManager;

    @Autowired
    private AccountManager accountManager;

    private Long id;
    /**
     * 多选操作使用
     */
    private List<Long> ids;
    private ProviderDaily entity;
    private Page<ProviderDaily> page = new Page<ProviderDaily>(15);

    private String treexml; // 省市树

    private Page<Map<String, Object>> userPage = new Page<Map<String, Object>>(15);
    private Integer userTotal;

    // 开放数据
    private Integer profitTotal; // 总收益
    private Integer activeTotal; // 激活总量
    private Integer trnum; // 表格数

    /** 查询条件 ****/
    private String begindate;
    private String enddate;
    private String customerid;

    @Override
    public ProviderDaily getModel() {
        return entity;
    }

    @Override
    protected void prepareModel() throws Exception {
        if (null != id) {
            entity = providerDailyManager.get(id);
        } else {
            entity = new ProviderDaily();
        }
    }

    // -- CRUD Action 函数 --//
    /**
     * list页面显示用户分页列表.
     */
    @Override
    public String list() throws Exception {
        Date date = new Date();
        if (StringUtils.isBlank(begindate)) {
            begindate = DateUtil.format(date, "yyyy-MM") + "-01";
        }
        if (StringUtils.isBlank(enddate)) {
            enddate = DateUtil.format(date, "yyyy-MM-dd");
        }
        List<PropertyFilter> filters = HibernateUtils.buildPropertyFilters(Struts2Utils.getRequest());
        // 设置默认排序方式
        if (!page.isOrderBySetted()) {
            page.setOrderBy("currentdate,customerId");
            page.setOrder("desc,asc");
        }

        if (StringUtils.isNotBlank(begindate)) {
            filters.add(new PropertyFilter("GES_currentdate", begindate));
        }
        if (StringUtils.isNotBlank(enddate)) {
            filters.add(new PropertyFilter("LTS_currentdate", DateUtils.nextDate(enddate)));
        }

        page = providerDailyManager.searchPage(page, filters);
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
        providerDailyManager.save(entity);
        addActionMessage("保存资源成功");
        if (flag) {
            this.logToDB(106, "新增ProviderDaily-ID：" + entity.getId());
        } else {
            this.logToDB(106, "修改ProviderDaily-ID：" + entity.getId());
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
            if (providerDailyManager.delAll(ids)) {
                this.addActionMessage("删除成功！！！");
                this.logToDB(106, "删除客户广告-ID：" + ids);
            } else {
                this.addActionMessage("删除失败！！！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            addActionMessage("客户广告-使用中无法删除！！！");
        }

        return RELOAD;
    }

    /**
     * 开放客户每日数据
     */
    public String customerDailyView() {
//        Date date = new Date();
//        if (StringUtils.isBlank(begindate)) {
//            begindate = DateUtil.format(date, "yyyy-MM") + "-01";
//        }
//        if (StringUtils.isBlank(enddate)) {
//            enddate = DateUtil.format(date, "yyyy-MM-dd");
//        }
        trnum = OPEN_VIEW_TDLENTH_OTHER;
        User user = SpringSecurityUtils.getCurrentCoreUser();
        user = accountManager.getUser(user.getId());
        String dominCode = user.getDomain().getCode();
        String user_customerId = user.getCustomId();
        List<PropertyFilter> filters = HibernateUtils.buildPropertyFilters(Struts2Utils.getRequest());
        // 设置默认排序方式
        if (!page.isOrderBySetted()) {
            page.setOrderBy("currentdate,customerId");
            page.setOrder("desc,asc");
        }
        if (StringUtils.isNotBlank(begindate)) {
            filters.add(new PropertyFilter("GES_currentdate", begindate));
        }
        if (StringUtils.isNotBlank(enddate)) {
            filters.add(new PropertyFilter("LTS_currentdate", DateUtils.nextDate(enddate)));
        }

        if (StringUtils.isNotBlank(user_customerId)) {
            if (Constants.DOMAIN_PARTNER.equals(dominCode)) {
                filters.add(new PropertyFilter("EQS_customerId", user_customerId));
                trnum = OPEN_VIEW_TDLENTH_PARTNER;
            }
            if (Constants.DOMAIN_BUSINESS.equals(dominCode)) {
                filters.add(new PropertyFilter("INS_customerId", user_customerId));
            }
        }
        filters.add(new PropertyFilter("EQI_status", "1"));
        page = providerDailyManager.searchPage(page, filters);
        if (page.getTotalCount() > 0) {
            setProfitTotal(providerDailyManager.getOpenPrice(begindate, enddate, user_customerId, dominCode));
            setActiveTotal(providerDailyManager.getOpenActivity(begindate, enddate, user_customerId, dominCode));
        }
        // excel导出
        if (page.isExcelExp())
            return toExcel(page);

        return "customerDailyView";
    }

    // *************** 新增、活跃用户、留存率等 *********************
    public String statRetention() throws ParseException {
        Date date = new Date();
        if (StringUtils.isBlank(begindate)) {
            begindate = DateUtil.format(date, "yyyy-MM") + "-01";
        }
        if (StringUtils.isBlank(enddate)) {
            enddate = DateUtil.format(date, "yyyy-MM-dd");
        }
        userPage = providerDailyManager.getRetentions(userPage, begindate,
                DateUtil.format(DateUtils.getFDay(DateUtil.convertStringToDate("yyyy-MM-dd", enddate), -1), "yyyy-MM-dd"), customerid);
        return "statRetention";
    }

    public String statActivitys() throws ParseException {
        Date date = new Date();
        if (StringUtils.isBlank(begindate)) {
            begindate = DateUtil.format(date, "yyyy-MM") + "-01";
        }
        if (StringUtils.isBlank(enddate)) {
            enddate = DateUtil.format(date, "yyyy-MM-dd");
        }
        userPage = providerDailyManager.getActivitiusers(userPage, begindate, enddate, customerid);
        return "statActivitys";
    }

    public String statTotalUser() {

        userTotal = providerDailyManager.getTotalByCusid(customerid);
        userPage = providerDailyManager.getOverviewUser(userPage, customerid);
        return "statTotalUser";
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

    public ProviderDaily getEntity() {
        return entity;
    }

    public void setEntity(ProviderDaily entity) {
        this.entity = entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Page<ProviderDaily> getPage() {
        return page;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public void setPage(Page<ProviderDaily> page) {
        this.page = page;
    }

    public String getTreexml() {
        return treexml;
    }

    public void setTreexml(String treexml) {
        this.treexml = treexml;
    }

    public Page<Map<String, Object>> getUserPage() {
        return userPage;
    }

    public void setUserPage(Page<Map<String, Object>> userPage) {
        this.userPage = userPage;
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

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public Integer getUserTotal() {
        return userTotal;
    }

    public void setUserTotal(Integer userTotal) {
        this.userTotal = userTotal;
    }

    public Integer getProfitTotal() {
        return profitTotal;
    }

    public void setProfitTotal(Integer profitTotal) {
        this.profitTotal = profitTotal;
    }

    public Integer getActiveTotal() {
        return activeTotal;
    }

    public void setActiveTotal(Integer activeTotal) {
        this.activeTotal = activeTotal;
    }

    public Integer getTrnum() {
        return trnum;
    }

    public void setTrnum(Integer trnum) {
        this.trnum = trnum;
    }

}