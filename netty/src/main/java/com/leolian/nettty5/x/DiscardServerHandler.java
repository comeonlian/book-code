package com.leolian.nettty5.x;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 处理器
 * 
 * @author Lian
 *
 */
public class DiscardServerHandler extends ChannelHandlerAdapter {
	
	/**
	 * 读取来自客户端的数据
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		/*ByteBuf in = (ByteBuf) msg;
		while (in.isReadable()) { 
			System.out.print(in.readInt());
			System.out.flush();
		}
		System.out.println();
		ByteBuf buf = ctx.alloc().buffer().writeBytes("Hello client".getBytes("UTF-8"));*/
		System.out.println(msg);
		String result = "I have receive your message ";
		ctx.writeAndFlush(result);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		ctx.close();
	}

}
