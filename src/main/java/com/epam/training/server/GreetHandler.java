package com.epam.training.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class GreetHandler extends ChannelInboundHandlerAdapter {

	@Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client connected");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String s = (String) msg;
        System.out.println(s);
        ctx.write(s);
        ctx.flush();
    }
}
