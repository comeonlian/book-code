package com.leolian.proto.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.util.AttributeKey;

import com.leolian.nettty5.x.common.CommonConstant;
import com.leolian.proto.ResponseProtobuf.Response;

public class ProtobufClient {
	private String server;
	private int port;
	private Bootstrap client = null;
	private EventLoopGroup workGroup = null;
	
	public ProtobufClient(String server,int port) {
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
					ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
					ch.pipeline().addLast(new ProtobufDecoder(Response.getDefaultInstance()));
					ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
					ch.pipeline().addLast(new ProtobufEncoder());
					ch.pipeline().addLast(new ProtobufClientHandler());
				}
			});
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Response run(Object obj) throws Exception{
		try{
			ChannelFuture future = client.connect(server, port).sync();
			future.channel().writeAndFlush(obj);
			future.channel().closeFuture().sync();
			return (Response) future.channel().attr(AttributeKey.valueOf(CommonConstant.ATTRIBUTE_KEY)).get();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	

}
