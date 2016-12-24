package com.leolian.http.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

import java.nio.charset.Charset;

import com.leolian.http.entity.RequestParameter;
import com.leolian.media.Media;
import com.leolian.utils.JsonUtils;

public class HttpServerHandler extends ChannelHandlerAdapter {
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		FullHttpRequest requst = (FullHttpRequest) msg;
		ByteBuf buf = requst.content();
		String content = buf.toString(Charset.forName("UTF-8"));
		RequestParameter requestParameter = JsonUtils.jsonToBean(content, RequestParameter.class);
		
		Object obj = Media.execute(requestParameter);
		String resultJson = JsonUtils.beanToJson(obj);
		
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(resultJson.getBytes(Charset.forName("UTF-8"))));
		response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
		response.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
		response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
		
		ctx.channel().writeAndFlush(response);
	}
	
}
