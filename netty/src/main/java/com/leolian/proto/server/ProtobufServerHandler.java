package com.leolian.proto.server;

import com.google.protobuf.ByteString;
import com.leolian.proto.RequestParamProtobuf.RequestParam;
import com.leolian.proto.UserProtobuf.User;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ProtobufServerHandler extends ChannelHandlerAdapter {
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		RequestParam requestParam = (RequestParam) msg;
		String cmd = requestParam.getCmd();
		System.out.println("Server get cmd : "+cmd);
		ByteString requestContent = requestParam.getRequestContent();
		User user = User.parseFrom(requestContent);
		ctx.channel().writeAndFlush(user);
	}
	
}
