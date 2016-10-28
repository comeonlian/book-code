package com.t35.pojo.query;

import java.util.List;

public class Page {
	
	private Integer pageNo;			//当前页
	private Integer pageSize ;	//每页大小
	private Integer totalCount;		//总记录数
	@SuppressWarnings("unused")
	private Integer totalPage;		//总页数
	private List<Object> list;		//列表显示的数据
	
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		return (totalCount % pageSize) == 0 ? totalCount/pageSize : totalCount/pageSize + 1 ;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<Object> getList() {
		return list;
	}
	public void setList(List<Object> list) {
		this.list = list;
	}
	
}
