package com.leolian.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChatRoomHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
	private static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	/**
	 * 新的连接通道
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		for (Channel channel : group) {
			channel.writeAndFlush(new TextWebSocketFrame(ctx.channel().remoteAddress()+" 加入了聊天室."));
		}
		group.add(ctx.channel());
	}
	
	@Override
	protected void messageReceived(ChannelHandlerContext ctx,TextWebSocketFrame msg)
			throws Exception {
		String content = msg.text();
		Channel incoming = ctx.channel();
		for (Channel channel : group) {
			if(channel==incoming){
				incoming.writeAndFlush(new TextWebSocketFrame("服务返回: "+content));
			}else{
				channel.writeAndFlush(new TextWebSocketFrame(incoming.remoteAddress()+": "+content));
			}
		}
	}
	
	/**
	 * 删除连接通道
	 */
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		group.remove(ctx.channel());
		for (Channel channel : group) {
			channel.writeAndFlush(new TextWebSocketFrame(ctx.channel().remoteAddress()+" 离开了聊天室."));
		}
	}
	
}
