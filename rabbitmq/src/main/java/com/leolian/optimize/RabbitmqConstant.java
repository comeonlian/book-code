package com.leolian.optimize;

public interface RabbitmqConstant {
	//IP
	final String HOST1 = "192.168.1.33";
	final String HOST2 = "192.168.1.34";
	final String HOST3 = "192.168.1.35";
	
	//用户名密码
	final String USERNAME = "admin";
	final String PASSWORD = "admin";
	
	//转发器的名称
	//转发器类型  —— 多个数据队列共用一个转发器
	final String EXCHANGE_NAME = "klonetech.gdcptest.ptExchange";
	final String EXCHANGE_TYPE = "topic";
	
	// 持久化标志
	final boolean DURATION = true;
	
	final int PREFETCH_COUNT = 1;
	
	//生产者消息路由键
	//命名需要根据实际的消息类型
	final String COMMON_KEY = "klonetech.gdcptest.commonData";
	final String GB_KEY = "klonetech.gdcptest.gbData";
	final String BM_KEY = "klonetech.gdcptest.bmData";
	final String RB_KEY = "klonetech.gdcptest.rbData";
	final String SF_KEY = "klonetech.gdcptest.sfData";
	
	
	
	//队列名称
	//多个平台，存在多个队列
	final String COMMON_QUEUE = "klonetech.gdcptest.commonQueue";
	final String GB_QUEUE = "klonetech.gdcptest.gbQueue";
	final String BM_QUEUE = "klonetech.gdcptest.bmQueue";
	final String RB_QUEUE = "klonetech.gdcptest.rbQueue";
	final String SF_QUEUE = "klonetech.gdcptest.sfQueue";
	
	
	
	//较通用的匹配字符
	final String MATCH_CHAR = "klone.gdcp.*";
	
}
