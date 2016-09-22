package com.game.comm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.game.comm.entity.CommonCustom;
import com.game.comm.service.CommonCustomeService;
import com.game.entity.LabelValue;
import com.game.modules.orm.Page;
import com.game.modules.orm.PropertyFilter;
import com.game.modules.orm.hibernate.HibernateUtils;
import com.game.modules.web.CrudActionSupport;
import com.game.modules.web.struts2.Struts2Utils;
import com.game.services.account.ResourceManager;

@Namespace("/common/custom")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "custom.action?authId=${authId}", type = "redirect")})
public class CustomAction extends CrudActionSupport<CommonCustom> {	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		@Autowired
	    private CommonCustomeService commonCustomeService;

	    private Long id;

		/**
	     * 多选操作使用
	     */
	    private List<Long> ids;
	    private CommonCustom entity;
	    private Page<CommonCustom> page = new Page<CommonCustom>(15);
	    private List<LabelValue> resTypeLabelValueList;
	    private String customid;

	    @Override
	    public CommonCustom getModel() {
	        return entity;
	    }

	    @Override
	    protected void prepareModel() throws Exception {
	        if (null != id) {
	            entity = commonCustomeService.get(id);
	        } else {
	            entity = new CommonCustom();
	            entity.setCreatetime(sdf.format(new Date()));
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
	            page.setOrderBy("createtime");
	            page.setOrder(Page.DESC);
	        }
	        
	        page = commonCustomeService.searchPage(page, filters);
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
	    	int e=0;
	    	if(entity.getIsdefault()==1)//是默认客户
	        {
	        	e=commonCustomeService.editdefaultcustom(entity.getId());
	        }
    		 commonCustomeService.save(entity);
    		 addActionMessage("保存成功!");
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
	            if (commonCustomeService.delAll(ids)) {
	                this.addActionMessage("删除成功！！！");
	            } else {
	                this.addActionMessage("删除失败！！！");
	            }
	        } catch (Exception e) {
	            logger.error(e.getMessage(), e);
	            addActionMessage("删除异常！！！");
	        }
	        return RELOAD;
	    }
	    
	    public String checkcustomid(){
	    	boolean flag = commonCustomeService.checkcustomid(customid);
	    	if(flag){
	    		write("OK");
	    	}else{
	    		write("NO");
	    	}
	    	return null;
	    }
	    
	    public void write(String json){
			try {
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out = response.getWriter();
				out.print(json);
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage(), e);
			}
		}
	    
	    public String queryAllCustom(){
	    	List<CommonCustom> list = commonCustomeService.queryAllCustom();
	    	JSONArray jsonArray = JSONArray.fromObject(list);
	    	write(jsonArray.toString());
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

	    //************* getting and setting *****************
	    public CommonCustom getEntity() {
	        return entity;
	    }

	    public void setEntity(CommonCustom entity) {
	        this.entity = entity;
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public Page<CommonCustom> getPage() {
	        return page;
	    }

	    public List<Long> getIds() {
	        return ids;
	    }

	    public void setIds(List<Long> ids) {
	        this.ids = ids;
	    }

	    public void setPage(Page<CommonCustom> page) {
	        this.page = page;
	    }

		public String getCustomid() {
			return customid;
		}

		public void setCustomid(String customid) {
			this.customid = customid;
		}
}