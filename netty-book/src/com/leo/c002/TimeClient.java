package com.leo.c002;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try{
			socket = new Socket("127.0.0.1", port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			out.println("QUERY TIME ORDER");
			System.out.println("Send order 2 server succeed.");
			String resp = in.readLine();
			System.out.println("Now is :"+resp);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try{
				out.close();
				in.close();
				socket.close();
				socket = null;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}

}
