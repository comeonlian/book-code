package com.leolian.nettty5.x.coder;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.Delimiters;

public class MyEncoder extends ByteToMessageCodec<String> {

	@Override
	protected void decode(ChannelHandlerContext arg0, ByteBuf arg1,
			List<Object> arg2) throws Exception {
		
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out)
			throws Exception {
		out.writeBytes(msg.getBytes("UTF-8"));
		out.writeBytes(Delimiters.lineDelimiter()[0]);
	}

}
