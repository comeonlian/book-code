package com.leo.c003;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * @author lianliang
 */
public class TimeClientHandle implements Runnable{
	private String host;
	private int port;
	private Selector selector;
	private SocketChannel socketChannel;
	private volatile boolean stop = false;
	
	public TimeClientHandle(String host,int port) {
		this.host = null==host? "127.0.0.1":host;
		this.port = port;
		try{
			selector = Selector.open();
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private void doConnect() throws Exception{
		if(socketChannel.connect(new InetSocketAddress(host, port))){
			socketChannel.register(selector, SelectionKey.OP_READ);
		}else{
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
		}
	}
	
	@Override
	public void run() {
		try{
			doConnect();
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
		while(!stop){
			try{
				selector.select(1000);
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> it = keys.iterator();
				SelectionKey key = null;
				while(it.hasNext()){
					key = it.next();
					it.remove();
					try{
						handleInput(key);
					}catch(Exception e){
						if(null!=key){
							key.cancel();
							if(null!=key.channel())
								key.channel().close();
						}
					}
				}
			}catch(Exception e){
				e.printStackTrace();
				System.exit(1);
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
			SocketChannel sc = (SocketChannel) key.channel();
			if(key.isConnectable()){
				if(sc.finishConnect()){
					sc.register(selector, SelectionKey.OP_READ);
					doWrite(sc);
				}else
					System.exit(1);
			}
			if(key.isReadable()){
				ByteBuffer buf = ByteBuffer.allocate(1024);
				int readBytes = sc.read(buf);
				if(readBytes>0){
					buf.flip();
					byte[] bytes = new byte[buf.remaining()];
					buf.get(bytes);
					String body = new String(bytes, "UTF-8");
					System.out.println("Now is : "+body);
					this.stop = true;
				}else if(readBytes<0){
					key.cancel();
					sc.close();
				}else
					;
			}
		}
	}

	private void doWrite(SocketChannel sc) throws Exception{
		byte[] req = "QUERY TIME ORDER".getBytes();
		ByteBuffer buf = ByteBuffer.wrap(req);
		sc.write(buf);
		if(!buf.hasRemaining()){
			System.out.println("Send order 2 server succeed.");
		}
	}
	
	
}
