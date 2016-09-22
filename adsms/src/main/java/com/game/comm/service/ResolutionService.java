package com.game.comm.service;
import java.util.List;

import com.game.comm.entity.Resolution;
import com.game.modules.orm.Page;
import com.game.modules.service.GenericManager;

public interface ResolutionService extends GenericManager<Resolution, Long> {
	
	public boolean delAll(List<Long> ids);
	
	/*
	 * 分页获取分辨率列表	
	 */
	public List<Resolution> GetResolutionList(String isshow, int pageno, Page<Resolution> page);
	/*
	 * 获取所有分辨率列表	
	 */
	public List<Resolution> GetResolutionList(String isshow) ;
	/*
	 * 分页获取具体分辨率	
	 */
	public List<Resolution> GetResolution(String uiid);
	/*
	 * 添加分辨率	
	 */
	public long addResolution(Resolution addResolution,String username) ;
	/*
	 * 修改分辨率
	 */
	public long updateResolution(Resolution Resolution,String username) ;
	/*
	 * 删除分辨率	
	 */
	public int deleteResolution(String idlist,String username);
	/*
	 * 判断分辨率是否重复
	 */
	public List<Resolution> GetResolutionByResol(String resolution,String Uid);
	/*
	 * 根据传入的分辨率获得最接近的数据库分辨率 如果没有匹配的数据 默认返回480x800
	 */
	public String getTheClosedResolution(String fromresolution);
}
