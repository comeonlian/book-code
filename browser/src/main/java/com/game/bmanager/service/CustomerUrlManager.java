package com.game.bmanager.service;

import com.game.bmanager.entity.CustomerUrl;
import com.game.modules.service.GenericManager;

public interface CustomerUrlManager extends GenericManager<CustomerUrl, Long> {
	
	//查询客户配置的url
	String queryUrl(String customid);
	
}
