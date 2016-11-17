package com.leolian.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * NIO客户端
 * @author Lian
 *
 */
public class SocketChannelDemo {
	
	/**
	 * 开启客户端
	 * @throws Exception
	 */
	public void startClient()throws Exception{
		//开启客户端监听
		SocketChannel socketChannel = SocketChannel.open();
		//socketChannel.bind(new InetSocketAddress(9000));
		socketChannel.connect(new InetSocketAddress("127.0.0.1", 8999));
		//socketChannel.configureBlocking(false);
		//需要发送的数据
		String msg = "Hello server 这是中文哦.";
		ByteBuffer wbuffer = ByteBuffer.wrap(msg.getBytes());
		//buffer.flip();
		socketChannel.write(wbuffer);
		//接受来自服务的写入
		ByteBuffer rbuffer = ByteBuffer.allocate(1024);
		int size = socketChannel.read(rbuffer);
		while(size>0){
			rbuffer.flip();
			System.out.println(new String(rbuffer.array()));
			rbuffer.clear();
			size = socketChannel.read(rbuffer);
		}
		
		//wbuffer.clear();
		//rbuffer.clear();
		//socketChannel.close();
		Thread.sleep(30000);
	}
	
	public static void main(String[] args) throws Exception {
		new SocketChannelDemo().startClient();
	}
	
}
