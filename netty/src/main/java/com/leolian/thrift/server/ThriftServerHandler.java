package com.leolian.thrift.server;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import com.leolian.media.Media;

public class ThriftServerHandler extends ChannelHandlerAdapter {
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		
		
		
		Object response = Media.execute(msg);
		ctx.channel().writeAndFlush(response);
	}
	
}
