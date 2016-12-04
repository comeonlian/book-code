package com.leolian.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;

/**
 * AIO编程客户端
 * @author Lian
 *
 */
public class AioClientDemo {
	
	public static void startClient(int port) throws Exception{
		AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
		Future<?> future = client.connect(new InetSocketAddress("localhost", port));
		//异步的IO，等待连接成功再往下执行
		future.get();
		ByteBuffer buf = ByteBuffer.wrap("Hi Server,I'm client ".getBytes("UTF-8"));
		client.write(buf);
		buf.clear();
		client.close();
	}
	
	public static void main(String[] args) throws Exception {
		startClient(8999);
		
	}

}
