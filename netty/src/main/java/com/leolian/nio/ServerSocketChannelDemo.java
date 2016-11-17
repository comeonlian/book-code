package com.leolian.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * NIO的服务端
 * 
 * @author Lian
 *
 */
public class ServerSocketChannelDemo {

	/**
	 * 开启服务端
	 * 
	 * @throws Exception
	 */
	public void startServer() throws Exception {
		System.out.println("*************    SERVER START   *************");
		// 开启服务端端口监听
		ServerSocketChannel serverScoketChannel = ServerSocketChannel.open();
		serverScoketChannel.socket().bind(new InetSocketAddress(8999));
		serverScoketChannel.configureBlocking(false);
		// Buffer
		int size = 0;
		// 持续监听
		while (true) {
			SocketChannel socketChannel = serverScoketChannel.accept();
			if (null != socketChannel) {
				ByteBuffer rbuffer = ByteBuffer.allocate(1024);
				System.out.println("Client Connect host:" + socketChannel.socket().getLocalAddress() + ", port:"
						+ socketChannel.socket().getPort());
				// 读入客户端的数据
				size = socketChannel.read(rbuffer);
				while (size > 0) {
					rbuffer.flip();
					System.out.println(new String(rbuffer.array()).trim());
					rbuffer.flip();
					rbuffer.clear();
					size = socketChannel.read(rbuffer);
				}
				// System.out.println();
				// 写出到客户端
				String msg = "Hey client, I get you.";
				ByteBuffer wbuffer = ByteBuffer.wrap(msg.getBytes());
				socketChannel.write(wbuffer);

				rbuffer.clear();
				wbuffer.clear();
				socketChannel.close();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		//new ServerSocketChannelDemo().startServer();
		
		new ServerSocketChannelDemo().startServerTutorail();
	}

	public void startServerTutorail() throws Exception {

		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().bind(new InetSocketAddress(8999));
		serverSocketChannel.configureBlocking(false);

		while (true) {
			SocketChannel socketChannel = serverSocketChannel.accept();
			if (socketChannel != null) {
				ByteBuffer buf = ByteBuffer.allocate(64);
				int size = socketChannel.read(buf);
				while (size > 0) {
					buf.flip();
					Charset charset = Charset.forName("UTF-8");
					System.out.println(charset.newDecoder().decode(buf));
					//buf.clear();
					size = socketChannel.read(buf);
				}
				buf.clear();
				ByteBuffer response = ByteBuffer.wrap("hello 小美，我已经接受到你的邀请!".getBytes("UTF-8"));
				socketChannel.write(response);
				response.clear();
				// socketChannel.close();
			}

		}
	}

}
