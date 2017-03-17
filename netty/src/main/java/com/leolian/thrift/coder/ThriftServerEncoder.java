package com.leolian.thrift.coder;

import java.lang.reflect.Method;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TMemoryBuffer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.MessageToByteEncoder;

public class ThriftServerEncoder extends MessageToByteEncoder<Object> {

	@Override
	protected void encode(ChannelHandlerContext arg0, Object obj, ByteBuf out)
			throws Exception {
		Method read = obj.getClass().getMethod("write", TProtocol.class);
		TMemoryBuffer buffer = new TMemoryBuffer(1024);
		TProtocol prot = new TBinaryProtocol(buffer);
		read.invoke(obj, prot);
		out.writeBytes(buffer.getArray());
		out.writeBytes(Delimiters.lineDelimiter()[0]);
	}
	

}
