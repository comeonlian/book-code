package com.leolian.nettty5.x;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class DiscardClientInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast(new DiscardClientHandler());
	}

}
