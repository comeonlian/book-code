package com.leolian.nettty5.x;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

import com.leolian.nettty5.x.common.CommonConstant;

public class DiscardClient {
	private String server;
	private int port;
	private Bootstrap client = null;
	private EventLoopGroup workGroup = null;
	private PooledByteBufAllocator pool = new PooledByteBufAllocator();
	
	public DiscardClient(String server,int port) {
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
			.handler(new DiscardClientInitializer());
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String run(int obj) throws Exception{
		try{
			
			ChannelFuture future = client.connect(server, port).sync();
			//future.channel().attr(AttributeKey.valueOf(CommonConstant.ATTRIBUTE_KEY)).set(obj);
			
			//ByteBuf buf = pool.buffer().writeInt(obj);
			future.channel().writeAndFlush(" HEHE ");
			
			future.channel().closeFuture().sync();
			
			return future.channel().attr(AttributeKey.valueOf(CommonConstant.ATTRIBUTE_KEY)).get().toString();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	

}
