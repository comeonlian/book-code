package com.leolian.thrift.coder;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TMemoryBuffer;

import com.leolian.thrift.demo.ThriftRequest;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.MessageToByteEncoder;

public class ThriftClientEncoder extends MessageToByteEncoder<Object> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out)
			throws Exception {
		ThriftRequest request = (ThriftRequest) msg;
		TMemoryBuffer buffer2 = new TMemoryBuffer(1024);
		TProtocol prot2 = new TBinaryProtocol(buffer2);
		request.write(prot2);
		
		//System.out.println("Client : "+request.getCommond());
		
		out.writeBytes(buffer2.getArray());
		out.writeBytes(Delimiters.lineDelimiter()[0]);
		buffer2.close();
	}

}
