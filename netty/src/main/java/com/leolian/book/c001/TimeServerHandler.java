package com.leolian.book.c001;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class TimeServerHandler implements Runnable {
	
	private Socket socket = null;
	
	public TimeServerHandler(Socket socket) {
		this.socket = socket;
		System.out.println("Init thread ....");
	}
	
	@Override
	public void run() {
		System.out.println("Execute run method ....");
		BufferedReader in = null;
		PrintWriter out = null;
		try{
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			String currentTime = null;
			String body = null;
			body = in.readLine();
			System.out.println("The time server receive order:"+body);
			currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString():"BAD ORDER";
			out.println(currentTime);
		}catch(Exception e){
			if(in!=null){
				try{
					in.close();
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
			if(null!=out){
				try{
					out.close();
				}catch(Exception e2){
					e2.printStackTrace();
				}
			}
			if(null!=socket){
				try{
					socket.close();
				}catch(Exception e3){
					e3.printStackTrace();
				}
			}
			socket = null;
		}
	}

}
