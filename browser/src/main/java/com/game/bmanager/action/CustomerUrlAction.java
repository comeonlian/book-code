package com.game.bmanager.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.game.bmanager.entity.Customer;
import com.game.bmanager.entity.CustomerUrl;
import com.game.bmanager.service.CustomerManager;
import com.game.bmanager.service.CustomerUrlManager;
import com.game.comm.service.CityManager;
import com.game.entity.account.User;
import com.game.modules.orm.Page;
import com.game.modules.orm.PropertyFilter;
import com.game.modules.orm.hibernate.HibernateUtils;
import com.game.modules.web.CrudActionSupport;
import com.game.modules.web.struts2.Struts2Utils;

@Namespace("/bmanager/url")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "customer-url.action?authId=${authId}", type = "redirect") })
public class CustomerUrlAction extends CrudActionSupport<CustomerUrl> {
	/**
	 */
	private static final long serialVersionUID = -4338083078681494861L;
	//分页信息
	private Long id;
    private List<Long> ids;
    private CustomerUrl entity;
    private Page<CustomerUrl> page = new Page<CustomerUrl>(15);
    private Page<Customer> pageResource = new Page<Customer>(15);
    //查询条件
    private String customid;
    private String urlname;
    private Integer status;
    
    private String treexml; // 省市树
	
	@Autowired
	private CustomerUrlManager urlManager;
	@Autowired
    private CityManager cityManager;
	@Autowired
    private CustomerManager customerManager;
	
	
	@Override
	protected void prepareModel() throws Exception {
		if (null != id) {
            entity = urlManager.get(id);
        } else {
            entity = new CustomerUrl();
        }
	}
	
	@Override
	public CustomerUrl getModel() {
		return entity;
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = HibernateUtils.buildPropertyFilters(Struts2Utils.getRequest());
        // 设置默认排序方式
        if (!page.isOrderBySetted()) {
            page.setOrderBy("id");
            page.setOrder(Page.DESC);
        }
        if(StringUtils.isNotBlank(customid)){
			filters.add(new PropertyFilter("LIKES_customid", customid));
		}
        if(StringUtils.isNotBlank(urlname)){
        	filters.add(new PropertyFilter("LIKES_urlname", urlname));
        }
		if(null != status){
			filters.add(new PropertyFilter("EQI_status", status.toString()));
		}else{
			filters.add(new PropertyFilter("INI_status", "0,1"));
		}

        page = urlManager.searchPage(page, filters);

        return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		treexml = cityManager.findTreeXml();
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		User user = getUser();
		if(entity.getId()==null){
			entity.setCreateby(user.getUsername());
			entity.setCreatetime(new Date());
		}else{
			entity.setModifyby(user.getUsername());
			entity.setModifytime(new Date());
		}
		urlManager.save(entity);
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		return RELOAD;
	}
	
	/**
     * 选择资源客户
     * 
     * @return
     * @throws Exception
     */
    public String selectresource() throws Exception {
        List<PropertyFilter> filters = HibernateUtils.buildPropertyFilters(Struts2Utils.getRequest());
        // 设置默认排序方式
        if (!pageResource.isOrderBySetted()) {
            pageResource.setOrderBy("id");
            pageResource.setOrder(Page.DESC);
        }
        if(StringUtils.isNotBlank(customid)){
			filters.add(new PropertyFilter("LIKES_customerid", customid));
		}
        filters.add(new PropertyFilter("EQI_status", "1"));
        pageResource = customerManager.searchPage(pageResource, filters);
        return "select";
    }
	
	/*--------------------------getter and setter----------------------------*/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public String getTreexml() {
		return treexml;
	}

	public void setTreexml(String treexml) {
		this.treexml = treexml;
	}

	public CustomerUrlManager getUrlManager() {
		return urlManager;
	}

	public void setUrlManager(CustomerUrlManager urlManager) {
		this.urlManager = urlManager;
	}

	public CustomerUrl getEntity() {
		return entity;
	}

	public void setEntity(CustomerUrl entity) {
		this.entity = entity;
	}

	public Page<CustomerUrl> getPage() {
		return page;
	}

	public void setPage(Page<CustomerUrl> page) {
		this.page = page;
	}

	public String getCustomid() {
		return customid;
	}

	public void setCustomid(String customid) {
		this.customid = customid;
	}

	public String getUrlname() {
		return urlname;
	}

	public void setUrlname(String urlname) {
		this.urlname = urlname;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Page<Customer> getPageResource() {
		return pageResource;
	}

	public void setPageResource(Page<Customer> pageResource) {
		this.pageResource = pageResource;
	}
	
	
}
