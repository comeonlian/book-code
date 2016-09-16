package com.leo.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
	private int flag = 0;
	//缓冲区大小
	private int block = 4096;
	//接受数据缓冲区和发送数据缓冲区
	private ByteBuffer rbuf = ByteBuffer.allocate(block);
	private ByteBuffer sbuf = ByteBuffer.allocate(block);
	//选择器
	private Selector selector;
	//启动服务，监听端口
	public NIOServer(int port) throws Exception{
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		ServerSocket serverSocket = serverSocketChannel.socket();
		serverSocket.bind(new InetSocketAddress(port));
		selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("服务器启动，监听："+port+"端口");
	}
	/*
	 * 监听事件
	 * 应用Select机制轮循是否有用户感兴趣的新的网络事件发生，
	 * 当没有新的网络事件发生时，此方法会阻塞，直到有新的网络事件发生为止。
	 */
	private void listen()throws Exception{
		while(true){
			selector.select();
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			Iterator<SelectionKey> itr = selectedKeys.iterator();
			System.out.println("SelectionKeys size: "+selectedKeys.size());
			while(itr.hasNext()){
				SelectionKey key = itr.next();
				itr.remove();
				handleKey(key);
			}
		}
	}
	/*
	 * 分类型的处理请求
	 */
	public void handleKey(SelectionKey key)throws Exception{
		ServerSocketChannel server = null;
		SocketChannel client = null;
		String rtext,stext;
		int count = 0;
		//是否准备连接新的请求
		if(key.isAcceptable()){
			System.out.println("-----isAcceptable-----");
			server = (ServerSocketChannel) key.channel();
			client = server.accept();
			client.configureBlocking(false);
			client.register(selector, SelectionKey.OP_READ);
		}else if(key.isReadable()){ //是否准备从连接中读取
			System.out.println("-----isReadable-----");
			client = (SocketChannel) key.channel();
			rbuf.clear();
			//读取客户端发过来的数据到缓冲中
			count = client.read(rbuf);
			if(count>0){
				rtext = new String(rbuf.array(), 0, count);
				System.out.println("服务器接收到客户端发送的数据："+rtext);
				client.register(selector, SelectionKey.OP_WRITE);
			}
		}else if(key.isWritable()){ //是否准备向连接中写入
			System.out.println("-----isWritable-----");
			sbuf.clear();
			client = (SocketChannel) key.channel();
			stext = "Message from server: "+flag++;
			sbuf.put(stext.getBytes());
			sbuf.flip();
			client.write(sbuf);
			System.out.println("服务器向客户端发送数据："+stext);
			client.register(selector, SelectionKey.OP_READ);
		}
	}
	
	public static void main(String[] args) throws Exception{
		int port = 8001;
		NIOServer server = new NIOServer(port);
		server.listen();
	}
	
}
