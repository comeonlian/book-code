package com.game.comm.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.game.comm.entity.CommonLang;
import com.game.comm.service.CommonLangService;
import com.game.modules.orm.Page;
import com.game.modules.orm.PropertyFilter;
import com.game.modules.orm.hibernate.HibernateUtils;
import com.game.modules.web.CrudActionSupport;
import com.game.modules.web.struts2.Struts2Utils;
import com.game.services.account.ResourceManager;

@Namespace("/common/commonlang")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "commonlang.action?authId=${authId}", type = "redirect")})
public class CommonlangAction extends CrudActionSupport<CommonLang> {	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		@Autowired
	    private CommonLangService commonLangService;

	    private Long id;

		/**
	     * 多选操作使用
	     */
	    private List<Long> ids;
	    private CommonLang entity;
	    private Page<CommonLang> page = new Page<CommonLang>(15);
	    private Long gameid;

	    @Override
	    public CommonLang getModel() {
	        return entity;
	    }

	    @Override
	    protected void prepareModel() throws Exception {
	        if (null != id) {
	            entity = commonLangService.get(id);
	        } else {
	            entity = new CommonLang();
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
	        page = commonLangService.searchPage(page, filters);
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
	    	commonLangService.save(entity);
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
	            if (commonLangService.delAll(ids)) {
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
	    
	    public String queryAllLang(){
	    	
//	    	JSONArray array = JSONArray.fromObject(allLang);
//	    	write(array.toString());
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
	    public CommonLang getEntity() {
	        return entity;
	    }

	    public void setEntity(CommonLang entity) {
	        this.entity = entity;
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public Page<CommonLang> getPage() {
	        return page;
	    }

	    public List<Long> getIds() {
	        return ids;
	    }

	    public void setIds(List<Long> ids) {
	        this.ids = ids;
	    }

	    public void setPage(Page<CommonLang> page) {
	        this.page = page;
	    }

		public Long getGameid() {
			return gameid;
		}

		public void setGameid(Long gameid) {
			this.gameid = gameid;
		}
}