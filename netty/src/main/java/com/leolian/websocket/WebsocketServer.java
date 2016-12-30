package com.leolian.websocket;

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
public class WebsocketServer {
	
	private int port;
	
	public WebsocketServer() {
	}

	public WebsocketServer(int port) {
		this.port = port;
	}
	
	public void run(){
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try{
			ServerBootstrap server = new ServerBootstrap();
			server.group(bossGroup, workGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(new WebsocketServerInitializer())
			.option(ChannelOption.SO_BACKLOG, 128)
			.childOption(ChannelOption.SO_KEEPALIVE, true);
			System.out.println(" * START NETTY SERVER *");
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
		new WebsocketServer(8999).run();
	}
	
}
