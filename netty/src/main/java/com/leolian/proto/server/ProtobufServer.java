package com.leolian.proto.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Netty服务端
 * @author Lian
 */
public class ProtobufServer {
	
	private int port;
	
	public ProtobufServer(int port) {
		this.port = port;
	}
	
	public void run(){
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try{
			ServerBootstrap server = new ServerBootstrap();
			server.group(bossGroup, workGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(new ProtobufServerInitializer())
			.option(ChannelOption.SO_BACKLOG, 128)
			.childOption(ChannelOption.SO_KEEPALIVE, true);
			System.out.println(" * START SERVER *");
			ChannelFuture sync = server.bind(port).sync();
			sync.channel().closeFuture().sync();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			workGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) {
		new ProtobufServer(8999).run();
	}

}
