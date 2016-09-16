package com.leo.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOClient {
	private static int flag = 0;
	//缓冲区大小
	private static int block = 4096;
	//创建接受和发送缓冲区
	private static ByteBuffer sbuf = ByteBuffer.allocate(block);
	private static ByteBuffer rbuf = ByteBuffer.allocate(block);
	
	public static void main(String[] args) throws Exception{
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);
		Selector selector = Selector.open();
		socketChannel.register(selector, SelectionKey.OP_CONNECT);
		socketChannel.connect(new InetSocketAddress("127.0.01", 8001));
		
		Set<SelectionKey> selectionKeys;
		Iterator<SelectionKey> itr;
		SelectionKey key;
		SocketChannel client;
		String rtext,stext;
		int count = 0;
		while(true){
			selector.select();
			selectionKeys = selector.selectedKeys();
			itr = selectionKeys.iterator();
			while(itr.hasNext()){
				key = itr.next();
				if(key.isConnectable()){
					System.out.println("Client connect");
					client = (SocketChannel) key.channel();
					//判断是否在准备连接
					if(client.isConnectionPending()){
						client.finishConnect();
						System.out.println("完成连接");
						sbuf.clear();
						sbuf.put("Hello, Server".getBytes());
						sbuf.flip();
						client.write(sbuf);
					}
					client.register(selector, SelectionKey.OP_READ);
				}else if(key.isReadable()){
					client = (SocketChannel) key.channel();
					rbuf.clear();
					count = client.read(rbuf);
					if(count>0){
						rtext = new String(rbuf.array(), 0, count);
						System.out.println("客户端接受到服务器的数据："+rtext);
						client.register(selector, SelectionKey.OP_WRITE);
					}
				}else if(key.isWritable()){
					client = (SocketChannel) key.channel();
					sbuf.clear();
					stext = "Message from client: "+flag++;
					sbuf.put(stext.getBytes());
					sbuf.flip();
					client.write(sbuf);
					System.out.println("客户端向服务器发送数据："+stext);
					client.register(selector, SelectionKey.OP_READ);
				}
			}
			selectionKeys.clear();
		}
	}
}
