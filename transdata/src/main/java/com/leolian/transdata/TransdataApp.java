package com.leolian.transdata;

import com.leolian.transdata.subscribe.SubscribeData;

/**
 * Description: 
 * @author lianliang
 * @date 2017年11月23日 上午11:27:03
 */
public class TransdataApp {
	
	public static void main(String[] args) throws Exception {
		/*if(args.length!=2) {
			System.out.println("参数必须为3个");
			return ;
		}
		String args1 = args[0];
		String args2 = args[1];
		String args3 = args[2];
		
		MysqlToRedisService serv = new MysqlToRedisService(args1, args2, args3);
		serv.transfer();
		
		Transfer tranfer = new Transfer(args1, args2);
		tranfer.execute();*/
		
		SubscribeData obj = new SubscribeData();
		obj.subscribeData();
	}
	
}

