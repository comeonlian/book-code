package com.leolian.nettty5.x;

import com.leolian.nettty5.x.common.CommonConstant;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

/**
 * 客户端处理器
 * @author Lian
 *
 */
public class DiscardClientHandler extends ChannelHandlerAdapter {
	
	/**
	 * 连接被激活的时候向服务端发送数据
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//ByteBuf buf = ctx.alloc().buffer().writeBytes("Hello netty server".getBytes("UTF-8"));
		//String param = ctx.channel().attr(AttributeKey.valueOf(CommonConstant.ATTRIBUTE_KEY)).get().toString();
		//ByteBuf buf = ctx.alloc().buffer().writeBytes(param.getBytes("UTF-8"));
		//ctx.writeAndFlush(buf);
		super.channelActive(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		/*ByteBuf in = (ByteBuf) msg;
		StringBuilder sb = new StringBuilder();
		while (in.isReadable()) { 
			sb.append((char) in.readByte());
			//System.out.print();
			//System.out.flush();
		}*/
		ctx.channel().attr(AttributeKey.valueOf(CommonConstant.ATTRIBUTE_KEY)).set(msg.toString());
		//in.clear();
		ctx.close();
	}
	
	

}
