package com.leolian.http.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.AttributeKey;

import java.net.URI;
import java.nio.charset.Charset;

import com.leolian.http.entity.RequestParameter;
import com.leolian.nettty5.x.common.CommonConstant;
import com.leolian.utils.JsonUtils;

public class HttpClient {
	private String server;
	private int port;
	private Bootstrap client = null;
	private EventLoopGroup workGroup = null;
	
	public HttpClient(String server,int port) {
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
			.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new HttpRequestEncoder());
					ch.pipeline().addLast(new HttpObjectAggregator(65536));
					ch.pipeline().addLast(new HttpResponseDecoder());
					ch.pipeline().addLast(new HttpClientHandler());
				}
			});
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Object run(RequestParameter parameter) throws Exception{
		try{
			ChannelFuture future = client.connect(server, port).sync();
			URI uri = new URI("http://127.0.0.1:8999");
			
			ByteBuf content = Unpooled.wrappedBuffer(JsonUtils.beanToJson(parameter).getBytes("UTF-8"));
			DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, uri.toASCIIString(), content);
			request.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
			request.headers().set(HttpHeaderNames.HOST, "127.0.0.1");
			request.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
			request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
			
			future.channel().writeAndFlush(request);
			future.channel().closeFuture().sync();
			return future.channel().attr(AttributeKey.valueOf(CommonConstant.ATTRIBUTE_KEY)).get();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	

}
