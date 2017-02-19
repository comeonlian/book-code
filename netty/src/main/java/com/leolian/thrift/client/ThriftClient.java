package com.leolian.thrift.client;

import java.lang.reflect.Method;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TMemoryBuffer;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.util.AttributeKey;

import com.leolian.nettty5.x.common.CommonConstant;
import com.leolian.thrift.coder.ThriftClientEncoder;
import com.leolian.thrift.demo.Content;

public class ThriftClient {
	private String server;
	private int port;
	private Bootstrap client = null;
	private EventLoopGroup workGroup = null;
	
	public ThriftClient(String server,int port) {
		this.server = server;
		this.port = port;
		init();
	}
	
	private void init(){
		workGroup = new NioEventLoopGroup();
		try{
			client = new Bootstrap();
			client.group(workGroup)
			.channel(NioSocketChannel.class)
			.option(ChannelOption.SO_KEEPALIVE, true)
			.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new ThriftClientEncoder());
					ch.pipeline().addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE, Delimiters.lineDelimiter()[0]));
					ch.pipeline().addLast(new ThriftClientHandler());
				}
			});
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Object run(Object obj, Class clazz) throws Exception{
		try{
			ChannelFuture future = client.connect(server, port).sync();
			future.channel().writeAndFlush(obj);
			future.channel().closeFuture().sync();
			
			ByteBuf result = (ByteBuf) future.channel().attr(AttributeKey.valueOf(CommonConstant.ATTRIBUTE_KEY)).get();
			byte[] dst = new byte[result.readableBytes()];
			result.readBytes(dst);
			TMemoryBuffer buffer3 = new TMemoryBuffer(1024);
			buffer3.write(dst);
			TProtocol prot3 = new TBinaryProtocol(buffer3);
//			Content response = new Content();
//			response.read(prot3);
			Object clazzObj = clazz.newInstance();
			Method read = clazz.getMethod("read", TProtocol.class);
			read.invoke(clazzObj, prot3);
			return clazzObj;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	

}
