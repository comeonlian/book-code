package com.leo.c003;


/**
 * 客户端
 * @author lianliang
 *
 */
public class TimeClient {

	public static void main(String[] args) {
		int port = 8080;
		if(null!=args&&args.length>0){
			try{
				port = Integer.valueOf(args[0]);
			}catch(Exception e){
				
			}
		}
		new Thread(new TimeClientHandle("127.0.0.1", port), "TimeClient-001").start();
	}

}
