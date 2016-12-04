package com.leolian.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.concurrent.CountDownLatch;

/**
 * AIO编程服务端
 * @author Lian
 *
 */
public class AioServerDemo {
	private static CountDownLatch latch = null;
	
	
	public static void startServer(int port)throws Exception{
		final AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();
		server.bind(new InetSocketAddress(port));
		
		latch = new CountDownLatch(1); //保持线程启动
		
		server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
			@Override
			public void completed(AsynchronousSocketChannel result,
					Void attachment) {
				try {
					server.accept(null, this);
					doOperation(result);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			@Override
			public void failed(Throwable exc, Void attachment) {
				latch.countDown();
			}
		});
		
		latch.await();
	}
	private static void doOperation(AsynchronousSocketChannel socketChannel) throws Exception{
		ByteBuffer buf = ByteBuffer.allocate(1024);
		int size = socketChannel.read(buf).get();
		CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
		while(size>0){
			buf.flip();
			System.out.println(decoder.decode(buf).toString());
			size = socketChannel.read(buf).get();
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		startServer(8999);
	}

}
