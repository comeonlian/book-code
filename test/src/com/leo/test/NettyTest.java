package com.leo.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class NettyTest {
	
	
	public static void main(String[] args) {
		ByteBuf buf = Unpooled.buffer(1024 * 10);
		byte[] bytes = new byte[]{(byte) 0xb1, (byte) 0xe3};
		buf.writeBytes(bytes);
		System.out.println(buf.readShort());
	}
	
	
}
