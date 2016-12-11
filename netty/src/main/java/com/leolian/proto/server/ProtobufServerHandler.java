package com.leolian.proto.server;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import com.leolian.media.Media;
import com.leolian.proto.RequestParamProtobuf.RequestParam;

public class ProtobufServerHandler extends ChannelHandlerAdapter {
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		RequestParam requestParam = (RequestParam) msg;
		
		Object obj = Media.execute(requestParam);
		
		ctx.channel().writeAndFlush(obj);
	}
	
}
