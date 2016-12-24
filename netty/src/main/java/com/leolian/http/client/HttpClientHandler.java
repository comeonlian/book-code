package com.leolian.http.client;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.util.AttributeKey;

import com.leolian.http.entity.Person;
import com.leolian.nettty5.x.common.CommonConstant;
import com.leolian.utils.JsonUtils;

public class HttpClientHandler extends ChannelHandlerAdapter {
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		if(msg instanceof HttpResponse){
			HttpResponse response = (HttpResponse) msg;
			System.out.println(response.headers().get(HttpHeaderNames.CONTENT_TYPE));
		}
		if(msg instanceof HttpContent){
			HttpContent response = (HttpContent) msg;
			ByteBuf buf = response.content();
			String responseJson = buf.toString(Charset.forName("UTF-8"));
			Person person = JsonUtils.jsonToBean(responseJson, Person.class);
			ctx.channel().attr(AttributeKey.valueOf(CommonConstant.ATTRIBUTE_KEY)).set(person);
			ctx.channel().close();
		}
	}
}
