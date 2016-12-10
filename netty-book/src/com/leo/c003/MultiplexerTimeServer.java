package com.leo.c003;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * 多路复用器
 * @author lianliang
 */
public class MultiplexerTimeServer implements Runnable {
	
	private Selector selector;
	private ServerSocketChannel serverSocketChannel;
	private volatile boolean stop = false;
	
	
	public MultiplexerTimeServer(int port) {
		try{
			selector = Selector.open();
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.bind(new InetSocketAddress(port), 1024);
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("The time server is start in port: "+port);
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void stop(){
		this.stop = true;
	}
	
	@Override
	public void run() {
		while(!stop){
			try{
				selector.select(1000);
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> it = selectedKeys.iterator();
				SelectionKey key = null;
				while(it.hasNext()){
					key = it.next();
					it.remove();
					try{
						handleInput(key);
					}catch(Exception e){
						if(null!=key){
							key.cancel();
							if(null!=key.channel()){
								key.channel().close();
							}
						}
					}
				}
			}catch(Throwable e){
				e.printStackTrace();
			}
		}
		if(null!=selector){
			try{
				selector.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	private void handleInput(SelectionKey key) throws Exception{
		if(key.isValid()){
			//处理新的连接
			if(key.isAcceptable()){
				ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
				SocketChannel sc = ssc.accept();
				sc.configureBlocking(false);
				sc.register(selector, SelectionKey.OP_READ);
			}
			//读取数据
			if(key.isReadable()){
				SocketChannel sc = (SocketChannel) key.channel();
				ByteBuffer buf = ByteBuffer.allocate(1024);
				int readBytes = sc.read(buf);
				if(readBytes>0){
					buf.flip();
					byte[] bytes = new byte[buf.remaining()];
					buf.get(bytes);
					String body = new String(bytes, "UTF-8");
					System.out.println("The time server receive order: "+ body);
					String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? 
							new Date(System.currentTimeMillis()).toString():"BAD ORDER";
					doWrite(sc, currentTime);
				} else if(readBytes<0){
					//关闭链路
					key.cancel();
					sc.close();
				} else 
					;
			}
			
		}
	}

	private void doWrite(SocketChannel channel, String response) throws Exception{
		if(null!=response && response.trim().length()>0){
			byte[] bytes = response.getBytes("UTF-8");
			ByteBuffer buf = ByteBuffer.allocate(bytes.length);
			buf.put(bytes);
			buf.flip();
			channel.write(buf);
		}
	}
	
	
}
