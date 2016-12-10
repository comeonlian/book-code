package com.leolian.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * 引入多路复用器
 * 服务端
 * @author Lian
 *
 */
public class SelectorServerSocketChannelDemo {
	
	public static void startServer(int port)throws Exception{
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.bind(new InetSocketAddress(port));
		serverSocketChannel.configureBlocking(false);
		Selector selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("SERVER IS  START .");
		SocketChannel socketChannel = null;
		int size = 0;
		CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
		while(true){
			if(selector.select()>0){
				for(SelectionKey key: selector.selectedKeys()){
					if(key.isAcceptable()){
						socketChannel = ((ServerSocketChannel)key.channel()).accept();
						ByteBuffer buf = ByteBuffer.allocate(1024);
						size = socketChannel.read(buf);
						while(size>0){
							buf.flip();
							System.out.println(decoder.decode(buf).toString());
							size = socketChannel.read(buf);
						}
						buf.clear();
						
						ByteBuffer response = ByteBuffer.wrap("您好,收到请求".getBytes("UTF-8"));
						socketChannel.write(response);
						socketChannel.close();
						response.clear();
						selector.selectedKeys().remove(key);
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		startServer(8999);
		
	}

}
