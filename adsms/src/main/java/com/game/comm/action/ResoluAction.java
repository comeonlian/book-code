package com.game.comm.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.game.comm.entity.Resolution;
import com.game.comm.service.ResolutionService;
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

@Namespace("/common/resolu")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "resolu.action?authId=${authId}", type = "redirect")})
public class ResoluAction extends CrudActionSupport<Resolution> {	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Autowired
	    private ResolutionService resolutionService;

	    @Autowired
	    private AccountManager accountManager;
	    private Long id;

		/**
	     * 多选操作使用
	     */
	    private List<Long> ids;
	    private Resolution entity;
	    private Page<Resolution> page = new Page<Resolution>(15);
	    private List<LabelValue> resTypeLabelValueList;
	    

	    @Override
	    public Resolution getModel() {
	        return entity;
	    }

	    @Override
	    protected void prepareModel() throws Exception {
	        if (null != id) {
	            entity = resolutionService.get(id);
	        } else {
	            entity = new Resolution();
	        }
	    }

	    // -- CRUD Action 函数 --//
	    /**
	     * list页面显示用户分页列表.
	     */
	    @Override
	    public String list() throws Exception {
	        resTypeLabelValueList = LabelValueUtils.getResStringLabelValueList();
	        List<PropertyFilter> filters = HibernateUtils.buildPropertyFilters(Struts2Utils.getRequest());
	        // 设置默认排序方式
	        if (!page.isOrderBySetted()) {
	            page.setOrderBy("id");
	            page.setOrder(Page.DESC);
	        }
	        
	        page = resolutionService.searchPage(page, filters);
	        // excel导出
	        if (page.isExcelExp())
	            return toExcel(page);

	        return SUCCESS;
	    }
	    
	    /**
	     * 详情界面
	     * 
	     * @return
	     */
	    public String detail() {
	    	entity = resolutionService.get(id);
	    	return "detail";
	    }
	    
	    @Override
	    public String input() throws Exception {
	    	if (id != null) {
	    		
	    	}
	        return INPUT;
	    }

	    @Override
	    public String save() throws Exception {
	    	User user = accountManager.getUser(SpringSecurityUtils.getCurrentCoreUser().getId());
	        boolean flag = false;
	        
	        String rel=entity.getResolution();				
			String[] re=rel.split("X");
			if(re.length==2)
			{
				entity.setWidth(Integer.parseInt(re[0]));
				entity.setHeight(Integer.parseInt(re[1]));
			}			
	        if (entity.getId() == null) {
	            flag = true;
	            //resolutionService.save(entity);
	        }	        
		/*	if (id == null) {
				entity.setCreateTime(new Date());
				entity.setCreateBy(user.getUsername());
			} else {
				entity.setLastModifyBy(user.getUsername());
				entity.setLastModifyTime(new Date());
			}*/      
			resolutionService.save(entity);
	        addActionMessage("保存成功!");
	        if (flag) {
	            this.logToDB(106, user.getUsername()+"新增分辨率ID：" + entity.getId());
	        } else {
	            this.logToDB(106, user.getUsername()+"修改分辨率ID：" + entity.getId());
	        }
	        return RELOAD;
	    }
	    
	    @Override
	    public String delete() throws Exception {
	        addActionMessage("删除成功！");
	        return RELOAD;
	    }

	    public String delAll() throws Exception {
	        try {
	            Assert.notEmpty(ids, "没有选择删除列！！！");
	            if (resolutionService.delAll(ids)) {
	                this.addActionMessage("删除成功！！！");
	                User user = accountManager.getUser(SpringSecurityUtils.getCurrentCoreUser().getId());
	                this.logToDB(106, user.getUsername()+"删除分辨率ID：" + ids);
	            } else {
	                this.addActionMessage("删除失败！！！");
	            }
	        } catch (Exception e) {
	            logger.error(e.getMessage(), e);
	            addActionMessage("删除异常！！！");
	        }
	        return RELOAD;
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

	    //************* getting and setting *****************
	    public Resolution getEntity() {
	        return entity;
	    }

	    public void setEntity(Resolution entity) {
	        this.entity = entity;
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public Page<Resolution> getPage() {
	        return page;
	    }

	    public List<Long> getIds() {
	        return ids;
	    }

	    public void setIds(List<Long> ids) {
	        this.ids = ids;
	    }

	    public void setPage(Page<Resolution> page) {
	        this.page = page;
	    }

	    public List<LabelValue> getResTypeLabelValueList() {
	        return resTypeLabelValueList;
	    }

	    public void setResTypeLabelValueList(List<LabelValue> resTypeLabelValueList) {
	        this.resTypeLabelValueList = resTypeLabelValueList;
	    }		
}