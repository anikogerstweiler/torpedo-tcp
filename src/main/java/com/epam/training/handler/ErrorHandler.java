package com.epam.training.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorHandler extends ChannelHandlerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(ErrorHandler.class);

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        ctx.writeAndFlush(new Error(cause.getMessage()));
        ctx.disconnect().get();

        LOG.error("Exception cought", cause);
    }

}
