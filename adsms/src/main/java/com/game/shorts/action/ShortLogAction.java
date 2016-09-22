package com.game.shorts.action;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.game.comm.entity.ShortLogIvrLi;
import com.game.comm.entity.ShortLogIvrTyxx;
import com.game.comm.entity.ShortLogSdkback;
import com.game.comm.entity.ShortLogSmsDtmo;
import com.game.comm.entity.ShortLogSmsDtmr;
import com.game.comm.entity.ShortLogSmsKy;
import com.game.comm.entity.ShortLogSmsTyxx;
import com.game.comm.service.CityManager;
import com.game.comm.service.ShortLogSdkbackManager;
import com.game.docking.service.ShortLogSmsDtmoManager;
import com.game.docking.service.ShortLogSmsDtmrManager;
import com.game.docking.service.ShortLogSmsKyManager;
import com.game.modules.orm.Page;
import com.game.modules.orm.PropertyFilter;
import com.game.modules.orm.hibernate.HibernateUtils;
import com.game.modules.utils.DateUtil;
import com.game.modules.web.CrudActionSupport;
import com.game.modules.web.struts2.Struts2Utils;
import com.game.services.account.ResourceManager;
import com.game.shorts.entity.ShortLogAccess;
import com.game.shorts.entity.ShortLogErrorCmd;
import com.game.shorts.entity.ShortLogErrorNull;
import com.game.shorts.entity.ShortLogErrorUpdate;
import com.game.shorts.entity.ShortLogProductBack;
import com.game.shorts.entity.ShortLogSmsupload;
import com.game.shorts.entity.ShortLogVisitImsi;
import com.game.shorts.service.ShortLogAccessManager;
import com.game.shorts.service.ShortLogErrorCmdManager;
import com.game.shorts.service.ShortLogErrorNullManager;
import com.game.shorts.service.ShortLogErrorUpdateManager;
import com.game.shorts.service.ShortLogProductBackManager;
import com.game.shorts.service.ShortLogSmsuploadManager;
import com.game.shorts.service.ShortLogVisitImsiManager;
import com.game.util.DateUtils;

/**
 * 测试日志访问 Action.
 */
@Namespace("/shorts/shortLog")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "short-log.action?authId=${authId}", type = "redirect") })
public class ShortLogAction extends CrudActionSupport<ShortLogAccess> {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ShortLogAccessManager shortLogAccessManager;
    @Autowired
    private CityManager cityManager;
    @Autowired
    private ShortLogVisitImsiManager shortLogVisitImsiManager;
    @Autowired
    private ShortLogErrorCmdManager shortLogErrorCmdManager;
    @Autowired
    private ShortLogErrorNullManager shortLogErrorNullManager;
    @Autowired
    private ShortLogErrorUpdateManager shortLogErrorUpdateManager;
    @Autowired
    private ShortLogSdkbackManager shortLogSdkbackManager;
    @Autowired
    private ShortLogProductBackManager shortLogProductBackManager;
    @Autowired
    private ShortLogSmsuploadManager shortLogSmsuploadManager;
    @Autowired
    private ShortLogSmsDtmoManager moMnager;
    @Autowired
    private ShortLogSmsDtmrManager mrManger;
    @Autowired
    private ShortLogSmsKyManager kyManger;

    private Long id;
    /**
     * 多选操作使用
     */
    private List<Long> ids;
    private ShortLogAccess entity;
    private Page<ShortLogAccess> page = new Page<ShortLogAccess>(15);
    private Page<ShortLogVisitImsi> pageImsi = new Page<ShortLogVisitImsi>(15);
    private Page<ShortLogErrorCmd> pageErrorCmd = new Page<ShortLogErrorCmd>(15);
    private Page<ShortLogErrorNull> pageErrorNull = new Page<ShortLogErrorNull>(15);
    private Page<ShortLogErrorUpdate> pageErrorUpdate = new Page<ShortLogErrorUpdate>(15);
    private Page<ShortLogSdkback> pageSdkback = new Page<ShortLogSdkback>(15);
    private Page<ShortLogProductBack> pageProductBack = new Page<ShortLogProductBack>(15);
    private Page<ShortLogSmsupload> pageSmsupload = new Page<ShortLogSmsupload>(15);
    private Page<ShortLogIvrLi> pageIvrLi = new Page<ShortLogIvrLi>(15);
    private Page<ShortLogSmsTyxx> pageSmsTyxx = new Page<ShortLogSmsTyxx>(15);
    private Page<ShortLogIvrTyxx> pageIvrTyxx = new Page<ShortLogIvrTyxx>(15);
    private Page<ShortLogSmsDtmo> pageDtmo = new Page<ShortLogSmsDtmo>(15);
    private Page<ShortLogSmsDtmr> pageDtmr = new Page<ShortLogSmsDtmr>(15);
    private Page<ShortLogSmsKy> pageKy = new Page<ShortLogSmsKy>(15);

    private String begindate;
    private String enddate;

    @Override
    public ShortLogAccess getModel() {
        return entity;
    }

    @Override
    protected void prepareModel() throws Exception {
        if (null != id) {
            entity = shortLogAccessManager.get(id);
        } else {
            entity = new ShortLogAccess();
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

        if (StringUtils.isBlank(begindate)) {
            begindate = DateUtil.format(new Date(), "yyyy-MM-dd");
        }
        if (StringUtils.isBlank(enddate)) {
            enddate = DateUtil.format(new Date(), "yyyy-MM-dd");
        }

        filters.add(new PropertyFilter("GED_accesstime", begindate));
        filters.add(new PropertyFilter("LED_accesstime", DateUtil.format(DateUtils.getFDay(DateUtil.convertStringToDate("yyyy-MM-dd", enddate), -1), "yyyy-MM-dd")));
        page = shortLogAccessManager.searchPage(page, filters);

        // excel导出
        if (page.isExcelExp())
            return toExcel(page);

        return RELOAD;
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
        shortLogAccessManager.save(entity);
        addActionMessage("保存成功");
        if (flag) {
            this.logToDB(106, "新增ShortLogAccess-ID：" + entity.getId());
        } else {
            this.logToDB(106, "修改ShortLogAccess-ID：" + entity.getId());
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
        entity = shortLogAccessManager.get(id);
        return "detail";
    }

    /**
     * TODO 访问日志
     */
    public String access() throws Exception {
        List<PropertyFilter> filters = HibernateUtils.buildPropertyFilters(Struts2Utils.getRequest());
        // 设置默认排序方式
        if (!page.isOrderBySetted()) {
            page.setOrderBy("id");
            page.setOrder(Page.DESC);
        }

        if (StringUtils.isBlank(begindate)) {
            begindate = DateUtil.format(new Date(), "yyyy-MM-dd");
        }
        if (StringUtils.isBlank(enddate)) {
            enddate = DateUtil.format(new Date(), "yyyy-MM-dd");
        }

        filters.add(new PropertyFilter("GED_accesstime", begindate));
        filters.add(new PropertyFilter("LED_accesstime", DateUtil.format(DateUtils.getFDay(DateUtil.convertStringToDate("yyyy-MM-dd", enddate), -1), "yyyy-MM-dd")));
        page = shortLogAccessManager.searchPage(page, filters);

        // excel导出
        if (page.isExcelExp())
            return toExcel(page);

        return "access";
    }

    /**
     * 访问详细信息
     */
    public String accessDetail() {
        entity = shortLogAccessManager.get(id);
        return "accessDetail";
    }

    /**
     * TODO imsi访问日志
     */
    public String visitImsi() throws Exception {
        List<PropertyFilter> filters = HibernateUtils.buildPropertyFilters(Struts2Utils.getRequest());
        // 设置默认排序方式
        if (!pageImsi.isOrderBySetted()) {
            pageImsi.setOrderBy("id");
            pageImsi.setOrder(Page.DESC);
        }

        if (StringUtils.isBlank(begindate)) {
            begindate = DateUtil.format(new Date(), "yyyy-MM-dd");
        }
        if (StringUtils.isBlank(enddate)) {
            enddate = DateUtil.format(new Date(), "yyyy-MM-dd");
        }

        filters.add(new PropertyFilter("GED_accesstime", begindate));
        filters.add(new PropertyFilter("LED_accesstime", DateUtil.format(DateUtils.getFDay(DateUtil.convertStringToDate("yyyy-MM-dd", enddate), -1), "yyyy-MM-dd")));
        pageImsi = shortLogVisitImsiManager.searchPage(pageImsi, filters);
        return "visitImsi";
    }

    /**
     * TODO 命令执行反馈日志
     */
    public String errorCmd() throws Exception {
        List<PropertyFilter> filters = HibernateUtils.buildPropertyFilters(Struts2Utils.getRequest());
        // 设置默认排序方式
        if (!pageErrorCmd.isOrderBySetted()) {
            pageErrorCmd.setOrderBy("id");
            pageErrorCmd.setOrder(Page.DESC);
        }

        if (StringUtils.isBlank(begindate)) {
            begindate = DateUtil.format(new Date(), "yyyy-MM" + "-01");
        }
        if (StringUtils.isBlank(enddate)) {
            enddate = DateUtil.format(new Date(), "yyyy-MM-dd");
        }

        filters.add(new PropertyFilter("GED_accesstime", begindate));
        filters.add(new PropertyFilter("LED_accesstime", DateUtil.format(DateUtils.getFDay(DateUtil.convertStringToDate("yyyy-MM-dd", enddate), -1), "yyyy-MM-dd")));
        pageErrorCmd = shortLogErrorCmdManager.searchPage(pageErrorCmd, filters);
        return "errorCmd";
    }

    /**
     * TODO 更新反馈日志
     */
    public String errorUpdate() throws Exception {
        List<PropertyFilter> filters = HibernateUtils.buildPropertyFilters(Struts2Utils.getRequest());
        // 设置默认排序方式
        if (!pageErrorUpdate.isOrderBySetted()) {
            pageErrorUpdate.setOrderBy("id");
            pageErrorUpdate.setOrder(Page.DESC);
        }

        if (StringUtils.isBlank(begindate)) {
            begindate = DateUtil.format(new Date(), "yyyy-MM" + "-01");
        }
        if (StringUtils.isBlank(enddate)) {
            enddate = DateUtil.format(new Date(), "yyyy-MM-dd");
        }

        filters.add(new PropertyFilter("GED_accesstime", begindate));
        filters.add(new PropertyFilter("LED_accesstime", DateUtil.format(DateUtils.getFDay(DateUtil.convertStringToDate("yyyy-MM-dd", enddate), -1), "yyyy-MM-dd")));
        pageErrorUpdate = shortLogErrorUpdateManager.searchPage(pageErrorUpdate, filters);
        return "errorUpdate";
    }

    /**
     * TODO 空访问记录日志
     */
    public String errorNull() throws Exception {
        List<PropertyFilter> filters = HibernateUtils.buildPropertyFilters(Struts2Utils.getRequest());
        // 设置默认排序方式
        if (!pageErrorNull.isOrderBySetted()) {
            pageErrorNull.setOrderBy("id");
            pageErrorNull.setOrder(Page.DESC);
        }

        if (StringUtils.isBlank(begindate)) {
            begindate = DateUtil.format(new Date(), "yyyy-MM" + "-01");
        }
        if (StringUtils.isBlank(enddate)) {
            enddate = DateUtil.format(new Date(), "yyyy-MM-dd");
        }

        filters.add(new PropertyFilter("GED_accesstime", begindate));
        filters.add(new PropertyFilter("LED_accesstime", DateUtil.format(DateUtils.getFDay(DateUtil.convertStringToDate("yyyy-MM-dd", enddate), -1), "yyyy-MM-dd")));
        pageErrorNull = shortLogErrorNullManager.searchPage(pageErrorNull, filters);
        return "errorNull";
    }

    /**
     * TODO sdk反馈日志(sms-Li)
     */
    public String sdkback() throws ParseException {
        List<PropertyFilter> filters = HibernateUtils.buildPropertyFilters(Struts2Utils.getRequest());
        // 设置默认排序方式
        if (!pageSdkback.isOrderBySetted()) {
            pageSdkback.setOrderBy("id");
            pageSdkback.setOrder(Page.DESC);
        }
        if (StringUtils.isBlank(begindate)) {
            begindate = DateUtil.format(new Date(), "yyyy-MM" + "-01");
        }
        if (StringUtils.isBlank(enddate)) {
            enddate = DateUtil.format(new Date(), "yyyy-MM-dd");
        }
        filters.add(new PropertyFilter("GED_accesstime", begindate));
        filters.add(new PropertyFilter("LED_accesstime", DateUtil.format(DateUtils.getFDay(DateUtil.convertStringToDate("yyyy-MM-dd", enddate), -1), "yyyy-MM-dd")));
        pageSdkback = shortLogSdkbackManager.searchPage(pageSdkback, filters);
        return "sdkback";
    }

    public String ivrLi() throws ParseException {
        List<PropertyFilter> filters = HibernateUtils.buildPropertyFilters(Struts2Utils.getRequest());
        // 设置默认排序方式
        if (!pageIvrLi.isOrderBySetted()) {
            pageIvrLi.setOrderBy("id");
            pageIvrLi.setOrder(Page.DESC);
        }
        if (StringUtils.isBlank(begindate)) {
            begindate = DateUtil.format(new Date(), "yyyy-MM" + "-01");
        }
        if (StringUtils.isBlank(enddate)) {
            enddate = DateUtil.format(new Date(), "yyyy-MM-dd");
        }
        filters.add(new PropertyFilter("GED_accesstime", begindate));
        filters.add(new PropertyFilter("LED_accesstime", DateUtil.format(DateUtils.getFDay(DateUtil.convertStringToDate("yyyy-MM-dd", enddate), -1), "yyyy-MM-dd")));
        pageIvrLi = shortLogSdkbackManager.searchPageIvrLi(pageIvrLi, filters);
        return "ivrLi";
    }

    public String ivrTyxx() throws ParseException {
        List<PropertyFilter> filters = HibernateUtils.buildPropertyFilters(Struts2Utils.getRequest());
        // 设置默认排序方式
        if (!pageIvrTyxx.isOrderBySetted()) {
            pageIvrTyxx.setOrderBy("id");
            pageIvrTyxx.setOrder(Page.DESC);
        }
        if (StringUtils.isBlank(begindate)) {
            begindate = DateUtil.format(new Date(), "yyyy-MM" + "-01");
        }
        if (StringUtils.isBlank(enddate)) {
            enddate = DateUtil.format(new Date(), "yyyy-MM-dd");
        }
        filters.add(new PropertyFilter("GED_accesstime", begindate));
        filters.add(new PropertyFilter("LED_accesstime", DateUtil.format(DateUtils.getFDay(DateUtil.convertStringToDate("yyyy-MM-dd", enddate), -1), "yyyy-MM-dd")));
        pageIvrTyxx = shortLogSdkbackManager.searchPageIvrTyxx(pageIvrTyxx, filters);
        return "ivrTyxx";
    }

    public String smsTyxx() throws ParseException {
        List<PropertyFilter> filters = HibernateUtils.buildPropertyFilters(Struts2Utils.getRequest());
        // 设置默认排序方式
        if (!pageSmsTyxx.isOrderBySetted()) {
            pageSmsTyxx.setOrderBy("id");
            pageSmsTyxx.setOrder(Page.DESC);
        }
        if (StringUtils.isBlank(begindate)) {
            begindate = DateUtil.format(new Date(), "yyyy-MM" + "-01");
        }
        if (StringUtils.isBlank(enddate)) {
            enddate = DateUtil.format(new Date(), "yyyy-MM-dd");
        }
        filters.add(new PropertyFilter("GED_accesstime", begindate));
        filters.add(new PropertyFilter("LED_accesstime", DateUtil.format(DateUtils.getFDay(DateUtil.convertStringToDate("yyyy-MM-dd", enddate), -1), "yyyy-MM-dd")));
        pageSmsTyxx = shortLogSdkbackManager.searchPageSmsTyxx(pageSmsTyxx, filters);
        return "smsTyxx";
    }
    
    public String smsDtmo() throws ParseException {
    	List<PropertyFilter> filters = HibernateUtils.buildPropertyFilters(Struts2Utils.getRequest());
    	// 设置默认排序方式
    	if (!pageDtmo.isOrderBySetted()) {
    		pageDtmo.setOrderBy("id");
    		pageDtmo.setOrder(Page.DESC);
    	}
    	if (StringUtils.isBlank(begindate)) {
    		begindate = DateUtil.format(new Date(), "yyyy-MM" + "-01");
    	}
    	if (StringUtils.isBlank(enddate)) {
    		enddate = DateUtil.format(new Date(), "yyyy-MM-dd");
    	}
    	filters.add(new PropertyFilter("GED_accesstime", begindate));
    	filters.add(new PropertyFilter("LED_accesstime", DateUtil.format(DateUtils.getFDay(DateUtil.convertStringToDate("yyyy-MM-dd", enddate), -1), "yyyy-MM-dd")));
    	pageDtmo = moMnager.searchPageSmsDtmo(pageDtmo, filters);
    	return "smsDtmo";
    }
    
    public String smsDtmr() throws ParseException {
    	List<PropertyFilter> filters = HibernateUtils.buildPropertyFilters(Struts2Utils.getRequest());
    	// 设置默认排序方式
    	if (!pageDtmr.isOrderBySetted()) {
    		pageDtmr.setOrderBy("id");
    		pageDtmr.setOrder(Page.DESC);
    	}
    	if (StringUtils.isBlank(begindate)) {
    		begindate = DateUtil.format(new Date(), "yyyy-MM" + "-01");
    	}
    	if (StringUtils.isBlank(enddate)) {
    		enddate = DateUtil.format(new Date(), "yyyy-MM-dd");
    	}
    	filters.add(new PropertyFilter("GED_accesstime", begindate));
    	filters.add(new PropertyFilter("LED_accesstime", DateUtil.format(DateUtils.getFDay(DateUtil.convertStringToDate("yyyy-MM-dd", enddate), -1), "yyyy-MM-dd")));
    	pageDtmr = mrManger.searchPageSmsDtmr(pageDtmr, filters);
    	return "smsDtmr";
    }
    
    public String smsKy() throws ParseException {
    	List<PropertyFilter> filters = HibernateUtils.buildPropertyFilters(Struts2Utils.getRequest());
    	// 设置默认排序方式
    	if (!pageKy.isOrderBySetted()) {
    		pageKy.setOrderBy("id");
    		pageKy.setOrder(Page.DESC);
    	}
    	if (StringUtils.isBlank(begindate)) {
    		begindate = DateUtil.format(new Date(), "yyyy-MM" + "-01");
    	}
    	if (StringUtils.isBlank(enddate)) {
    		enddate = DateUtil.format(new Date(), "yyyy-MM-dd");
    	}
    	filters.add(new PropertyFilter("GED_accesstime", begindate));
    	filters.add(new PropertyFilter("LED_accesstime", DateUtil.format(DateUtils.getFDay(DateUtil.convertStringToDate("yyyy-MM-dd", enddate), -1), "yyyy-MM-dd")));
    	pageKy = kyManger.searchPageSmsKy(pageKy, filters);
    	return "smsKy";
    }
    
    
    
    /**
     * TODO 产品反馈
     */
    public String productBack() throws ParseException {
        List<PropertyFilter> filters = HibernateUtils.buildPropertyFilters(Struts2Utils.getRequest());
        // 设置默认排序方式
        if (!pageProductBack.isOrderBySetted()) {
            pageProductBack.setOrderBy("id");
            pageProductBack.setOrder(Page.DESC);
        }
        if (StringUtils.isBlank(begindate)) {
            begindate = DateUtil.format(new Date(), "yyyy-MM" + "-01");
        }
        if (StringUtils.isBlank(enddate)) {
            enddate = DateUtil.format(new Date(), "yyyy-MM-dd");
        }
        filters.add(new PropertyFilter("GED_accesstime", begindate));
        filters.add(new PropertyFilter("LED_accesstime", DateUtil.format(DateUtils.getFDay(DateUtil.convertStringToDate("yyyy-MM-dd", enddate), -1), "yyyy-MM-dd")));
        pageProductBack = shortLogProductBackManager.searchPage(pageProductBack, filters);
        return "productBack";
    }

    /**
     * TODO 号码上传
     */
    public String smsupload() throws ParseException {
        List<PropertyFilter> filters = HibernateUtils.buildPropertyFilters(Struts2Utils.getRequest());
        // 设置默认排序方式
        if (!pageSmsupload.isOrderBySetted()) {
            pageSmsupload.setOrderBy("id");
            pageSmsupload.setOrder(Page.DESC);
        }
        if (StringUtils.isBlank(begindate)) {
            begindate = DateUtil.format(new Date(), "yyyy-MM" + "-01");
        }
        if (StringUtils.isBlank(enddate)) {
            enddate = DateUtil.format(new Date(), "yyyy-MM-dd");
        }
        filters.add(new PropertyFilter("GED_accesstime", begindate));
        filters.add(new PropertyFilter("LED_accesstime", DateUtil.format(DateUtils.getFDay(DateUtil.convertStringToDate("yyyy-MM-dd", enddate), -1), "yyyy-MM-dd")));
        pageSmsupload = shortLogSmsuploadManager.searchPage(pageSmsupload, filters);
        return "smsupload";
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

    public ShortLogAccess getEntity() {
        return entity;
    }

    public void setEntity(ShortLogAccess entity) {
        this.entity = entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Page<ShortLogAccess> getPage() {
        return page;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public void setPage(Page<ShortLogAccess> page) {
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

    public Page<ShortLogVisitImsi> getPageImsi() {
        return pageImsi;
    }

    public void setPageImsi(Page<ShortLogVisitImsi> pageImsi) {
        this.pageImsi = pageImsi;
    }

    public Page<ShortLogErrorCmd> getPageErrorCmd() {
        return pageErrorCmd;
    }

    public void setPageErrorCmd(Page<ShortLogErrorCmd> pageErrorCmd) {
        this.pageErrorCmd = pageErrorCmd;
    }

    public Page<ShortLogErrorNull> getPageErrorNull() {
        return pageErrorNull;
    }

    public void setPageErrorNull(Page<ShortLogErrorNull> pageErrorNull) {
        this.pageErrorNull = pageErrorNull;
    }

    public Page<ShortLogErrorUpdate> getPageErrorUpdate() {
        return pageErrorUpdate;
    }

    public void setPageErrorUpdate(Page<ShortLogErrorUpdate> pageErrorUpdate) {
        this.pageErrorUpdate = pageErrorUpdate;
    }

    public Page<ShortLogSdkback> getPageSdkback() {
        return pageSdkback;
    }

    public void setPageSdkback(Page<ShortLogSdkback> pageSdkback) {
        this.pageSdkback = pageSdkback;
    }

    public Page<ShortLogProductBack> getPageProductBack() {
        return pageProductBack;
    }

    public void setPageProductBack(Page<ShortLogProductBack> pageProductBack) {
        this.pageProductBack = pageProductBack;
    }

    public Page<ShortLogSmsupload> getPageSmsupload() {
        return pageSmsupload;
    }

    public void setPageSmsupload(Page<ShortLogSmsupload> pageSmsupload) {
        this.pageSmsupload = pageSmsupload;
    }

    public Page<ShortLogIvrLi> getPageIvrLi() {
        return pageIvrLi;
    }

    public void setPageIvrLi(Page<ShortLogIvrLi> pageIvrLi) {
        this.pageIvrLi = pageIvrLi;
    }

    public Page<ShortLogSmsTyxx> getPageSmsTyxx() {
        return pageSmsTyxx;
    }

    public void setPageSmsTyxx(Page<ShortLogSmsTyxx> pageSmsTyxx) {
        this.pageSmsTyxx = pageSmsTyxx;
    }

    public Page<ShortLogIvrTyxx> getPageIvrTyxx() {
        return pageIvrTyxx;
    }

    public void setPageIvrTyxx(Page<ShortLogIvrTyxx> pageIvrTyxx) {
        this.pageIvrTyxx = pageIvrTyxx;
    }

	public Page<ShortLogSmsDtmo> getPageDtmo() {
		return pageDtmo;
	}

	public void setPageDtmo(Page<ShortLogSmsDtmo> pageDtmo) {
		this.pageDtmo = pageDtmo;
	}

	public Page<ShortLogSmsDtmr> getPageDtmr() {
		return pageDtmr;
	}

	public void setPageDtmr(Page<ShortLogSmsDtmr> pageDtmr) {
		this.pageDtmr = pageDtmr;
	}

	public Page<ShortLogSmsKy> getPageKy() {
		return pageKy;
	}

	public void setPageKy(Page<ShortLogSmsKy> pageKy) {
		this.pageKy = pageKy;
	}
	
    
}