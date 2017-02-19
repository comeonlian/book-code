package com.leolian.thrift.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TMemoryBuffer;

import com.leolian.media.Media;
import com.leolian.thrift.demo.ThriftRequest;

public class ThriftServerHandler extends ChannelHandlerAdapter {
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		//ByteBuf转换成一个ThriftRequest
		ByteBuf bytebuf = (ByteBuf) msg;
		
		TMemoryBuffer trans = new TMemoryBuffer(1024);
		if(bytebuf.hasArray()){
			trans.write(bytebuf.array());
		}else{
			byte[] dst = new byte[bytebuf.readableBytes()];
			bytebuf.readBytes(dst);
			trans.write(dst);
		}
		TProtocol iprot = new TBinaryProtocol(trans);
		ThriftRequest thriftRequest = new ThriftRequest();
		thriftRequest.read(iprot);
		
		Object response = Media.execute(thriftRequest);
		ctx.channel().writeAndFlush(response);
	}
	
}
