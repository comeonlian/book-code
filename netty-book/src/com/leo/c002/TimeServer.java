package com.leo.c002;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author lianliang
 */
public class TimeServer {

	public static void main(String[] args) throws Exception{
		int port = 8080;
		if(args!=null && args.length>0){
			try{
				port = Integer.valueOf(args[0]);
			}catch(NumberFormatException e){
				
			}
		}
		ServerSocket server = null;
		try{
			server = new ServerSocket(port);
			System.out.println("The time server is start in port: "+port);
			Socket socket = null;
			TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50, 10000);
			
			while(true){
				socket = server.accept();
				singleExecutor.execute(new TimeServerHandler(socket));
			}
		}finally{
			if(null!=server){
				System.out.println("The time server close");
				server.close();
				server = null;
			}
		}
	}

}
