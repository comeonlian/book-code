package com.leo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class BootstrapDemo {
	
	public static void main(String[] args) throws Exception {

        NioEventLoopGroup parentBosser = new NioEventLoopGroup();
        NioEventLoopGroup childWorker = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(parentBosser, childWorker);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ChannelPipeline cp = ch.pipeline();
                // 基于长度的解码器
                cp.addLast("framer", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 2, 0, 2));
                cp.addLast("prepender", new LengthFieldPrepender(4));
                //
                cp.addLast("handler", new SimpleChannelInboundHandler<Object>() {

                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

                        System.out.println();
                        ctx.channel().writeAndFlush(msg);

                    }
                });
            }
        });
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        //bootstrap.childAttr()
        try {
            // 绑定并监听端口
            ChannelFuture future = bootstrap.bind(8001).sync();
            // 等待关闭事件
            future.channel().closeFuture().sync();
        } finally {
            // 释放资源
            parentBosser.shutdownGracefully();
            childWorker.shutdownGracefully();
        }
    }
}

