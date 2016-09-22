package com.game.ota.action;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.game.comm.entity.City;
import com.game.comm.service.CityManager;
import com.game.modules.orm.Page;
import com.game.modules.orm.PropertyFilter;
import com.game.modules.orm.hibernate.HibernateUtils;
import com.game.modules.utils.DateUtil;
import com.game.modules.web.CrudActionSupport;
import com.game.modules.web.struts2.Struts2Utils;
import com.game.ota.entity.OtaLogAccess;
import com.game.ota.entity.OtaLogSmsupload;
import com.game.ota.service.OtaLogAccessManager;
import com.game.ota.service.OtaLogSmsuploadManager;
import com.game.services.account.ResourceManager;
import com.game.util.DateUtils;

/**
 * 测试日志访问 Action.
 */
@Namespace("/ota/otaLog")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "ota-log.action?authId=${authId}", type = "redirect") })
public class OtaLogAction extends CrudActionSupport<OtaLogAccess> {

    private static final long serialVersionUID = 1L;

    @Autowired
    private OtaLogAccessManager otaLogAccessManager;
    @Autowired
    private OtaLogSmsuploadManager otaLogSmsuploadManager;
    @Autowired
    private CityManager cityManager;

    private Long id;
    /**
     * 多选操作使用
     */
    private List<Long> ids;
    private OtaLogAccess entity;
    private Page<OtaLogAccess> page = new Page<OtaLogAccess>(15);
    private Page<OtaLogSmsupload> pageSmsupload = new Page<OtaLogSmsupload>(15);

    private String begindate;
    private String enddate;

    @Override
    public OtaLogAccess getModel() {
        return entity;
    }

    @Override
    protected void prepareModel() throws Exception {
        if (null != id) {
            entity = otaLogAccessManager.get(id);
        } else {
            entity = new OtaLogAccess();
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
        page = otaLogAccessManager.searchPage(page, filters);

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
        otaLogAccessManager.save(entity);
        addActionMessage("保存成功");
        if (flag) {
            this.logToDB(106, "新增OtaLogAccess-ID：" + entity.getId());
        } else {
            this.logToDB(106, "修改OtaLogAccess-ID：" + entity.getId());
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
        entity = otaLogAccessManager.get(id);
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
        page = otaLogAccessManager.searchPage(page, filters);

        List<City> ctlist = cityManager.getAll();

        for (OtaLogAccess log : page.getResult()) {
            Long citycode = log.getCountryId();
            if (citycode != null) {
                for (City c : ctlist) {
                    if (citycode.equals(c.getId())) {
                        log.setCountryName(c.getName());
                        break;
                    }
                }
            }
        }

        // excel导出
        if (page.isExcelExp())
            return toExcel(page);

        return "access";
    }

    /**
     * 访问详细信息
     */
    public String accessDetail() {
        entity = otaLogAccessManager.get(id);
        return "accessDetail";
    }

    /**
     * sms
     */
    public String smsupload() throws ParseException {
        List<PropertyFilter> filters = HibernateUtils.buildPropertyFilters(Struts2Utils.getRequest());
        // 设置默认排序方式
        if (!pageSmsupload.isOrderBySetted()) {
            pageSmsupload.setOrderBy("id");
            pageSmsupload.setOrder(Page.DESC);
        }

        if (StringUtils.isBlank(begindate)) {
            begindate = DateUtil.format(new Date(), "yyyy-MM") + "-01";
        }
        if (StringUtils.isBlank(enddate)) {
            enddate = DateUtil.format(new Date(), "yyyy-MM-dd");
        }
        if (StringUtils.isNotBlank(begindate)) {
            filters.add(new PropertyFilter("GED_accesstime", begindate));
        }
        if (StringUtils.isNotBlank(enddate)) {
            filters.add(new PropertyFilter("LED_accesstime", DateUtil.format(DateUtils.getFDay(DateUtil.convertStringToDate("yyyy-MM-dd", enddate), -1), "yyyy-MM-dd")));
        }
        pageSmsupload = otaLogSmsuploadManager.searchPage(pageSmsupload, filters);
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

    public OtaLogAccess getEntity() {
        return entity;
    }

    public void setEntity(OtaLogAccess entity) {
        this.entity = entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Page<OtaLogAccess> getPage() {
        return page;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public void setPage(Page<OtaLogAccess> page) {
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

    public Page<OtaLogSmsupload> getPageSmsupload() {
        return pageSmsupload;
    }

    public void setPageSmsupload(Page<OtaLogSmsupload> pageSmsupload) {
        this.pageSmsupload = pageSmsupload;
    }

}