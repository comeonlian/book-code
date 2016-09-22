package com.game.comm.service;


import java.util.List;

import com.game.comm.entity.CommonLang;
import com.game.modules.service.GenericManager;



public interface CommonLangService extends GenericManager<CommonLang, Long>{

	 public boolean delAll(List<Long> ids);

	 public List<CommonLang> queryAllLang();
}
