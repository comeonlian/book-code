package com.leo.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class DateServer {

	public static void main(String[] args) throws Exception{
		int[] ports = {8000,8001,8002,8003};
		Selector selector = Selector.open();
		for(int i=0; i<ports.length; i++){
			ServerSocketChannel ssc = ServerSocketChannel.open();
			ssc.configureBlocking(false);
			ServerSocket socket = ssc.socket();
			InetSocketAddress address = new InetSocketAddress(ports[i]);
			socket.bind(address);
			ssc.register(selector,SelectionKey.OP_ACCEPT);
			System.out.println("服务器运营，在"+ports[i]+"端口监听。");
		}
		int keyAdd = 0;
		while((keyAdd=selector.select())>0){
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> itr = keys.iterator();
			while(itr.hasNext()){
				SelectionKey selectionKey = itr.next();
				if(selectionKey.isAcceptable()){
					ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
					SocketChannel client = ssc.accept();
					client.configureBlocking(false);
					ByteBuffer buf = ByteBuffer.allocateDirect(1024);
					buf.put(("Current Time : "+new Date()).getBytes());
					buf.flip();
					client.write(buf);
					client.close();
				}
			}
			keys.clear();
		}
	}

}
