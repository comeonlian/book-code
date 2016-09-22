package com.game.comm.service;


import java.util.List;

import com.game.comm.entity.CommonCustom;
import com.game.modules.service.GenericManager;



public interface CommonCustomeService extends GenericManager<CommonCustom, Long>{

	 public boolean delAll(List<Long> ids);

	 public List<CommonCustom> queryAllCustom();
	 
	 public boolean checkcustomid(String customid);

	 public int editdefaultcustom(long id);
	 
	 public void queryCustomid();
}
