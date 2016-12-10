package com.leolian.nettty5.x;


import com.leolian.nettty5.x.coder.MyEncoder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;

/**
 * 初始化句柄处理器
 * @author Lian
 *
 */
@Sharable
public class DiscardServerInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		//去除尾部的标示符
		ch.pipeline().addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE, Delimiters.lineDelimiter()[0]));
		//把数据转成字符串
		ch.pipeline().addLast(new StringDecoder());
		//添加自定义编码器
		ch.pipeline().addLast(new MyEncoder());
		
		ch.pipeline().addLast(new DiscardServerHandler());
	}

}
