package com.leolian.thrift.server;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

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
@Component
public class ThriftServer implements ApplicationListener<ContextRefreshedEvent>,Ordered{
	
	private int port;
	
	public ThriftServer() {
	}

	public ThriftServer(int port) {
		this.port = port;
	}
	
	public void run(){
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try{
			ServerBootstrap server = new ServerBootstrap();
			server.group(bossGroup, workGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(new ThriftServerInitializer())
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
		new ThriftServer(8999).run();
	}

	/* ************************** spring 容器启动  ********************************* */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		new ThriftServer(8999).run();
	}
	
	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}

}
