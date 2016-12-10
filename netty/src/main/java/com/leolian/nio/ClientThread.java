package com.leolian.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class ClientThread extends Thread {
	
	private Selector selector;
	
	public ClientThread(Selector selector) {
		this.selector = selector;
	}
	
	@Override
	public void run() {
		try {
			SocketChannel socketChannel = null;
			int size = 0;
			CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
			while(selector.select()>0){
				for(SelectionKey key : selector.keys()){
					socketChannel = (SocketChannel) key.channel();
					ByteBuffer buf = ByteBuffer.allocate(1024);
					size = socketChannel.read(buf);
					while(size>0){
						buf.flip();
						System.out.println(decoder.decode(buf).toString());
						size = socketChannel.read(buf);
					}
					buf.clear();
					selector.selectedKeys().remove(key);
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
