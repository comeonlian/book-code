package com.leolian.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class SelectorSocketChannel {
	
	public static void startClient(int port) throws Exception{
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.connect(new InetSocketAddress("localhost", port));
		socketChannel.configureBlocking(false);
		Selector selector = Selector.open();
		socketChannel.register(selector, SelectionKey.OP_READ);
		new ClientThread(selector).start();
		
		ByteBuffer buf = ByteBuffer.wrap("Hello 我是客户端".getBytes("UTF-8"));
		socketChannel.write(buf);
		buf.clear();
	}
	
	public static void main(String[] args) throws Exception{
		startClient(8999);
	}

}
