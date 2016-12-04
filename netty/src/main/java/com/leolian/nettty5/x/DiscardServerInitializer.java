package com.leolian.nettty5.x;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.socket.SocketChannel;

/**
 * 初始化句柄处理器
 * @author Lian
 *
 */
@Sharable
public class DiscardServerInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast(new DiscardServerHandler());
	}

}
